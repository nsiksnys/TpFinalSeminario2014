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
            	<security:authorize access="!isAuthenticated()">
            		<li><a href="#">Complejos y salas</a></li>
	               	<!-- <li><a href="<s:url value="/cartelera"/>">Cartelera</a></li> -->
	               	<li><a href="#">Proximos estrenos</a></li>
            	</security:authorize>
            	
		    	<security:authorize access="isAuthenticated()">
	               	<security:authorize ifAllGranted="C">
	            		<li><a href="#">Complejos y salas</a></li>
	               		<li><a href="<s:url value="/cartelera"/>">Cartelera</a></li>
	               		<li><a href="#">Proximos estrenos</a></li>
	            		<li><a href="<s:url value="/reserva"/>">Reservar entradas</a></li>
	               	</security:authorize>
               
	         		<security:authorize ifAllGranted="A"> <!-- Menues que puede ver un admin -->
	         			<li class="dropdown">
							<a href="<s:url value="/cartelera"/>" class="dropdown-toggle" data-toggle="dropdown">
								<font>Cartelera</font>
								<b class="caret"></b>
							</a>
							<ul class="dropdown-menu">
								<li>
									<a href="<s:url value="/cartelera/lista"/>"><font><font>Lista</font></font></a>
								</li>
								<li>
									<a href='<s:url value="/cartelera/alta"></s:url>'><font><font>Agregar</font></font></a>
								</li>
							</ul>
						</li>
		         		<li class="dropdown">
							<a href="<s:url value="/pelicula"/>" class="dropdown-toggle" data-toggle="dropdown">
								<font>Peliculas</font>
								<b class="caret"></b>
							</a>
							<ul class="dropdown-menu">
								<li>
									<a href="<s:url value="/pelicula/lista"/>"><font><font>Lista</font></font></a>
								</li>
								<li>
									<a href='<s:url value="/pelicula/alta"></s:url>'><font><font>Nueva</font></font></a>
								</li>
							</ul>
						</li>
						<li class="dropdown">
							<a href="<s:url value="/usuario"/>" class="dropdown-toggle" data-toggle="dropdown">
								<font>Usuarios</font>
								<b class="caret"></b>
							</a>
							<ul class="dropdown-menu">
								<li>
									<a href="<s:url value="/usuario/lista"/>"><font><font>Lista</font></font></a>
								</li>
								<li>
									<a href='<s:url value="/usuario/alta"></s:url>'><font><font>Nuevo</font></font></a>
								</li>
							</ul>
						</li>
						<li class="dropdown">
							<a href="<s:url value="/promo"/>" class="dropdown-toggle" data-toggle="dropdown">
								<font>Promociones</font>
								<b class="caret"></b>
							</a>
							<ul class="dropdown-menu">
								<li>
									<a href="<s:url value="/promo/lista"/>"><font><font>Lista</font></font></a>
								</li>
								<li>
									<a href='<s:url value="/promo/alta"></s:url>'><font><font>Nuevo</font></font></a>
								</li>
							</ul>
						</li>
						<li class="dropdown">
							<a href="<s:url value="/precio"/>" class="dropdown-toggle" data-toggle="dropdown">
								<font>Precios</font>
								<b class="caret"></b>
							</a>
							<ul class="dropdown-menu">
								<li>
									<a href="<s:url value="/precio/lista"/>"><font><font>Lista</font></font></a>
								</li>
								<li>
									<a href='<s:url value="/precio/alta"></s:url>'><font><font>Nuevo</font></font></a>
								</li>
							</ul>
						</li>
				    </security:authorize>
				    
	            	<security:authorize ifAllGranted="G"> <!-- Menues que puede ver un gerente -->
	                	<li><a href="<s:url value="/stats"/>">Estadisticas</a></li>
	                </security:authorize>
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
							<b class="caret"></b>
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