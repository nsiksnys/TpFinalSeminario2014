<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<h3>Lista Complejos</h3>
<br>
<!-- 		<button type="button" class="btn btn-default">Crear nuevo complejo</button> -->
	<table class="table table-bordered table-hover">
		<thead>
			<tr>
				<td>Id</td>
				<td>Nombre</td>
				<td>Direccion</td>
				<td>Salas</td>
<!-- 				<td colspan="2">Opciones</td> -->
				<!-- <td>Detalles</td> -->
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
						<td>${item.direccion}</td>
						<td>${item.salas}</td>
<!-- 						<td> -->
<%-- 						<c:forEach items="${item.salas}" var="sala" varStatus="status"> --%>
<%--  							${sala.id}  --%>
<%--  							<c:if test="${not status.last}">, </c:if>  --%>
						 
<%-- 						</c:forEach> --%>
<!-- 						</td> -->
						<td><a href='<s:url value="/complejo/modificar?id=${item.id}"/>'>Modificar</a></td>
						<td>
							<c:if test="${item.activo == true}"> <a href='<s:url value="/complejo/borrar?id=${item.id}"/>'>Desactivar</a> </c:if>
							<c:if test="${item.activo == false}"> <a href='<s:url value="/complejo/activar?id=${item.id}"/>'>Activar</a> </c:if>
						</td>
<!-- 						<td> -->
<%-- 							<c:if test="${item.activo == true}"> <a href='<s:url value="/complejo/borrar?id=${item.id}"/>'>Desactivar</a> </c:if> --%>
<%-- 							<c:if test="${item.activo == false}"> <a href='<s:url value="/complejo/activar?id=${item.id}"/>'>Activar</a> </c:if> --%>
<!-- 						</td> -->
					</tr>
				</c:forEach>
			</c:if>
			<c:if test="${empty lista}">
				<tr><td colspan="6"><center>No existen complejos. <a href='<s:url value="/complejo/alta"/>'>Agregar registro.</a></center></td></tr>
			</c:if>
		</tbody>
	</table>