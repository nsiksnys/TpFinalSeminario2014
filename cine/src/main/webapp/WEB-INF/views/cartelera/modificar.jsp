<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<h3>Alta de una pelicula</h3>
<br>
<!-- Si hay un error, avisa -->

<div class="row">
	<div class="col-6"> <!-- por alguna razon class="col-md-6" no funciona -->
		<form class="form" action="modificar" method="post">
			<fieldset>
				<div class="form-group">
					<label for="id">Id: ${registro.id}</label>
					<input class="form-control" id="id" name="id" type="hidden" value="${registro.id}"/>
				</div>
				<div class="form-group">
					<label for="pelicula">Titulo: ${registro.pelicula.nombre}</label>
					<input class="form-control" id="pelicula" name="pelicula" type="hidden" value="${registro.pelicula.id}"/>
				</div>
				<div class="form-group">
					<label for="version">Version a proyectar</label>
					<input class="form-control" id="version" name="version" type="text" value="${registro.proyeccion}" alt="3a"/>
				</div>
				<div class="checkbox">
					<label>Subtitulos? <input type="checkbox" id="subtitulos" name="subtitulos" value="${registro.subtitulada}"/></label>
				</div>
				<div class="form-group">
					<label for="inicio">Inicio de proyeccion</label>
					<input class="form-control" id="inicio" name="inicio" type="text"  value="<fmt:formatDate value="${registro.fechaInicio}" pattern="dd-MM-yyyy" />" alt="date"/>
				</div>
				<div class="form-group">
					<label for="fin">Fin de proyeccion</label>
					<input class="form-control" id="fin" name="fin" type="text" value="<fmt:formatDate value="${registro.fechaFin}" pattern="dd-MM-yyyy" />" alt="date"/>
				</div>
				<button type="submit" class="btn btn-default">Guardar</button>
			</fieldset>
		</form>
	</div>
</div>