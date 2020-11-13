/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Personas;

import java.io.InputStream;
import java.sql.Date;

/**
 *
 * @author joel
 */
public class Cliente extends Usuario{
    
    private Date birth;
    private InputStream PDF_DPI;
    
    public Cliente(String codigo, String nombre, String DPI, String direccion, String sexo, String password, String tipoUsuario, Date birth, InputStream PDF_DPI) {
        super(codigo, nombre, DPI, direccion, sexo, password, tipoUsuario);
        this.birth = birth;
        this.PDF_DPI = PDF_DPI;
    }
    
    public Cliente(){

    }

    public Date getBirth() {
        return birth;
    }

    public void setBirth(Date birth) {
        this.birth = birth;
    }

    public InputStream getPDF_DPI() {
        return PDF_DPI;
    }

    public void setPDF_DPI(InputStream PDF_DPI) {
        this.PDF_DPI = PDF_DPI;
    }
    
    
    
}
