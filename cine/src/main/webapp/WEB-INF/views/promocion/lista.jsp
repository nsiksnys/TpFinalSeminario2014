<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<h3>Promociones</h3>
<br>
<div class="row">
	<div class="col-md-12">
		<table class="table table-bordered table-hover">
			<thead>
				<tr>
					<th>Id</th>
					<th>Nombre</th>
					<th>Descripcion</th>
					<th>Fecha Inicio</th>
					<th>Fecha Fin</th>
					<th>Estado</th>
					<th>Modificar</th>
					<th>Eliminar</th>
				</tr>
			</thead>
			<tbody>
				<c:if test="${not empty lista}">
					<c:forEach items="${lista}" var="item">
						<tr>
							<td>${item.id}</td>
							<td>${item.nombre}</td>
							<td>${item.descripcion}</td>
							<td>${item.fechaInicio}</td>
							<td>${item.fechaFin}</td>
							<td>${item.fechaEstado}</td>
							<td><a href='<s:url value="/modificar?id=${item.id}"/>'>Modificar</a></td>
							<td><a href='<s:url value="/borrar?id=${item.id}"/>'>Eliminar</a></td>
						</tr>
					</c:forEach>
				</c:if>
				<c:if test="${empty lista}">
					<tr><td colspan="7"><center>No hay promociones ingresadas a la fecha</center></td></tr>
				</c:if>
			</tbody>
		</table>
	</div>
</div>