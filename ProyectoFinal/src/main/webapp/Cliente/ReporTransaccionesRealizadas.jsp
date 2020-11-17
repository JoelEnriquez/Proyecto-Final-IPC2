<%-- 
    Document   : TransaccionesRealizadas
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
        
        <script type="text/javascript" src="/resources/js/addons/datatables2.min.js"></script>
        <script type="text/javascript" src="/WEB-INF/Extras/JSTable.js"></script>
        <jsp:include page="/WEB-INF/Extras/extraJS.jsp"/>
    </body>
</html>
