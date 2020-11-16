/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

/**
 *
 * @author DANMAR
 */
public class Conexion {
    Connection cn;
    
    public Connection conexion(){
        try{
            Class.forName("org.postgresql.Driver");
            cn = DriverManager.getConnection("jdbc:postgresql://localhost:5432/dbregas","postgres","");
            System.out.println("Conectado");
        }catch(Exception e){
            System.out.println(e.getMessage());
        }return cn;
    }
    
    Statement createStatement(){
        throw new UnsupportedOperationException("No Conectado");
    }
    
    public void cierraConexion(){
        try{
            cn.close();
            System.out.println("Conexión cerrada");
        }catch(Exception e){
            System.out.println(e.getMessage());
        }
    }
}
