/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ControladoresInicio;

import ArchivoEntrada.LecturaXML;
import ConexionDB.Conexion;
import ModelosInicio.VerificarDatos;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.sql.Connection;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

/**
 *
 * @author joel
 */
@MultipartConfig//(maxFileSize = 16177215) //Maximo = 16mb
@WebServlet(name = "CargaArchivo", urlPatterns = {"/CargaArchivo"})
public class CargaArchivo extends HttpServlet {

   

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //GuardarXML en servidor
        Part filePartXML = request.getPart("archivoXML");
        String rutaXML = Paths.get(filePartXML.getSubmittedFileName()).getFileName().toString();    
        saveFiles(filePartXML, rutaXML);
        
        //GuardarArchivos en server
        ArrayList<Part> filePartArchivosDB = (ArrayList<Part>) request.getParts();       
        for (Part part : filePartArchivosDB) {
            String rutaArchivo = Paths.get(part.getSubmittedFileName()).getFileName().toString();
            saveFiles(part, rutaArchivo);
        }
        
        //Se ingresa el path donde se van a guardar los archivos
        String pathAbsolut = "/tmp/";
        String pathAbsoluteXML = pathAbsolut+rutaXML;  
        LecturaXML lecturaXML = new LecturaXML(pathAbsoluteXML, pathAbsolut);
        lecturaXML.leerXML();
        
        //Comprobar carga de datos
        VerificarDatos verificarDatos = new VerificarDatos();
        int auxVer = verificarDatos.verificarDatos();
        if (auxVer==0) {
            request.setAttribute("Sucess", false);
        }
        else{
            request.setAttribute("Sucess", true);
        }
        RequestDispatcher requestD = request.getRequestDispatcher("Inicio/CargaDatos.jsp");
        requestD.forward(request, response);
    }

    private void saveFiles(Part filePart,String rutaArchivo){
        File rutaInicial = new File("/tmp");
        File file = new File(rutaInicial,rutaArchivo);
        
        try(InputStream inputS = filePart.getInputStream()) {
            Files.copy(inputS, file.toPath(), StandardCopyOption.REPLACE_EXISTING);
            
        } catch (Exception e) {
            e.printStackTrace(System.out);
        }
    }

}
