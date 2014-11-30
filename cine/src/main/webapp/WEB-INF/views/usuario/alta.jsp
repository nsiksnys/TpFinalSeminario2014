<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>

<h3>Alta de un usuario</h3>

<br>
<div class="row">
	<div class="col-6">
		<form class="form" action="alta" method="post" >
		    <fieldset>
		        <div class="form-group">
		            <label for="dni">DNI</label>
		            <div class="form-group">
		                <input id="dni" name="dni" class="form-control" type="text" placeholder="DNI" alt="integer" required />
		                <span id="dni.errors" class="help-block"></span>
		            </div>
		        </div>
		        <div class="form-group">
		            <label for="nombre">Nombre</label>
		            <div class="form-group">
		                <input name="nombre" id="nombre" class="form-control" type="text" placeholder="Nombre" required />
		                <span id="nombre.errors" class="help-block"></span>
		            </div>
		        </div>
		        <div class="form-group">
		            <label for="apellido">Apellido</label>
		            <div class="form-group">
		                <input name="apellido" class="form-control" type="text" id="apellido" placeholder="Apellido" required />
		                <span id="apellido.errors" class="help-block"></span>
		            </div>
		        </div>
		        <div class="form-group">
		            <label for="sexo">Sexo</label>
		            <div class="form-group">
		                <select class="form-control" id="sexo" name="sexo">
							<option value="F">Femenino</option>
							<option value="M">Masculino</option>
						</select>
		            </div>
		        </div>
		        <div class="form-group">
		            <label for="email">Email</label>
		            <div class="form-group">
		                <input name="email" class="form-control" id="email" placeholder="email" alt="email" required />
		                <span id="email.errors" class="help-block"></span>
		            </div>
		        </div>
		        <div class="form-group">
		            <label for="pasada">Fecha de Nacimiento</label>
		            <div class="form-group">
		                <input name="fechaNacimiento" class="form-control" type="text" id="fechaNacimiento" placeholder="dd/mm/aaaa" alt="date"/>
		                <span id="pasada.errors" class="help-block"></span>
		            </div>
		        </div>
		        <div class="form-group">
		            <label for="password">Clave</label>
		            <div class="form-group">
		                <input name="password" type="password" class="form-control" type="text" id="password" placeholder="Clave" required />
		                <span id="password.errors" class="help-block"></span>
		            </div>
		        </div>
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
		                <input id="direccion" class="form-control" type="text" name="direccion" placeholder="direccion"/>
		            </div>
		        </div>
		        <div class="form-group" id="generoDiv">
		            <label for="genero">Genero preferido (opcional)</label>
		            <div class="form-group">
		                <input name="genero" class="form-control" type="text" id="genero" placeholder="genero"/>
		            </div>
		        </div>
		        <button type="submit" class="btn btn-default">Guardar</button>
		    </fieldset>
		</form>
	</div>
</div>