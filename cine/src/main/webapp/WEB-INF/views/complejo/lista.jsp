<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<div class="row">
	<div class="col-md-12">
		<button type="button" class="btn btn-default">Crear nuevo complejo</button>
		<table class="table">
			<thead>
				<tr>
					<th>#</th>
					<th>Nombre del complejo</th>
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
							<td><a href='<s:url value="/modificar?id=${item.id}"/>'>Modificar</a></td>
							<td><a href='<s:url value="/delete?id=${item.id}"/>'>Borrar</a></td>
						</tr>
					</c:forEach>
				</c:if>
				<c:if test="${empty lista}">
					<tr><td colspan="6"><center>No hay peliculas en la cartelera</center></td></tr>
				</c:if>
			</tbody>
		</table>
	</div>
</div>
