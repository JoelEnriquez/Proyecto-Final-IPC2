/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ArchivoEntrada;

import ConexionDB.Conexion;
import Encriptacion.Encriptar;
import EntidadesUsuario.Empleado;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;
import org.jdom2.Element;

/**
 *
 * @author joel
 */
public class SubidaCajero {
    
    private Connection conexion = Conexion.getConexion();
    private Encriptar encriptar;
    private LecturaXML lecturaXML;
    private List<Element> listaCajeros;

    public SubidaCajero(List<Element> listaCajeros, LecturaXML lecturaXML) {
        this.listaCajeros = listaCajeros;
        this.lecturaXML = lecturaXML;
        encriptar = new Encriptar();
    }
    
    public void ejecutarSubida() throws Exception{
        for (int i = 0; i < listaCajeros.size(); i++) {
            Element cajero = listaCajeros.get(i);
            
            String codigoCajero = cajero.getChildTextTrim("CODIGO");
            String nombreCajero =  cajero.getChildTextTrim("NOMBRE");
            String turnoCajero =  cajero.getChildTextTrim("TURNO");
            String noDPICajero =  cajero.getChildTextTrim("DPI");
            String direccionCajero =  cajero.getChildTextTrim("DIRECCION");
            String sexoCajero =  cajero.getChildTextTrim("SEXO");
            String passCajero =  encriptar.encriptar(cajero.getChildTextTrim("PASSWORD"));
            
            Empleado nuevoCajero = construirCajero(codigoCajero, nombreCajero, noDPICajero, direccionCajero, sexoCajero, passCajero, "Cajero",turnoCajero);
            insertarNuevoUsuario(nuevoCajero);
            insertarNuevoCajero(nuevoCajero);
        }   
    }
    
    private Empleado construirCajero(String codigoCajero, String nombreCajero, String noDPICajero, String direccionCajero,String sexoCajero,String passCajero,String tipoEmpleado,String turnoCajero){
        String codigoTurno = "";
        
        if (turnoCajero.equals("MATUTINO")) {
            codigoTurno = "1";
        }
        else if (turnoCajero.equals("VESPERTINO")) {
            codigoTurno = "2";
        }
        
        return new Empleado(codigoCajero, nombreCajero, noDPICajero, direccionCajero, sexoCajero, passCajero, tipoEmpleado, codigoTurno);      
    }
    
    private void insertarNuevoUsuario(Empleado nuevoUsuario){
        String query  = "INSERT INTO USUARIO VALUES (?,?,?,?,?,?,?)";
        try (PreparedStatement ps = conexion.prepareStatement(query)){
            ps.setInt(1, Integer.parseInt(nuevoUsuario.getCodigo()));
            ps.setString(2, nuevoUsuario.getNombre());
            ps.setString(3, nuevoUsuario.getDPI());
            ps.setString(4, nuevoUsuario.getDireccion());
            ps.setString(5, nuevoUsuario.getSexo());
            ps.setString(6, nuevoUsuario.getPassword());
            ps.setString(7, nuevoUsuario.getTipoUsuario());
            
            ps.execute();
            
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
    }
    
    private void insertarNuevoCajero(Empleado nuevoCajero){
        String query  = "INSERT INTO EMPLEADO VALUES (?,?,?)";
        try (PreparedStatement ps = conexion.prepareStatement(query)){
            ps.setInt(1, Integer.parseInt(nuevoCajero.getCodigo()));
            ps.setString(2, nuevoCajero.getTipoUsuario());
            ps.setString(3, nuevoCajero.getCodigoTurno());
            
            ps.execute();
            
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
    }
}
