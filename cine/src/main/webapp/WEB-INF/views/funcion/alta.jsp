<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<h3>Asignar Funciones</h3>
<br>
<div class="row">
	<div class="col-6 column">
		<form class="form" action="guardar" method="post">
			<fieldset>
				<div class="form-group">
					<label for="complejos">Complejo</label>
					<select	id="complejos" name="complejos" class="form-control">
						<option value="0">No hay complejos</option>
					</select>
					<span id="complejos.errors" class="help-block"></span>
				</div>
				<div class="form-group">
					<label for="salas">Sala</label>
					<select id="salas" name="salas" class="form-control">
						<option value="0">No hay salas</option>
					</select>
					<span id="salas.errors" class="help-block"></span>
				</div>
				<div class="form-group">
					<label for="peliculas">Pelicula</label> </br>
					<select id="peliculas" name="peliculas" class="form-control">
						<option value="0">No hay peliculas</option>
					</select>
					<span id="peliculas.errors" class="help-block"></span>
				</div>
				<div>
					<label id="buscarHorario" name="buscarHorario" class="btn btn-success">Buscar Horarios</label>
				</div>
				<br>
				<div class="form-group">
					<label for="horarios">Horarios</label> </br>
					<select id="horarios" name="horarios" multiple class="form-control">
						<option value="0">Elija complejo, sala y pelicula</option>
					</select>
					<span id="horarios.errors" class="help-block"></span>
				</div>
			<button type="submit" class="btn btn-default">Guardar</button>
			</fieldset>
		</form>
	</div>
</div>
