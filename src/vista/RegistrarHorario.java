/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import java.awt.Image;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import modelo.Horario;

/**
 *
 * @author elias
 */
public class RegistrarHorario extends javax.swing.JFrame {
    private int limite = 100;
    public int mensaje = 1, dia = 0, horario=1, horario1=1;
    
    public static final int MYSQL_DUPLICATE_PK = 1062;
    public static final int MYSQL_FOREIGN_KEY    = 1452;

    //Se establece la conexión con la base de datos
    controlador.Conexion con = new controlador.Conexion();
    Connection cn  = con.conexion();
    /**
     * Creates new form RegistrarHorario
     */
    public RegistrarHorario() {
        initComponents();
        setLocationRelativeTo(null);
        setTitle("Registrar  Horario");
        llenaStatus();
        llenardias();
        habilitar();
        ImageIcon imagen = new ImageIcon("src/imagen/fondo.jpg");
        Icon icono = new ImageIcon(imagen.getImage().getScaledInstance(jLabel1.getWidth(), jLabel1.getHeight(), Image.SCALE_DEFAULT));
        jLabel1.setIcon(icono);
    }
    public static boolean validafecha(String fecha){
        fecha=fecha.toUpperCase().trim();
        return fecha.matches("^([01]?[0-9]|2[0-3]):[0-5][0-9]$");
    }
    void inhabilitar(){
        txt_IdHorario.setEnabled(false);
         CB_Status.setEnabled(false);
         jButton1.setEnabled(false);
         txt_desHor.setEditable(false);
        


    }
    void habilitar(){
        txt_IdHorario.setEnabled(true);
        CB_Status.setEnabled(true);
        jButton1.setEnabled(true);
        jButton2.setEnabled(false);
        txt_desHor.setEditable(true);

    }
    void limpiar(){
        txt_HorasS.setText("");
        txt_IdHorario.setText("");
        txt_MinS.setText("");
        txt_desHor.setText("");
        txt_horasE.setText("");
        txt_minE.setText("");
        
    }
void llenaStatus(){
     CB_Status.removeAllItems();
     CB_Status.addItem("A");
     CB_Status.addItem("I");
}
void llenardias(){
     CB_Dias.removeAllItems();
     CB_Dias.addItem("Domingo");
     CB_Dias.addItem("Lunes");
     CB_Dias.addItem("Martes");
     CB_Dias.addItem("Miercoles");
     CB_Dias.addItem("Jueves");
     CB_Dias.addItem("Viernes");
     CB_Dias.addItem("Sabado");
     
}
void horario(){
    Horario datosHorario;
        datosHorario = new Horario();
        controlador.Inserciones insHor;
        insHor = new controlador.Inserciones();
            datosHorario.setNIDHORA(Integer.parseInt(txt_IdHorario.getText()));
            datosHorario.setNDIASEM(CB_Dias.getSelectedIndex()+1);
            datosHorario.setCHORENT(txt_horasE.getText()+":"+txt_minE.getText());
            datosHorario.setCHORSAL(txt_HorasS.getText()+":"+txt_MinS.getText());
            datosHorario.setCSTATUS(CB_Status.getItemAt(CB_Status.getSelectedIndex()));
            
         if (txt_IdHorario.getText().equals("")){
        JOptionPane.showMessageDialog(null, "Por favor ingresa la clave del horario");
        }
         else if (txt_IdHorario.getText().equals("")){
        JOptionPane.showMessageDialog(null, "Por favor ingresa la clave del horario");
        
        }
       
        else if( validafecha(datosHorario.getCHORENT()) != true){
        JOptionPane.showMessageDialog(null, "Por favor ingresa una Hora valida para la entrada");
         horario ++;
        }
        else if(validafecha(datosHorario.getCHORSAL()) != true){
        JOptionPane.showMessageDialog(null, "Por favor ingresa una hora valida para la salida");
        horario1 ++;
        }
      else{
            
            datosHorario.setNIDHORA(Integer.parseInt(txt_IdHorario.getText()));
            datosHorario.setCDESCHR(txt_desHor.getText());
            datosHorario.setCSTATUS(CB_Status.getItemAt(CB_Status.getSelectedIndex()));
        
            //JOptionPane.showMessageDialog(this, datosHorario.getCSTATUS());
        try {
            horario = 1;
            horario1 = 1;
            PreparedStatement pps = cn.prepareStatement(insHor.insertHorario());
            pps.setInt(1, datosHorario.getNIDHORA());
            pps.setString(2,datosHorario.getCDESCHR());
            pps.setString(3, datosHorario.getCSTATUS());
            pps.executeUpdate();
            JOptionPane.showMessageDialog(this, "Horario ingresado con exito");
        } catch (SQLException ex) {
            if (ex.getErrorCode() == MYSQL_DUPLICATE_PK) {
                JOptionPane.showMessageDialog(this, "Clave de horario ya existe");
            }
            Logger.getLogger(ConsultaEmpleados.class.getName()).log(Level.SEVERE, null, ex);
        }
         }
}

void datosHorario(){
     Horario datosHorario;
        datosHorario = new Horario();
        controlador.Inserciones insdetHor;
        insdetHor = new controlador.Inserciones();
            datosHorario.setNIDHORA(Integer.parseInt(txt_IdHorario.getText()));
            datosHorario.setNDIASEM(CB_Dias.getSelectedIndex()+1);
            datosHorario.setCHORENT(txt_horasE.getText()+":"+txt_minE.getText());
            datosHorario.setCHORSAL(txt_HorasS.getText()+":"+txt_MinS.getText());
            datosHorario.setCSTATUS(CB_Status.getItemAt(CB_Status.getSelectedIndex()));
    if (txt_IdHorario.getText().equals("")){
        JOptionPane.showMessageDialog(null, "Por favor ingresa la clave del horario");
        }
       
        else if( validafecha(datosHorario.getCHORENT()) != true){
            if(horario <= 1){
        JOptionPane.showMessageDialog(null, "Por favor ingresa una Hora valida para la entrada");
            }
        }
        else if(validafecha(datosHorario.getCHORSAL()) != true){
            if (horario1 <=1){
        JOptionPane.showMessageDialog(null, "Por favor ingresa una hora valida para la salida");
            }
        }
        else{
    if (dia<=7){
               
        try {
            PreparedStatement pps = cn.prepareStatement(insdetHor.insertHorarioHoras());
            pps.setInt(1, datosHorario.getNIDHORA());
            pps.setInt(2,datosHorario.getNDIASEM());
            pps.setString(3, datosHorario.getCHORENT());
            pps.setString(4, datosHorario.getCHORSAL());
            pps.setString(5, datosHorario.getCSTATUS());
            pps.executeUpdate();
            JOptionPane.showMessageDialog(this, "Detalles del Horario ingresado con exito");
        } catch (SQLException ex) {
            if (ex.getErrorCode() == MYSQL_FOREIGN_KEY) {
                JOptionPane.showMessageDialog(this, "Clave de horario ya existe");
            }
            Logger.getLogger(ConsultaEmpleados.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        inhabilitar();
        jButton2.setEnabled(true);
        dia+=1;
        JOptionPane.showMessageDialog(this, "Agrega los demas dias llevas " + dia);
    }
    else{
        JOptionPane.showMessageDialog(this, "Hasta el dia de hoy solo existen 7 dias en una semana no puedes agregar mas de 7");
    }
        }
    }
    
    

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        txt_IdHorario = new javax.swing.JTextField();
        CB_Dias = new javax.swing.JComboBox<>();
        txt_horasE = new javax.swing.JTextField();
        txt_minE = new javax.swing.JTextField();
        txt_HorasS = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txt_MinS = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txt_desHor = new javax.swing.JTextPane();
        jLabel11 = new javax.swing.JLabel();
        CB_Status = new javax.swing.JComboBox<>();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jLabel12 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(850, 500));
        getContentPane().setLayout(null);

        jLabel2.setFont(new java.awt.Font("Dialog", 0, 16)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Id Horario");
        getContentPane().add(jLabel2);
        jLabel2.setBounds(60, 50, 80, 20);

        jLabel3.setFont(new java.awt.Font("Dialog", 0, 16)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Dia de la semana");
        getContentPane().add(jLabel3);
        jLabel3.setBounds(60, 100, 130, 20);

        jLabel4.setFont(new java.awt.Font("Dialog", 0, 16)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Hora de Entrada");
        getContentPane().add(jLabel4);
        jLabel4.setBounds(60, 170, 110, 20);

        jLabel5.setFont(new java.awt.Font("Dialog", 0, 16)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Hora de Salida");
        getContentPane().add(jLabel5);
        jLabel5.setBounds(350, 180, 120, 20);

        jLabel6.setFont(new java.awt.Font("Dialog", 0, 16)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Horas");
        getContentPane().add(jLabel6);
        jLabel6.setBounds(180, 200, 80, 20);

        jLabel7.setFont(new java.awt.Font("Dialog", 0, 16)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Minutos");
        getContentPane().add(jLabel7);
        jLabel7.setBounds(270, 200, 80, 20);

        txt_IdHorario.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        txt_IdHorario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_IdHorarioActionPerformed(evt);
            }
        });
        getContentPane().add(txt_IdHorario);
        txt_IdHorario.setBounds(180, 40, 100, 30);

        CB_Dias.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        CB_Dias.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        CB_Dias.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CB_DiasActionPerformed(evt);
            }
        });
        getContentPane().add(CB_Dias);
        CB_Dias.setBounds(200, 100, 170, 30);

        txt_horasE.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        getContentPane().add(txt_horasE);
        txt_horasE.setBounds(170, 170, 60, 30);

        txt_minE.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        getContentPane().add(txt_minE);
        txt_minE.setBounds(260, 170, 60, 30);

        txt_HorasS.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        txt_HorasS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_HorasSActionPerformed(evt);
            }
        });
        getContentPane().add(txt_HorasS);
        txt_HorasS.setBounds(470, 170, 60, 30);

        jLabel8.setFont(new java.awt.Font("Dialog", 0, 16)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("Horas");
        getContentPane().add(jLabel8);
        jLabel8.setBounds(480, 200, 80, 20);

        jLabel9.setFont(new java.awt.Font("Dialog", 0, 16)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Minutos");
        getContentPane().add(jLabel9);
        jLabel9.setBounds(570, 200, 80, 20);

        txt_MinS.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        txt_MinS.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_MinSActionPerformed(evt);
            }
        });
        getContentPane().add(txt_MinS);
        txt_MinS.setBounds(560, 170, 60, 30);

        jLabel10.setFont(new java.awt.Font("Dialog", 0, 16)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Descripcion del Horario");
        getContentPane().add(jLabel10);
        jLabel10.setBounds(60, 240, 210, 30);

        jScrollPane1.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);

        txt_desHor.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        txt_desHor.setToolTipText("");
        txt_desHor.setMaximumSize(new java.awt.Dimension(7, 20));
        txt_desHor.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                txt_desHorKeyPressed(evt);
            }
        });
        jScrollPane1.setViewportView(txt_desHor);

        getContentPane().add(jScrollPane1);
        jScrollPane1.setBounds(240, 240, 380, 130);

        jLabel11.setFont(new java.awt.Font("Dialog", 0, 16)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("Status");
        getContentPane().add(jLabel11);
        jLabel11.setBounds(450, 100, 80, 30);

        CB_Status.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        CB_Status.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        CB_Status.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CB_StatusActionPerformed(evt);
            }
        });
        getContentPane().add(CB_Status);
        CB_Status.setBounds(560, 102, 100, 30);

        jButton1.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jButton1.setText("Guardar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1);
        jButton1.setBounds(320, 390, 130, 30);

        jButton2.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jButton2.setText("Asignar dia");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2);
        jButton2.setBounds(510, 390, 120, 30);

        jButton3.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jButton3.setText("Limpiar");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton3);
        jButton3.setBounds(680, 390, 100, 30);

        jButton4.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jButton4.setText("Nuevo Horario");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton4);
        jButton4.setBounds(160, 390, 110, 30);

        jButton5.setFont(new java.awt.Font("Tahoma", 0, 13)); // NOI18N
        jButton5.setText("Inicio");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton5);
        jButton5.setBounds(20, 390, 80, 30);

        jLabel12.setFont(new java.awt.Font("Tahoma", 0, 18)); // NOI18N
        jLabel12.setText("Registrar horario");
        getContentPane().add(jLabel12);
        jLabel12.setBounds(310, 20, 150, 23);

        jLabel1.setText("jLabel1");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(0, -6, 810, 480);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void txt_IdHorarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_IdHorarioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_IdHorarioActionPerformed

    private void CB_DiasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CB_DiasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CB_DiasActionPerformed

    private void CB_StatusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CB_StatusActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_CB_StatusActionPerformed

    private void txt_HorasSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_HorasSActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_HorasSActionPerformed

    private void txt_MinSActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_MinSActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_MinSActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
       horario();
       datosHorario();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void txt_desHorKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_txt_desHorKeyPressed
      txt_desHor.addKeyListener(new KeyListener(){
 
public void keyTyped(KeyEvent e)
 
{if (txt_desHor.getText().length()== limite)
{
    if (mensaje <= 1){
       mensaje = 2;
     e.consume();
     JOptionPane.showMessageDialog(null, "No se permiten mas de 100 caracteres");
    }       
}
else{
        mensaje=1;
    }

}
public void keyPressed(KeyEvent arg0) {
}
 
public void keyReleased(KeyEvent arg0) {
}
});  // TODO add your handling code here:
    }//GEN-LAST:event_txt_desHorKeyPressed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        inhabilitar();
        datosHorario();
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        txt_HorasS.setText("");
        txt_MinS.setText("");
        txt_horasE.setText("");
        txt_minE.setText("");
        txt_desHor.setText("");
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        limpiar();
        habilitar();
        dia=0;
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        Inicio ini = new Inicio();
        ini.setVisible(true);
        this.setVisible(false);
        
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
            java.util.logging.Logger.getLogger(RegistrarHorario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(RegistrarHorario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(RegistrarHorario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(RegistrarHorario.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new RegistrarHorario().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> CB_Dias;
    private javax.swing.JComboBox<String> CB_Status;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField txt_HorasS;
    private javax.swing.JTextField txt_IdHorario;
    private javax.swing.JTextField txt_MinS;
    private javax.swing.JTextPane txt_desHor;
    private javax.swing.JTextField txt_horasE;
    private javax.swing.JTextField txt_minE;
    // End of variables declaration//GEN-END:variables
}
