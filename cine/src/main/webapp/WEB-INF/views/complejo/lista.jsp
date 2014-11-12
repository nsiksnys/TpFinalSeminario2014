<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>

<h3>Complejos</h3>
<br>
<div class="row">
	<div class="col-md-12">
<!-- 		<button type="button" class="btn btn-default">Crear nuevo complejo</button> -->
		<table class="table table-bordered table-hover">
			<thead>
				<tr>
					<th>Id</th>
					<th>Nombre</th>
					<th>Direccion</th>
					<th>Salas</th>
					<th colspan="2">Opciones</th>
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
							<td><a href='<s:url value="/complejo/modificar?id=${item.id}"/>'>Modificar</a></td>
							<td>
								<c:if test="${item.activo == true}"> <a href='<s:url value="/complejo/borrar?id=${item.id}"/>'>Desactivar</a> </c:if>
								<c:if test="${item.activo == false}"> <a href='<s:url value="/complejo/activar?id=${item.id}"/>'>Activar</a> </c:if>
							</td>
						</tr>
					</c:forEach>
				</c:if>
				<c:if test="${empty lista}">
					<tr><td colspan="6"><center>No existen complejos. <a href='<s:url value="/complejo/alta"/>'>Agregar registro.</a></center></td></tr>
				</c:if>
			</tbody>
		</table>
	</div>
</div>
