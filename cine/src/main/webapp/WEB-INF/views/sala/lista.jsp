<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>

<h3>Salas</h3>
<br>
<div class="row">
	<div class="col-md-12">
<!-- 		<button type="button" class="btn btn-default">Crear nuevo complejo</button> -->
		<table class="table table-bordered table-hover">
			<thead>
				<tr>
					<th>Id</th>
					<th>Nombre Complejo</th>
					<th>Sala Numero</th>
					<th>Status</th>
<!-- 					<th></th> -->
					<th colspan="3">Opciones</th>
				</tr>
			</thead>
			<tbody>
				<c:if test="${not empty lista}">
					<c:forEach items="${lista}" var="item">
						<tr>
							<td>${item.id}</td>
							<td>${item.complejo}</td>
							<td>${item.numeroSala}</td>
							<td>
								<c:if test="${item.activa == true}">Activo</c:if>
								<c:if test="${item.activa == false}">Inactivo</c:if>
							</td>
							<td>
								<c:if test="${item.activa == true}"> <a href='<s:url value="/sala/borrar?id=${item.id}"/>'>Desactivar</a> </c:if>
								<c:if test="${item.activa == false}"> <a href='<s:url value="/sala/activar?id=${item.id}"/>'>Activar</a> </c:if>
							</td>
						</tr>
					</c:forEach>
				</c:if>
				<c:if test="${empty lista}">
					<tr><td colspan="6"><center>No existen salas. <a href='<s:url value="/complejo/lista"/>'>Edite o cree un complejo para agregar salas.</a></center></td></tr>
				</c:if>
			</tbody>
		</table>
	</div>
</div>
