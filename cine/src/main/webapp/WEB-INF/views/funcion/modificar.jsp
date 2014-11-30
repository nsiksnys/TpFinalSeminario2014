<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<h3>Asignar Funciones</h3>
<br>
<div class="row">
	<div class="col-6 column">
		<form class="form" action="modificar" method="post">
			<fieldset>
				<div class="form-group">
					<label for="complejos">Complejo: ${registro.nombreComplejo}</label>
					<input id="complejos" name="complejos" type="hidden" value="${registro.complejos}">
				</div>
				<div class="form-group">
					<label for="salas">Sala: ${registro.nombreSala}</label>
					<input id="salas" name="salas" type="hidden" value="${registro.salas}">
				</div>
				<div class="form-group">
					<label for="peliculas">Pelicula</label> </br>
					<input name="pelicula" id="pelicula" type="hidden" value="${registro.peliculas}" />
					<select id="peliculas" name="peliculas" class="form-control">
						<option value="0">No hay peliculas</option>
					</select>
				</div>
				<div class="form-group">
					<label for="horarios">Horarios</label> </br>
					<input name="horario" id="horario" type="hidden" value="${registro.horarios}" />
					<select name="horarios" id="horarios" class="form-control">
						<option value="0">Elija complejo, sala y pelicula</option>
					</select>
				</div>
			<button type="submit" class="btn btn-default">Guardar</button>
			</fieldset>
		</form>
	</div>
</div>