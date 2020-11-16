<%-- 
    Document   : TransferirDinero
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

        <c:if test="${empty(noAsociaciones)}">
            <div class="row justify-content-center">
                <form class="mt-4 col-8" action="${pageContext.request.contextPath}/RealizarTransferencia" method="POST">
                    <div class="form-row">
                        <div class="form-group col-md-6">
                            <label for="cuentaTransferencia">Cuenta Transferencia</label>
                            <select class="custom-select mr-sm-2" id="cuentaTransferencia" name="Cuenta Transferir">
                                <c:forEach items="${cuentas}" var="cuenta">
                                    <option value="${cuenta.codigo}">
                                        ${cuenta.codigo} (${cuenta.monto})
                                    </option>
                                </c:forEach>
                            </select>
                        </div>
                        <div class="form-group col-md-6">
                            <label for="cuentaTransferir">Cuenta Asociada</label>
                            <select class="custom-select mr-sm-2" id="cuentaTransferir">
                                <c:forEach items="${cAsociadas}" var="cAsociada">
                                    <option value="${cAsociada}">
                                        ${cAsociada}
                                    </option>
                                </c:forEach>
                                    
                            </select>
                        </div>
                    </div>
                    <div class="form-group ">
                        <label for="inputMonto">Monto</label>
                        <input type="number" class="form-control" min="1" max="2147483647" name="monto" id="inputMonto" placeholder="Ingrese el monto" required>
                    </div>
                    
                    <button type="submit" class="btn btn-primary justify-content-center">Realizar Transferencia</button>
                    
                </form>
            </div>
            <c:if test="${!empty(error)}">
                <p class="alert alert-danger text-center mt-2">${error}</p>
            </c:if>

        </c:if>
        <c:if test="${!empty(noAsociaciones)}">
            <p class="alert alert-danger text-center mt-2">No hay cuentas asociadas</p>
        </c:if>  

        <jsp:include page="/WEB-INF/Extras/extraJS.jsp"/>
    </body>
</html>
