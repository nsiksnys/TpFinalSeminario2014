<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<h3>Lista de Promociones</h3>
<br>
	<table class="table table-bordered table-hover">
		<thead>
			<tr>
				<td>Id</td>
				<td>Nombre</td>
				<td>Descripcion</td>
				<td>Fecha Inicio</td>
				<td>Fecha Fin</td>
<!-- 			<td>Estado</td> -->
				<td>Modificar</td>
				<td>Status</td>
			</tr>
		</thead>
		<tbody>
			<c:if test="${not empty lista}">
				<c:forEach items="${lista}" var="item">
					<tr>
						<td>${item.id}</td>
						<td>${item.nombre}</td>
						<td>${item.descripcion}</td>
						<td><fmt:formatDate type="date" value="${item.fechaInicio}"/></td>
						<td><fmt:formatDate type="date" value="${item.fechaFin}"/></td>
						<td><a href='<s:url value="/promocion/modificar?id=${item.id}"/>'>Modificar</a></td>
						<td>
							<c:if test="${item.activo ==true}"> <a href='<s:url value="/promocion/borrar?id=${item.id}"/>'>Desactivar</a></c:if>
							<c:if test="${item.activo ==false}"> <a href='<s:url value="/promocion/activar?id=${item.id}"/>'>Activar</a></c:if>
						</td>
					</tr>
				</c:forEach>
			</c:if>
			<c:if test="${empty lista}">
				<tr><td colspan="6"><center>No hay promociones ingresadas <a href='<s:url value="/promocion/alta"/>'> Agregar registro. </a></center></td></tr>
			</c:if>
		</tbody>
	</table>
