<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>

<h3>Modificar un usuario</h3>
<br>
<div class="row">
	<div class="col-6"> <!-- por alguna razon class="col-md-6" no funciona -->
		<form class="form" action="modificar" method="post">
			<fieldset>
		        <div class="form-group">
		            <label for="dni">DNI</label>
		            <div class="form-group">
		                <input name="dni" class="form-control" type="text" id="dni" value="${registro.dni}" placeholder="DNI" readonly/>
		                <span id="dni.errors" class="help-block"></span>
		            </div>
		        </div>
		        <div class="form-group">
		            <label for="email">Email</label>
		            <div class="form-group">
		                <input name="email" class="form-control" id="email" value="${registro.email}" placeholder="email" alt="email" readonly/>
		                <span id="mail.errors" class="help-block"></span>
		            </div>
		        </div>
		        <div class="form-group">
		            <label for="nombre">Nombre</label>
		            <div class="form-group">
		                <input name="nombre" class="form-control" type="text" id="nombre" value="${registro.nombre}" placeholder="Nombre" readonly required/>
		                <span id="nombre.errors" class="help-block"></span>
		            </div>
		        </div>
		        <div class="form-group">
		            <label for="apellido">Apellido</label>
		            <div class="form-group">
		                <input name="apellido" class="form-control" type="text" id="apellido" value="${registro.apellido}" placeholder="Apellido" readonly required/>
		                <span id="apellido.errors" class="help-block"></span>
		            </div>
		        </div>
		        <div class="form-group">
		            <label for="sexo">Sexo</label>
		            <div class="form-group">
		               <input name="sexo" id="sexo" class="form-control" value="${registro.sexo}" readonly/>
		            </div>
		        </div>
		        <div class="form-group">
		            <label for="pasada">Fecha de nacimiento</label>
		            <div class="form-group">
		                <input name="fechaNacimiento" class="form-control" type="text" id="fechaNacimiento" value="${registro.fechaNacimiento}"  placeholder="dd/mm/aaaa" readonly alt="date" />
		                <span id="fechaNacimiento.errors" class="help-block"></span>
		            </div>
		        </div>
		        <input type="hidden" name="password" id="password" value="N/A">
		        <div class="form-group">
		            <label for="role">Rol</label>
		            <div class="form-group">
		            	<select class="form-control" id="role" name="role">
							<c:forEach items="${roles}" var="item">
								<c:if test="${registro.role == item.value}">
									<option value="${item.key}" selected> ${item.value}</option>
								</c:if>
								<c:if test="${registro.role != item.value}">
									<option value="${item.key}"> ${item.value}</option>
								</c:if>
							</c:forEach>
						</select>
		            </div>
		        </div>
		        <!-- Estos dos inputs solo se ven si el administrador selecciona la opcion "cliente" -->
		        <div class="form-group" id="direccionDiv">
		            <label for="direccion">Direccion</label>
		            <div class="form-group">
		                <input name="direccion" class="form-control" type="text" id="direccion" placeholder="direccion" value="${direccion}" readonly/>
		            </div>
		        </div>
		        <div class="form-group" id="generoDiv">
		            <label for="genero">Genero preferido</label>
		            <div class="form-group">
		                <input name="genero" class="form-control" type="text" id="genero" placeholder="genero" value="${genero}" readonly/>
		            </div>
		        </div>
		        <button type="submit" class="btn btn-default">Guardar</button>
		    </fieldset>
		</form>
	</div>
</div>