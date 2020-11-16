/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModelosCliente;

import ConexionDB.Conexion;
import EntidadesUsuario.Cliente;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author joel
 */
public class ClienteModel {
    
    Connection conexion = Conexion.getConexion();
    private final String CLIENTE_POR_CODIGO = "SELECT U.*,C.birth,C.PDF_DPI FROM USUARIO U INNER JOIN CLIENTE C ON U.codigo = C.codigo_cliente WHERE codigo=?";
    
    public Cliente retornarPorCodigo(int codigoCliente){
        String query = CLIENTE_POR_CODIGO;
        Cliente cliente = null;
        
        try (PreparedStatement ps = conexion.prepareStatement(query)){
            ps.setInt(1, codigoCliente);
            
            try(ResultSet rs = ps.executeQuery()){
                if(rs.next()) {
                    cliente = new Cliente(
                            String.valueOf(rs.getInt(1)),
                            rs.getString(2),
                            rs.getString(3),
                            rs.getString(4),
                            rs.getString(5),
                            rs.getString(6),
                            "Cliente",
                            rs.getDate(8),
                            rs.getBlob(9).getBinaryStream());
                    return cliente;
                }
            }
            
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return cliente;
    }
    
    public boolean existenciaCliente(int codigoCliente){
        String query = "SELECT codigo FROM USUARIO WHERE codigo = ?";
        
        try (PreparedStatement ps = conexion.prepareStatement(query)){
            ps.setInt(1, codigoCliente);
            
            try(ResultSet rs = ps.executeQuery()){
                if (rs.next()) {
                    return true;
                }
            }
            
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return false;
    }
}
