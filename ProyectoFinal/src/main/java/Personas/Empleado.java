/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Personas;

/**
 *
 * @author joel
 */
public class Empleado extends Usuario{
    
    private String tipoEmpleado;
    private String codigoTurno;

    public Empleado(String codigo, String nombre, String DPI, String direccion, String sexo, String password, String tipoUsuario, String tipoEmpleado, String codigoTurno) {
        super(codigo, nombre, DPI, direccion, sexo, password, tipoUsuario);
        this.tipoEmpleado = tipoEmpleado;
        this.codigoTurno = codigoTurno;
    }
    
    public Empleado(){
        
    }

    public String getTipoEmpleado() {
        return tipoEmpleado;
    }

    public void setTipoEmpleado(String tipoEmpleado) {
        this.tipoEmpleado = tipoEmpleado;
    }

    public String getCodigoTurno() {
        return codigoTurno;
    }

    public void setCodigoTurno(String codigoTurno) {
        this.codigoTurno = codigoTurno;
    }
    
    
    
    
}
