<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<h3>Peliculas</h3>
<br>
<table class="table table-bordered table-hover">
	<thead>
		<tr>
			<td>Id</td>
			<td>Pelicula</td>
			<td>Inicio</td>
			<td>Fin</td>
			<td>Proyeccion</td>
			<td>Subtitulos</td>
			<td>Modificar</td>
			<td>Eliminar</td>
		</tr>
	</thead>
	<tbody>
		<c:if test="${not empty lista}">
			<c:forEach items="${lista}" var="item">
				<tr>
					<td>${item.id}</td>
					<td>${item.pelicula.nombre}</td>
					<td><fmt:formatDate type="date" value="${item.fechaInicio}" pattern="dd-MM-yyyy"/></td>
					<td><fmt:formatDate type="date" value="${item.fechaFin}" pattern="dd-MM-yyyy"/></td>
					<td>${item.proyeccion}</td>
					<td>
						<c:if test="${item.subtitulada == true}"> Si </c:if>
						<c:if test="${item.subtitulada == false}"> No </c:if>
					</td>
					<td><a href='<s:url value="/cartelera/modificar?id=${item.id}"/>'>Modificar</a></td>
					<td>
						<c:if test="${item.activo == true}"> <a href='<s:url value="/cartelera/borrar?id=${item.id}"/>'>Desactivar</a> </c:if>
						<c:if test="${item.activo == false}"> <a href='<s:url value="/cartelera/activar?id=${item.id}"/>'>Activar</a> </c:if>
					</td>
				</tr>
			</c:forEach>
		</c:if>
		<c:if test="${empty lista}">
			<tr><td colspan="7"><center>No hay peliculas en la cartelera. <a href='<s:url value="/cartelera/alta"/>'>Agregar registros.</a></center></td></tr>
		</c:if>
	</tbody>
</table>