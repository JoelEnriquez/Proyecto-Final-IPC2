/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ControladorCajero;

import EntidadesUsuario.Cliente;
import ModelosCliente.ClienteModel;
import ModelosCuenta.CuentaModel;
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
@WebServlet(name = "SolicitarDeposito", urlPatterns = {"/SolicitarDeposito"})
public class SolicitarDeposito extends HttpServlet {

    private CuentaModel cuentaM = new CuentaModel();
    private ClienteModel clienteM = new ClienteModel();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int codigoCuenta = Integer.parseInt(request.getParameter("codigo"));

        if (cuentaM.existenciaCuenta(codigoCuenta)) {

            int codigoCliente = cuentaM.codigoDueñoCuenta(codigoCuenta);
            Cliente infoCliente = clienteM.retornarPorCodigo(codigoCliente);
            request.setAttribute("cliente", infoCliente);
            request.setAttribute("cantidad", request.getParameter("monto"));
            request.setAttribute("cuenta", codigoCuenta);
            request.getRequestDispatcher("/Cajero/ConfirmarDeposito.jsp").forward(request, response);

        } else {
            request.setAttribute("fail", "No existe dicha cuenta");
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/Cajero/AtenderDeposito.jsp");
            requestDispatcher.forward(request, response);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
