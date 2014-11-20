<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<h3>Alta de una pelicula</h3>
<br>

<div class="row">
	<div class="col-6"> <!-- por alguna razon class="col-md-6" no funciona -->
		<form:form class="form" method="post" modelAttribute="carteleraForm">
			<fieldset>
				<div class="form-group">
					<label for="pelicula">Titulo: ${registro.pelicula.nombre}</label>
					<input class="form-control" id="pelicula" name="pelicula" type="hidden" value="${registro.pelicula.id}"/>
				</div>
				<div class="form-group">
					<label for="version">Version a proyectar</label>
					<div class="form-group">
		                <form:input path="version" class="form-control" cssErrorClass="form-control" id="version" placeholder="2D, 3D" value="${registro.proyeccion}" alt="3a"/>
		                <form:errors path="version" element="span" class="help-block"/>
		            </div>
		        </div>
				<div class="checkbox">
					<label>Subtitulos? <input type="checkbox" id="subtitulos" name="subtitulos" value="${registro.subtitulada}"/></label>
				</div>
				<div class="form-group">
					<label for="inicio">Inicio de proyeccion</label>
					<div class="form-group">
		                <form:input path="inicio" class="form-control" cssErrorClass="form-control" id="inicio" placeholder="dd/mm/aaaa"  alt="date" value="<fmt:formatDate value="${registro.fechaInicio}" pattern="dd-MM-yyyy" />"/>
		                <form:errors path="inicio" element="span" class="help-block"/>
		            </div>
		        </div>
				<div class="form-group">
					<label for="fin">Fin de proyeccion</label>
					<div class="form-group">
		                <form:input path="fin" class="form-control" cssErrorClass="form-control" id="fin" placeholder="dd/mm/aaaa"  alt="date" value="<fmt:formatDate value="${registro.fechaFin}" pattern="dd-MM-yyyy" />" />
		                <form:errors path="fin" element="span" class="help-block"/>
		            </div>
		        </div>
				<button type="submit" class="btn btn-default">Guardar</button>
			</fieldset>
		</form:form>
	</div>
</div>