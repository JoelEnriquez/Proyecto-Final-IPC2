/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModelosCliente;

import ConexionDB.Conexion;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author joel
 */
public class AccesoDPI {
    
    private Connection conexion = Conexion.getConexion();
    
    
    public InputStream devolverDPIPorCodigo(int codigo){
        String query = "SELECT PDF_DPI FROM CLIENTE WHERE codigo_cliente = ?";
        
        try(PreparedStatement ps = conexion.prepareStatement(query)) {
            ps.setInt(1, codigo);
            
            try(ResultSet rs = ps.executeQuery()){
                if (rs.next()) {
                    return rs.getBlob(1).getBinaryStream();
                }
            }
            
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return null;
    }
}
