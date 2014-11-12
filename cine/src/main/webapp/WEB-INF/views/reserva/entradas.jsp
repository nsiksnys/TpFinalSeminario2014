<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>

<h3>Reserva de entradas</h3>

<c:if test="${null error}">
	<form action="entradas" method="post">
		<b>Entradas</b>
		<div class="form-group">
			<label for="cantidad">Cantidad</label>
			<input type="text" name="cantidad" id="cantidad"/>
		</div>
		
		<b>Promociones</b>
		<div class="form-group">
			<label for="promo">Elija la promocion a utilizar</label>
			<select id="promo" name="promo">
				<c:if test="${empty promociones}">
					<option value=0>N/A</option>
				</c:if>
				<c:if test="${not empty promociones}">
					<option value=0>Ninguna</option>
					<c:forEach items="${promociones}" var="promo">
						<option value="${promo.id}">${promo.nombre}</option>
					</c:forEach>
				</c:if>
			</select>
		</div>
		<button type="submit" class="btn btn-default">Siguiente</button>
	</form>
</c:if>