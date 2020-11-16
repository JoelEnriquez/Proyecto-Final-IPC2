/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EntidadesBanco;

/**
 *
 * @author joel
 */
public class SolicitudTransaccion extends AsociacionCuenta {
 
    private String codigo;
    private String estado;

    public SolicitudTransaccion(String codigo, String estado, String codigoCuenta, String codigoCliente) {
        super(codigoCuenta, codigoCliente);
        this.codigo = codigo;
        this.estado = estado;
    }

    public SolicitudTransaccion(String estado, String codigoCuenta, String codigoCliente) {
        super(codigoCuenta, codigoCliente);
        this.estado = estado;
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
    
    
    
}
