<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>

<h3>Alta de Promociones</h3>
<br>
<div class="row">
	<div class="col-6"> <!-- por alguna razon class="col-md-6" no funciona -->
		<form class="form" action="guardar" method="post">
			<fieldset>
				<div class="form-group">
					<label for="nombre">Nombre</label>
					<input class="form-control" id="nombre" name="nombre" type="text" placeholder="nombre"/>
				</div>
				<div class="form-group">
					<label for="descripcion">Descripcion</label>
					<textarea name="descripcion" rows="5" cols="40"></textarea>
				</div>
				<div class="form-group">
					<label for="imagen">Imagen</label>
					<input class="form-control" id="imagen" name="imagen" type="file"/>
				</div>
				<div class="form-group">
					<label for="fechainicio">Fecha Inicio</label>
					<input class="form-control" id="fechainicio" name="fechainicio" type="date"/>
				</div>
				<div class="form-group">
					<label for="fechafin">Fecha Fin</label>
					<input class="form-control" id="fechafin" name="fechafin" type="date"/>
				</div>
				<button type="submit" class="btn btn-default">Guardar</button>
			</fieldset>
		</form>
	</div>
</div>