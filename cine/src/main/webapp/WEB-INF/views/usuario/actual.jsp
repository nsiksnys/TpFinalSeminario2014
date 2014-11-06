<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>

<h3>Modificar un usuario</h3>
<br>
<div class="row">
	<div class="col-6"> <!-- por alguna razon class="col-md-6" no funciona -->
        <div class="form-group">
            <label for="dni">DNI</label>
            <input class="form-control" id="dni" name="dni" value="${registro.dni}" disabled/>
        </div>
        <div class="form-group">
            <label for="email">Email</label>
            <input name="email" class="form-control" id="email" value="${registro.email}" disabled/>
        </div>
        <div class="form-group">
            <label for="nombre">Nombre</label>
            <input name="nombre" class="form-control" id="nombre" value="${registro.nombre}" disabled/>
        </div>
        <div class="form-group">
            <label for="apellido">Apellido</label>
            <input name="apellido" class="form-control" id="apellido" value="${registro.apellido}" disabled/>
        </div>
        <div class="form-group">
            <label for="sexo">Sexo</label>
            <input class="form-control" id="sexo" name="sexo" value="${registro.sexo}" disabled/>
        </div>
        <div class="form-group">
            <label for="fechaNacimiento">Fecha de nacimiento</label>
            <input name="fechaNacimiento" class="form-control" id="fechaNacimiento" value="${registro.fechaNacimiento}" disabled/>
        </div>
        <div class="form-group">
            <label for="preguntaSeguridad">Pregunta de seguridad</label>
            <input name="preguntaSeguridad" class="form-control" id="preguntaSeguridad" value="${registro.preguntaSeguridad}" disabled/>
        </div>
        <div class="form-group">
            <label for="respuestaSeguridad">Respuesta a la pregunta de seguridad</label>
            <input name="respuestaSeguridad" class="form-control" id="respuestaSeguridad" value="${registro.respuestaSeguridad}" disabled/>
        </div>
        <div class="form-group">
            <label for="role">Rol</label>
            <input class="form-control" id="role" name="role" value="${registro.role}" disabled/>
        </div>
        <div class="form-group">
            <label for="direccion">Direccion</label>
            <input class="form-control" id="direccion" name="direccion" value="${direccion}" disabled/>
        </div>
        <div class="form-group">
            <label for="genero">Rol</label>
            <input class="form-control" id="genero" name="genero" value="${genero}" disabled/>
        </div>
	</div>
</div>