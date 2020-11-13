/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ArchivoEntrada;

import ConexionDB.Conexion;
import Encriptacion.Encriptar;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.text.ParseException;
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
    private Connection conexion = Conexion.getConexion();
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

            //Datos que no dependen de la estructura de la DB
            List<Element> listaAdmins = root.getChildren("admin");
            List<Element> listaPacientes = root.getChildren("paciente");
            List<Element> listaConsultas = root.getChildren("consulta");

            CargaEntidadesInde entidadesIndepen = new CargaEntidadesInde(conexion, encriptacion,
                    listaAdmins, listaPacientes, listaConsultas);
            try {
                entidadesIndepen.ejecutarCarga();
            } catch (Exception ex) {
                ex.getMessage();
            }

            //Datos que dependen de la estructura de la DB
            //Agregamos doctores y citas
            List<Element> listaDoctores = root.getChildren("doctor");
            List<Element> listaCitas = root.getChildren("cita");
            CargaMedicosYEspecialidad medicoYAsigEspecialida
                    = new CargaMedicosYEspecialidad(conexion, encriptacion, listaDoctores);
            try {
                medicoYAsigEspecialida.ejecutarCarga();
            } catch (Exception ex) {
                ex.getMessage();
            }

            List<Element> listaReportes = root.getChildren("reporte");
            CargaReporteYCitas cargaRYC = new CargaReporteYCitas(conexion, listaReportes, listaCitas);
            try {
                cargaRYC.ejecutarCarga();
            } catch (ParseException ex) {
                ex.getMessage();
            }

            List<Element> listaLaboratoristas = root.getChildren("laboratorista");
            List<Element> listaExamenes = root.getChildren("examen");
            List<Element> listaResultados = root.getChildren("resultado");
            CargaLabDiasTrab cargaLE = new CargaLabDiasTrab(conexion, encriptacion,
                    listaLaboratoristas, listaExamenes, listaResultados,rutaPathAbsoluto);
            cargaLE.ejecutarCarga();
        } catch (JDOMException e) {
            throw new IOException("No se ha encontrado el archivo xml");
        } catch (Exception ex) {
            ex.getMessage();
        }
    }
    
}
