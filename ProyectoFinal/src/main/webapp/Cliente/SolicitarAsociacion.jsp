<%-- 
    Document   : SolicitarAsociacion
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

        <div class="mx-auto">
            <div class="my-5 text-center">
                <h3>Peticion Asociacion</h3>    
            </div>
            <div class="col-3 mx-auto">
                <form action="${pageContext.request.contextPath}/SolicitarAsociacion" method="POST">
                    <div class="form-group text-center">
                        <label>Codigo Cuenta</label>
                        <input type="number" min="0" max="2147483647" step="1" class="form-control" name="codigo" required>
                    </div>
                    <div class="text-center">
                        <button type="submit" class="btn btn-primary">Ingresar</button>
                    </div>                           
                </form>
                <c:if test="${!empty(fail)}">
                    <p class="alert alert-danger text-center mt-2">${fail}</p>
                </c:if>
            </div>
        </div>
        <jsp:include page="/WEB-INF/Extras/extraJS.jsp"/>
    </body>
</html>
