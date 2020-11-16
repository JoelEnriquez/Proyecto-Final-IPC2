/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EntidadesReporte;

import EntidadesBanco.SolicitudAsociacion;
import java.sql.Date;

/**
 *
 * @author joel
 */
public class SolicitudAsoYSolicitante extends SolicitudAsociacion {
    
    private String nombre;
    private String DPI;
    
    public SolicitudAsoYSolicitante(String codigo, String estado, Date fechaSolicitud, String codigoCuenta, String codigoCliente, String nombre, String DPI) {
        super(codigo, estado, fechaSolicitud, codigoCuenta, codigoCliente);
        this.nombre = nombre;
        this.DPI = DPI;
    }

    public SolicitudAsoYSolicitante(String estado, Date fechaSolicitud, String codigoCuenta, String codigoCliente, String nombre, String DPI) {
        super(estado, fechaSolicitud, codigoCuenta, codigoCliente);
        this.nombre = nombre;
        this.DPI = DPI;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDPI() {
        return DPI;
    }

    public void setDPI(String DPI) {
        this.DPI = DPI;
    }
    
    
    
    
    
}
