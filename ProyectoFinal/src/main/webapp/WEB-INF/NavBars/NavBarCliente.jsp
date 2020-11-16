<div class="container">
    <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
        <a class="navbar-brand text-white" href="${pageContext.request.contextPath}/Cliente/InicioCliente.jsp">Billeton <%=request.getSession().getAttribute("usuario")%></a>
        <button class="navbar-toggler" data-target="#menu" data-toggle="collapse" type="button">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="menu">
            <ul class="navbar-nav mr-auto">
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle active" data-toggle="dropdown" data-target="desplegable" href="#">
                        Transacciones Online
                    </a>
                    <div class="dropdown-menu">
                        <a class="dropdown-item" href="${pageContext.request.contextPath}/RealizarTransferencia">Transferir Dinero</a>
                        <a class="dropdown-item" href="${pageContext.request.contextPath}/Cliente/SolicitarAsociacion.jsp">Solicitar Asociacion</a>
                        <a class="dropdown-item" href="${pageContext.request.contextPath}/ResponderSolicitud">Responder Asociacion</a>
                    </div>
                </li>
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle active" data-toggle="dropdown" data-target="desplegable" href="#">
                        Reportes
                    </a>
                    <div class="dropdown-menu">
                        <a class="dropdown-item" href="#">Transacciones mas grandes</a>
                        <a class="dropdown-item" href="#">Transacciones realizadas</a>
                        <a class="dropdown-item" href="#">Cuentas mas capital y transacciones</a>
                        <a class="dropdown-item" href="#">Historial de solicitud recibidas</a>
                        <a class="dropdown-item" href="#">Historial de solicitud realizadas</a>
                    </div>
                </li>
                
                <li class="btn btn-sucess ml-2">
                    <a class="" href="${pageContext.request.contextPath}/ControlLogOut">Cerrar Sesion</a>
                </li>
            </ul>
            <span class="navbar-text mr-4 text-white">
                Nombre: ${nombre}
            </span>
            <span class="navbar-text text-white">
                Codigo: ${codigo}
            </span>   
        </div>
    </nav>
</div>
