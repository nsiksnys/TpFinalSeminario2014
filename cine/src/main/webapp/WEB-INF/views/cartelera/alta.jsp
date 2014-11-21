<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<h3>Alta de una pelicula en cartelera</h3>

<div class="row">
	<div class="col-6"> <!-- por alguna razon class="col-md-6" no funciona -->
		<form:form class="form" method="post" modelAttribute="carteleraForm">
			<fieldset>
				<form:errors path="" element="p" class="text-error"/>
				<div class="form-group">
					<label for="pelicula">Titulo</label>
					<form:select class="form-control" id="pelicula" name="pelicula" path="pelicula" >
						<c:if test="${not empty peliculas}">
							<c:forEach items="${peliculas}" var="item">
								<option value="${item.id}">${item.nombre}</option>
							</c:forEach>
						</c:if>
						<c:if test="${empty peliculas}">
							<option value="0">No hay peliculas en la base de datos</option>
						</c:if>
			        </form:select>
				</div>
				<div class="form-group">
					<label for="version">Version a proyectar</label>
					<div class="form-group">
		                <form:input path="version" class="form-control" cssErrorClass="form-control" id="version" placeholder="2D, 3D" alt="3a"/>
		                <form:errors path="version" element="span" class="help-block"/>
		            </div>
		        </div>
				<div class="checkbox">
					<label>Subtitulos? <input type="checkbox" id="subtitulos" name="subtitulos"/></label>
				</div>
				<div class="form-group">
					<label for="inicio">Inicio de proyeccion</label>
				<div class="form-group">
		                <form:input path="inicio" class="form-control" cssErrorClass="form-control" id="inicio" placeholder="dd/mm/aaaa" alt="date"/>
		                <form:errors path="inicio" element="span" class="help-block"/>
		            </div>
		        </div>
				<div class="form-group">
					<label for="fin">Fin de proyeccion</label>
					<div class="form-group">
		                <form:input path="fin" class="form-control" cssErrorClass="form-control" id="fin" placeholder="dd/mm/aaaa" alt="date"/>
		                <form:errors path="fin" element="span" class="help-block"/>
		            </div>
		        </div>
				<button type="submit" class="btn btn-default">Guardar</button>
			</fieldset>
		</form:form>
	</div>
</div>