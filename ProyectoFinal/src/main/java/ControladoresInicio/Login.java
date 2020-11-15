/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ControladoresInicio;

import Encriptacion.Encriptar;
import ModelosInicio.LoginModel;
import ModelosUsuarios.UsuarioModel;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author joel
 */
@WebServlet(name = "Login", urlPatterns = {"/Login"})
public class Login extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int codigo = Integer.parseInt(request.getParameter("codigo"));
        String password = request.getParameter("password");

        //Encriptamos la contraseña entrante
        Encriptar encriptacion = new Encriptar();
        String encryptedPass = "";
        try {
            encryptedPass = encriptacion.encriptar(password);
        } catch (Exception ex) {
            ex.printStackTrace(System.out);
        }

        LoginModel loginM = new LoginModel();
        boolean accesoConcedido = loginM.comprobarCredencialesValidas(codigo, encryptedPass);

        if (accesoConcedido) {

            String tipoUsuario = loginM.devolverTipoUsuario(codigo, encryptedPass);
            request.getSession().setAttribute("codigo", codigo);
            request.getSession().setAttribute("usuario", tipoUsuario);
            UsuarioModel usuarioModel = new UsuarioModel();
            request.getSession().setAttribute("nombre", usuarioModel.devolverNombrePorCodigo(codigo));

            switch (tipoUsuario) {
                case "Gerente":
                    response.sendRedirect(request.getContextPath() + "/Gerente/InicioGerente.jsp");
                    break;
                case "Cajero":
                    response.sendRedirect(request.getContextPath() + "/Cajero/InicioCajero.jsp");
                    break;
                case "Cliente":
                    response.sendRedirect(request.getContextPath() + "/Cliente/InicioCliente.jsp");
                    break;
            }
        } else {
            request.setAttribute("fail", true);
            request.getRequestDispatcher("/Inicio/Login.jsp").forward(request, response);
        }

    }
}
