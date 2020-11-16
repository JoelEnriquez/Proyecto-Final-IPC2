<div class="container">
    <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
        <a class="navbar-brand text-white" href="${pageContext.request.contextPath}/Cajero/InicioCajero.jsp">Billeton Cajero</a>
        <button class="navbar-toggler" data-target="#menu" data-toggle="collapse" type="button">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="menu">
            <ul class="navbar-nav mr-auto">
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle active" data-toggle="dropdown" data-target="desplegable" href="#">
                        Atender Cliente
                    </a>
                    <div class="dropdown-menu">
                        <a class="dropdown-item" href="#">Retiro</a>
                        <a class="dropdown-item" href="#">Deposito</a>
                    </div>
                </li>
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle active" data-toggle="dropdown" data-target="desplegable" href="#">
                        Reportes
                    </a>
                    <div class="dropdown-menu">
                        <a class="dropdown-item" href="#">Depositos y retiros realizados en turno</a>
                        <a class="dropdown-item" href="#">Transacciones realizadas en dia</a>
                    </div>
                </li>
                <li class="btn btn-secundary">
                    <a href="${pageContext.request.contextPath}/ControlLogOut">Cerrar Sesion</a>
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
