<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<table class="table">
	<thead>
		<tr>
			<th>#</th>
			<th>Complejo</th>
			<th>Sala</th>
			<th>Pelicula</th>
			<th>Horarios</th>
			<th>Opciones</th>
		</tr>
	</thead>
	<tbody>
		<c:if test="${not empty lista}">
			<c:forEach items="${lista}" var="item">
				<tr>
					<td>${item.id}</td>
					<td>${item.sala.complejo.nombre}</td>
					<td>${item.sala.id}</td>
					<td>${item.pelicula.nombre}</td>
					<td><a href='<s:url value="/view?id=${item.id}"/>'>Ver</a></td>
					<td><a href='<s:url value="/modificar?id=${item.id}"/>'>Modificar</a></td>
					<td><a href='<s:url value="/delete?id=${item.id}"/>'>X</a></td>
				</tr>
			</c:forEach>
		</c:if>
		<c:if test="${empty lista}">
			<tr><td colspan="7"><center>No hay peliculas en la cartelera</center></td></tr>
		</c:if>
		<tr>
		<td>1</td>
		<td>Complejo</td>
		<td>Sala</td>
		<td>Pelicula</td>
		<td>Horarios</td>
		<td>Modificar</td>
		<td>Borrar</td>
		</tr>
	</tbody>
</table>