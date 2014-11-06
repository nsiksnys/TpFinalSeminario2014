<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<h3>Asignar Funciones</h3>
<br>
<div class="row">
	<div class="col-6 column">
		<form class="form" action="guardar" method="post">
			<fieldset>
				<div class="form-group">
					<label for="complejos">Complejo: ${registro.nombreComplejo}</label>
					<input id="complejos" name="compeljos" type="hidden" value="${registro.complejo}">
				</div>
				<div class="form-group">
					<label for="salas">Sala: ${registro.nombreSala}</label>
					<input id="salas" name="salas" type="hidden" value="${registro.sala}">
				</div>
				<div class="form-group">
					<label for="peliculas">Pelicula</label> </br>
					<select id="peliculas" name="peliculas" class="form-control">
						<c:if test="${not empty peliculas}">
							<c:forEach items="${peliculas}" var="pelicula">
								<option value="${pelicula.id}">${peliculas.nombre}</option>
							</c:forEach>
						</c:if>
						<c:if test="${empty peliculas}">
							<option value="0">No hay peliculas</option>
						</c:if>
					</select>
				</div>
				<div class="form-group">
					<label for="horarios">Horarios</label> </br>
					<select name="horarios" size="5" multiple style='width: 100%;'>
						<c:if test="${not empty horarios}">
							<c:forEach items="${horarios}" var="horario">
								<option value="${horario.id}">${horario.horaInicio} - ${horario.horaFin}</option>
							</c:forEach>
						</c:if>
						<c:if test="${empty horarios}">
							<option value="0">No hay horarios</option>
						</c:if>
					</select>
				</div>
			</fieldset>

			<button type="button" class="btn btn-success">Guardar</button>
		</form>
	</div>
</div>