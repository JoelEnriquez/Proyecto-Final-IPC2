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
public class SolicitudAsociacion extends AsociacionCuenta {
 
    private String codigo;
    private String estado;
    private Date fechaSolicitud;

    public SolicitudAsociacion(String codigo, String estado,Date fechaSolicitud, String codigoCuenta, String codigoCliente) {
        super(codigoCuenta, codigoCliente);
        this.codigo = codigo;
        this.estado = estado;
        this.fechaSolicitud = fechaSolicitud;
    }

    public SolicitudAsociacion(String estado, Date fechaSolicitud, String codigoCuenta, String codigoCliente) {
        super(codigoCuenta, codigoCliente);
        this.estado = estado;
        this.fechaSolicitud = fechaSolicitud;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public Date getFechaSolicitud() {
        return fechaSolicitud;
    }

    public void setFechaSolicitud(Date fechaSolicitud) {
        this.fechaSolicitud = fechaSolicitud;
    }
    
    
    
}
