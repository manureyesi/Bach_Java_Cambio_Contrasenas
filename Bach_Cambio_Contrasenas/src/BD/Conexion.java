/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package BD;

import UTILES.Credenciales;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.log4j.Logger;

/**
 *
 * @author MANU
 */
public class Conexion {
    
    private static Logger log = Logger.getLogger(Conexion.class);
    
    private Connection con;
    
    public Conexion() {
        try{
            con = DriverManager.getConnection("jdbc:mysql://"+Credenciales.DIR+":"+Credenciales.PUERTODB+"/"+Credenciales.DB,Credenciales.USUARIO,Credenciales.CONTRASENA);
        }
        catch(SQLException ex){
            log.info("Error al crear conexion de DB");
        }
    }
    
    public Connection devoverConexion(){
        return this.con;
    }
    
    public void cerrarConexion() throws SQLException{
        this.con.close();
    }
}
