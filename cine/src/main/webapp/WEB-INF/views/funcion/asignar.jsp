<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="row">
	<div class="col-md-12 column">
		<form class="form-inline" role="form">
			<h3>Asignar Funciones</h3>
			<div class="row clearfix">
				<div class="col-md-6 column">
					<div class="row clearfix">
						<div class="col-md-6 column">
							<label for="complejos">Complejo: </label> </br>
							<select	name="complejos" size="5" style='width: 100%;'>
								<c:if test="${not empty complejos}">
									<c:forEach items="${complejos}" var="complejo">
										<option value="${complejo.id}">${complejo.nombre}</option>
									</c:forEach>
								</c:if>
								<c:if test="${empty complejos}">
									<option value="0">No hay complejos</option>
								</c:if>
							</select>
						</div>
						<div class="col-md-6 column">
							<label for="">Sala: </label> </br> 
							<select name="salas" size="5" style='width: 100%;'>
								<c:if test="${not empty salas}">
									<c:forEach items="${salas}" var="sala">
										<option value="${sala.id}">${sala.id}</option>
									</c:forEach>
								</c:if>
								<c:if test="${empty salas}">
									<option value="0">No hay salas</option>
								</c:if>
							</select>
						</div>
					</div>
				</div>
				<div class="col-md-6 column">
					<div class="row clearfix">
						<div class="col-md-6 column">
							<label for="peliculas">Peliculas: </label> </br>
							<select name="peliculas" size="5" style='width: 100%;'>
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
						<div class="col-md-6 column">
							<label for="horarios">Horarios: </label> </br>
							<select name="horarios" size="5" multiple style='width: 100%;'>
								<c:if test="${not empty horarios}">
									<c:forEach items="${horarios}" var="horario">
										<option value="${horario.id}">${horario.horaInicio}</option>
									</c:forEach>
								</c:if>
								<c:if test="${empty horarios}">
									<option value="0">No hay horarios</option>
								</c:if>
							</select>
						</div>
					</div>
				</div>
			</div>

			</br>
			<button type="button" class="btn btn-success">Guardar</button>
			<button type="button" class="btn btn-danger">Cancelar</button>
			</br> </br>
		</form>
	</div>
</div>