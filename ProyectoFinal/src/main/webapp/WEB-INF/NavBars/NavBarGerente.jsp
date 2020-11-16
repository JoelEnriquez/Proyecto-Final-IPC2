<div class="container">
    <nav class="navbar navbar-expand-lg navbar-dark bg-primary">
        <a class="navbar-brand text-white" href="${pageContext.request.contextPath}/Gerente/InicioGerente.jsp">Billeton Gerente</a>
        <button class="navbar-toggler" data-target="#menu" data-toggle="collapse" type="button">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="menu">
            <ul class="navbar-nav mr-auto">
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle active" data-toggle="dropdown" data-target="desplegable" href="#">
                        Clientes
                    </a>
                    <div class="dropdown-menu">
                        <a class="dropdown-item" href="#">Crear Cliente</a>
                        <a class="dropdown-item" href="#">Crear cuenta</a>
                    </div>
                </li>
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle active" data-toggle="dropdown" data-target="desplegable" href="#">
                        Modificar
                    </a>
                    <div class="dropdown-menu">
                        <a class="dropdown-item" href="#">Cliente</a>
                        <a class="dropdown-item" href="#">Cajero</a>
                        <a class="dropdown-item" href="#">Limites reportes</a>
                    </div>
                </li>
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle active" data-toggle="dropdown" data-target="desplegable" href="#">
                        Reportes
                    </a>
                    <div class="dropdown-menu">
                        <a class="dropdown-item" href="#">Historial cambios realizados</a>
                        <a class="dropdown-item" href="#">Clientes mayor transacciones</a>
                        <a class="dropdown-item" href="#">Clientes mayor transacciones sumadas</a>
                        <a class="dropdown-item" href="#">Clientes mayor dinero</a>
                        <a class="dropdown-item" href="#">Clientes sin transacciones</a>
                        <a class="dropdown-item" href="#">Historial transacciones por cliente</a>
                        <a class="dropdown-item" href="#">Cajero con mas transacciones</a>
                    </div>
                </li>
                <li class="btn btn-dark">
                    <a href="${pageContext.request.contextPath}/ControlLogOut">Cerrar Sesion</a>
                </li>
            </ul>
            <span class="navbar-text mr-4 text-dark">
                Nombre: ${nombre}
            </span>
            <span class="navbar-text text-dark">
                Codigo: ${codigo}
            </span> 
        </div>
    </nav>
</div>
