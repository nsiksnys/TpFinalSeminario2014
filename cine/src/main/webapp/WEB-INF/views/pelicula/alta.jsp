<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>

<h3>Alta de una pelicula</h3>
<br>
<div class="row">
	<div class="col-6"> <!-- por alguna razon class="col-md-6" no funciona -->
		<form class="form" action="alta" method="post">
			<fieldset>
				<div class="form-group">
					<label for="titulo">Titulo</label>
					<input class="form-control" id="titulo" name="titulo" type="text" required />
					<span id="titulo.errors" class="help-block"></span>
				</div>
				<div class="form-group">
					<label for="actores">Actores</label>
					<input class="form-control" id="actores" name="actores" type="text" required />
					<span id="actores.errors" class="help-block"></span>
				</div>
				<div class="form-group">
					<label for="director">Director</label>
					<input class="form-control" id="director" name="director" type="text" required />
					<span id="director.errors" class="help-block"></span>
				</div>
				<div class="form-group">
					<label for="sinopsis">Sinopsis del argumento</label>
					<textarea class="form-control" id="sinopsis" name="sinopsis" required></textarea>
					<span id="sinopsis.errors" class="help-block"></span>
				</div>
				<div class="form-group">
					<label for="trailer">Trailer (URL)</label>
					<input class="form-control" id="trailer" name="trailer" type="text" placeholder="www.youtube.com/watch?v=1111111" />
					<span id="trailer.errors" class="help-block"></span>
				</div>
				<div class="form-group">
					<label for="idioma">Idioma original</label>
					<input class="form-control" id="idioma" name="idioma" type="text" placeholder="ES, EN, FR" alt="aa" required />
					<span id="idioma.errors" class="help-block"></span>
				</div>
				<div class="checkbox">
					<label>Subtitulos? <input type="checkbox" name="subs" id="subs" /></label>
				</div>
				<div class="checkbox">
					<label>Reposicion? <input type="checkbox" id="reposicion" name="reposicion"/></label>
				</div>
				<div class="form-group">
					<label for="clasificacion">Clasificacion</label>
					<input class="form-control" id="clasificacion" name="clasificacion" type="text" placeholder="ATP, 13, 16, 18" required/>
					<span id="clasificacion.errors" class="help-block"></span>
				</div>
				<div class="form-group">
					<label for="duracion">Duracion</label>
					<input class="form-control" id="duracion" name="duracion" type="text" placeholder="HH:MM" alt="time" required/>
					<span id="duracion.errors" class="help-block"></span>
				</div>
				<button type="submit" class="btn btn-default">Guardar</button>
			</fieldset>
		</form>
	</div>
</div>