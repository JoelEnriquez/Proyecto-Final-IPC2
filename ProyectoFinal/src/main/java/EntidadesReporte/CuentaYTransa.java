/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EntidadesReporte;

import EntidadesBanco.Cuenta;
import java.sql.Date;

/**
 *
 * @author joel
 */
public class CuentaYTransa extends Cuenta{
    private double cantidadMomentanea;
    private String tipoTransaccion;
    private Date fechaTransaccion;

    public CuentaYTransa(double monto, String tipoTransaccion, Date fechaTransaccion, String codigo, Date fechaCreacion, double cantidadMomentanea, String codigoCliente) {
        super(codigo, fechaCreacion, monto, codigoCliente);
        this.cantidadMomentanea = monto;
        this.tipoTransaccion = tipoTransaccion;
        this.fechaTransaccion = fechaTransaccion;
    }

    public double getCantidadMomentanea() {
        return cantidadMomentanea;
    }

    public void setCantidadMomentanea(double cantidadMomentanea) {
        this.cantidadMomentanea = cantidadMomentanea;
    }

    public String getTipoTransaccion() {
        return tipoTransaccion;
    }

    public void setTipoTransaccion(String tipoTransaccion) {
        this.tipoTransaccion = tipoTransaccion;
    }

    public Date getFechaTransaccion() {
        return fechaTransaccion;
    }

    public void setFechaTransaccion(Date fechaTransaccion) {
        this.fechaTransaccion = fechaTransaccion;
    }
    
    
    
}
