<%-- 
    Document   : ConfirmarRetiro
    Author     : joel
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <%@include file="/WEB-INF/HeadTittle.html"%>
        <jsp:include page="/WEB-INF/Extras/extraCSS.jsp"/>
    </head>
    <body>
        <%--Si no encuentra codigo o que sea Cliente, redirigir al inicio --%>
        <%
            response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");
            if (session.getAttribute("codigo") == null || !session.getAttribute("usuario").equals("Cajero")) {
                response.sendRedirect(request.getContextPath() + "/Inicio/Login.jsp");
            }
        %>
        <jsp:include page="/WEB-INF/NavBars/NavBarCajero.jsp"/>

        <c:if test="${!empty(success)}">
            <p class="alert alert-success text-center mt-2">Se ha realizado el deposito exitosamente</p>
        </c:if>
        <c:if test="${empty(success)}">     

            <div class="mx-auto">
                <div class="my-5 text-center">
                    <h3>Confirmar Retiro</h3>    
                </div>
                <div class="col-3 mx-auto">
                    <form action="${pageContext.request.contextPath}/ConfirmarRetiro" method="POST">
                        <div class="form-group row">
                            <label for="staticCodeCliente" class="col-form-label">Codigo Cliente</label>
                            <div class="col-sm-10 text-center">
                                <input type="text" readonly class="form-control-plaintext" id="staticCodeCliente" value="${cliente.codigo}" name="codigo">
                            </div>
                        </div>
                        <div class="form-group row">
                            <label for="staticNombre" class="col-form-label">Nombre</label>
                            <div class="col-sm-10 text-center">
                                <input type="text" readonly class="form-control-plaintext" id="staticNombre" value="${cliente.nombre}" name="nombre">
                            </div>
                        </div>
                        <div class="form-group row">
                            <label for="staticNoCuenta" class="col-form-label">Numero DPI</label>
                            <div class="col-sm-10 text-center">
                                <input type="text" readonly class="form-control-plaintext" id="staticNoCuenta" value="${cliente.DPI}" name="DPI">
                            </div>
                        </div>
                        <div class="form-group row">
                            <label for="staticNoCuenta" class="col-form-label">Numero de Cuenta</label>
                            <div class="col-sm-10 text-center">
                                <input type="text" readonly class="form-control-plaintext" id="staticNoCuenta" value="${cuenta}" name="cuenta">
                            </div>
                        </div>
                        <div class="form-group row">
                            <label for="staticMonto" class="col-form-label">Monto a Retirar</label>
                            <div class="col-sm-10 text-center">
                                <input type="text" readonly class="form-control-plaintext" name="cantidad" id="staticMonto" value="${cantidad}">
                            </div>
                        </div>
                        <div class="form-group row mb-3">
                            <a class="btn btn-dark" href="${pageContext.request.contextPath}/VisualizarDPI?codigo=${cliente.codigo}" target="_blank">Ver DPI</a>
                        </div>
                        <div class="text-center">
                            <button type="submit" class="btn btn-primary">Confirmar</button>
                            <a href="${pageContext.request.contextPath}/Cajero/AtenderRetiro.jsp" 
                               class="btn btn-outline-danger">
                                <i class="fas fa-times"></i> Rechazar
                            </a>
                        </div>                           
                    </form>
                </div>
            </div>
        </c:if> 

        <jsp:include page="/WEB-INF/Extras/extraJS.jsp"/>
    </body>
</html>
