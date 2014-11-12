<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>

<h3>Reserva de entradas</h3>

<c:if test="${not empty error}">
	<div class="alert alert-dismissable alert-warning">
		<h3>Ha ocurrido un error</h3>
		<button type="button" class="close" data-dismiss="alert">x</button>
		<p>${error}</p>
	</div>
</c:if>

<c:if test="${not empty error}">
	<div class="alert alert-success">
		<h3>Detalles de su reserva</h3>
		<p><b>Codigo:</b> ${reserva.codigo}</p>
		<p><b>Complejo:</b> ${reserva.complejo}</p>
		<p><b>Pelicula:</b> ${reserva.pelicula}</p>
		<p><b>Horario:</b> ${reserva.horario}</p>
		<p><b>Asientos:</b> ${reserva.asientos}</p>
		<p><b>Promocion:</b> ${reserva.promo}</p>
		<p><b>Total:</b> ${reserva.total}</p>
	</div>
</c:if>