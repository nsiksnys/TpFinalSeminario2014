<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>

<h3>Modificar una pelicula</h3>
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
	  <p>${ok}</p>
	</div>
</c:if>

<div class="row">
	<div class="col-6">
		<form class="form" action="modificar" method="post">
			<fieldset>
				<div class="form-group">
					<label for="titulo">Id: ${registro.id} </label>
					<input class="form-control" id="id" name="id" type="hidden" value="${registro.id}"/>
				</div>
				<div class="form-group">
					<label for="titulo">Titulo</label>
					<input class="form-control" id="titulo" name="titulo" type="text" value="${registro.nombre}"/>
				</div>
				<div class="form-group">
					<label for="actores">Actores</label>
					<input class="form-control" id="actores" name="actores" type="text" value="${registro.detalles.actores}"/>
				</div>
				<div class="form-group">
					<label for="director">Director</label>
					<input class="form-control" id="director" name="director"  type="text" value="${registro.detalles.director}"/>
				</div>
				<div class="form-group">
					<label for="sinopsis">Sinopsis del argumento</label>
					<textarea class="form-control" id="sinopsis" name="sinopsis">${registro.detalles.descripcion}</textarea>
				</div>
				<div class="form-group">
					<label for="trailer">Trailer (URL)</label>
					<input class="form-control" id="trailer" name="trailer" type="text" placeholder="www.youtube.com/watch?v=1111111" value="${registro.detalles.urlTrailer}"/>
				</div>
				<div class="form-group">
					<label for="idioma">Idioma original</label>
					<input class="form-control" id="idioma" name="idioma" type="text" placeholder="ES, EN, FR" value="${registro.idioma}"/>
				</div>
				<div class="checkbox">
					<label>Reposicion? <input type="checkbox" id="reposicion" name="reposicion" value="${registro.reposicion}"/></label>
				</div>
				<div class="form-group">
					<label for="clasificacion">Clasificacion</label>
					<input class="form-control" id="clasificacion" name="clasificacion" type="text" placeholder="ATP, 13, 16, 18" value="${registro.clasificacion}"/>
				</div>
				<button type="submit" class="btn btn-default">Guardar</button>
			</fieldset>
		</form>
	</div>
</div>