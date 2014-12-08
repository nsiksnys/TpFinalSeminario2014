<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<h3>Sus reservas</h3>
<br>
<table class="table table-bordered table-hover">
	<thead>
		<tr>
			<td>Codigo</td>
			<td>Complejo</td>
			<td>Pelicula</td>
			<td>Fecha</td>
			<td>Horario</td>
			<td>Total</td>
			<td>Cancelar</td>
		</tr>
	</thead>
	<tbody>
		<c:if test="${not empty lista}">
			<c:forEach items="${lista}" var="item">
				<tr>
					<td>${item.codigo}</td>
					<td>${item.complejo}</td>
					<td>${item.pelicula}</td>
					<td>${item.fecha}</td>
					<td>${item.horario}</td>
					<td>${item.total}</td>
					<td><a href='<s:url value="/reserva/borrar?id=${item.id}"/>'>X</a></td>
				</tr>
			</c:forEach>
		</c:if>
		<c:if test="${empty lista}">
			<tr>
				<td colspan="7"><center>No hay reservas en la base de datos</center></td>
			</tr>
		</c:if>
	</tbody>
</table>