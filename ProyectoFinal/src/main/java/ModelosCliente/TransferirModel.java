/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModelosCliente;

import ConexionDB.Conexion;
import EntidadesBanco.Cuenta;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author joel
 */
public class TransferirModel {
    
    private final String OBTENER_CUENTAS = "SELECT * FROM CUENTA WHERE codigo_cliente = ?";
    private final String OBTENER_CODIGO_CUENTAS_ASOCIADAS = "SELECT codigo_cuenta FROM ASOCIACION_CUENTA WHERE codigo_cliente = ?";
    Connection conexion = Conexion.getConexion();
    
    public ArrayList<Cuenta>obtenerCuentas(int codigoDueño){
        String query = OBTENER_CUENTAS;
        ArrayList<Cuenta>listCuentas = new ArrayList<>();
        
        try(PreparedStatement ps = conexion.prepareStatement(query)) {
            ps.setInt(1, codigoDueño);
            
            try(ResultSet rs = ps.executeQuery()){
                while (rs.next()) {
                    listCuentas.add(new Cuenta(
                            String.valueOf(rs.getInt(1)),
                            rs.getDate(2),
                            rs.getDouble(3),
                            String.valueOf(rs.getInt(4))));
                }
            }
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return listCuentas;
    }
    
    /**
     * Obtiene el codigo de todas las cuentas asociadas
     * @param codigoCliente
     * @return 
     */
    public ArrayList<Integer> codigosCuentasAsociadas(int codigoCliente){
        String query = OBTENER_CODIGO_CUENTAS_ASOCIADAS;
        ArrayList<Integer>listaCodigos = new ArrayList<>();
        
        try(PreparedStatement ps = conexion.prepareStatement(query)) {
            ps.setInt(1, codigoCliente);
            
            try(ResultSet rs = ps.executeQuery()){
                while (rs.next()) {
                    listaCodigos.add(rs.getInt(1));
                }
            }
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return listaCodigos;
    }
            
            
            
}
