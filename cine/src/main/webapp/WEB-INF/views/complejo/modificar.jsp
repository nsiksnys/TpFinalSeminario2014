<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>

<h3>Modificar un complejo</h3>

<div class="row">
	<div class="col-6"> <!-- por alguna razon class="col-md-6" no funciona -->
		<form class="form" action="modificar" method="post">
			<fieldset>
				<div class="form-group">
					<label for="titulo">Id: ${registro.id} </label>
					<input class="form-control" id="id" name="id" type="hidden" value="${registro.id}"/>
				</div>
				<div class="form-group">
					<label for="nombre">Nombre</label>
					<input class="form-control" id="nombre" name="nombre" type="text" value="${registro.nombre}" required/>
					<span id="nombre.errors" class="help-block"></span>
				</div>
				<div class="form-group">
					<label for="direccion">Direccion</label>
					<input class="form-control" id="direccion" name="direccion" type="text" value="${registro.direccion}" required/>
					<span id="direccion.errors" class="help-block"></span>
				</div>
				<div class="form-group">
					<label for="salas">Salas</label><br>
					<c:if test="${empty registro.salas}">
						<label for="salas">No existen salas. Por favor ingrese la cantidad deseada.</label>
					</c:if>
					<c:if test="${not empty registro.salas}">
						<table class="table table-bordered table-hover">
							<thead>
								<tr>
									<th>Numero</th>
									<th>Status</th>
									<th></th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${registro.salas}" var="sala">
									<tr>
										<td>${sala.numeroSala}</td>
										<td>
											<c:if test="${sala.activa == true}"> Activo </c:if>
											<c:if test="${sala.activa == false}"> Inactivo </c:if>
										</td>
										<td>
											<c:if test="${sala.activa == true}"> <a href='<s:url value="/sala/borrar?id=${sala.id}"/>'>Desactivar</a> </c:if>
											<c:if test="${sala.activa == false}"> <a href='<s:url value="/sala/activar?id=${sala.id}"/>'>Activar</a> </c:if>
										</td>
									</tr>
								</c:forEach>
							</tbody>
						</table>
						<br>
						<label for="cantidad">Cantidad a agregar</label>
					</c:if>
						<input class="form-control" id="cantidad" name="salas" type="text" alt="99"/>
 						<span id="cantidad.errors" class="help-block"></span>
				</div>
				<button type="submit" class="btn btn-default">Guardar</button>
			</fieldset>
		</form>
	</div>
</div>