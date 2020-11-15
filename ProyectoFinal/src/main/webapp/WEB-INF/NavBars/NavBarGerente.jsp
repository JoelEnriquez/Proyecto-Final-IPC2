<div class="container">
    <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
        <a class="navbar-brand text-white" href="${pageContext.request.contextPath}/Admin/InicioAdmin.jsp">Corpomedic <%=request.getSession().getAttribute("persona")%></a>
        <button class="navbar-toggler" data-target="#menu" data-toggle="collapse" type="button">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="menu">
            <ul class="navbar-nav mr-auto">
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle active" data-toggle="dropdown" data-target="desplegable" href="#">
                        Pacientes
                    </a>
                    <div class="dropdown-menu">
                        <a class="dropdown-item" href="#">Listado Pacientes</a>
                        <a class="dropdown-item" href="#">Modificar</a>
                        <a class="dropdown-item" href="#">Ingresos por paciente</a>
                    </div>
                </li>
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle active" data-toggle="dropdown" data-target="desplegable" href="#">
                        Medicos
                    </a>
                    <div class="dropdown-menu">
                        <a class="dropdown-item" href="#">Crear</a>
                        <a class="dropdown-item" href="#">Editar</a>
                        <a class="dropdown-item" href="#">Listado Medicos</a>
                        <a class="dropdown-item" href="#">Ingresos por medico</a>
                        <a class="dropdown-item" href="${pageContext.request.contextPath}/Admin/MedicosMayoresInformes.jsp">Medicos con mas informes</a>
                        <a class="dropdown-item" href="#">Medicos con menos citas</a>
                    </div>
                </li>
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle active" data-toggle="dropdown" data-target="desplegable" href="#">
                        Laboratoristas
                    </a>
                    <div class="dropdown-menu">
                        <a class="dropdown-item" href="#">Crear</a>
                        <a class="dropdown-item" href="#">Editar</a>
                        <a class="dropdown-item" href="#">Listado Laboratoristas</a>
                    </div>
                </li>
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle active" data-toggle="dropdown" data-target="desplegable" href="#">
                        Consultas
                    </a>
                    <div class="dropdown-menu">
                        <a class="dropdown-item" href="#">Cambiar Costo</a>
                    </div>
                </li>
                <li class="nav-item dropdown">
                    <a class="nav-link dropdown-toggle active" data-toggle="dropdown" data-target="desplegable" href="#">
                        Examen
                    </a>
                    <div class="dropdown-menu">
                        <a class="dropdown-item" href="#">Crear</a>
                        <a class="dropdown-item" href="#">Editar</a>
                    </div>
                </li>
                <li class="btn btn-secundary">
                    <a href="${pageContext.request.contextPath}/ControlLogOut">Cerrar Sesion</a>
                </li>
            </ul>
            <span class="navbar-text">
                <%="Codigo:" + request.getSession().getAttribute("codigo")%>
            </span> 
        </div>
    </nav>
</div>
