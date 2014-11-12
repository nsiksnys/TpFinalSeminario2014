<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<h3>Modificar un usuario</h3>
<br>
<div class="row">
	<div class="col-6"> <!-- por alguna razon class="col-md-6" no funciona -->
		<form:form class="form" action="modificar" method="post" modelAttribute="signupForm">
			<fieldset>
		        <div class="form-group">
		            <label for="dni">DNI</label>
		            <div class="form-group">
		                <form:input path="dni" class="form-control" cssErrorClass="form-control" id="dni" value="${registro.dni}" placeholder="DNI"/>
		                <form:errors path="dni" element="span" class="help-block"/>
		            </div>
		        </div>
		        <form:errors path="" element="p" class="text-error"/>
		        <div class="form-group">
		            <label for="email">Email</label>
		            <div class="form-group">
		                <form:input path="email" class="form-control" cssErrorClass="form-control" id="email" value="${registro.email}" placeholder="email"/>
		                <form:errors path="email" element="span" class="help-block"/>
		            </div>
		        </div>
		        <div class="form-group">
		            <label for="nombre">Nombre</label>
		            <div class="form-group">
		                <form:input path="nombre" class="form-control" cssErrorClass="form-control" id="nombre" value="${registro.nombre}" placeholder="Nombre"/>
		                <form:errors path="nombre" element="span" class="help-block"/>
		            </div>
		        </div>
		        <div class="form-group">
		            <label for="apellido">Apellido</label>
		            <div class="form-group">
		                <form:input path="apellido" class="form-control" cssErrorClass="form-control" id="apellido" value="${registro.apellido}" placeholder="Apellido"/>
		                <form:errors path="apellido" element="span" class="help-block"/>
		            </div>
		        </div>
		        <div class="form-group">
		            <label for="sexo">Sexo</label>
		            <div class="form-group">
		                <select class="form-control" id="sexo" name="sexo">
							<option value="F">Femenino</option>
							<option value="M">Masculino</option>
						</select>
		                <form:errors path="sexo" element="span" class="help-block"/>
		            </div>
		        </div>
		        <div class="form-group">
		            <label for="fechaNacimiento">Fecha de nacimiento</label>
		            <div class="form-group">
		                <form:input path="fechaNacimiento" class="form-control" cssErrorClass="form-control" id="fechaNacimiento" value="${registro.fechaNacimiento}" placeholder="dd/mm/aaaa" alt="date" />
		                <form:errors path="fechaNacimiento" element="span" class="help-block"/>
		            </div>
		        </div>
		        <input type="hidden" name="password" id="password" value="N/A">
		        <div class="form-group">
		            <label for="preguntaSeguridad">Pregunta de seguridad (opcional)</label>
		            <div class="form-group">
		                <form:input path="preguntaSeguridad" class="form-control" cssErrorClass="form-control" id="preguntaSeguridad" value="${registro.preguntaSeguridad}"/>
		                <form:errors path="preguntaSeguridad" element="span" class="help-block"/>
		            </div>
		        </div>
		        <div class="form-group">
		            <label for="respuestaSeguridad">Respuesta a la pregunta de seguridad (opcional)</label>
		            <div class="form-group">
		                <form:input path="respuestaSeguridad" class="form-control" cssErrorClass="form-control" id="respuestaSeguridad" value="${registro.respuestaSeguridad}"/>
		                <form:errors path="respuestaSeguridad" element="span" class="help-block"/>
		            </div>
		        </div>
		        <div class="form-group">
		            <label for="role">Rol</label>
		            <div class="form-group">
		            	<select class="form-control" id="role" name="role">
							<c:forEach items="${roles}" var="item">
								<c:if test="${registro.role == item.key}">
									<option value="${item.key}" selected> ${item.value}</option>
								</c:if>
								<c:if test="${registro.role != item.key}">
									<option value="${item.key}"> ${item.value}</option>
								</c:if>
							</c:forEach>
						</select>
		            </div>
		        </div>
		        <!-- Estos dos inputs solo se ven si el administrador selecciona la opcion "cliente" -->
		        <div class="form-group" id="direccionDiv">
		            <label for="direccion">Direccion (opcional)</label>
		            <div class="form-group">
		                <form:input path="direccion" class="form-control" cssErrorClass="form-control" id="direccion" placeholder="direccion" value="${direccion}"/>
		                <form:errors path="direccion" element="span" class="help-block"/>
		            </div>
		        </div>
		        <div class="form-group" name="generoDiv">
		            <label for="genero">Genero preferido (opcional)</label>
		            <div class="form-group">
		                <form:input path="genero" class="form-control" cssErrorClass="form-control" id="genero" placeholder="genero" value="${genero}"/>
		                <form:errors path="genero" element="span" class="help-block"/>
		            </div>
		        </div>
		        <button type="submit" class="btn btn-default">Guardar</button>
		    </fieldset>
		</form:form>
	</div>
</div>