/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ControladorCliente;

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
@WebServlet(name = "ConfirmarTransferencia", urlPatterns = {"/ConfirmarTransferencia"})
public class ConfirmarTransferencia extends HttpServlet {

    private TransferirModel transferirModel = new TransferirModel();
    private CuentaModel cuentaModel = new CuentaModel();

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        double monto = Double.parseDouble(request.getParameter("cantidad"));
        int cuentaEmisora = Integer.parseInt(request.getParameter("cuentaDescontar"));
        int cuentaReceptora = Integer.parseInt(request.getParameter("cuentaTransferir"));

        //Actualizar ambas cuentas
        cuentaModel.acreditarMonto(monto, cuentaReceptora);
        cuentaModel.debitarMonto(monto, cuentaEmisora);
        
        //Registrar transferencia cuenta receptora
        transferirModel.realizarTransferencia(new Transaccion(new Date(Calendar.getInstance().getTime().getTime()),
        Time.valueOf(LocalTime.now()), "CREDITO", monto, cuentaModel.devolverMontoActual(cuentaReceptora), String.valueOf(cuentaReceptora), String.valueOf(101)));
        
        //Registrar transferencia cuenta emisora
        transferirModel.realizarTransferencia(new Transaccion(new Date(Calendar.getInstance().getTime().getTime()),
        Time.valueOf(LocalTime.now()), "DEBITO", monto, cuentaModel.devolverMontoActual(cuentaEmisora), String.valueOf(cuentaEmisora), String.valueOf(101)));

        request.setAttribute("success", true);
        request.getRequestDispatcher("/Cliente/ConfirmacionTransferencia.jsp").forward(request, response);
    }

}
