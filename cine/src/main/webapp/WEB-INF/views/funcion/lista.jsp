<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>

<h3>Funciones</h3>
<br>
<table class="table table-bordered table-hover">
	<thead>
		<tr>
			<th>Id</th>
			<th>Nombre Complejo</th>
			<th>Sala Numero</th>
			<th>Pelicula</th>
			<th>Inicio</th>
			<th>Fin</th>
			<th>Status</th>
			<th colspan="3">Opciones</th>
		</tr>
	</thead>
	<tbody>
		<c:if test="${not empty lista}">
			<c:forEach items="${lista}" var="item">
				<tr>
					<td>${item.id}</td>
					<td>${item.nombreComplejo}</td>
					<td>${item.nombreSala}</td>
					<td>${item.nombrePelicula}</td>
					<td>${item.inicio}</td>
					<td>${item.fin}</td>
					<td>
						<c:if test="${item.activo == true}"> Activa </c:if>
						<c:if test="${item.activo == false}"> Inactiva </c:if>
					</td>
					<!-- <td><a href='<s:url value="/funcion/detalles?id=${item.id}"/>'>Ver</a></td> -->
					<td><a href='<s:url value="/funcion/modificar?id=${item.id}"/>'>Modificar</a></td>
					<td>
						<c:if test="${item.activo == true}"> <a href='<s:url value="/funcion/borrar?id=${item.id}"/>'>Desactivar</a> </c:if>
						<c:if test="${item.activo == false}"> <a href='<s:url value="/funcion/activar?id=${item.id}"/>'>Activar</a> </c:if>
					</td>
				</tr>
			</c:forEach>
		</c:if>
		<c:if test="${empty lista}">
			<tr><td colspan="7"><center>No hay funciones en la cartelera</center></td></tr>
		</c:if>
	</tbody>
</table>
