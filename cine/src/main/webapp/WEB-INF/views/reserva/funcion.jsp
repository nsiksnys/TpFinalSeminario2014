<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>

<h3>Reserva de entradas</h3>

<c:if test="${not empty error}">
	<div class="alert alert-dismissable alert-warning">
		<button type="button" class="close" data-dismiss="alert">x</button>
		<p>${error}</p>
	</div>
</c:if>

<c:if test="${null error}">
	<form action="funcion" method="post">
		<div class="form-group">
			<label for="funcion">Elija la funcion</label>
			<select id="funcion" name="funcion">
				<c:if test="${empty funciones}">
					<option value=0>N/A</option>
				</c:if>
				<c:if test="${not empty funciones}">
					<c:forEach items="${funciones}" var="funcion">
						<option value="${funcion.id}">${funcion.nombre}</option>
					</c:forEach>
				</c:if>
			</select>
		</div>
		<button type="submit" class="btn btn-default">Siguiente</button>
	</form>
</c:if>