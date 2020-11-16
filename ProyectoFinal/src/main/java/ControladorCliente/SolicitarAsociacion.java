/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ControladorCliente;

import EntidadesUsuario.Cliente;
import ModelosCliente.AsociacionModel;
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
@WebServlet(name = "SolicitarAsociacion", urlPatterns = {"/SolicitarAsociacion"})
public class SolicitarAsociacion extends HttpServlet {

    private ClienteModel clienteM = new ClienteModel();
    private CuentaModel cuentaM = new CuentaModel();
    private AsociacionModel asoM = new AsociacionModel();
            
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        int codigoCuenta = Integer.parseInt(request.getParameter("codigo"));

        if (cuentaM.existenciaCuenta(codigoCuenta)) {
            if (asoM.cuentasPropias(codigoCuenta, (Integer)request.getSession().getAttribute("codigo"))) {
                request.setAttribute("fail", "No puedes asociar cuentas tuyas");
            }
            else if (asoM.cuentaAsociadasPrev(codigoCuenta, (Integer)request.getSession().getAttribute("codigo"))) {
                request.setAttribute("fail", "Ya has asociado dicha cuenta");
            }
            else if (asoM.solicitudTransito(codigoCuenta, (Integer)request.getSession().getAttribute("codigo"))) {
                request.setAttribute("fail", "Ya hay una solicitud en proceso");
            }
            else if (asoM.sobrepasoSolicitudes(codigoCuenta, (Integer)request.getSession().getAttribute("codigo"))==3) {
                request.setAttribute("fail", "Ya no puedes enviar solicitud");
            }
            else{
            int codigoCliente = cuentaM.codigoDueñoCuenta(codigoCuenta);
            Cliente infoCliente = clienteM.retornarPorCodigo(codigoCliente);
            request.setAttribute("cliente", infoCliente);
            request.setAttribute("codigoAso", codigoCuenta);
            request.getRequestDispatcher("/Cliente/ConfirmacionSolicitud.jsp").forward(request, response);
            }
        }
        else{
            request.setAttribute("fail", "No existe dicha cuenta");
        }
        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/Cliente/SolicitarAsociacion.jsp");
        requestDispatcher.forward(request, response);
    }

    

}
