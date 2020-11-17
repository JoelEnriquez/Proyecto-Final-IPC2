/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EntidadesReporte;

import EntidadesBanco.Cuenta;
import EntidadesBanco.Transaccion;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author joel
 */
public class CuentaDTO extends Cuenta{
    
    private List<Transaccion> listadoTransacciones;
    
    public CuentaDTO(List<Transaccion> listadoTransacciones, String codigo){
        super(codigo);
        this.listadoTransacciones = listadoTransacciones;
    }

    public List<Transaccion> getListadoTransacciones() {
        return listadoTransacciones;
    }

    public void setListadoTransacciones(ArrayList<Transaccion> listadoTransacciones) {
        this.listadoTransacciones = listadoTransacciones;
    }
    
    
    
}
