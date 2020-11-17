/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ControladorCajero;

import EntidadesUsuario.Cliente;
import ModelosCliente.ClienteModel;
import ModelosCliente.TransferirModel;
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
@WebServlet(name = "SolicitarRetiro", urlPatterns = {"/SolicitarRetiro"})
public class SolicitarRetiro extends HttpServlet {

    private CuentaModel cuentaM = new CuentaModel();
    private ClienteModel clienteM = new ClienteModel();
    private TransferirModel transM = new TransferirModel();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int codigoCuenta = Integer.parseInt(request.getParameter("codigo"));
        double monto = Double.parseDouble(request.getParameter("monto"));

        if (cuentaM.existenciaCuenta(codigoCuenta)) {
            if (transM.dineroSuficiente(codigoCuenta, monto)) {
                int codigoCliente = cuentaM.codigoDueñoCuenta(codigoCuenta);
                Cliente infoCliente = clienteM.retornarPorCodigo(codigoCliente);
                request.setAttribute("cliente", infoCliente);
                request.setAttribute("cantidad", monto);
                request.setAttribute("cuenta", codigoCuenta);
                request.getRequestDispatcher("/Cajero/ConfirmarRetiro.jsp").forward(request, response);
            } else {
                request.setAttribute("fail", "Solicitud mayor, al saldo disponible");
                RequestDispatcher requestDispatcher = request.getRequestDispatcher("/Cajero/AtenderRetiro.jsp");
                requestDispatcher.forward(request, response);
            }

        } else {
            request.setAttribute("fail", "No existe dicha cuenta");
            RequestDispatcher requestDispatcher = request.getRequestDispatcher("/Cajero/AtenderRetiro.jsp");
            requestDispatcher.forward(request, response);
        }
    }

}
