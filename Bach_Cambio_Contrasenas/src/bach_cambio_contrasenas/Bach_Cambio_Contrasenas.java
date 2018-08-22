/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bach_cambio_contrasenas;

import com.jcraft.jsch.JSchException;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

/**
 *
 * @author ManuReyesI
 */
public class Bach_Cambio_Contrasenas {
    
    private static Logger log = Logger.getLogger(Bach_Cambio_Contrasenas.class);
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args){
        
        // Carga el archivo de configuracion de log4J
        PropertyConfigurator.configure("log4j.properties"); 
        
        log.info("Iniciando Bach");
        
        log.info("Consultando DB");
        //Preparando Consultas
        BD.Consultas con = new BD.Consultas();
        
        try{
        
            ResultSet rs = con.SelectPreparado("Servers", "1");
            
            while(rs.next()){
            
                //Crear conexion por SSH
                SSH.SSHConnector ssh = new SSH.SSHConnector();
                
                String contrasena = "";
                
                
                for(int i = 0; i < 15 ; i++){
                    contrasena += (char)((int)(Math.random() * 93) + 48);
                }
                
                try{
                    
                    //Preparar conversion
                    UTILES.Encriptar en = new UTILES.Encriptar();
                    
                    //Conectarse a Servidor por SSH
                    ssh.connect(rs.getString("usuario"), en.hexToString(rs.getString("contrasena")), rs.getString("ip"), UTILES.Credenciales.PUERTOSSH);
                    
                    log.info("Entrando en servidor "+ rs.getString("ip"));
                    
                    ssh.executeCommand("echo '"+ rs.getString("usuario") +":"+ contrasena +"' | chpasswd");
                    
                    log.info("Cambiada contraseña en servidor "+ rs.getString("ip"));
                    
                    //Desconectandose de Consola
                    ssh.disconnect();
                    
                    //Guardar contraseña en DB
                    con.UpdatePreparado("Servers", "contrasena = '"+en.stringToHex(contrasena)+"'", "ip = '"+ rs.getString("ip") +"'");
                    
                }
                catch(JSchException ex){
                    log.error("Error inesperado al conectar por SSH");
                    log.error(ex.getMessage());
                }
                catch(IllegalAccessException ex){
                    log.error("Error de acceso por SSH");
                    log.error(ex.getMessage());
                } catch (IOException ex) {
                    log.error("Error al ejecutar comando");
                    log.error(ex.getMessage());
                }
            
            }
            
        }
        catch(SQLException ex){
            log.error("Error al consultar IP de servidores");
        }
        
    }
    
}
