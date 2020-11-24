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
        ImageIcon imagen = new ImageIcon( "src/imagen/fondo.jpg");
        Icon icono = new ImageIcon(imagen.getImage().getScaledInstance(jLabel1.getWidth(),jLabel1.getHeight(),Image.SCALE_DEFAULT));
        jLabel1.setIcon(icono);
        ImageIcon imagen2 = new ImageIcon( "src/imagen/EmpleadoNuevo.png");
        Icon icono2 = new ImageIcon(imagen2.getImage().getScaledInstance(jLabel2.getWidth(),jLabel2.getHeight(),Image.SCALE_DEFAULT));
        jLabel2.setIcon(icono2);
        ImageIcon imagen3 = new ImageIcon( "src/imagen/HorarioNuevo.png");
        Icon icono3 = new ImageIcon(imagen3.getImage().getScaledInstance(jLabel3.getWidth(),jLabel3.getHeight(),Image.SCALE_DEFAULT));
        jLabel3.setIcon(icono3);
        ImageIcon imagen4 = new ImageIcon( "src/imagen/Descansos.jpeg");
        Icon icono4 = new ImageIcon(imagen4.getImage().getScaledInstance(jLabel4.getWidth(),jLabel4.getHeight(),Image.SCALE_DEFAULT));
        jLabel4.setIcon(icono4);
        ImageIcon imagen5 = new ImageIcon( "src/imagen/AsignarHorario.png");
        Icon icono5 = new ImageIcon(imagen5.getImage().getScaledInstance(jLabel7.getWidth(),jLabel7.getHeight(),Image.SCALE_DEFAULT));
        jLabel7.setIcon(icono5);
        ImageIcon imagen6 = new ImageIcon( "src/imagen/SubirRegistro.png");
        Icon icono6 = new ImageIcon(imagen6.getImage().getScaledInstance(jLabel6.getWidth(),jLabel6.getHeight(),Image.SCALE_DEFAULT));
        jLabel6.setIcon(icono6);
        
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
                    Logger.getLogger(Principal.class.getName()).log(Level.SEVERE, null, ex);
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
        jButton6 = new javax.swing.JButton();
        jLabel8 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMaximumSize(new java.awt.Dimension(840, 583));
        setMinimumSize(new java.awt.Dimension(833, 534));
        setPreferredSize(new java.awt.Dimension(833, 534));
        getContentPane().setLayout(null);

        jButton1.setText("jButton1");
        getContentPane().add(jButton1);
        jButton1.setBounds(380, 250, 73, 24);

        jButton2.setText("jButton1");
        getContentPane().add(jButton2);
        jButton2.setBounds(640, 250, 73, 24);

        jButton3.setText("Registrar Empleado");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton3);
        jButton3.setBounds(90, 250, 140, 24);

        jButton4.setText("jButton1");
        getContentPane().add(jButton4);
        jButton4.setBounds(120, 460, 73, 24);

        jButton5.setText("Subir Archivo de asistencia");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton5);
        jButton5.setBounds(320, 460, 200, 24);

        jButton6.setText("jButton1");
        getContentPane().add(jButton6);
        jButton6.setBounds(660, 460, 73, 24);

        jLabel8.setFont(new java.awt.Font("Dialog", 0, 24)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Bienvenido");
        getContentPane().add(jLabel8);
        jLabel8.setBounds(380, 20, 230, 40);

        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("label");
        jLabel7.setMaximumSize(new java.awt.Dimension(171, 99));
        jLabel7.setMinimumSize(new java.awt.Dimension(171, 99));
        jLabel7.setPreferredSize(new java.awt.Dimension(171, 99));
        getContentPane().add(jLabel7);
        jLabel7.setBounds(70, 330, 171, 99);

        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("label");
        jLabel6.setMaximumSize(new java.awt.Dimension(171, 99));
        jLabel6.setMinimumSize(new java.awt.Dimension(171, 99));
        jLabel6.setPreferredSize(new java.awt.Dimension(171, 99));
        getContentPane().add(jLabel6);
        jLabel6.setBounds(330, 330, 171, 99);

        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("label");
        jLabel5.setMaximumSize(new java.awt.Dimension(171, 99));
        jLabel5.setMinimumSize(new java.awt.Dimension(171, 99));
        jLabel5.setPreferredSize(new java.awt.Dimension(171, 99));
        getContentPane().add(jLabel5);
        jLabel5.setBounds(590, 330, 171, 99);

        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("label");
        jLabel4.setMaximumSize(new java.awt.Dimension(171, 99));
        jLabel4.setMinimumSize(new java.awt.Dimension(171, 99));
        jLabel4.setPreferredSize(new java.awt.Dimension(171, 99));
        getContentPane().add(jLabel4);
        jLabel4.setBounds(590, 120, 171, 99);

        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("label");
        jLabel3.setMaximumSize(new java.awt.Dimension(171, 99));
        jLabel3.setMinimumSize(new java.awt.Dimension(171, 99));
        jLabel3.setPreferredSize(new java.awt.Dimension(171, 99));
        getContentPane().add(jLabel3);
        jLabel3.setBounds(330, 120, 171, 99);

        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("label");
        jLabel2.setMaximumSize(new java.awt.Dimension(171, 99));
        jLabel2.setMinimumSize(new java.awt.Dimension(171, 99));
        jLabel2.setPreferredSize(new java.awt.Dimension(171, 99));
        getContentPane().add(jLabel2);
        jLabel2.setBounds(70, 120, 171, 99);
        getContentPane().add(jLabel1);
        jLabel1.setBounds(0, 0, 840, 535);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        Principal pn = new Principal();
        Inicio in = new Inicio();
        pn.setVisible(true);
        this.setVisible(false);

    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        abrirArchivo();
        JOptionPane.showMessageDialog(this, "Proceso Finalizado");
    }//GEN-LAST:event_jButton5ActionPerformed

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
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    // End of variables declaration//GEN-END:variables
}
