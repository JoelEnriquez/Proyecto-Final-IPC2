/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ControladorCliente;

import EntidadesReporte.SolicitudAsoYSolicitante;
import ModelosCliente.AsociacionModel;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author joel
 */
@WebServlet(name = "ResponderSolicitud", urlPatterns = {"/ResponderSolicitud"})
public class ResponderSolicitud extends HttpServlet {

    private AsociacionModel asociacionModel = new AsociacionModel();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        if (request.getSession().getAttribute("codigo") == null) {
            response.sendRedirect(request.getContextPath() + "/Inicio/Login.jsp");
        }

        if (request.getParameter("responder") != null) {
            String codigo = request.getParameter("codigo");
            if (request.getParameter("responder").equals("aceptar")) {
                //Aceptar
                String codigoCuenta = request.getParameter("codCuenta");
                String codigoCliente = request.getParameter("codCliente");
                
                asociacionModel.actualizarSolicitud("Aceptada", Integer.parseInt(codigo));
                asociacionModel.crearAsociacion(Integer.parseInt(codigoCuenta),Integer.parseInt(codigoCliente));
                request.setAttribute("logro", 1);
            } else if (request.getParameter("responder").equals("declinar")) {
                //Rechazar
                asociacionModel.actualizarSolicitud("Rechazada", Integer.parseInt(codigo));
                request.setAttribute("logro", 0);
            }
        }

        ArrayList<SolicitudAsoYSolicitante> solicitudesPen = asociacionModel.solicitudesPendientes((Integer) request.getSession().getAttribute("codigo"));
        request.setAttribute("solicitudesPen", solicitudesPen);
        request.getRequestDispatcher("/Cliente/ResponderAsociacion.jsp").forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

}
