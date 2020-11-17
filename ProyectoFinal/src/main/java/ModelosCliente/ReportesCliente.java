/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModelosCliente;

import ConexionDB.Conexion;
import EntidadesBanco.Cuenta;
import EntidadesBanco.SolicitudAsociacion;
import EntidadesBanco.Transaccion;
import EntidadesReporte.CuentaDTO;
import EntidadesReporte.CuentaYTransa;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author joel
 */
public class ReportesCliente {
    
    private final String HISTORIAL_ASOCIACION_REALIZADAS="SELECT SOAS.* FROM USUARIO US INNER JOIN CLIENTE CL ON US.codigo = CL.codigo_cliente INNER JOIN SOLICITUD_ASOCIACION SOAS ON CL.codigo_cliente = SOAS.codigo_cliente WHERE CL.codigo_cliente = ?";
    private final String HISTORIAL_ASOCIACION_RECIBIDAS="SELECT soas.* from USUARIO us inner join CLIENTE cl on us.codigo = cl.codigo_cliente INNER JOIN CUENTA cu on cl.codigo_cliente = cu.codigo_cliente INNER JOIN SOLICITUD_ASOCIACION soas on cu.codigo = soas.codigo_cuenta WHERE cl.codigo_cliente = ?";
    private final String CUENTA_MAS_MONTO_Y_TRANS = "SELECT cu.*, tr.monto AS monto_trans, tr.tipo, tr.fecha from USUARIO us INNER JOIN CLIENTE cl on us.codigo = cl.codigo_cliente INNER JOIN CUENTA cu on cl.codigo_cliente = cu.codigo_cliente INNER JOIN TRANSACCION tr on cu.codigo = tr.codigo_cuenta where us.codigo = ? AND tr.fecha between ? and curdate() ORDER BY cu.monto DESC";
    private final String LISTADO_TRANS_INTERVALO_TIME = "SELECT tr.*, cu.monto as dinero_actual from USUARIO us inner join CLIENTE cl on us.codigo = cl.codigo_cliente INNER JOIN CUENTA cu ON cl.codigo_cliente = cu.codigo_cliente inner join TRANSACCION tr on cu.codigo = tr.codigo_cuenta where cl.codigo_cliente = ? AND tr.fecha between ? and ?";
    
    private Connection conexion = Conexion.getConexion();
    
    public List<Cuenta>obtenerConTransacciones(int codigoCliente){
        String query = "SELECT codigo FROM CUENTA WHERE codigo_cliente = ?";
        List<Cuenta> cuentas = new ArrayList<>();
        
        try (PreparedStatement ps = conexion.prepareStatement(query)){
            ps.setInt(1, codigoCliente);
            
            try(ResultSet rs = ps.executeQuery()){
                while (rs.next()) {
                    int codigoCuenta = rs.getInt("codigo");
                    CuentaDTO cuentaDTO = new CuentaDTO(obtenerTransaccionesPorCuenta(codigoCuenta),
                            rs.getString("codigo"));
                    cuentas.add(cuentaDTO);
                }  
            }
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return cuentas;
    }
    
    public List<Transaccion> obtenerTransaccionesPorCuenta (int codigoCuenta){
        String query = "SELECT T.* FROM TRANSACCION T INNER JOIN CUENTA C ON T.codigo_cuenta=C.codigo "
                + "WHERE C.codigo = ? AND (T.fecha BETWEEN DATE_SUB(NOW(), interval 1 YEAR)"
                + " AND NOW()) ORDER BY T.monto DESC LIMIT 15";
        List<Transaccion> transacciones = new ArrayList<>();
        
        try (PreparedStatement ps = conexion.prepareStatement(query)){
            ps.setInt(1, codigoCuenta);
            
            try(ResultSet rs = ps.executeQuery()){
                transacciones = new ArrayList<>();
                
                while (rs.next()) {
                    Transaccion transaccion = new Transaccion(
                            String.valueOf(rs.getInt(1)),
                            rs.getDate(2),
                            rs.getTime(3),
                            rs.getString(4),
                            rs.getDouble(5),
                            rs.getDouble(6),
                            rs.getString(7),
                            rs.getString(8));
                    transacciones.add(transaccion);
                }
            }
            
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return transacciones;
    }
    
    public ArrayList<SolicitudAsociacion> solicitudesRecibidas(int codigoCliente){
        String query = HISTORIAL_ASOCIACION_RECIBIDAS;
        ArrayList<SolicitudAsociacion> solicitudesRecibidas = new ArrayList<>();
        
        try (PreparedStatement ps = conexion.prepareStatement(query)){
            ps.setInt(1, codigoCliente);
            
            try(ResultSet rs = ps.executeQuery()){
                while (rs.next()) {
                    solicitudesRecibidas.add(
                    new SolicitudAsociacion(rs.getString(1),rs.getString(2), rs.getDate(3), rs.getString(4), rs.getString(4)));
                }
            }
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return solicitudesRecibidas;
    }

    public ArrayList<SolicitudAsociacion> solicitudesEnviadas(int codigoCliente){
        String query = HISTORIAL_ASOCIACION_REALIZADAS;
        ArrayList<SolicitudAsociacion> solicitudesRecibidas = new ArrayList<>();
        
        try (PreparedStatement ps = conexion.prepareStatement(query)){
            ps.setInt(1, codigoCliente);
            
            try(ResultSet rs = ps.executeQuery()){
                while (rs.next()) {
                    solicitudesRecibidas.add(
                    new SolicitudAsociacion(rs.getString(1),rs.getString(2), rs.getDate(3), rs.getString(4), rs.getString(4)));
                }
            }
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return solicitudesRecibidas;
    }
    
    public ArrayList<CuentaYTransa> getCuentaMayorYTransacciones(int codigo, Date fechaInicio){
        String query = CUENTA_MAS_MONTO_Y_TRANS;
        ArrayList<CuentaYTransa> cuentaMayorYTrans = new ArrayList<>();
        
        try (PreparedStatement ps = conexion.prepareStatement(query)){
            ps.setInt(1, codigo);
            ps.setDate(2, fechaInicio);
            
            try(ResultSet rs = ps.executeQuery()){
                while (rs.next()) {
                    cuentaMayorYTrans.add(
                    new CuentaYTransa(rs.getDouble("monto"),
                            rs.getString("tipo"),
                            rs.getDate("fecha"),
                            rs.getString("codigo"),
                            rs.getDate("fecha_creacion"),
                            rs.getDouble("monto_trans"),
                            rs.getString("codigo_cliente")));
                }
            }
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return cuentaMayorYTrans;
    }
}
