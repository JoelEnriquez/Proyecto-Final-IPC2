<%-- 
    Document   : HistorialSolicitudesRealizadas
    Author     : joel
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
    <head>
        <%@include file="/WEB-INF/HeadTittle.html"%>
        <jsp:include page="/WEB-INF/Extras/extraCSS.jsp"/>
        <!-- MDBootstrap Datatables  -->
        <link href="/WEB-INF/Extras/CSSTable.css" rel="stylesheet">
        <link href="/resources/css/addons/datatables2.min.css" rel="stylesheet">
    </head>
    <body>
        <jsp:include page="/WEB-INF/NavBars/NavBarCliente.jsp"/>
        <div class="container-fluid my-5 ml-5 mx-auto pl-4">
            <div class="row justify-content-center ">
                <table id="dtVerticalScrollExample" class="table table-striped table-bordered table-sm mt-5" cellspacing="0"
                       width="100%">
                    <thead>
                        <tr>
                            <th class="th-sm">Codigo
                            </th>
                            <th class="th-sm">Estado
                            </th>
                            <th class="th-sm">Fecha Solicitud
                            </th>
                            <th class="th-sm">Codigo Cuenta
                            </th>
                            <th class="th-sm">Codigo Cliente
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="solicitud" items="${solicitudes}">
                            <tr>
                                <td>${solicitud.codigo}</td>
                                <td>${solicitud.estado}</td>
                                <td>${solicitud.fechaSolicitud}</td>
                                <td>${solicitud.codigoCuenta}</td>
                                <td>${solicitud.codigoCliente}</td>
                            </tr>
                        </c:forEach>                             
                    </tbody>
                </table>
            </div>
        </div>

        <script type="text/javascript" src="/resources/js/addons/datatables2.min.js"></script>
        <script type="text/javascript" src="/WEB-INF/Extras/JSTable.js"></script>
        <jsp:include page="/WEB-INF/Extras/extraJS.jsp"/>
    </body>
</html>
