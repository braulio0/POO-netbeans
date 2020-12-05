/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import java.awt.Image;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import static java.lang.System.exit;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.JOptionPane;
import modelo.Empleado;
import modelo.RegistroAsistencia;
/**
 *
 * @author elias
 */
public class Inicio extends javax.swing.JFrame {
    public static final int MYSQL_DUPLICATE_PK   = 1062;
    public static final int MYSQL_FMTO_ERRONEO   = 1292;
    public static final int MYSQL_FOREIGN_KEY    = 1452;
    //Se establece la conexión con la base de datos
    controlador.Conexion con = new controlador.Conexion();
    Connection cn  = con.conexion();
    /**
     * Creates new form Inicio
     */
    public Inicio() {
        initComponents();
        setLocationRelativeTo(null);
        setTitle("Inicio");
        ImageIcon imagen = new ImageIcon( "src/imagen/fondo.jpg");
        Icon icono = new ImageIcon(imagen.getImage().getScaledInstance(jLabel1.getWidth(),jLabel1.getHeight(),Image.SCALE_DEFAULT));
        jLabel1.setIcon(icono);
        ImageIcon imagen2 = new ImageIcon( "src/imagen/agregarUsuario.png");
        Icon icono2 = new ImageIcon(imagen2.getImage().getScaledInstance(jLabel2.getWidth(),jLabel2.getHeight(),Image.SCALE_DEFAULT));
        jLabel2.setIcon(icono2);
        ImageIcon imagen3 = new ImageIcon( "src/imagen/nuevoHorario.png");
        Icon icono3 = new ImageIcon(imagen3.getImage().getScaledInstance(jLabel3.getWidth(),jLabel3.getHeight(),Image.SCALE_DEFAULT));
        jLabel3.setIcon(icono3);
        ImageIcon imagen4 = new ImageIcon( "src/imagen/diasDescanso.png");
        Icon icono4 = new ImageIcon(imagen4.getImage().getScaledInstance(jLabel4.getWidth(),jLabel4.getHeight(),Image.SCALE_DEFAULT));
        jLabel4.setIcon(icono4);
        ImageIcon imagen5 = new ImageIcon( "src/imagen/horarioEmpleado.png");
        Icon icono5 = new ImageIcon(imagen5.getImage().getScaledInstance(jLabel7.getWidth(),jLabel7.getHeight(),Image.SCALE_DEFAULT));
        jLabel7.setIcon(icono5);
        ImageIcon imagen6 = new ImageIcon( "src/imagen/subirArchivo.png");
        Icon icono6 = new ImageIcon(imagen6.getImage().getScaledInstance(jLabel6.getWidth(),jLabel6.getHeight(),Image.SCALE_DEFAULT));
        jLabel6.setIcon(icono6);
        ImageIcon imagen7 = new ImageIcon( "src/imagen/justificante.png");
        Icon icono7 = new ImageIcon(imagen7.getImage().getScaledInstance(jLabel9.getWidth(),jLabel9.getHeight(),Image.SCALE_DEFAULT));
        jLabel9.setIcon(icono7);
    }
    
private String abrirArchivo(){
        
        String aux   = "";   
        String texto = "";
        String cveEmp = "";
        String fecReg = "";
        String numBio = "";
        int c_graba2 = 0;
        int c_duplica2 = 0;
        int c_fmtoerr = 0;
        int c_lei = 0;
        int c_fk = 0;
        try{
            /**llamamos el metodo que permite cargar la ventana*/
            JFileChooser file=new JFileChooser();
            file.showOpenDialog(this);
            /**abrimos el archivo seleccionado*/
            File abre = file.getSelectedFile();
            /**recorremos el archivo, lo leemos para plasmarlo en el area de texto*/
            if(abre != null){     
                FileReader archivos = new FileReader(abre);
                BufferedReader lee  = new BufferedReader(archivos);
                while((aux = lee.readLine()) != null){
                    //para cada registro se extra la información para el insert
                    c_lei++;
                    cveEmp = aux.substring(0,6);
                    fecReg = "20" + aux.substring(10,12) + "-" + aux.substring(8,10) +
                         "-" + aux.substring(6,8) + " " + aux.substring(12,14) + ":" + 
                         aux.substring(14,16) + ":" + aux.substring(16,18);
                    numBio = aux.substring(18,19);   
                  RegistroAsistencia datosRegAsi;
                  datosRegAsi = new RegistroAsistencia();
                  controlador.Inserciones insertRegistroAsistencia;
                  insertRegistroAsistencia = new controlador.Inserciones();
                  datosRegAsi.setCCVEEMP(cveEmp);
                  datosRegAsi.setDFECREG(fecReg);
                  datosRegAsi.setCNUMBIO(numBio);
                  datosRegAsi.setCSTATUS("R");
                  try {
                    PreparedStatement pps = cn.prepareStatement(insertRegistroAsistencia.insertRegistroAsistencia());
                    pps.setString(1, datosRegAsi.getCCVEEMP());
                    pps.setString(2, datosRegAsi.getDFECREG());
                    pps.setString(3, datosRegAsi.getCNUMBIO());
                    pps.setString(4, datosRegAsi.getCSTATUS());
                    pps.executeUpdate();
                    c_graba2++;
                  } catch (SQLException ex) {
                    if(ex.getErrorCode() == MYSQL_DUPLICATE_PK ){
                        c_duplica2++;
                    }
                    if(ex.getErrorCode() == MYSQL_FMTO_ERRONEO ){
                        c_fmtoerr++;
                    }
                    if(ex.getErrorCode() == MYSQL_FOREIGN_KEY ){
                        c_fk++;
                    }
                    Logger.getLogger(RegistrarEmpleado.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
                lee.close();
            }    
        }catch(IOException ex){
            JOptionPane.showMessageDialog(null,ex+"" +
            "\nNo se ha encontrado el archivo",
            "ADVERTENCIA!!!",JOptionPane.WARNING_MESSAGE);
    }
    JOptionPane.showMessageDialog(this, "Leidos " + c_lei);
    JOptionPane.showMessageDialog(this, "Grabados " + c_graba2);
    JOptionPane.showMessageDialog(this, "Duplicados " + c_duplica2);
    JOptionPane.showMessageDialog(this, "Error por formato " + c_fmtoerr);
    JOptionPane.showMessageDialog(this, "Error por no estar en datos de empleado " + c_fk);
    return texto;//El texto se almacena en el JTextArea
}



    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jButton6 = new javax.swing.JButton();
        jLabel9 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(833, 534));
        getContentPane().setLayout(null);

        jButton1.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jButton1.setText("Registrar Horario");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1);
        jButton1.setBounds(350, 230, 140, 40);

        jButton2.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jButton2.setText("Dias No habiles");
        jButton2.setPreferredSize(new java.awt.Dimension(118, 24));
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2);
        jButton2.setBounds(110, 420, 150, 40);

