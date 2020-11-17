/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ControladorCliente;

import EntidadesBanco.Cuenta;
import ModelosCliente.ReportesCliente;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author joel
 */
@WebServlet(name = "ReporTransaccionesGrandes", urlPatterns = {"/ReporTransaccionesGrandes"})
public class ReporTransaccionesGrandes extends HttpServlet {

    ReportesCliente reportesCliente = new ReportesCliente();
    
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        List<Cuenta> listadoCuentas =  reportesCliente.obtenerConTransacciones((Integer)request.getSession().getAttribute("codigo"));
        request.setAttribute("cuentas", listadoCuentas);
        request.getRequestDispatcher("/Cliente/ReporTransaccionesGrandes.jsp").forward(request, response);
    }

    

}
