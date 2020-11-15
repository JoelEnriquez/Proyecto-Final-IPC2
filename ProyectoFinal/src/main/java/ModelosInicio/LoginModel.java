/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModelosInicio;

import ConexionDB.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author joel
 */
public class LoginModel {
    
    private Connection conexion = Conexion.getConexion();
    
    public boolean comprobarCredencialesValidas(int codigo, String password){
        String query = "SELECT COUNT(*) FROM USUARIO WHERE codigo=? AND password = ?";
        
        try(PreparedStatement ps = conexion.prepareStatement(query)) {
            ps.setInt(1, codigo);
            ps.setString(2, password);
            
            try(ResultSet rs = ps.executeQuery()){
                if (rs.next()) {
                    if (rs.getInt(1)==1) {
                        return true;
                    }
                }
            }
        } catch (Exception e) {
        }
        return false;
    }
    
    
    public String devolverTipoUsuario(int codigo, String password){
        String query = "SELECT tipo_usuario FROM USUARIO WHERE codigo=? AND password = ?";
        try(PreparedStatement ps = conexion.prepareStatement(query)) {
            ps.setInt(1, codigo);
            ps.setString(2, password);
            
            try(ResultSet rs = ps.executeQuery()){
                if (rs.next()) {
                    return rs.getString(1);
                }
            }
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return "";
    }
    
}
