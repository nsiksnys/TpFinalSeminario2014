<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<h3>Modificar una promocion</h3>
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
					<label for="nombre">Nombre</label>
					<input class="form-control" id="nombre" name="nombre" type="text" value="${registro.nombre}" required/>
					<span id="nombre.errors" class="help-block"></span>
				</div>
				<div class="form-group">
					<label for="descripcion">Descripcion</label>
					<textarea class="form-control" id="descripcion" name="descripcion" required>${registro.descripcion}</textarea>
					<span id="descripcion.errors" class="help-block"></span>
				</div>
				<div class="form-group">
					<label for="inicio">Fecha Inicio</label>
					<input class="form-control" id="inicio" name="inicio" type="text" placeholder="dd/mm/aaaa"  value="${registro.fechaInicio}" alt="date" required/>
					<span id="inicio.errors" class="help-block"></span>
				</div>
				<div class="form-group">
					<label for="fin">Fecha Fin</label>
					<input class="form-control" id="fin" name="fin" type="text" placeholder="dd/mm/aaaa" value="${registro.fechaFin}" alt="date" required/>
					<span id="fin.errors" class="help-block"></span>
				</div>
				<button type="submit" class="btn btn-default">Guardar</button>
			</fieldset>
		</form>
	</div>
</div>