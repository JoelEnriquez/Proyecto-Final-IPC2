<%-- 
    Document   : ResponderAsociacion
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
            if (session.getAttribute("codigo") == null || !session.getAttribute("usuario").equals("Cliente")) {
                response.sendRedirect(request.getContextPath() + "/Inicio/Login.jsp");
            }
        %>
        <jsp:include page="/WEB-INF/NavBars/NavBarCliente.jsp"/>

        <div class="row justify-content-center vh-100 mt-4">
            <div class="col-8">
                <div class="card">
                    <div class="card-header text-center">
                        <h3>Solicitudes pendientes</h3>
                    </div>
                    <div class="card-body">
                        <table class="table table-striped">
                            <thead class="thead-dark">
                                <tr>
                                    <th>#</th>
                                    <th>Codigo Solicitante</th>
                                    <th>Nombre Solicitante</th>
                                    <th>Fecha Solicitud</th>
                                    <th>Estado</th>
                                    <th></th>
                                </tr>
                            </thead>
                            <tbody>
                                <c:forEach var="solicitud" items="${solicitudesPen}" varStatus="status">
                                    <tr>
                                        <td>${status.count}</td>
                                        <td>${solicitud.DPI}</td>
                                        <td>${solicitud.nombre}</td>
                                        <td>${solicitud.fechaSolicitud}</td>
                                        <td>${solicitud.estado}</td>
                                        <td>
                                            <a href="${pageContext.request.contextPath}/ResponderSolicitud?responder=aceptar&codigo=${solicitud.codigo}&codCliente=${solicitud.codigoCliente}&codCuenta=${solicitud.codigoCuenta}" 
                                               class="btn btn-outline-success">
                                                <i class="fas fa-check"></i> Aceptar
                                            </a>
                                            <a href="${pageContext.request.contextPath}/ResponderSolicitud?responder=declinar&codigo=${solicitud.codigo}" 
                                               class="btn btn-outline-danger">
                                                <i class="fas fa-times"></i> Rechazar
                                            </a>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                            <c:if test="${!empty(logro)}">
                                <c:if test="${logro==1}">
                                <p class="alert alert-success text-center mt-2">Se han vinculado las cuentas</p>
                                </c:if>
                                <c:if test="${logro==0}">
                                <p class="alert alert-danger text-center mt-2">Se ha rechazado la solicitud</p>
                                </c:if>
                            </c:if>
                        </table>
                    </div>
                </div>
            </div>
        </div>
        <jsp:include page="/WEB-INF/Extras/extraJS.jsp"/>
    </body>
</html>
