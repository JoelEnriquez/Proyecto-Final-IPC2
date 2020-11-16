/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModelosCliente;

import ConexionDB.Conexion;
import EntidadesBanco.SolicitudAsociacion;
import EntidadesReporte.SolicitudAsoYSolicitante;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

/**
 *
 * @author joel
 */
public class AsociacionModel {
    
    private final String SOLICITUD_ASOCIACION = "SELECT SA.* FROM SOLICITUD_ASOCIACION SA";
    private final String INSERTAR_SOLICITUD = "INSERT INTO SOLICITUD_ASOCIACION (estado,fecha_solicitud, codigo_cuenta, codigo_cliente) VALUES (?,?,?,?)";
    private final String SOLICITUD_CURSO = SOLICITUD_ASOCIACION + " WHERE SA.estado='En espera' AND SA.codigo_cuenta = ? AND SA.codigo_cliente=? ORDER BY SA.codigo DESC";
    private final String SOLICITUDES_PENDIENTES = "SELECT SA.*,U.nombre,U.DPI FROM SOLICITUD_ASOCIACION SA INNER JOIN USUARIO U ON SA.codigo_cliente = U.codigo INNER JOIN CUENTA C ON SA.codigo_cuenta = C.codigo WHERE C.codigo_cliente = ? AND estado = 'En espera'";
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
    
    public void realizarSolicitud(SolicitudAsociacion solicitud){
        String query = INSERTAR_SOLICITUD;
        
        try(PreparedStatement ps = conexion.prepareStatement(query)) {
            ps.setString(1, solicitud.getEstado());
            ps.setDate(2, solicitud.getFechaSolicitud());
            ps.setInt(3, Integer.parseInt(solicitud.getCodigoCuenta()));
            ps.setInt(4, Integer.parseInt(solicitud.getCodigoCliente()));
            
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
    
    public ArrayList<SolicitudAsoYSolicitante> solicitudesPendientes(int codigoCliente){
        String query = SOLICITUDES_PENDIENTES;
        ArrayList<SolicitudAsoYSolicitante> listaSolicitudes = new ArrayList<>();
        
        try (PreparedStatement ps = conexion.prepareStatement(query)){
            ps.setInt(1, codigoCliente);
            
            try(ResultSet rs = ps.executeQuery()){
                while (rs.next()) {
                    listaSolicitudes.add(new SolicitudAsoYSolicitante(
                            String.valueOf(rs.getInt(1)),
                            rs.getString(2),
                            rs.getDate(3), 
                            String.valueOf(rs.getInt(4)),
                            String.valueOf(rs.getInt(5)),
                            rs.getString(6),
                            rs.getString(7)));
                }
            }
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return listaSolicitudes;
    }
    
    /**
     * Actualizamos los datos en base al codigo de la solicitud
     * @param nuevoEstado
     * @param codigoSolicitud 
     */
    public void actualizarSolicitud(String nuevoEstado, int codigoSolicitud){
        String query = "UPDATE SOLICITUD_ASOCIACION SET estado = ? WHERE codigo = ?";
        
        try (PreparedStatement ps = conexion.prepareStatement(query)){
            ps.setString(1, nuevoEstado);
            ps.setInt(2, codigoSolicitud);
            
            ps.execute();
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
    }
    
    public void crearAsociacion(int codigoCuenta, int codigoCliente){
        String query  = "INSERT INTO ASOCIACION_CUENTA VALUES(?,?)";
        try (PreparedStatement ps = conexion.prepareStatement(query)){
            ps.setInt(1, codigoCuenta);
            ps.setInt(2, codigoCliente);
            
            ps.execute();
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
    }
}
