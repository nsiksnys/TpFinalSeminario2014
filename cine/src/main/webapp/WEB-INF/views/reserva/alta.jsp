<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>

<!-- Por si se quiere mostrar todos los pasos en una sola ventana, excepto los asientos -->
<h3>Reserva de entradas</h3>
<br>
<div class="row">
	<div class="col-6"> <!-- por alguna razon class="col-md-6" no funciona -->
		<form action="guardar" method="post">
			<fieldset>
				<div class="form-group">
					<label for="complejo">Elija el complejo</label>
					<select id="complejo" name="complejo" class="form-control">
						<c:if test="${empty complejos}">
							<option value=0>N/A</option>
						</c:if>
						<c:if test="${not empty complejos}">
							<c:forEach items="${complejos}" var="complejo">
								<option value="${complejo.id}">${complejo.nombre}</option>
							</c:forEach>
						</c:if>
					</select>
				</div>
				
				<div class="form-group">
					<label for="pelicula">Elija la pelicula</label>
					<select id="pelicula" name="pelicula" class="form-control">
						<c:if test="${empty peliculas}">
							<option value=0>N/A</option>
						</c:if>
						<c:if test="${not empty peliculas}">
							<c:forEach items="${peliculas}" var="pelicula">
								<option value="${pelicula.id}">${pelicula.nombre}</option>
							</c:forEach>
						</c:if>
					</select>
				</div>
				
				<div class="form-group">
					<label for="funcion">Elija la funcion</label>
					<select id="funcion" name="funcion" class="form-control">
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
				
				<div class="form-group">
					<label for="cantidad">Cantidad de entradas</label>
					<input type="text" name="cantidad" id="cantidad" class="form-control"/>
				</div>
				
				<div class="form-group">
					<label for="promo">Elija la promocion a utilizar</label>
					<select id="promo" name="promo" class="form-control">
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
			</fieldset>
		</form>
	</div>
</div>