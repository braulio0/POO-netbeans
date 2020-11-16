/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

/**
 *
 * @author DANMAR
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
}
