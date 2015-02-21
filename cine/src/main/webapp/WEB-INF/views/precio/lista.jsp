<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<h3>Lista de Precios</h3>
<br>
	<table class="table table-bordered table-hover">
		<thead>
			<tr>
				<th>Id</th>
				<th>Menor</th>
				<th>General</th>
				<th>Mayor</th>
<!-- 				<td>Modificar</td> -->
				<th>Status</th>
			</tr>
		</thead>
		<tbody>
			<c:if test="${not empty lista}">
				<c:forEach items="${lista}" var="item">
					<tr>
						<td>${item.id}</td>
						<td>${item.menor}</td>
						<td>${item.general}</td>
						<td>${item.mayor}</td>

						<td>
							<c:if test="${item.activo ==true}"> <a href='<s:url value="/precio/borrar?id=${item.id}"/>'>Desactivar</a></c:if>
							<c:if test="${item.activo ==false}"> <a href='<s:url value="/precio/activar?id=${item.id}"/>'>Activar</a></c:if>
						</td>						
					</tr>
				</c:forEach>
			</c:if>
			<c:if test="${empty lista}">
				<tr><td colspan="6"><center>No hay precios ingresados. <a href='<s:url value="/precio/alta"/>'> Agregar Registro. </a></center></td></tr>
			</c:if>
		</tbody>
	</table>
	