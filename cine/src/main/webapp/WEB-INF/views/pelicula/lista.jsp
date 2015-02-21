<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<!-- Si se guardo o actualizo bien, avisa -->
<c:if test="${not empty ok}">
	<div class="alert alert-dismissable alert-warning">
	  <button type="button" class="close" data-dismiss="alert">×</button>
	  <p>${ok}</p>
	</div>
</c:if>

<!-- Si se guardo bien, avisa -->
<c:if test="${not empty ok}">
	<div class="alert alert-dismissable alert-warning">
	  <button type="button" class="close" data-dismiss="alert">×</button>
	  <p>Nuevo registro guardado con id ${ok}</p>
	</div>
</c:if>

<h3>Peliculas</h3>
<br>
<table class="table table-bordered table-hover">
	<thead>
		<tr>
			<th>Id</th>
			<th>Nombre</th>
			<th>Fecha de creacion</th>
			<th>Modificar</th>
			<th>Status</th>
		</tr>
	</thead>
	<tbody>
		<c:if test="${not empty lista}">
			<c:forEach items="${lista}" var="item">
				<tr>
					<td>${item.id}</td>
					<td>${item.nombre}</td>
					<td><fmt:formatDate type="date" value="${item.fechaCreacion}"/></td>
					<!-- <td><a href='<s:url value="/pelicula/view?id=${item.id}"/>'>Ver</a></td> -->
					<td><a href='<s:url value="/pelicula/modificar?id=${item.id}"/>'>Modificar</a></td>
					<td>
						<c:if test="${item.activo == true}"> <a href='<s:url value="/pelicula/borrar?id=${item.id}"/>'>Desactivar</a> </c:if>
						<c:if test="${item.activo == false}"> <a href='<s:url value="/pelicula/activar?id=${item.id}"/>'>Activar</a> </c:if>
					</td>
				</tr>
			</c:forEach>
		</c:if>
		<c:if test="${empty lista}">
			<tr><td colspan="6"><center>No hay peliculas en la base de datos. <a href='<s:url value="/pelicula/alta"/>'>Agregar registro.</a></center></td></tr>
		</c:if>
	</tbody>
</table>