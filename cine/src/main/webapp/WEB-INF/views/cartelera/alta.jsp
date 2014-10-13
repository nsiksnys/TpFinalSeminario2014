<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>

<h3>Alta de una pelicula</h3>
<br>
<!-- Si hay un error, avisa -->
<c:if test="${not empty error}">
	<div class="alert alert-dismissable alert-warning">
	  <button type="button" class="close" data-dismiss="alert">×</button>
	  <p>${error}</p>
	</div>
</c:if>

<!-- Si se guardo bien, avisa -->
<c:if test="${not empty ok}">
	<div class="alert alert-dismissable alert-warning">
	  <button type="button" class="close" data-dismiss="alert">×</button>
	  <p>Nuevo registro guardado con id ${ok}</p>
	</div>
</c:if>

<div class="row">
	<div class="col-6"> <!-- por alguna razon class="col-md-6" no funciona -->
		<form class="form" action="alta" method="post">
			<fieldset>
				<div class="form-group">
					<label for="pelicula">Titulo</label>
					<select class="form-control" id="pelicula">
						<c:if test="${not empty peliculas}">
							<c:forEach items="${peliculas}" var="item">
								<option value="${item.id}">${item.nombre}</option>
							</c:forEach>
						</c:if>
						<c:if test="${empty lista}">
							<option>No hay peliculas en la base de datos</option>
						</c:if>
			        </select>
				</div>
				<div class="form-group">
					<label for="version">Version a proyectar</label>
					<input class="form-control" id="version" type="text" placeholder="2D, 3D"/>
				</div>
				<div class="checkbox">
					<label>Subtitulos? <input type="checkbox" id="subtitulo"/></label>
				</div>
				<div class="form-group">
					<label for="inicio">Inicio de proyeccion</label>
					<input class="form-control" id="inicio" type="text" placeholder="dd/mm/aaaa"/>
				</div>
				<div class="form-group">
					<label for="fin">Fin de proyeccion</label>
					<input class="form-control" id="fin" type="text" placeholder="dd/mm/aaaa"/>
				</div>
				<button type="submit" class="btn btn-default">Guardar</button>
			</fieldset>
		</form>
	</div>
</div>