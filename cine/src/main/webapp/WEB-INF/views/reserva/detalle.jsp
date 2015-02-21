<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>

<h3>Detalle</h3>

<div class="alert alert-info">
		<p><b>Codigo:</b> ${registro.codigo}</p>
		<p><b>Complejo:</b> ${registro.complejo}</p>
		<p><b>Pelicula:</b> ${registro.pelicula}</p>
		<p><b>Horario:</b> ${registro.fecha}, ${registro.funcion} hs</p>
		<p><b>Asientos:</b> ${registro.asientos}</p>
		<p><b>Promocion:</b> ${registro.promo}</p>
		<p><b>Total:</b> ARS ${registro.total}</p>
</div>
<div class="form-group">
	<a href='<s:url value="/reserva/lista"/>'><label>Ir a la lista de reservas</label></a>
</div>