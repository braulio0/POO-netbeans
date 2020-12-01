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
    public String insertHorario(){
        String sql;
        sql = "INSERT INTO chorars (NIDHORA,CDESCHR,CSTATUS)"
                + "VALUES(?,?,?);";
        return sql;
    }
    public String insertHorarioHoras(){
        String sql;
        sql = "INSERT INTO DDETHOR (NIDHORA,NDIASEM,CHORENT,CHORSAL,CSTATUS)"
                + "VALUES(?,?,?,?,?);";
        return sql;
    }
     public String insertRegistroAsistencia(){
        String sql = "INSERT INTO PREGASI (CCVEEMP,DFECREG,CNUMBIO,CSTATUS) "
                + "VALUES(?,?,?,?)";
        return sql;
    }
    public String insertAsignarHorarios(){
         String sql = "INSERT INTO DHREMPS (NIDHORA,CCVEEMP,CSTATUS) " 
                 + "VALUES(?,?,?)";
         return sql;
     }
    
    public String ModificarDiaNH( ){
        String sql;
        sql = "UPDATE MDIASNL SET CMOTIVO=?,CSTATUS=?"
              + "WHERE DFECHNL=?";
        return sql;
    }
}
