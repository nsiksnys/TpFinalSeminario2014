<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security"%>
<div class="navbar navbar-inverse navbar-fixed-top">
    <div class="container">
        <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".nav-collapse">
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
            <span class="icon-bar"></span>
        </button>
        <a class="navbar-brand" href="#">CINE</a>
        <div class="nav-collapse collapse">
            <ul class="nav navbar-nav">
               <li><a href="#">Complejos y salas</a></li>
               <li><a href="#">Cartelera</a></li>
               <li><a href="#">Proximos estrenos</a></li>
               <li><a href="#">Peliculas</a></li>
		         	<security:authorize access="isAuthenticated()">
		            	<li><a href="#">Usuarios</a></li>
  			            <li><a href="#">Promociones</a></li>
  			            <li><a href="#">Precios</a></li>
		            	<li><a href="#">Reservar entradas</a></li>
		                <li><a href="#">Estadisticas</a></li>
		            </security:authorize>
            </ul>
            
            <ul class="nav navbar-nav pull-right">
                <security:authorize access="!isAuthenticated()">
                    <li><a href='<s:url value="/signin"/>'>Iniciar sesion</a></li>
                    <li><a href='<s:url value="/signup"/>'>Registrarse </a></li>
                </security:authorize>
                <security:authorize access="isAuthenticated()">
                    <li class="dropdown">
						<a href="#" class="dropdown-toggle" data-toggle="dropdown">
							<font><security:authentication property="principal.username"/></font>
						</a>
						<ul class="dropdown-menu">
							<li>
								<a href="#"><font><font>Perfil</font></font></a>
							</li>
							<li>
								<a href='<s:url value="/logout"></s:url>'><font><font>Cerrar sesion</font></font></a>
							</li>
						</ul>
					</li>
                </security:authorize>
            </ul>
        </div>
        <!--/.nav-collapse -->
    </div>
</div>