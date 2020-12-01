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
public class Consultas {
   
    public String consultaEstados(){
        String sql = "SELECT CONCAT(NIDESTA,' ',CNOMEST) "
                + "FROM CESTADS "
                + "ORDER BY NIDESTA";
        return sql;
    }
    
    public String consultaMunicipios(int idEdo){
        String sql = "SELECT CONCAT(NIDMUNI,' ',CNOMMUN) "
                + " FROM CMUNICS "
                + " WHERE NIDESTA = " + idEdo 
                + " ORDER BY NIDMUNI";
        return sql;
    }
    

    public String consultaEmpleados(String idEmp) {
            String sql = "SELECT * "
                + " FROM ddatemp "
                + " WHERE ccveemp = '" + idEmp+"'";
        return sql;    }
    
    
    
    public String consultaHorario(int idhorario){
            String sql = "Select * from chorars where nidhora ="+idhorario ;
              
    return sql;
    }
    
    public String consultaDiaNhabil(){
        String sql = "select concat(dfechnl) from mdiasnl ;";
        return sql;
    }
    public String consutaStatusNH( String fecha){
        String sql = "select * from mdiasnl where dfechnl = '"+fecha+"'";
       
        return sql;
        
    }
    public String consutaStatusHN( String fecha){
        String sql = "select cstatus from mdiasnl where dfechnl = '"+fecha+"'";
       
        return sql;
        
    }
    
    public String consultaDescripcion(String fecha)
    {
        String sql = " select cmotivo  from mdiasnl where dfechnl = '"+fecha+"'";
        return sql;
    }
    
    public String consultaCatalogoJudtificantes() {
        String sql = "SELECT CONCAT(NIDTPJU, ' ', CDESJUS, ' | ', 'Activo') "
                + "FROM CJUSASI "
                +"Where CSTATUS = 'A'"
                + "ORDER BY NIDTPJU ASC";
        return sql;
    }
    
    public String consultaCatalogoJudtificantesInactivos() {
        String sql = "SELECT CONCAT(NIDTPJU, ' ', CDESJUS, ' | ', 'Inactivo') "
                + "FROM CJUSASI "
                +"Where CSTATUS = 'I'"
                + "ORDER BY NIDTPJU ASC";
        return sql;
    }
    
    



}
