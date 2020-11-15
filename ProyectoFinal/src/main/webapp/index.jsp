<%-- 
    Document   : index
    Author     : joel
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <%@include file="/WEB-INF/HeadTittle.html"%>
    </head>
    <body>
        <% if (request.getAttribute("SolicitudDatos") == null) {%>
        <% RequestDispatcher rd = request.getRequestDispatcher("ComprobarDatos");
            rd.forward(request, response);%>
        <% } else {%>
        <% if ((Integer)request.getAttribute("SolicitudDatos")==0) {%>
        <% response.sendRedirect("Inicio/CargarDatos.jsp"); %>
        <% } else {%>
        <% response.sendRedirect("Inicio/Login.jsp"); %>
        <% }%>
        <% }%>
    </body>
</html>
