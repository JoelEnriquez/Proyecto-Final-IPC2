/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModelosCliente;

import ConexionDB.Conexion;
import ControladorCliente.SolicitarAsociacion;
import EntidadesBanco.SolicitudTransaccion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author joel
 */
public class AsociacionModel {
    
    private final String INSERTAR_SOLICITUD = "INSERT INTO SOLICITUD_ASOCIACION (estado, codigo_cuenta, codigo_cliente) VALUES (?,?,?)";
    private final String SOLICITUD_CURSO = "SELECT * FROM SOLICITUD_ASOCIACION WHERE estado='En espera' AND codigo_cuenta = ? AND codigo_cliente=? ORDER BY codigo DESC";
    Connection conexion = Conexion.getConexion();
    
    /**
     * Retorna si la cuenta en parametro, pertence a la persona
     * @param codigoCuenta
     * @param CodigoDueño
     * @return 
     */
    public boolean cuentasPropias(int codigoCuenta, int CodigoDueño){
        String query = "SELECT * FROM CUENTA WHERE codigo = ? AND codigo_cliente = ?";
        
        try(PreparedStatement ps = conexion.prepareStatement(query)){
            ps.setInt(1, codigoCuenta);
            ps.setInt(2, CodigoDueño);
            
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
    
    public boolean cuentaAsociadasPrev(int codigoCuenta, int codigoPropio){
        String query = "SELECT * FROM ASOCIACION_CUENTA WHERE codigo_cuenta = ? AND codigo_cliente = ?";
        
        try(PreparedStatement ps = conexion.prepareStatement(query)){
            ps.setInt(1, codigoCuenta);
            ps.setInt(2, codigoPropio);
            
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
    
    public int sobrepasoSolicitudes(int codigoCuenta, int codigoPropio){
        String query = "SELECT COUNT(*) FROM SOLICITUD_ASOCIACION WHERE codigo_cuenta = ? AND codigo_cliente = ?";
        
        try(PreparedStatement ps = conexion.prepareStatement(query)){
            ps.setInt(1, codigoCuenta);
            ps.setInt(2, codigoPropio);
            
            try(ResultSet rs = ps.executeQuery()){
                if (rs.next()) {
                   return rs.getInt(1);
                }
            }
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return 0;
    }
    
    public void realizarSolicitud(SolicitudTransaccion solicitud){
        String query = INSERTAR_SOLICITUD;
        
        try(PreparedStatement ps = conexion.prepareStatement(query)) {
            ps.setString(1, solicitud.getEstado());
            ps.setInt(2, Integer.parseInt(solicitud.getCodigoCuenta()));
            ps.setInt(3, Integer.parseInt(solicitud.getCodigoCliente()));
            
            ps.execute();
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
    }
    
    public boolean solicitudTransito(int codigoCuenta, int codigoCliente){
        String query = SOLICITUD_CURSO;
        
        try (PreparedStatement ps = conexion.prepareStatement(query)){
            ps.setInt(1, codigoCuenta);
            ps.setInt(2, codigoCliente);
            
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
