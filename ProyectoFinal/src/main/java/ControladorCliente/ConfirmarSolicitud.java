/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ControladorCliente;

import EntidadesBanco.SolicitudTransaccion;
import ModelosCliente.AsociacionModel;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author joel
 */
@WebServlet(name = "ConfirmarSolicitud", urlPatterns = {"/ConfirmarSolicitud"})
public class ConfirmarSolicitud extends HttpServlet {

    AsociacionModel asoModel = new AsociacionModel();
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (request.getSession().getAttribute("codigo") == null) {
            response.sendRedirect(request.getContextPath() + "/Inicio/Login.jsp");
        }
        
        SolicitudTransaccion solicitud = new SolicitudTransaccion("En espera", request.getParameter("codigoAsociar"), String.valueOf(request.getSession().getAttribute("codigo")));
        asoModel.realizarSolicitud(solicitud);
        
        request.setAttribute("success", true);
        request.getRequestDispatcher("/Cliente/ConfirmacionSolicitud.jsp").forward(request, response);
    }

    

}
