<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<h3>Usuarios</h3>
<br>
<table class="table table-bordered table-hover">
	<thead>
		<tr>
			<th>DNI</th>
			<th>Nombre</th>
			<th>Apellido</th>
			<th>Mail</th>
			<th>Rol</th>
			<th>Modificar</th>
			<th>Status</th>
		</tr>
	</thead>
	<tbody>
		<c:if test="${not empty lista}">
			<c:forEach items="${lista}" var="item">
				<tr>
					<td>${item.dni}</td>
					<td>${item.nombre}</td>
					<td>${item.apellido}</td>
					<td>${item.email}</td>
					<td>${item.role}</td>
					<td><a href='<s:url value="/usuario/modificar?id=${item.email}"/>'>Modificar</a></td>
					<td>
						<c:if test="${item.active == true}"> <a href='<s:url value="/usuario/borrar?id=${item.email}"/>'>Desactivar</a> </c:if>
						<c:if test="${item.active == false}"> <a href='<s:url value="/usuario/activar?id=${item.email}"/>'>Activar</a> </c:if>
					</td>
				</tr>
			</c:forEach>
		</c:if>
		<c:if test="${empty lista}">
			<tr><td colspan="6"><center>No hay usuarios en la base de datos. <a href='<s:url value="/usuario/alta"/>'>Agregar registro.</a></center></td></tr>
		</c:if>
	</tbody>
</table>