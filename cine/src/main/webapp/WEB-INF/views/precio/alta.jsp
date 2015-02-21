<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>

<h3>Alta de Precios1</h3>
<br>
<div class="row">
	<div class="col-6"> <!-- por alguna razon class="col-md-6" no funciona -->
		<form class="form" action="alta" method="post">
			<fieldset>
				<div class="form-group">
					<label for="menor">Menor</label>
					<input class="form-control" id="menor" name="menor" type="text" placeholder="$00.00" alt="decimal-us" required/>
					<span id="menor.errors" class="help-block"></span>
				</div>
				<div class="form-group">
					<label for="general">General</label>
					<input class="form-control" id="general" name="general" type="text" placeholder="$00.00" alt="decimal-us" required/>
					<span id="general.errors" class="help-block"></span>
				</div>
				<div class="form-group">
					<label for="mayor">Mayor</label>
					<input class="form-control" id="mayor" name="mayor" type="text" placeholder="$00.00" alt="decimal-us" required/>
					<span id="mayor.errors" class="help-block"></span>
				</div>
				<button type="submit" class="btn btn-default">Guardar</button>
			</fieldset>
		</form>
	</div>
</div>