<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>

<h3>Alta de Promociones</h3>
<br>
<div class="row">
	<div class="col-6"> <!-- por alguna razon class="col-md-6" no funciona -->
		<form class="form" action="alta" method="post">
			<fieldset>
				<div class="form-group">
					<label for="nombre">Nombre</label>
					<input class="form-control" id="nombre" name="nombre" type="text" required />
					<span id="nombre.errors" class="help-block"></span>
				</div>
				<div class="form-group">
					<label for="descripcion">Descripcion</label>
					<textarea class="form-control" id="descripcion" name="descripcion" required></textarea>
					<span id="descripcion.errors" class="help-block"></span>
				</div>
				<div class="form-group">
					<label for="inicio">Fecha Inicio</label>
					<input class="form-control" id="inicio" name="inicio" type="text" placeholder="dd/mm/aaaa" alt="date" required/>
					<span id="inicio.errors" class="help-block"></span>
				</div>
				<div class="form-group">
					<label for="fin">Fecha Fin</label>
					<input class="form-control" id="fin" name="fin" type="text" placeholder="dd/mm/aaaa" alt="date" required/>
					<span id="fin.errors" class="help-block"></span>
				</div>
				<button type="submit" class="btn btn-default">Guardar</button>
			</fieldset>
		</form>
	</div>
</div>