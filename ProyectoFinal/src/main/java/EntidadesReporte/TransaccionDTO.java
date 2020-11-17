/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EntidadesReporte;

import EntidadesBanco.Transaccion;
import java.sql.Date;
import java.sql.Time;

/**
 *
 * @author joel
 */
public class TransaccionDTO extends Transaccion{
    
    private double montoReporte;
    
    public TransaccionDTO(String codigo, Date fecha, Time hora, String tipo, double monto, double dineroActualCuenta, String codigoCuenta, String codigoCajero,double montoReporte) {
        super(codigo, fecha, hora, tipo, monto, dineroActualCuenta, codigoCuenta, codigoCajero);
        this.montoReporte =montoReporte;
    }

    public double getMontoReporte() {
        return montoReporte;
    }

    public void setMontoReporte(double montoReporte) {
        this.montoReporte = montoReporte;
    }
    
    
    
    
    
}
