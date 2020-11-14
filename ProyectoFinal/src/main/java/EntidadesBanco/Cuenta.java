/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EntidadesBanco;

import java.sql.Date;

/**
 *
 * @author joel
 */
public class Cuenta {
    
    private String codigo;
    private Date fechaCreacion;
    private double monto;
    private String codigoCliente;

    /**
     * Constructor entrada XML
     * @param codigo
     * @param fechaCreacion
     * @param monto
     * @param codigoCliente 
     */
    public Cuenta(String codigo, Date fechaCreacion, double monto, String codigoCliente) {
        this.codigo = codigo;
        this.fechaCreacion = fechaCreacion;
        this.monto = monto;
        this.codigoCliente = codigoCliente;
    }

    /**
     * Constructor creacion de cuenta.
     * @param fechaCreacion
     * @param monto
     * @param codigoCliente 
     */
    public Cuenta(Date fechaCreacion, double monto, String codigoCliente) {
        this.fechaCreacion = fechaCreacion;
        this.monto = monto;
        this.codigoCliente = codigoCliente;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public Date getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public String getCodigoCliente() {
        return codigoCliente;
    }

    public void setCodigoCliente(String codigoCliente) {
        this.codigoCliente = codigoCliente;
    }
    
    
    
    
}
