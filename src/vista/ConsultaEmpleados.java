/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vista;

import java.awt.Image;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import modelo.Empleado;

/**
 *
 * @author elias
 */
public class ConsultaEmpleados extends javax.swing.JFrame {
    public static final int MYSQL_DUPLICATE_PK = 1062;
    //Se establece la conexión con la base de datos
    controlador.Conexion con = new controlador.Conexion();
    Connection cn  = con.conexion();
    
    public ConsultaEmpleados() {
        initComponents();
        llena_estados();
        inhabilitar();
         ImageIcon imagen = new ImageIcon( "src/imagen/fondo.jpg");
        Icon icono = new ImageIcon(imagen.getImage().getScaledInstance(jLabel14.getWidth(),jLabel14.getHeight(),Image.SCALE_DEFAULT));
        jLabel14.setIcon(icono);
    }
    public static boolean validarCurp(String curp){
        curp=curp.toUpperCase().trim();
        return curp.matches("[A-Z]{4}[0-9]{6}[H,M][A-Z]{5}[0-9,A-Z]{2}");
    }
    public static boolean validarFecha(String fecha){
        return fecha.matches("(^(19|[2-9][0-9])(00|04|08|12|16|20|24|28|32|36|40|44|48|52|56|60|64|68|72|76|80|84|88|92|96)[-]02[-]29$)|(^((19|20)\\d{2})[-](((0[1-9]|1[012])[-](0[1-9]|1[0-9]|2[0-8]))|((0[13578]|1[02])[-](29|30|31))|((0[4,6,9]|11)[-](29|30)))$)");
    }
    void habilitar(){
        txt_nombre.setEnabled(true);
        txt_apeuno.setEnabled(true);
        txt_apedos.setEnabled(true);
        txt_curp.setEnabled(true);
        txt_fecing.setEnabled(true);
        txt_calle.setEnabled(true);
        txt_numext.setEnabled(true);
        txt_numint.setEnabled(true);
        txt_codpos.setEnabled(true);
        txt_coloni.setEnabled(true);
        CB_estados.setEnabled(true);
        CB_municipios.setEnabled(true);
        jButton1.setEnabled(true);
    }
    void inhabilitar(){
        txt_nombre.setEnabled(false);
        txt_apeuno.setEnabled(false);
        txt_apedos.setEnabled(false);
        txt_curp.setEnabled(false);
        txt_fecing.setEnabled(false);
        txt_calle.setEnabled(false);
        txt_numext.setEnabled(false);
        txt_numint.setEnabled(false);
        txt_codpos.setEnabled(false);
        txt_coloni.setEnabled(false);
        CB_estados.setEnabled(false);
        CB_municipios.setEnabled(false);
        jButton1.setEnabled(false);
        jButton6.setEnabled(false);
    }
    void llenaDatos(){
        txt_cveemp.setEnabled(true);
        inhabilitar();
        Empleado datosEmpleado;
        datosEmpleado = new Empleado();
        controlador.Consultas consEmps;
        consEmps = new controlador.Consultas();
        Statement st;
        
        try {
            st = cn.createStatement();
            ResultSet rs = st.executeQuery(consEmps.consultaEmpleados(txt_cveemp.getText()));
             while(rs.next()){
            datosEmpleado.setCNOMBRE(rs.getString("cnombre"));
            datosEmpleado.setCAPEUNO(rs.getString("capeuno"));
            datosEmpleado.setCAPEDOS(rs.getString("capedos"));
            datosEmpleado.setCCURPEM(rs.getString("ccurpem"));
            datosEmpleado.setDFECING(rs.getString("dfecing"));
            datosEmpleado.setCNMCALL(rs.getString("cnmcall"));
            datosEmpleado.setCNUMEXT(rs.getString("cnumext"));
            datosEmpleado.setCNUMINT(rs.getString("cnumint"));
            datosEmpleado.setCCODPOS(rs.getString("ccodpos"));
            datosEmpleado.setCCOLONI(rs.getString("ccoloni"));
            datosEmpleado.setNIDESTA(Integer.parseInt(rs.getString("nidesta")));
            datosEmpleado.setNIDMUNI(Integer.parseInt(rs.getString("nidmuni")));
            txt_nombre.setText(datosEmpleado.getCNOMBRE());
            txt_apeuno.setText(datosEmpleado.getCAPEUNO());
            txt_apedos.setText(datosEmpleado.getCAPEDOS());
            txt_curp.setText(datosEmpleado.getCCURPEM());
            txt_fecing.setText(datosEmpleado.getDFECING());
            txt_calle.setText(datosEmpleado.getCNMCALL());
            txt_numext.setText(datosEmpleado.getCNUMEXT());
            txt_numint.setText(datosEmpleado.getCNUMINT());
            txt_codpos.setText(datosEmpleado.getCCODPOS());
            txt_coloni.setText(datosEmpleado.getCCOLONI());
            CB_estados.setSelectedIndex(datosEmpleado.getNIDESTA()-1);
            CB_municipios.setSelectedIndex(datosEmpleado.getNIDMUNI()-1);
            
             }
            jButton6.setEnabled(true);
        } catch (SQLException ex) {
            Logger.getLogger(ConsultaEmpleados.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    void llena_estados(){
        controlador.Consultas consEdos;
        consEdos = new controlador.Consultas();
        Statement st;
        try {
            st = cn.createStatement();
            ResultSet rs = st.executeQuery(consEdos.consultaEstados());
            while (rs.next()){
                CB_estados.addItem(rs.getString(1));
            }
        } catch (SQLException ex) {
            Logger.getLogger(ConsultaEmpleados.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    void llena_municipios(){
        controlador.Consultas consMpos;
        consMpos = new controlador.Consultas();
        CB_municipios.removeAllItems();
        Statement st;
        try {
            st = cn.createStatement();
            ResultSet rs = st.executeQuery(consMpos.consultaMunicipios(CB_estados.getSelectedIndex() + 1));
            while (rs.next()){
                CB_municipios.addItem(rs.getString(1));
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

        jButton2 = new javax.swing.JButton();
        jButton1 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        txt_coloni = new javax.swing.JTextField();
        CB_estados = new javax.swing.JComboBox<>();
        jLabel4 = new javax.swing.JLabel();
        CB_municipios = new javax.swing.JComboBox<>();
        jLabel1 = new javax.swing.JLabel();
        jButton3 = new javax.swing.JButton();
        jLabel3 = new javax.swing.JLabel();
        txt_codpos = new javax.swing.JTextField();
        jLabel13 = new javax.swing.JLabel();
        txt_numint = new javax.swing.JTextField();
        jLabel12 = new javax.swing.JLabel();
        txt_numext = new javax.swing.JTextField();
        jLabel11 = new javax.swing.JLabel();
        txt_calle = new javax.swing.JTextField();
        jLabel10 = new javax.swing.JLabel();
        txt_cveemp = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txt_nombre = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        txt_apeuno = new javax.swing.JTextField();
        jLabel7 = new javax.swing.JLabel();
        txt_apedos = new javax.swing.JTextField();
        jLabel8 = new javax.swing.JLabel();
        txt_curp = new javax.swing.JTextField();
        jLabel9 = new javax.swing.JLabel();
        txt_fecing = new javax.swing.JTextField();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jLabel14 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(910, 450));
        getContentPane().setLayout(null);

        jButton2.setText("Limpiar");
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton2);
        jButton2.setBounds(400, 390, 69, 24);

        jButton1.setText("Guardar");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton1);
        jButton1.setBounds(650, 390, 100, 24);

        jButton4.setText("Modificar");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton4);
        jButton4.setBounds(510, 390, 100, 24);

        txt_coloni.setToolTipText("");
        txt_coloni.setActionCommand("<Not Set>");
        txt_coloni.setAlignmentX(0.0F);
        txt_coloni.setAlignmentY(0.0F);
        txt_coloni.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        txt_coloni.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_coloniActionPerformed(evt);
            }
        });
        getContentPane().add(txt_coloni);
        txt_coloni.setBounds(490, 195, 233, 30);
        txt_coloni.getAccessibleContext().setAccessibleName("");

        CB_estados.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                CB_estadosItemStateChanged(evt);
            }
        });
        CB_estados.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                CB_estadosMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                CB_estadosMouseEntered(evt);
            }
        });
        CB_estados.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                CB_estadosActionPerformed(evt);
            }
        });
        CB_estados.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyPressed(java.awt.event.KeyEvent evt) {
                CB_estadosKeyPressed(evt);
            }
        });
        getContentPane().add(CB_estados);
        CB_estados.setBounds(490, 250, 200, 23);

        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("Colonia");
        getContentPane().add(jLabel4);
        jLabel4.setBounds(430, 200, 310, 20);

        getContentPane().add(CB_municipios);
        CB_municipios.setBounds(490, 290, 283, 23);

        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Seleccione el Estado");
        getContentPane().add(jLabel1);
        jLabel1.setBounds(360, 250, 390, 20);

        jButton3.setText("Consultar");
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton3);
        jButton3.setBounds(300, 30, 120, 24);

        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("Seleccione el Municipio");
        getContentPane().add(jLabel3);
        jLabel3.setBounds(340, 290, 270, 14);

        txt_codpos.setActionCommand("<Not Set>");
        txt_codpos.setAlignmentX(0.0F);
        txt_codpos.setAlignmentY(0.0F);
        txt_codpos.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        getContentPane().add(txt_codpos);
        txt_codpos.setBounds(490, 145, 80, 30);

        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setText("Código Postal");
        getContentPane().add(jLabel13);
        jLabel13.setBounds(410, 150, 200, 20);

        txt_numint.setActionCommand("<Not Set>");
        txt_numint.setAlignmentX(0.0F);
        txt_numint.setAlignmentY(0.0F);
        txt_numint.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        txt_numint.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_numintActionPerformed(evt);
            }
        });
        getContentPane().add(txt_numint);
        txt_numint.setBounds(490, 90, 103, 30);

        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setText("Num. Interior");
        getContentPane().add(jLabel12);
        jLabel12.setBounds(410, 100, 90, 14);

        txt_numext.setActionCommand("<Not Set>");
        txt_numext.setAlignmentX(0.0F);
        txt_numext.setAlignmentY(0.0F);
        txt_numext.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        getContentPane().add(txt_numext);
        txt_numext.setBounds(690, 90, 95, 30);

        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setText("Num. Exterior");
        getContentPane().add(jLabel11);
        jLabel11.setBounds(610, 100, 90, 14);

        txt_calle.setActionCommand("<Not Set>");
        txt_calle.setAlignmentX(0.0F);
        txt_calle.setAlignmentY(0.0F);
        txt_calle.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        getContentPane().add(txt_calle);
        txt_calle.setBounds(490, 35, 283, 30);

        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setText("Calle");
        getContentPane().add(jLabel10);
        jLabel10.setBounds(440, 40, 50, 20);

        txt_cveemp.setActionCommand("<Not Set>");
        txt_cveemp.setAlignmentX(0.0F);
        txt_cveemp.setAlignmentY(0.0F);
        txt_cveemp.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        txt_cveemp.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_cveempActionPerformed(evt);
            }
        });
        getContentPane().add(txt_cveemp);
        txt_cveemp.setBounds(170, 30, 91, 30);

        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("* Clave");
        getContentPane().add(jLabel2);
        jLabel2.setBounds(40, 40, 60, 14);

        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("Nombre(s)");
        getContentPane().add(jLabel5);
        jLabel5.setBounds(30, 90, 70, 14);

        txt_nombre.setActionCommand("<Not Set>");
        txt_nombre.setAlignmentX(0.0F);
        txt_nombre.setAlignmentY(0.0F);
        txt_nombre.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        getContentPane().add(txt_nombre);
        txt_nombre.setBounds(170, 80, 116, 30);

        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("Primer Apellido");
        getContentPane().add(jLabel6);
        jLabel6.setBounds(20, 140, 130, 14);

        txt_apeuno.setActionCommand("<Not Set>");
        txt_apeuno.setAlignmentX(0.0F);
        txt_apeuno.setAlignmentY(0.0F);
        txt_apeuno.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        getContentPane().add(txt_apeuno);
        txt_apeuno.setBounds(170, 130, 132, 30);

        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("Segundo Apellido");
        getContentPane().add(jLabel7);
        jLabel7.setBounds(10, 180, 140, 14);

        txt_apedos.setActionCommand("<Not Set>");
        txt_apedos.setAlignmentX(0.0F);
        txt_apedos.setAlignmentY(0.0F);
        txt_apedos.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        getContentPane().add(txt_apedos);
        txt_apedos.setBounds(170, 170, 175, 30);

        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("CURP");
        getContentPane().add(jLabel8);
        jLabel8.setBounds(60, 220, 70, 14);

        txt_curp.setActionCommand("<Not Set>");
        txt_curp.setAlignmentX(0.0F);
        txt_curp.setAlignmentY(0.0F);
        txt_curp.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        getContentPane().add(txt_curp);
        txt_curp.setBounds(170, 210, 190, 30);

        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setText("Fecha de Ingreso");
        getContentPane().add(jLabel9);
        jLabel9.setBounds(20, 250, 150, 20);

        txt_fecing.setActionCommand("<Not Set>");
        txt_fecing.setAlignmentX(0.0F);
        txt_fecing.setAlignmentY(0.0F);
        txt_fecing.setCursor(new java.awt.Cursor(java.awt.Cursor.TEXT_CURSOR));
        txt_fecing.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txt_fecingActionPerformed(evt);
            }
        });
        getContentPane().add(txt_fecing);
        txt_fecing.setBounds(170, 250, 86, 30);

        jButton5.setText("Inicio");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton5);
        jButton5.setBounds(40, 390, 59, 24);

        jButton6.setText("Eliminar");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });
        getContentPane().add(jButton6);
        jButton6.setBounds(780, 390, 100, 24);

        jLabel14.setText("fondo");
        getContentPane().add(jLabel14);
        jLabel14.setBounds(0, 0, 910, 430);

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
       if (txt_cveemp.getText().equals("")){
        JOptionPane.showMessageDialog(null, "Por favor ingresa la clave");
        }
        else if(txt_apeuno.getText().equals("") && txt_apedos.getText().equals("") ){
        JOptionPane.showMessageDialog(null, "Por favor ingresa uno de los dos apellidos");

        }
        else if(validarCurp(txt_curp.getText()) != true){
        JOptionPane.showMessageDialog(null, "Por favor ingresa un curp valido");
            
        }
        else if(validarFecha(txt_fecing.getText()) != true){
        JOptionPane.showMessageDialog(null, "Por favor ingresa una fecha valida aaaa-mm-dd");
        }
        else{
        //JOptionPane.showMessageDialog(null, CB_estados.getSelectedItem());
        //JOptionPane.showMessageDialog(null, CB_estados.getSelectedIndex());
        //JOptionPane.showMessageDialog(null, CB_municipios.getSelectedItem());
        //JOptionPane.showMessageDialog(null, CB_municipios.getSelectedIndex());
        Empleado datosEmpleado;
        datosEmpleado = new Empleado();
        controlador.Inserciones updEmps;
        updEmps = new controlador.Inserciones();
        datosEmpleado.setCCVEEMP(txt_cveemp.getText());
        datosEmpleado.setCNOMBRE(txt_nombre.getText());
        datosEmpleado.setCAPEUNO(txt_apeuno.getText());
        datosEmpleado.setCAPEDOS(txt_apedos.getText());
        datosEmpleado.setCCURPEM(txt_curp.getText());
        datosEmpleado.setDFECING(txt_fecing.getText());
        datosEmpleado.setCNMCALL(txt_calle.getText());
        datosEmpleado.setCNUMEXT(txt_numext.getText());
        datosEmpleado.setCNUMINT(txt_numint.getText());
        datosEmpleado.setCCODPOS(txt_codpos.getText());
        datosEmpleado.setNIDESTA(CB_estados.getSelectedIndex() + 1);
        datosEmpleado.setNIDMUNI(CB_municipios.getSelectedIndex() + 1);
        try {
            PreparedStatement pps = cn.prepareStatement(updEmps.ModificarEmpleado());
            pps.setString(1, datosEmpleado.getCNOMBRE());
            pps.setString(2, datosEmpleado.getCAPEUNO());
            pps.setString(3, datosEmpleado.getCAPEDOS());
            pps.setString(4, datosEmpleado.getCCURPEM());
            pps.setString(5, datosEmpleado.getDFECING());
            pps.setString(6, datosEmpleado.getCNMCALL());
            pps.setString(7, datosEmpleado.getCNUMEXT());
            pps.setString(8, datosEmpleado.getCNUMINT());
            pps.setString(9, datosEmpleado.getCCOLONI());
            pps.setString(10, datosEmpleado.getCCODPOS());
            pps.setInt(11, datosEmpleado.getNIDESTA());
            pps.setInt(12, datosEmpleado.getNIDMUNI());
            pps.setString(13, "A");
            pps.setString(14, datosEmpleado.getCCVEEMP());
            pps.executeUpdate();
            JOptionPane.showMessageDialog(this, "Empleado Modificado con exito");
        } catch (SQLException ex) {
            if(ex.getErrorCode() == MYSQL_DUPLICATE_PK ){
                JOptionPane.showMessageDialog(this, "Clave de usuario ya existe");
            }
            Logger.getLogger(ConsultaEmpleados.class.getName()).log(Level.SEVERE, null, ex);
        }
        }
       
    }//GEN-LAST:event_jButton1ActionPerformed

    private void CB_estadosKeyPressed(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_CB_estadosKeyPressed

    }//GEN-LAST:event_CB_estadosKeyPressed

    private void CB_estadosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_CB_estadosActionPerformed

    }//GEN-LAST:event_CB_estadosActionPerformed

    private void CB_estadosMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_CB_estadosMouseEntered

    }//GEN-LAST:event_CB_estadosMouseEntered

    private void CB_estadosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_CB_estadosMouseClicked

    }//GEN-LAST:event_CB_estadosMouseClicked

    private void CB_estadosItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_CB_estadosItemStateChanged
        llena_municipios();
    }//GEN-LAST:event_CB_estadosItemStateChanged

    private void txt_cveempActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_cveempActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_cveempActionPerformed

    private void txt_fecingActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_fecingActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_fecingActionPerformed

    private void txt_numintActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_numintActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_numintActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        llenaDatos();        // TODO add your handling code here:
    }//GEN-LAST:event_jButton3ActionPerformed

    private void txt_coloniActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txt_coloniActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txt_coloniActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        txt_cveemp.setText("");
        txt_nombre.setText("");
        txt_apeuno.setText("");
        txt_apedos.setText("");
        txt_curp.setText("");
        txt_fecing.setText("");
        txt_calle.setText("");
        txt_numext.setText("");
        txt_numint.setText("");
        txt_codpos.setText("");
        txt_coloni.setText("");
    }//GEN-LAST:event_jButton2ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        txt_cveemp.setEnabled(false);
        habilitar();

    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        Inicio ini = new Inicio();
        ini.setVisible(true);
        this.setVisible(false);
    }//GEN-LAST:event_jButton5ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
       controlador.Deletes deleEmps;
       deleEmps = new controlador.Deletes();
        try {
            PreparedStatement pps = cn.prepareStatement(deleEmps.DeteleEmpleados());
            pps.setString(1, txt_cveemp.getText());
            pps.executeUpdate();
            JOptionPane.showMessageDialog(this, "Empleado eliminado con exito");
        } catch (SQLException ex) {
        }
       
    }//GEN-LAST:event_jButton6ActionPerformed

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
            java.util.logging.Logger.getLogger(ConsultaEmpleados.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(ConsultaEmpleados.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(ConsultaEmpleados.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(ConsultaEmpleados.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new ConsultaEmpleados().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> CB_estados;
    private javax.swing.JComboBox<String> CB_municipios;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JTextField txt_apedos;
    private javax.swing.JTextField txt_apeuno;
    private javax.swing.JTextField txt_calle;
    private javax.swing.JTextField txt_codpos;
    private javax.swing.JTextField txt_coloni;
    private javax.swing.JTextField txt_curp;
    private javax.swing.JTextField txt_cveemp;
    private javax.swing.JTextField txt_fecing;
    private javax.swing.JTextField txt_nombre;
    private javax.swing.JTextField txt_numext;
    private javax.swing.JTextField txt_numint;
    // End of variables declaration//GEN-END:variables
}
