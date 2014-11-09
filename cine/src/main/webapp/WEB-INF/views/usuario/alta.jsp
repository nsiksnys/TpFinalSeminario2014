<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<h3>Alta de un usuario</h3>

<br>
<div class="row">
	<div class="col-6">
		<form:form class="form" method="post" modelAttribute="signupForm">
		    <fieldset>
		        <form:errors path="" element="p" class="text-error"/>
		        <div class="form-group">
		            <label for="dni">DNI</label>
		            <div class="form-group">
		                <form:input path="dni" class="form-control" cssErrorClass="form-control" id="dni" placeholder="DNI"/>
		                <form:errors path="dni" element="span" class="help-block"/>
		            </div>
		        </div>
		        <div class="form-group">
		            <label for="nombre">Nombre</label>
		            <div class="form-group">
		                <form:input path="nombre" class="form-control" cssErrorClass="form-control" id="nombre" placeholder="Nombre"/>
		                <form:errors path="nombre" element="span" class="help-block"/>
		            </div>
		        </div>
		        <div class="form-group">
		            <label for="apellido">Apellido</label>
		            <div class="form-group">
		                <form:input path="apellido" class="form-control" cssErrorClass="form-control" id="apellido" placeholder="Apellido"/>
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
		            <label for="email">Email</label>
		            <div class="form-group">
		                <form:input path="email" class="form-control" cssErrorClass="form-control" id="email" placeholder="email"/>
		                <form:errors path="email" element="span" class="help-block"/>
		            </div>
		        </div>
		        <div class="form-group">
		            <label for="fechaNacimiento">Fecha de Nacimiento</label>
		            <div class="form-group">
		                <form:input path="fechaNacimiento" class="form-control" cssErrorClass="form-control" id="fechaNacimiento" placeholder="dd/mm/aaaa" alt="date"/>
		                <form:errors path="fechaNacimiento" element="span" class="help-block"/>
		            </div>
		        </div>
		        <div class="form-group">
		            <label for="password">Clave</label>
		            <div class="form-group">
		                <form:password path="password" class="form-control" id="password" placeholder="Clave"/>
		                <form:errors path="password" element="span" class="help-block"/>
		            </div>
		        </div>
		        <form:errors path="" element="p" class="text-error"/>
		        <div class="form-group">
		            <label for="role">Rol</label>
		            <div class="form-group">
		                <select class="form-control" id="role" name="role">
							<option value="C">Cliente</option>
							<option value="A">Administrador</option>
							<option value="G">Gerente</option>
						</select>
		            </div>
		        </div>
		        <!-- Estos dos inputs solo se ven si el administrador selecciona la opcion "cliente" -->
		        <div class="form-group" id="direccionDiv">
		            <label for="direccion">Direccion (opcional)</label>
		            <div class="form-group">
		                <form:input path="direccion" class="form-control" cssErrorClass="form-control" id="direccion" placeholder="direccion"/>
		                <form:errors path="direccion" element="span" class="help-block"/>
		            </div>
		        </div>
		        <div class="form-group" name="generoDiv">
		            <label for="genero">Genero preferido (opcional)</label>
		            <div class="form-group">
		                <form:input path="genero" class="form-control" cssErrorClass="form-control" id="genero" placeholder="genero"/>
		                <form:errors path="genero" element="span" class="help-block"/>
		            </div>
		        </div>
		        <button type="submit" class="btn btn-default">Guardar</button>
		    </fieldset>
		</form:form>
	</div>
</div>