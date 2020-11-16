/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ControladorCliente;

import ModelosCliente.TransferirModel;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
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
@WebServlet(name = "RealizarTransferencia", urlPatterns = {"/RealizarTransferencia"})
public class RealizarTransferencia extends HttpServlet {

    private TransferirModel transferirModel = new TransferirModel();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //Redirigir si no hay credenciales
        if (request.getSession().getAttribute("codigo") == null) {
            response.sendRedirect(request.getContextPath() + "/Inicio/Login.jsp");
        }
        //Codigo del Cliente
        int codigo = (Integer) request.getSession().getAttribute("codigo");

        if (request.getParameter("monto") != null) {
            int monto = Integer.parseInt(request.getParameter("monto"));
            //Comprobar que monto sea >= al saldo de la cuenta
            RequestDispatcher rd = request.getRequestDispatcher("/Cliente/ConfirmacionTransferencia.jsp");
        } else {
            request.setAttribute("cuentas", transferirModel.obtenerCuentas(codigo));
            //Si no hay cuentas asociadas
            if (transferirModel.codigosCuentasAsociadas(codigo) == null || transferirModel.codigosCuentasAsociadas(codigo).isEmpty()) {
                request.setAttribute("error", "Sin cuentas asociadas");
            }
            else{
                request.setAttribute("cAsociadas", transferirModel.codigosCuentasAsociadas(codigo));
            }
        }
        
        RequestDispatcher rd = request.getRequestDispatcher("/Cliente/TransferirDinero.jsp");
        rd.forward(request, response);
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
