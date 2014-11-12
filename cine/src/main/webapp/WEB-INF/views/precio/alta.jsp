<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>

<h3>Alta de Precios</h3>
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
	  <p>Nuevo registro guardado con id ${ok}</p>
	</div>
</c:if>

<div class="row">
	<div class="col-6"> <!-- por alguna razon class="col-md-6" no funciona -->
		<form class="form" action="guardar" method="post">
			<fieldset>
				<div class="form-group">
					<label for="menor">Menor</label>
					<input class="form-control" id="menor" name="menor" type="text" />
				</div>
				<div class="form-group">
					<label for="general">General</label>
					<input class="form-control" id="general" name="general" type="text" />
				</div>
				<div class="form-group">
					<label for="mayor">Mayor</label>
					<input class="form-control" id="mayor" name="mayor" type="text" />
				</div>
				<button type="submit" class="btn btn-default">Guardar</button>
			</fieldset>
		</form>
	</div>
</div>