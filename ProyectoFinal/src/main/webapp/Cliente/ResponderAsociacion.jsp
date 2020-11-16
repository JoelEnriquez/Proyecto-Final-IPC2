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

        <div class="row justify-content-center align-items-center vh-100">
            <div class="col-8">
                <div class="card">
                    <div class="card-header">
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
                                <c:forEach var="solicitud" items="${solicitudes}" varStatus="status">
                                    <tr>
                                        <td>${status.count}</td>
                                        <td>${solicitud.cliente.noIdentificacion}</td>
                                        <td>${solicitud.cliente.nombre}</td>
                                        <td>${solicitud.fecha}</td>
                                        <td>${solicitud.estado}</td>
                                        <td>
                                            <c:if test="${solicitud.estadoN == 0}">
                                                <a href="${pageContext.request.contextPath}/solicitud?accion=aceptar&id=${solicitud.id}&cliente=${solicitud.cliente.codigo}&cuenta=${solicitud.cuenta.codigo}" 
                                                   class="btn btn-outline-success">
                                                    <i class="fas fa-check"></i> Aceptar
                                                </a>
                                                <a href="${pageContext.request.contextPath}/solicitud?accion=rechazar&id=${solicitud.id}" 
                                                   class="btn btn-outline-danger">
                                                    <i class="fas fa-times"></i> Rechazar
                                                </a>
                                            </c:if>
                                        </td>
                                    </tr>
                                </c:forEach>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
        <jsp:include page="/WEB-INF/Extras/extraJS.jsp"/>
    </body>
</html>
