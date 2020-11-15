/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModelosUsuarios;

import ConexionDB.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * Permite obtener consultas comunes de los usuarios
 * @author joel
 */
public class UsuarioModel {
    
    Connection conexion = Conexion.getConexion();
    
    /**
     * Nos retorna el nombre del usuario por medio del codigo
     * @param codigo
     * @return 
     */
    public String devolverNombrePorCodigo(int codigo){
        String query = "SELECT nombre FROM USUARIO WHERE codigo = ?";
        
        try(PreparedStatement ps = conexion.prepareStatement(query)) {
            ps.setInt(1, codigo);
            
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
