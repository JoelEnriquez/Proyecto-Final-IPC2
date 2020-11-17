/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ModelosCuenta;

import ConexionDB.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 *
 * @author joel
 */
public class CuentaModel {
    Connection conexion = Conexion.getConexion();
    
    public boolean existenciaCuenta(int codigoCuenta){      
        String query = "SELECT * FROM CUENTA WHERE codigo = ?";       
        try (PreparedStatement ps = conexion.prepareStatement(query)){
            ps.setInt(1, codigoCuenta);
            
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
    
    public int codigoDueñoCuenta(int codigoCuenta){
        String query = "SELECT codigo_cliente FROM CUENTA WHERE codigo = ?";       
        try (PreparedStatement ps = conexion.prepareStatement(query)){
            ps.setInt(1, codigoCuenta);
            
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
    
    
    public void acreditarMonto(double monto, int codigoCuenta){
        String query = "UPDATE CUENTA SET monto = ? WHERE codigo = ?";
        
        try (PreparedStatement ps = conexion.prepareStatement(query)){
            ps.setDouble(1, calcularDiferenciaCredito(monto, codigoCuenta));
            ps.setInt(2, codigoCuenta);
            ps.execute();
            
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
    }
    
    public void debitarMonto(double monto, int codigoCuenta){
        String query = "UPDATE CUENTA SET monto = ? WHERE codigo = ?";
        try (PreparedStatement ps = conexion.prepareStatement(query)){
            ps.setDouble(1, calcularDiferenciaDebito(monto, codigoCuenta));
            ps.setInt(2, codigoCuenta);
            ps.execute();
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
    }
    
    
    private double calcularDiferenciaCredito(double monto, int codigoCuenta){
        String query = "SELECT monto FROM CUENTA WHERE codigo = ?";
        try (PreparedStatement ps = conexion.prepareStatement(query)){
            ps.setInt(1, codigoCuenta);
            
            try(ResultSet rs = ps.executeQuery()){
                if (rs.next()) {
                    return rs.getDouble(1)+monto;
                }
            }
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return 0;
    }
    
    private double calcularDiferenciaDebito(double monto, int codigoCuenta){
        String query = "SELECT monto FROM CUENTA WHERE codigo = ?";
        try (PreparedStatement ps = conexion.prepareStatement(query)){
            ps.setInt(1, codigoCuenta);
            
            try(ResultSet rs = ps.executeQuery()){
                if (rs.next()) {
                    return rs.getDouble(1)-monto;
                }
            }
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return 0;
    }
    
    public double devolverMontoActual(int codigoCuenta){
        String query = "SELECT monto FROM CUENTA WHERE codigo = ?";
        try (PreparedStatement ps = conexion.prepareStatement(query)){
            ps.setInt(1, codigoCuenta);
            
            try(ResultSet rs = ps.executeQuery()){
                if (rs.next()) {
                    return rs.getDouble(1);
                }
            }
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
        return 0;
    }
    
    
}
