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
public class AsociacionCuenta {
    
    protected String codigoCuenta;
    protected String codigoCliente;

    public AsociacionCuenta(String codigoCuenta, String codigoCliente) {
        this.codigoCuenta = codigoCuenta;
        this.codigoCliente = codigoCliente;
    }

    public String getCodigoCuenta() {
        return codigoCuenta;
    }

    public void setCodigoCuenta(String codigoCuenta) {
        this.codigoCuenta = codigoCuenta;
    }

    public String getCodigoCliente() {
        return codigoCliente;
    }

    public void setCodigoCliente(String codigoCliente) {
        this.codigoCliente = codigoCliente;
    }
    
    
}
