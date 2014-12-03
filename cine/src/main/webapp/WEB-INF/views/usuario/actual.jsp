<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>

<h3>Datos del usuario</h3>
<br>
<div class="row">
	<div class="col-6"> <!-- por alguna razon class="col-md-6" no funciona -->
		<form class="form" action="actual" method="post">
			<fieldset>
		        <div class="form-group">
		            <label for="dni">DNI</label>
		            <input class="form-control" id="dni" name="dni" value="${registro.dni}" type="text" required disabled/>
		            <span id="dni.errors" class="help-block"></span>
		        </div>
		        <div class="form-group">
		            <label for="email">Email</label>
		            <input name="email" class="form-control" id="email" value="${registro.email}" alt="email" required disabled/>
		            <span id="email.errors" class="help-block"></span>
		        </div>
		        <div class="form-group">
		            <label for="nombre">Nombre</label>
		            <input name="nombre" class="form-control" id="nombre" value="${registro.nombre}" required readonly/>
	                <span id="nombre.errors" class="help-block"></span>
		        </div>
		        <div class="form-group">
		            <label for="apellido">Apellido</label>
		            <input name="apellido" class="form-control" id="apellido" value="${registro.apellido}" required readonly/>
	                <span id="apellido.errors" class="help-block"></span>
		        </div>
		        <div class="form-group">
		            <label for="sexo">Sexo</label>
		            	<select class="form-control" id="sexo" name="sexo" readonly>
							<c:forEach items="${sexos}" var="item">
								<c:if test="${registro.sexo == item.value}">
									<option value="${item.key}" selected> ${item.value}</option>
								</c:if>
								<c:if test="${registro.sexo != item.value}">
									<option value="${item.key}"> ${item.value}</option>
								</c:if>
							</c:forEach>
						</select>
		        </div>
		        <div class="form-group">
		            <label for="fechaNacimiento">Fecha de nacimiento</label>
		            <input name="fechaNacimiento" class="form-control" id="fechaNacimiento" value="${registro.fechaNacimiento}" type="text" placeholder="dd/mm/aaaa" alt="date" required readonly/>
		            <span id="fechaNacimiento.errors" class="help-block"></span>
		        </div>
		        <div class="form-group">
		            <label for="preguntaSeguridad">Pregunta de seguridad</label>
		            <input name="preguntaSeguridad" class="form-control" id="preguntaSeguridad" value="${registro.preguntaSeguridad}" readonly/>
		            <span id="preguntaSeguridad.errors" class="help-block"></span>
		        </div>
		        <div class="form-group">
		            <label for="respuestaSeguridad">Respuesta a la pregunta de seguridad</label>
		            <input name="respuestaSeguridad" class="form-control" id="respuestaSeguridad" placeholder="Escriba aqui la respuesta a la pregunta de seguridad" readonly/>
	                <span id="respuestaSeguridad.errors" class="help-block"></span>
		        </div>
		        <div class="form-group">
		            <label for="role">Rol</label>
		            <input class="form-control" id="role" name="role" value="${registro.role}" disabled/>
		        </div>
		        <c:if test="${registro.role == 'C' }">
			        <div class="form-group">
			            <label for="direccion">Direccion</label>
			            <input class="form-control" id="direccion" name="direccion" value="${direccion}" readonly/>
		                <span id="direccion.errors" class="help-block"></span>
			        </div>
			        <div class="form-group">
			            <label for="genero">Genero preferido</label>
			            <input class="form-control" id="genero" name="genero" value="${genero}" readonly/>
		                <span id="genero.errors" class="help-block"></span>
			        </div>
		        </c:if>
		        <button type="submit" class="btn btn-default">Modificar</button>
	        </fieldset>
        </form>
	</div>
</div>