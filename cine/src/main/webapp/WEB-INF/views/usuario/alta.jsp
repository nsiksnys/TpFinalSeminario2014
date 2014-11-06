<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<h3>Alta de un usuario</h3>
<br>
<div class="row">
	<div class="col-6"> <!-- por alguna razon class="col-md-6" no funciona -->
		<form:form class="form" action="guardar" method="post">
			<fieldset>
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
		        <form:errors path="" element="p" class="text-error"/>
		        <div class="form-group">
		            <label for="email">Email</label>
		            <div class="form-group">
		                <form:input path="email" class="form-control" cssErrorClass="form-control" id="email" placeholder="email"/>
		                <form:errors path="email" element="span" class="help-block"/>
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
		        <button type="submit" class="btn btn-default">Guardar</button>
		    </fieldset>
		</form:form>
	</div>
</div>