<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>

<h3>Alta de un complejo</h3>
<br>
<div class="row">
	<div class="col-6"> <!-- por alguna razon class="col-md-6" no funciona -->
		<form class="form" action="guardar" method="post">
			<fieldset>
				<div class="form-group">
					<label for="nombre">Nombre</label>
					<input class="form-control" id="nombre" name="nombre" type="text" required/>
					<span id="nombre.errors" class="help-block"></span>
				</div>
				<div class="form-group">
					<label for="direccion">Direccion</label>
					<input class="form-control" id="direccion" name="direccion" type="text" required/>
					<span id="direccion.errors" class="help-block"></span>
				</div>
				<div class="form-group">
					<label for="cantidad">Cantidad de salas</label>
					<input class="form-control" id="salas" name="salas" type="text" alt="integer" required/>
					<span id="cantidad.errors" class="help-block"></span>
				</div>
				<button type="submit" class="btn btn-default">Guardar</button>
			</fieldset>
		</form>
	</div>
</div>