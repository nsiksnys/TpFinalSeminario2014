<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<h3>Alta de una pelicula</h3>
<br>
<div class="row">
	<div class="col-6"> <!-- por alguna razon class="col-md-6" no funciona -->
		<form:form class="form" method="post" modelAttribute="peliculaForm">
			<fieldset>
		        <form:errors path="" element="p" class="text-error"/>
				<div class="form-group">
					<label for="titulo">Titulo</label>
					<div class="form-group">
		                <form:input path="titulo" class="form-control" cssErrorClass="form-control" id="titulo"/>
		                <form:errors path="titulo" element="span" class="help-block"/>
		            </div>
		        </div>
				<div class="form-group">
					<label for="actores">Actores</label>
					<div class="form-group">
		                <form:input path="actores" class="form-control" cssErrorClass="form-control" id="actores"/>
		                <form:errors path="actores" element="span" class="help-block"/>
		            </div>
		        </div>
				<div class="form-group">
					<label for="director">Director</label>
					<div class="form-group">
		                <form:input path="director" class="form-control" cssErrorClass="form-control" id="director"/>
		                <form:errors path="director" element="span" class="help-block"/>
		            </div>
		        </div>
				<div class="form-group">
					<label for="sinopsis">Sinopsis del argumento</label>
					<div class="form-group">
		                <form:textarea path="sinopsis" class="form-control" cssErrorClass="form-control" id="sinopsis"/>
		                <form:errors path="sinopsis" element="span" class="help-block"/>
		            </div>
		        </div>
				<div class="form-group">
					<label for="trailer">Trailer (URL)</label>
					<input class="form-control" id="trailer" name="trailer" type="text" placeholder="www.youtube.com/watch?v=1111111" />
				</div>
				<div class="form-group">
					<label for="idioma">Idioma original</label>
					<div class="form-group">
		                <form:input path="idioma" class="form-control" cssErrorClass="form-control" id="idioma" placeholder="ES, EN, FR"/>
		                <form:errors path="idioma" element="span" class="help-block"/>
		            </div>
		        </div>
				<div class="checkbox">
					<label>Subtitulos? <input type="checkbox" name="subs" id="subs" /></label>
				</div>
				<div class="checkbox">
					<label>Reposicion? <input type="checkbox" id="reposicion" name="reposicion"/></label>
				</div>
				<div class="form-group">
					<label for="clasificacion">Clasificacion</label>
					<div class="form-group">
		                <form:input path="clasificacion" class="form-control" cssErrorClass="form-control" id="clasificacion" placeholder="ATP, 13, 16, 18"/>
		                <form:errors path="clasificacion" element="span" class="help-block"/>
		            </div>
		        </div>
				<div class="form-group">
					<label for="duracion">Duracion</label>
					<div class="form-group">
		                <form:input path="duracion" class="form-control" cssErrorClass="form-control" id="duracion" placeholder="HH:MM" alt="time"/>
		                <form:errors path="duracion" element="span" class="help-block"/>
		            </div>
		       </div>
				<button type="submit" class="btn btn-default">Guardar</button>
			</fieldset>
		</form:form>
	</div>
</div>