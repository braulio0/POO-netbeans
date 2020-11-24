/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

/**
 *
 * @author elias
 */
public class Inserciones {
    
    public String insertEmpleados(){
        String sql = "INSERT INTO DDATEMP (CCVEEMP,CNOMBRE,CAPEUNO,CAPEDOS,CCURPEM,"
                + "DFECING,CNMCALL,CNUMEXT,CNUMINT,CCOLONI,CCODPOS,"
                + "NIDESTA,NIDMUNI,CSTATUS) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
        return sql;
    }
    public String ModificarEmpleado( ){
        String sql;
        sql = "UPDATE DDATEMP SET CNOMBRE=?,CAPEUNO=?"
                + ",CAPEDOS=?,CCURPEM=?,DFECING=?"
                +",CNMCALL=?,CNUMEXT=? ,CNUMINT=? ,CCOLONI=? ,CCODPOS=?"
                + ",NIDESTA=? ,NIDMUNI=? ,CSTATUS=?"
                + "WHERE CCVEEMP=?";
        return sql;
    }
     public String insertRegistroAsistencia(){
        String sql = "INSERT INTO PREGASI (CCVEEMP,DFECREG,CNUMBIO,CSTATUS) "
                + "VALUES(?,?,?,?)";
        return sql;
    }
    
}
