<%-- 
    Document   : TransaccionesGrandes
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
            <div class="row justify-content-center">
                <div class="col-xl-8">
                    <c:choose>
                        <c:when test="${!empty(cuentas)}">
                            <c:forEach var="cuenta" items="${cuentas}">
                            <div class="card mb-5">
                                <div class="card-header">
                                    <h3>Transacciones de cuenta: ${cuenta.codigo}</h3>
                                </div>
                                <div class="card-body">
                                    <table class="table table-striped table-bordered table-sm tabla" cellspacing="0" width="100%">
                                        <thead class="thead-dark">
                                            <tr>
                                                <th>Codigo</th>
                                                <th>Tipo</th>
                                                <th>Fecha</th>
                                                <th>Hora</th>
                                                <th>Monto</th>
                                            </tr>
                                        </thead>
                                        <tbody>
                                            <c:forEach var="transaccion" items="${cuenta.listadoTransacciones}">
                                                <tr>
                                                    <td>${transaccion.codigo}</td>
                                                    <td>${transaccion.tipo}</td>
                                                    <td>${transaccion.fecha}</td>
                                                    <td>${transaccion.hora}</td>
                                                    <td>${transaccion.monto}</td>
                                                </tr>
                                            </c:forEach>                             
                                        </tbody>
                                    </table>
                                </div>
                                
                            </div>
                            </c:forEach>
                        </c:when>
                    </c:choose>
                </div>
            </div>
        </div>
        

        <script type="text/javascript" src="/resources/js/addons/datatables2.min.js"></script>
        <script type="text/javascript" src="/WEB-INF/Extras/JSTable.js"></script>
        <jsp:include page="/WEB-INF/Extras/extraJS.jsp"/>
    </body>
</html>
