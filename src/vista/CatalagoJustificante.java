/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author emmaf
 */
public class CatalagoJustificante extends javax.swing.JFrame {
controlador.Conexion con = new controlador.Conexion();
        Connection cn  = con.conexion();
    /**
     * Creates new form CatalagoJustificante
     */
    public CatalagoJustificante() {
        initComponents();
        CB_justificante.removeAllItems();
        llenaJustificantes();
    }
void llenaJustificantes(){
    controlador.Consultas consjust;
    consjust = new controlador.Consultas();
        CB_justificante.removeAllItems();
        Statement st;
        try {
            st = cn.createStatement();
            ResultSet rs = st.executeQuery(consjust.consultaCatalogoJudtificantes());
            while (rs.next()){
                CB_justificante.addItem(rs.getString(1));
            }
        } catch (SQLException ex) {
            Logger.getLogger(RegistrarEmpleado.class.getName()).log(Level.SEVERE, null, ex);
        }
}
void llena_Descripion(){
    controlador.Consultas consjust;
    consjust = new controlador.Consultas();
        Statement st;
        try {
            st = cn.createStatement();
            ResultSet rs = st.executeQuery(consjust.consultaDescripcionJustifiante(CB_justificante.getSelectedIndex()+1));
            while (rs.next()){
                txt_desc.setText(rs.getString(1));
            }
        } catch (SQLException ex) {
            Logger.getLogger(RegistrarEmpleado.class.getName()).log(Level.SEVERE, null, ex);
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
        jTextField1 = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txt_desc = new javax.swing.JTextArea();
        jLabel3 = new javax.swing.JLabel();
        jTextField2 = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jLabel4 = new javax.swing.JLabel();
        CB_justificante = new javax.swing.JComboBox<>();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setLocation(new java.awt.Point(0, 0));
        setMaximumSize(new java.awt.Dimension(580, 450));
        setMinimumSize(new java.awt.Dimension(580, 450));
        setPreferredSize(new java.awt.Dimension(580, 450));
        getContentPane().setLayout(null);

        jLabel1.setText("Folio (No. de trabajador) ");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(20, 20, 121, 14);

        jTextField1.setText("jTextField1");
        getContentPane().add(jTextField1);
        jTextField1.setBounds(150, 8, 120, 30);

        jLabel2.setText("Descripcion de Justificación");
        getContentPane().add(jLabel2);
        jLabel2.setBounds(10, 200, 200, 14);

        txt_desc.setColumns(20);
        txt_desc.setRows(5);
        txt_desc.setTabSize(2);
        jScrollPane1.setViewportView(txt_desc);

        getContentPane().add(jScrollPane1);
        jScrollPane1.setBounds(150, 210, 183, 100);

        jLabel3.setText("Fecha ");
        getContentPane().add(jLabel3);
        jLabel3.setBounds(20, 70, 34, 14);

        jTextField2.setText("jTextField2");
        getContentPane().add(jTextField2);
        jTextField2.setBounds(150, 58, 120, 30);

        jButton1.setText("Guardar ");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1);
        jButton1.setBounds(160, 330, 80, 24);

        jButton2.setText("Inicio ");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2);
        jButton2.setBounds(410, 310, 73, 24);

        jLabel4.setText("Tipo de Justificación");
        getContentPane().add(jLabel4);
        jLabel4.setBounds(10, 130, 110, 14);

        CB_justificante.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        CB_justificante.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CB_justificanteActionPerformed(evt);
            }
        });
        getContentPane().add(CB_justificante);
        CB_justificante.setBounds(150, 130, 150, 23);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton2ActionPerformed

    private void CB_justificanteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CB_justificanteActionPerformed
        llena_Descripion();
    }//GEN-LAST:event_CB_justificanteActionPerformed

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
            java.util.logging.Logger.getLogger(CatalagoJustificante.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CatalagoJustificante.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CatalagoJustificante.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CatalagoJustificante.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new CatalagoJustificante().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> CB_justificante;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTextField1;
    private javax.swing.JTextField jTextField2;
    private javax.swing.JTextArea txt_desc;
    // End of variables declaration//GEN-END:variables
}
