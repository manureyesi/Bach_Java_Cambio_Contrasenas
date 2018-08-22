/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BD;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author MANU
 */
public class Consultas {
    
    public ResultSet SelectPreparado(String tabla, String condicion) throws SQLException{
         
        BD.Conexion con = new BD.Conexion();

        Statement statement = con.devoverConexion().createStatement();

        ResultSet rs = statement.executeQuery ("select * FROM "+tabla+" WHERE "+condicion);
        
        return rs;
    }
    
    public void UpdatePreparado(String tabla, String campos, String condicion) throws SQLException{
        
        BD.Conexion con = new BD.Conexion();
        
        Statement statement = con.devoverConexion().createStatement();
        
        statement.executeUpdate("UPDATE "+ tabla +" SET "+campos+"  WHERE "+condicion);
        
    }
    
}
