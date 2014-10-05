<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<h3>Peliculas</h3>
<br>
<table class="table table-bordered table-hover">
	<thead>
		<tr>
			<td>Id</td>
			<td>Nombre</td>
			<td>Fecha de creacion</td>
			<td>Detalles</td>
			<td>Modificar</td>
			<td>Eliminar</td>
		</tr>
	</thead>
	<tbody>
		<c:if test="${not empty lista}">
			<c:forEach items="${lista}" var="item">
				<tr>
					<td>${item.id}</td>
					<td>${item.nombre}</td>
					<td><fmt:formatDate type="date" value="${adicional.fechaCreacion}"/></td>
					<td><a href='<s:url value="/view?id=${item.id}"/>'>Ver</a></td>
					<td><a href='<s:url value="/modificar?id=${item.id}"/>'>Modificar</a></td>
					<td><a href='<s:url value="/view?id=${item.id}"/>'>X</a></td>
				</tr>
			</c:forEach>
		</c:if>
		<c:if test="${empty lista}">
			<tr><td colspan="6"><center>No hay peliculas en la base de datos</center></td></tr>
		</c:if>
	</tbody>
</table>