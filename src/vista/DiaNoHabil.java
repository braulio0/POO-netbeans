/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import java.awt.Image;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import modelo.Horario;

/**
 *
 * @author emmaf
 */
public class DiaNoHabil extends javax.swing.JFrame {
controlador.Conexion con = new controlador.Conexion();
        Connection cn  = con.conexion();
    /**
     * Creates new form DiaNoHabil
     */
    public DiaNoHabil() {
        initComponents();
        CB_Status.removeAllItems();
        llena_fechas();
        llena_datos();
        ImageIcon imagen = new ImageIcon( "src/imagen/fondo.jpg");
        Icon icono = new ImageIcon(imagen.getImage().getScaledInstance(jLabel4.getWidth(),jLabel4.getHeight(),Image.SCALE_DEFAULT));
        jLabel4.setIcon(icono);
    }
    void llena_fechas(){
        controlador.Consultas consfecha;
        consfecha = new controlador.Consultas();
        CB_fecha.removeAllItems();
        Statement st;
        try {
            st = cn.createStatement();
            ResultSet rs = st.executeQuery(consfecha.consultaDiaNhabil());
            while (rs.next()){
                CB_fecha.addItem(rs.getString(1));
            }
        } catch (SQLException ex) {
            Logger.getLogger(RegistrarEmpleado.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    void llena_datos(){
        controlador.Consultas consfecha;
        consfecha = new controlador.Consultas();
        CB_Status.removeAllItems();
        Statement st;
        try {
            st = cn.createStatement();
            ResultSet rs = st.executeQuery(consfecha.consutaStatusHN(CB_fecha.getItemAt(CB_fecha.getSelectedIndex())));
            while (rs.next()){
                CB_Status.addItem(rs.getString(1));
            }
        } catch (SQLException ex) {
            Logger.getLogger(RegistrarEmpleado.class.getName()).log(Level.SEVERE, null, ex);
        }
        Llenarmotivo(CB_fecha.getItemAt(CB_fecha.getSelectedIndex()));
    }
    void Llenarmotivo(String fecha){
    Horario datosHorario;
    datosHorario = new Horario();
    controlador.Consultas consHor;
    consHor = new controlador.Consultas();
    Statement st;
    try{
        st = cn.createStatement();
        ResultSet rs = st.executeQuery(consHor.consultaDescripcion(fecha));
            //JOptionPane.showMessageDialog(this, "tercero");
             while(rs.next()){
            txt_hdesc.setText(rs.getString(1));
                  }
              } catch (SQLException ex) {
            Logger.getLogger(ConsultaEmpleados.class.getName()).log(Level.SEVERE, null, ex);
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

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txt_hdesc = new javax.swing.JTextArea();
        CB_Status = new javax.swing.JComboBox<>();
        jButton2 = new javax.swing.JButton();
        CB_fecha = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMaximumSize(new java.awt.Dimension(720, 450));
        setMinimumSize(new java.awt.Dimension(720, 450));
        setPreferredSize(new java.awt.Dimension(718, 450));
        getContentPane().setLayout(null);

        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Fecha ");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(34, 90, 50, 14);

        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("Motivo ");
        getContentPane().add(jLabel2);
        jLabel2.setBounds(30, 160, 70, 14);

        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Status ");
        getContentPane().add(jLabel3);
        jLabel3.setBounds(430, 160, 90, 14);

        txt_hdesc.setColumns(20);
        txt_hdesc.setRows(5);
        jScrollPane1.setViewportView(txt_hdesc);

        getContentPane().add(jScrollPane1);
        jScrollPane1.setBounds(110, 160, 260, 60);

        CB_Status.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        CB_Status.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CB_StatusActionPerformed(evt);
            }
        });
        getContentPane().add(CB_Status);
        CB_Status.setBounds(490, 150, 58, 23);

        jButton2.setText("Inicio ");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2);
        jButton2.setBounds(290, 290, 73, 24);

        CB_fecha.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        CB_fecha.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CB_fechaActionPerformed(evt);
            }
        });
        getContentPane().add(CB_fecha);
        CB_fecha.setBounds(120, 90, 100, 23);

        jLabel4.setText("jLabel4");
        jLabel4.setMaximumSize(new java.awt.Dimension(815, 500));
        jLabel4.setMinimumSize(new java.awt.Dimension(815, 500));
        jLabel4.setPreferredSize(new java.awt.Dimension(815, 500));
        getContentPane().add(jLabel4);
        jLabel4.setBounds(0, 0, 930, 440);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void CB_StatusActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CB_StatusActionPerformed
        
    }//GEN-LAST:event_CB_StatusActionPerformed

    private void CB_fechaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CB_fechaActionPerformed
        llena_datos();
    }//GEN-LAST:event_CB_fechaActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
       Inicio in = new Inicio();
       in.setVisible(true);
       this.setVisible(false);
    }//GEN-LAST:event_jButton2ActionPerformed

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
            java.util.logging.Logger.getLogger(DiaNoHabil.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(DiaNoHabil.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(DiaNoHabil.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(DiaNoHabil.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new DiaNoHabil().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> CB_Status;
    private javax.swing.JComboBox<String> CB_fecha;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea txt_hdesc;
    // End of variables declaration//GEN-END:variables
}
