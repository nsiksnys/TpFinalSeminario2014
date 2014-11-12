<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>

<h3>Reserva de entradas</h3>

<form action="pelicula" method="post">
	<div class="form-group">
		<label for="complejo">Elija el complejo</label>
		<select id="complejo" name="complejo">
			<c:if test="${empty complejos}">
				<option value=0>N/A</option>
			</c:if>
			<c:if test="${not empty complejos}">
				<c:forEach items="${complejos}" var="complejo">
					<option value="${complejo.id}">${complejo.nombre}</option>
				</c:forEach>
			</c:if>
		</select>
	</div>
	
	<div class="form-group">
		<label for="pelicula">Elija la pelicula</label>
		<select id="pelicula" name="pelicula">
			<c:if test="${empty peliculas}">
				<option value=0>N/A</option>
			</c:if>
			<c:if test="${not empty peliculas}">
				<c:forEach items="${peliculas}" var="pelicula">
					<option value="${pelicula.id}">${pelicula.nombre}</option>
				</c:forEach>
			</c:if>
		</select>
	</div>
	
	<button type="submit" class="btn btn-default">Siguiente</button>
</form>