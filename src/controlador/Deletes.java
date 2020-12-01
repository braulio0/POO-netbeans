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
public class Deletes {
    public String DeteleEmpleados() {
            String sql = "DELETE  "
                + " FROM ddatemp "
                + " WHERE ccveemp = ?";
        return sql;    }
}
