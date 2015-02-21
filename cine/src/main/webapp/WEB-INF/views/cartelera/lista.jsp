<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security"%>

<h3>Cartelera</h3>
<br>
<table class="table table-bordered table-hover">
	<thead>
		<tr>
			<th>Id</th>
			<th>Pelicula</th>
			<th>Fecha Inicio</th>
			<th>Fecha Fin</th>
			<th>Proyeccion 2D/3D</th>
			<th>Subtitulos</th>
			<security:authorize access="isAuthenticated()"> <security:authorize ifAllGranted="A">
			<th>Modificar</th>
			<th>Status</th>
			</security:authorize> </security:authorize>
		</tr>
	</thead>
	<tbody>
		<c:if test="${not empty lista}">
			<c:forEach items="${lista}" var="item">
				<tr>
					<security:authorize ifAllGranted="A">
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
					</security:authorize>
					<security:authorize ifAllGranted="C">
						<c:if test="${item.activo == true}">
							<td>${item.id}</td>
							<td>${item.pelicula.nombre}</td>
							<td><fmt:formatDate type="date" value="${item.fechaInicio}" pattern="dd-MM-yyyy"/></td>
							<td><fmt:formatDate type="date" value="${item.fechaFin}" pattern="dd-MM-yyyy"/></td>
							<td>${item.proyeccion}</td>
							<td>
								<c:if test="${item.subtitulada == true}"> Si </c:if>
								<c:if test="${item.subtitulada == false}"> No </c:if>
							</td>
						</c:if>
					</security:authorize>
				</tr>
			</c:forEach>
		</c:if>
		<c:if test="${empty lista}">
			<tr><td colspan="7"><center>No hay peliculas en la cartelera. <a href='<s:url value="/cartelera/alta"/>'>Agregar registros.</a></center></td></tr>
		</c:if>
	</tbody>
</table>