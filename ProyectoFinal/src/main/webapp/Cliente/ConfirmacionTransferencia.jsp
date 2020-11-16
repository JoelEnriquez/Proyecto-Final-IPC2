<%-- 
    Document   : ConfirmacionTransferencia
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

        <c:if test="${!empty(success)}">
            <p class="alert alert-success text-center mt-2">Se ha realizado la transferencia</p>
        </c:if>
        <c:if test="${empty(success)}">            
            <div class="mx-auto">
                <div class="my-5 text-center">
                    <h3>Confirmar Transferencia</h3>    
                </div>
                <div class="col-3 mx-auto">
                    <form action="${pageContext.request.contextPath}/ConfirmarTransferencia" method="POST">
                        <div class="form-group row">
                            <label for="staticCodeCuenta" class="col-form-label">Cuenta Transferir</label>
                            <div class="col-sm-10 text-center">
                                <input type="text" readonly class="form-control-plaintext" id="staticCodeCuenta" value="${cuentaTransferir}">
                            </div>
                        </div>
                        <div class="form-group row">
                            <label for="staticMonto" class="col-form-label">Monto</label>
                            <div class="col-sm-10 text-center">
                                <input type="text" readonly class="form-control-plaintext" id="staticMonto" value="${monto}">
                            </div>
                        </div>
                        <div class="form-group row">
                            <label for="staticCuenta" class="col-form-label">Cuenta Descontar</label>
                            <div class="col-sm-10 text-center">
                                <input type="text" readonly class="form-control-plaintext" name="codigoAsociar" id="staticCuenta" value="${cuentaDes}">
                            </div>
                        </div>
                        <div class="text-center">
                            <button type="submit" class="btn btn-primary">Confirmar</button>
                            <a href="${pageContext.request.contextPath}/Cliente/SolicitarAsociacion.jsp" 
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
