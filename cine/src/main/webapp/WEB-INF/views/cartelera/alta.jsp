<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>

<h3>Alta de una pelicula en cartelera</h3>

<div class="row">
	<div class="col-6"> <!-- por alguna razon class="col-md-6" no funciona -->
		<form class="form" action="guardar" method="post">
			<fieldset>
				<div class="form-group">
					<label for="pelicula">Titulo</label>
					<select class="form-control" id="pelicula" name="pelicula">
						<c:if test="${not empty peliculas}">
							<c:forEach items="${peliculas}" var="item">
								<option value="${item.id}">${item.nombre}</option>
							</c:forEach>
						</c:if>
						<c:if test="${empty peliculas}">
							<option>No hay peliculas en la base de datos</option>
						</c:if>
			        </select>
				</div>
				<div class="form-group">
					<label for="version">Version a proyectar</label>
					<input class="form-control" id="version" name="version" type="text" placeholder="2D, 3D" alt="3a"/>
				</div>
				<div class="checkbox">
					<label>Subtitulos? <input type="checkbox" id="subtitulo" name="subtitulo"/></label>
				</div>
				<div class="form-group">
					<label for="inicio">Inicio de proyeccion</label>
					<input class="form-control" id="inicio" name="inicio" type="text" placeholder="dd/mm/aaaa" alt="date"/>
				</div>
				<div class="form-group">
					<label for="fin">Fin de proyeccion</label>
					<input class="form-control" id="fin" name="fin" type="text" placeholder="dd/mm/aaaa" alt="date"/>
				</div>
				<button type="submit" class="btn btn-default">Guardar</button>
			</fieldset>
		</form>
	</div>
</div>