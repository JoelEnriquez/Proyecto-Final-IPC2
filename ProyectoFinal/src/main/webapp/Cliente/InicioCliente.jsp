
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page session="true" %>
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

        <div class="text-center m-auto">
            <img src="${pageContext.request.contextPath}/resources/Cliente.jpg" alt="450">
        </div>

    <jsp:include page="/WEB-INF/Extras/extraJS.jsp"/>
    </body>
</html>
