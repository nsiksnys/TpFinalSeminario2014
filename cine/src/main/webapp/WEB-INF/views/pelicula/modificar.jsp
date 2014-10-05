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
	  <p>Registro con id ${ok} modificado.</p>
	</div>
</c:if>

<div class="row">
	<div class="col-md-6">
		<form class="form" action="alta" method="post">
			<fieldset>
				<div class="form-group">
					<label for="titulo">Titulo</label>
					<input class="form-control" id="titulo" type="text" />
				</div>
				<div class="form-group">
					<label for="actores">Actores</label>
					<input class="form-control" id="actores" type="text" />
				</div>
				<div class="form-group">
					<label for="director">Director</label>
					<input class="form-control" id="director" type="text" />
				</div>
				<div class="form-group">
					<label for="sinopsis">Sinopsis del argumento</label>
					<textarea class="form-control" id="director"></textarea>
				</div>
				<div class="form-group">
					<label for="trailer">Trailer (URL)</label>
					<input class="form-control" id="trailer" type="text" placeholder="www.youtube.com/watch?v=1111111" />
				</div>
				<div class="form-group">
					<label for="idioma">Idioma original</label>
					<input class="form-control" id="idioma" type="text" placeholder="ES, EN, FR"/>
				</div>
				<div class="checkbox">
					<label>Reposicion? <input type="checkbox" /></label>
				</div>
				<div class="form-group">
					<label for="clasificacion">Clasificacion</label>
					<input class="form-control" id="clasificacion" type="text" placeholder="ATP, 13, 16, 18"/>
				</div>
				<button type="submit" class="btn btn-default">Guardar</button>
			</fieldset>
		</form>
	</div>
</div>