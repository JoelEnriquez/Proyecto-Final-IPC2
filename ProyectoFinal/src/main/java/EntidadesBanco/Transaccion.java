/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EntidadesBanco;

import java.sql.Date;
import java.sql.Time;

/**
 *
 * @author joel
 */
public class Transaccion {
    
    private String codigo;
    private Date fecha;
    private Time hora;
    private String tipo;
    private double monto;
    private String codigoCuenta;
    private String codigoCajero;

    /**
     * Constructor para el archivo XML de entrada
     * @param codigo
     * @param fecha
     * @param hora
     * @param tipo
     * @param monto
     * @param codigoCuenta
     * @param codigoCajero 
     */
    public Transaccion(String codigo, Date fecha, Time hora, String tipo, double monto, String codigoCuenta, String codigoCajero) {
        this.codigo = codigo;
        this.fecha = fecha;
        this.hora = hora;
        this.tipo = tipo;
        this.monto = monto;
        this.codigoCuenta = codigoCuenta;
        this.codigoCajero = codigoCajero;
    }

    /**
     * Creacion normal, para las operaciones normales, ya sea credito o debito
     * @param fecha
     * @param hora
     * @param tipo
     * @param monto
     * @param codigoCuenta
     * @param codigoCajero 
     */
    public Transaccion(Date fecha, Time hora, String tipo, double monto, String codigoCuenta, String codigoCajero) {
        this.fecha = fecha;
        this.hora = hora;
        this.tipo = tipo;
        this.monto = monto;
        this.codigoCuenta = codigoCuenta;
        this.codigoCajero = codigoCajero;
    }


    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Time getHora() {
        return hora;
    }

    public void setHora(Time hora) {
        this.hora = hora;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public String getCodigoCuenta() {
        return codigoCuenta;
    }

    public void setCodigoCuenta(String codigoCuenta) {
        this.codigoCuenta = codigoCuenta;
    }

    public String getCodigoCajero() {
        return codigoCajero;
    }

    public void setCodigoCajero(String codigoCajero) {
        this.codigoCajero = codigoCajero;
    }
    
    
    
}
