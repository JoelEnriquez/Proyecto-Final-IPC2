/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package EntidadesUsuario;

/**
 *
 * @author joel
 */
public class Empleado extends Usuario{
    
    private String codigoTurno;

    public Empleado(String codigo, String nombre, String DPI, String direccion, String sexo, String password, String tipoUsuario, String codigoTurno) {
        super(codigo, nombre, DPI, direccion, sexo, password, tipoUsuario);
        this.codigoTurno = codigoTurno;
    }    
    
    public Empleado(){
        
    }

    public String getCodigoTurno() {
        return codigoTurno;
    }

    public void setCodigoTurno(String codigoTurno) {
        this.codigoTurno = codigoTurno;
    }
    
    
    
    
}
