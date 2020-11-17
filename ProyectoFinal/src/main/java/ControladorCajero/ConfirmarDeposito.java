/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ControladorCajero;

import EntidadesBanco.Transaccion;
import ModelosCliente.TransferirModel;
import ModelosCuenta.CuentaModel;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Date;
import java.sql.Time;
import java.time.LocalTime;
import java.util.Calendar;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author joel
 */
@WebServlet(name = "ConfirmarDeposito", urlPatterns = {"/ConfirmarDeposito"})
public class ConfirmarDeposito extends HttpServlet {

    private CuentaModel cuentaModel = new CuentaModel();
    private TransferirModel transferirModel = new TransferirModel();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        double monto = Double.parseDouble(request.getParameter("cantidad"));
        int cuentaDepositar = Integer.parseInt(request.getParameter("cuenta"));
        
        cuentaModel.acreditarMonto(monto, cuentaDepositar);
             
        //Registrar transferencia cuenta receptora
        transferirModel.realizarTransferencia(new Transaccion(new Date(Calendar.getInstance().getTime().getTime()),
        Time.valueOf(LocalTime.now()), "CREDITO", monto, cuentaModel.devolverMontoActual(cuentaDepositar),
                String.valueOf(cuentaDepositar), String.valueOf(request.getSession().getAttribute("codigo"))));
        
        request.setAttribute("success", true);
        request.getRequestDispatcher("/Cajero/ConfirmarDeposito.jsp").forward(request, response);
    }

    

}
