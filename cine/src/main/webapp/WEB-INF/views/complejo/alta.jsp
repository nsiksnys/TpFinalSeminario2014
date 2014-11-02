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
					<input class="form-control" id="nombre" name="nombre" type="text"/>
				</div>
				<div class="form-group">
					<label for="direccion">Direccion</label>
					<input class="form-control" id="direccion" name="direccion" type="text"/>
				</div>
				<div class="form-group">
					<label for="salas">Cantidad de salas</label>
					<input class="form-control" id="salas" name="salas" type="text" alt="99"/>
				</div>
				<button type="submit" class="btn btn-default">Guardar</button>
			</fieldset>
		</form>
	</div>
</div>