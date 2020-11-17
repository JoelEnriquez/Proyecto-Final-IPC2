/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ArchivoEntrada;

import Encriptacion.Encriptar;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.jdom2.input.SAXBuilder;
import org.jdom2.Document;
import org.jdom2.Element;
import org.jdom2.JDOMException;

/**
 *
 * @author joel
 */
public class LecturaXML {
    
    private String pathXML;
    private String rutaPathAbsoluto;
    private ArrayList<String> listaCorrectos;
    private ArrayList<String> listaFallos;

    public LecturaXML(String pathXML, String rutaPathAbsoluto) {
        this.pathXML = pathXML;
        this.rutaPathAbsoluto = rutaPathAbsoluto;
    }
    
    public void leerXML() throws IOException {
        SAXBuilder builder = new SAXBuilder();

        try {
            File archivoEntradaXML = new File(pathXML);
            Document documento = (Document) builder.build(archivoEntradaXML);
            Element root = documento.getRootElement();

            //Obtenemos las listas principales de datos
            List<Element> listaGerentes = root.getChildren("GERENTE");
            List<Element> listaCajeros = root.getChildren("CAJERO");
            List<Element> listaClientes = root.getChildren("CLIENTE");
            List<Element> listaTransacciones = root.getChildren("TRANSACCION");

            //Subida de Gerentes
            SubidaGerente subidaGerente = new SubidaGerente(listaGerentes,this);
            subidaGerente.ejecutarSubida();
            //Subida de Cajeros
            SubidaCajero subidaCajero = new SubidaCajero(listaCajeros,this);
            subidaCajero.ejecutarSubida();
            //Subida de Clientes y sus cuentas
            SubidaCliente subidaCliente = new SubidaCliente(listaClientes, rutaPathAbsoluto,this);
            subidaCliente.ejecutarSubida();
            //Subida de Transacciones
            SubidaTransaccion subidaTransaccion = new SubidaTransaccion(listaTransacciones,this);
            subidaTransaccion.ejecutarSubida();

        } catch (JDOMException e) {
            throw new IOException("No se ha encontrado el archivo xml");
            
        } catch (Exception ex) {
            ex.printStackTrace(System.out);
        }
    }

    public ArrayList<String> getListaCorrectos() {
        return listaCorrectos;
    }

    public void setListaCorrectos(ArrayList<String> listaCorrectos) {
        this.listaCorrectos = listaCorrectos;
    }

    public ArrayList<String> getListaFallos() {
        return listaFallos;
    }

    public void setListaFallos(ArrayList<String> listaFallos) {
        this.listaFallos = listaFallos;
    }
    
    public void agregarError(String error){
        listaFallos.add(error);
    }
    
    public void agregarCorrecto(String correcto){
        listaCorrectos.add(correcto);
    }
}
