/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ArchivoEntrada;

import Encriptacion.Encriptar;
import java.io.File;
import java.io.IOException;
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
    private Encriptar encriptacion;

    public LecturaXML(String pathXML, String rutaPathAbsoluto) {
        this.pathXML = pathXML;
        this.rutaPathAbsoluto = rutaPathAbsoluto;
        encriptacion = new Encriptar();
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
            SubidaGerente subidaGerente = new SubidaGerente(listaGerentes);
            subidaGerente.ejecutarSubida();
            //Subida de Cajeros
            SubidaCajero subidaCajero = new SubidaCajero(listaCajeros);
            subidaCajero.ejecutarSubida();
            //Subida de Clientes y sus cuentas
            SubidaCliente subidaCliente = new SubidaCliente(listaClientes, rutaPathAbsoluto);
            subidaCliente.ejecutarSubida();
            //Subida de Transacciones
            SubidaTransaccion subidaTransaccion = new SubidaTransaccion(listaTransacciones);
            subidaTransaccion.ejecutarSubida();

        } catch (JDOMException e) {
            throw new IOException("No se ha encontrado el archivo xml");
            
        } catch (Exception ex) {
            ex.printStackTrace(System.out);
        }
    }
}
