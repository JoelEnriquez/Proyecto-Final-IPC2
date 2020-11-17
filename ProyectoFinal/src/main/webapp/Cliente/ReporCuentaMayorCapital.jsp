<%-- 
    Document   : CuentaMayorCapital
    Created on : 15 nov. 2020, 19:34:00
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
                <table id="dtVerticalScrollExample" class="table table-striped table-bordered table-sm" cellspacing="0"
                       width="100%">
                    <thead>
                        <tr>
                            <th class="th-sm">Name
                            </th>
                            <th class="th-sm">Position
                            </th>
                            <th class="th-sm">Office
                            </th>
                            <th class="th-sm">Age
                            </th>
                            <th class="th-sm">Start date
                            </th>
                            <th class="th-sm">Salary
                            </th>
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
        
        <script type="text/javascript" src="/resources/js/addons/datatables2.min.js"></script>
        <script type="text/javascript" src="/WEB-INF/Extras/JSTable.js"></script>
        <jsp:include page="/WEB-INF/Extras/extraJS.jsp"/>

    </body>
</html>
