/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SubidaDB;

import ConexionDB.Conexion;
import EntidadesUsuario.Usuario;
import java.sql.Connection;
import java.sql.PreparedStatement;

/**
 *
 * @author joel
 */
public class GuardarUsuario {
    
    private final String insertarUsuario = "INSERT INTO USUARIO (nombre, DPI, direccion, sexo, password, tipo_usuario) VALUES (?,?,?,?,?,?)";
    private final String insertarUsuarioXML = "INSERT INTO USUARIO VALUES (?,?,?,?,?,?,?)";
    private Connection conexion = Conexion.getConexion();
    
    public void guardarUsuario(Usuario usuario){
        
        String query = insertarUsuario;
        
        try (PreparedStatement ps = conexion.prepareStatement(query)){
            ps.setString(1, usuario.getNombre());
            ps.setString(2, usuario.getDPI());
            ps.setString(3, usuario.getDireccion());
            ps.setString(4, usuario.getSexo());
            ps.setString(5, usuario.getPassword());
            ps.setString(6, usuario.getTipoUsuario());
            
            ps.execute();
            
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
    }
    
    
    public void guardarUsuarioXML(Usuario usuario){
        
        String query = insertarUsuarioXML;
        
        try (PreparedStatement ps = conexion.prepareStatement(query)){
            ps.setInt(1, Integer.parseInt(usuario.getCodigo()));
            ps.setString(2, usuario.getNombre());
            ps.setString(3, usuario.getDPI());
            ps.setString(4, usuario.getDireccion());
            ps.setString(5, usuario.getSexo());
            ps.setString(6, usuario.getPassword());
            ps.setString(7, usuario.getTipoUsuario());
            
            ps.execute();
            
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
    }
}
