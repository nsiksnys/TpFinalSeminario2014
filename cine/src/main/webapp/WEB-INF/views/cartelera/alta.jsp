<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>

<h3>Alta de una pelicula en cartelera</h3>
<br>
<div class="row">
	<div class="col-6"> <!-- por alguna razon class="col-md-6" no funciona -->
		<form class="form" action="alta" method="post">
			<fieldset>
				<div class="form-group">
					<label for="pelicula">Titulo</label>
					<select class="form-control" id="pelicula" name="pelicula">
						<option>No hay peliculas en la base de datos</option>
			        </select>
				</div>
				<div class="form-group">
					<label for="version">Version a proyectar</label>
					<input class="form-control" id="version" name="version" type="text" placeholder="2D, 3D" alt="3a" required/>
					<span id="version.errors" class="help-block"></span>
				</div>
				<div class="checkbox">
					<label>Subtitulos? <input type="checkbox" id="subtitulo" name="subtitulo"/></label>
				</div>
				<div class="form-group">
					<label for="inicio">Inicio de proyeccion (debe ser un jueves)</label>
					<input class="form-control" id="inicio" name="inicio" type="text" placeholder="dd/mm/aaaa" alt="date" required/>
					<span id="inicio.errors" class="help-block"></span>
				</div>
				<div class="form-group">
					<label for="fin">Fin de proyeccion</label>
					<input class="form-control" id="fin" name="fin" type="text" placeholder="dd/mm/aaaa" alt="date" required/>
					<span id="fin.errors" class="help-block"></span>
				</div>
				<button type="submit" class="btn btn-default">Guardar</button>
			</fieldset>
		</form>
	</div>
</div>