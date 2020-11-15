/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ControladoresInicio;

import ModelosInicio.VerificarDatos;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author joel
 */
@WebServlet(name = "ComprobarDatos", urlPatterns = {"/ComprobarDatos"})
public class ComprobarDatos extends HttpServlet {

   
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        VerificarDatos verificar = new VerificarDatos();
        int resultAux = verificar.verificarDatos();
        if (resultAux==0) { //No hay datos y requiere de subida
            request.setAttribute("SolicitudDatos", 0);
        }
        else{
            request.setAttribute("SolicitudDatos", 1);
        }
        RequestDispatcher rd = request.getRequestDispatcher("index.jsp");
        rd.forward(request, response);
    }

    
   
}