        jButton3.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jButton3.setText("Registrar Empleado");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton3);
        jButton3.setBounds(110, 230, 150, 40);

        jButton4.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jButton4.setText("Asignar horarios");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton4);
        jButton4.setBounds(570, 230, 140, 50);

        jButton5.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jButton5.setText("Subir Archivo de asistencia");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton5);
        jButton5.setBounds(550, 420, 200, 40);

        jLabel8.setFont(new java.awt.Font("Agency FB", 1, 48)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Bienvenido");
        getContentPane().add(jLabel8);
        jLabel8.setBounds(330, 20, 180, 40);

        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("label");
        jLabel7.setMaximumSize(new java.awt.Dimension(171, 99));
        jLabel7.setMinimumSize(new java.awt.Dimension(171, 99));
        jLabel7.setPreferredSize(new java.awt.Dimension(171, 99));
        getContentPane().add(jLabel7);
        jLabel7.setBounds(570, 100, 120, 99);

        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("label");
        jLabel6.setMaximumSize(new java.awt.Dimension(171, 99));
        jLabel6.setMinimumSize(new java.awt.Dimension(171, 99));
        jLabel6.setPreferredSize(new java.awt.Dimension(171, 99));
        getContentPane().add(jLabel6);
        jLabel6.setBounds(580, 300, 150, 110);

        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("label");
        jLabel4.setMaximumSize(new java.awt.Dimension(171, 99));
        jLabel4.setMinimumSize(new java.awt.Dimension(171, 99));
        jLabel4.setPreferredSize(new java.awt.Dimension(171, 99));
        getContentPane().add(jLabel4);
        jLabel4.setBounds(130, 300, 120, 99);

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("label");
        jLabel3.setMaximumSize(new java.awt.Dimension(171, 99));
        jLabel3.setMinimumSize(new java.awt.Dimension(171, 99));
        jLabel3.setPreferredSize(new java.awt.Dimension(171, 99));
        getContentPane().add(jLabel3);
        jLabel3.setBounds(370, 110, 110, 90);

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("label");
        jLabel2.setMaximumSize(new java.awt.Dimension(171, 99));
        jLabel2.setMinimumSize(new java.awt.Dimension(171, 99));
        jLabel2.setPreferredSize(new java.awt.Dimension(171, 99));
        getContentPane().add(jLabel2);
        jLabel2.setBounds(140, 100, 120, 99);

        jButton6.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jButton6.setText("Registrar justificante");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton6);
        jButton6.setBounds(340, 420, 160, 40);

        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("label");
        jLabel9.setMaximumSize(new java.awt.Dimension(171, 99));
        jLabel9.setMinimumSize(new java.awt.Dimension(171, 99));
        jLabel9.setPreferredSize(new java.awt.Dimension(171, 99));
        getContentPane().add(jLabel9);
        jLabel9.setBounds(380, 310, 90, 99);
        getContentPane().add(jLabel1);
        jLabel1.setBounds(0, 0, 840, 535);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        RegistrarEmpleado pn = new RegistrarEmpleado();
        pn.setVisible(true);
        this.setVisible(false);

    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        abrirArchivo();
        JOptionPane.showMessageDialog(this, "Proceso Finalizado");
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        RegistrarHorario rh = new RegistrarHorario();
        rh.setVisible(true);
        this.setVisible(false);
        
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
      AgregarDiaNoLab dnh = new AgregarDiaNoLab();
      dnh.setVisible(true);
      this.setVisible(false);
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        RegistrarJustificantes jus = new RegistrarJustificantes();
        jus.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        AsignarHorario hora = new AsignarHorario();
        hora.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_jButton4ActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Inicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Inicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Inicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Inicio.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Inicio().setVisible(true);
            }
        });
    }
  


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    // End of variables declaration//GEN-END:variables
}
