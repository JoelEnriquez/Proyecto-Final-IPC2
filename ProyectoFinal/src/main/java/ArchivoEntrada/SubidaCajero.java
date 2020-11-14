/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ArchivoEntrada;

import ConexionDB.Conexion;
import Encriptacion.Encriptar;
import java.sql.Connection;
import java.util.List;
import org.jdom2.Element;

/**
 *
 * @author joel
 */
public class SubidaCajero {
    
    private Connection conexion = Conexion.getConexion();
    private Encriptar encriptar;
    private List<Element> listaCajeros;

    public SubidaCajero(List<Element> listaCajeros) {
        this.listaCajeros = listaCajeros;
        encriptar = new Encriptar();
    }
}
