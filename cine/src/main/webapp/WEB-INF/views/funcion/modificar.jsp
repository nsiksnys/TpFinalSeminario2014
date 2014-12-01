<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<h3>Asignar Funciones</h3>
<br>
<div class="row">
	<div class="col-6 column">
		<form class="form" action="modificar" method="post">
			<fieldset>
				<div class="form-group">
					<label for="complejos">Id: </label>
					<input class="form-control" disabled value="${registro.id}">
					<input id="id" name="id" type="hidden" value="${registro.id}">
				</div>
				<div class="form-group">
					<label for="complejos">Complejo: </label>
					<input class="form-control" disabled value="${registro.nombreComplejo}">
					<input id="complejos" name="complejos" type="hidden" value="${registro.complejos}">
				</div>
				<div class="form-group">
					<label for="salas">Sala: </label>
					<input class="form-control" disabled value="${registro.nombreSala}">
					<input id="salas" name="salas" type="hidden" value="${registro.salas}">
				</div>
				<div class="form-group">
					<label for="peliculas">Pelicula</label> </br>
					<input name="peliculas" id="peliculas" type="hidden" value="${registro.peliculas}" />
					<input class="form-control" disabled value="${registro.nombrePelicula}" />
				</div>
				<div class="form-group">
					<label for="horarios">Horarios</label> </br>
					<input name="horario" id="horario" type="hidden" value="${registro.horarios}" />
					<select name="horarios" id="horarios" multiple class="form-control">
						<option value="0">Elija complejo, sala y pelicula</option>
					</select>
					<span id="horarios.errors" class="help-block"></span>
				</div>
			<button type="submit" class="btn btn-default">Guardar</button>
			</fieldset>
		</form>
	</div>
</div>