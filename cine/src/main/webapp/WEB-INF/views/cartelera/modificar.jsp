<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<h3>Alta de una pelicula</h3>
<br>
<div class="row">
	<div class="col-6"> <!-- por alguna razon class="col-md-6" no funciona -->
		<form class="form" action="modificar" method="post">
			<fieldset>
				<div class="form-group">
					<label for="id">Id: ${registro.id}</label>
					<input class="form-control" id="id" name="id" type="hidden" value="${registro.id}"/>
				</div>
				<div class="form-group">
					<label for="pelicula">Titulo: ${titulo}</label>
					<input class="form-control" id="pelicula" name="pelicula" type="hidden" value="${registro.pelicula}"/>
				</div>
				<div class="form-group">
					<label for="version">Version a proyectar</label>
					<input class="form-control" id="version" name="version" type="text" value="${registro.version}" alt="3a" required/>
					<span id="version.errors" class="help-block"></span>
				</div>
				<div class="checkbox">
					<label>Subtitulos?
						<c:if test="${registro.subtitulos == true }">
							<input type="checkbox" name="subtitulos" id="subtitulos"  checked="checked"/>
						</c:if>
						<c:if test="${registro.subtitulos == false }">
							<input type="checkbox" name="subtitulos" id="subtitulos"/>
						</c:if>
					</label>
				</div>
				<div class="form-group">
					<label for="inicio">Inicio de proyeccion</label>
					<input class="form-control" id="inicio" name="inicio" type="text" value="${registro.inicio}" alt="date" required/>
					<span id="inicio.errors" class="help-block"></span>
				</div>
				<div class="form-group">
					<label for="fin">Fin de proyeccion</label>
					<input class="form-control" id="fin" name="fin" type="text" value="${registro.fin}" alt="date" required/>
					<span id="fin.errors" class="help-block"></span>
				</div>
				<button type="submit" class="btn btn-default">Guardar</button>
			</fieldset>
		</form>
	</div>
</div>