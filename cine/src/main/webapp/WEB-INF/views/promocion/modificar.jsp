<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>

<h3>Modificar una promocion</h3>
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
					<label for="nombre">Nombre</label>
					<input class="form-control" id="nombre" name="nombre" type="text" value="${registro.nombre}"/>
				</div>
				<div class="form-group">
					<label for="descripcion">Descripcion</label>
					<textarea class="form-control" id="descripcion" name="descripcion">${registro.descripcion}</textarea>
				</div>
				<div class="form-group">
					<label for="imagen">Imagen</label>
					<input class="form-control" id="imagen" name="imagen"  type="file" value="${registro.imagen}"/>
				</div>
				<div class="form-group">
					<label for="sinopsis">Fecha Inicio</label>
					<input class="form-control" id="fechainicio" name="fechainicio" type="date" value="${registro.fechainicio}"/>
				</div>
				<div class="form-group">
					<label for="fechafin">Fecha Fin</label>
					<input class="form-control" id="fechafin" name="fechafin" type="date" value="${registro.fechafin}"/>
				</div>
				<button type="submit" class="btn btn-default">Guardar</button>
			</fieldset>
		</form>
	</div>
</div>