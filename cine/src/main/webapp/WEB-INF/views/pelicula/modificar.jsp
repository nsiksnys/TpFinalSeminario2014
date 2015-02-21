<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>

<h3>Modificar una pelicula</h3>
<br>
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
					<input class="form-control" id="titulo" name="titulo" type="text" value="${registro.nombre}" required/>
					<span id="titulo.errors" class="help-block"></span>
				</div>
				<div class="form-group">
					<label for="actores">Actores</label>
					<input class="form-control" id="actores" name="actores" type="text" value="${registro.detalles.actores}" required/>
					<span id="actores.errors" class="help-block"></span>
				</div>
				<div class="form-group">
					<label for="director">Director</label>
					<input class="form-control" id="director" name="director"  type="text" value="${registro.detalles.director}" required/>
					<span id="director.errors" class="help-block"></span>
				</div>
				<div class="form-group">
					<label for="sinopsis">Sinopsis del argumento</label>
					<textarea class="form-control" id="sinopsis" name="sinopsis" required>${registro.detalles.descripcion}</textarea>
					<span id="sinopsis.errors" class="help-block"></span>
				</div>
				<div class="form-group">
					<label for="trailer">Trailer (URL)</label>
					<input class="form-control" id="trailer" name="trailer" type="text" placeholder="www.youtube.com/watch?v=1111111" value="${registro.detalles.urlTrailer}"/>
					<span id="version.trailer" class="help-block"></span>
				</div>
				<div class="form-group">
					<label for="idioma">Idioma original</label>
					<input class="form-control" id="idioma" name="idioma" type="text" placeholder="ES, EN, FR" alt="aa" value="${registro.idioma}" required/>
					<span id="idioma.errors" class="help-block"></span>
				</div>
				<div class="checkbox">
					<label>Reposicion?
						<c:if test="${registro.reposicion == true }">
							<input type="checkbox" name="reposicion" id="reposicion"  checked="checked"/>
						</c:if>
						<c:if test="${registro.reposicion == false }">
							<input type="checkbox" name="reposicion" id="reposicion"/>
						</c:if>
					</label>
				</div>
				<div class="form-group">
					<label for="clasificacion">Clasificacion</label>
					<input class="form-control" id="clasificacion" name="clasificacion" type="text" placeholder="ATP, 13, 16, 18" value="${registro.clasificacion}" required/>
					<span id="clasificacion.errors" class="help-block"></span>
				</div>
				<div class="form-group">
					<label for="duracion">Duracion</label>
					<input class="form-control" id="duracion" name="duracion" type="text" placeholder="HH:MM" alt="time" value="${registro.duracion}" required/>
					<span id="duracion.errors" class="help-block"></span>
				</div>
				<button type="submit" class="btn btn-default">Guardar</button>
			</fieldset>
		</form>
	</div>
</div>