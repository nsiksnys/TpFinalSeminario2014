<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>

<!-- Por si se quiere mostrar todos los pasos en una sola ventana, excepto los asientos -->
<h3>Reserva de entradas</h3>
<br>
<div class="row">
	<div class="col-6"> <!-- por alguna razon class="col-md-6" no funciona -->
		<form action="guardar" method="post">
			<fieldset>
				<div class="form-group">
					<label for="complejo">Elija el complejo</label>
					<select id="complejo" name="complejo" class="form-control">
						<option value=0>N/A</option>
					</select>
					<span id="complejo.errors" class="help-block"></span>
				</div>
				
				<div class="form-group">
					<label for="pelicula">Elija la pelicula</label>
					<select id="pelicula" name="pelicula" class="form-control">
						<option value=0>N/A</option>
					</select>
					<span id="pelicula.errors" class="help-block"></span>
				</div>
				
				<div class="form-group">
					<label for="funcion">Elija la funcion</label>
					<select id="funcion" name="funcion" class="form-control">
						<option value=0>N/A</option>
					</select>
					<span id="funcion.errors" class="help-block"></span>
				</div>
				
				<div class="form-group">
					<label for="cantidad">Cantidad de entradas</label>
					<input type="text" name="cantidad" id="cantidad" class="form-control" alt="integer" required/>
					<span id="cantidad.errors" class="help-block"></span>
				</div>
				
				<div class="form-group">
					<label for="promo">Elija la promocion a utilizar</label>
					<select id="promo" name="promo" class="form-control">
						<option value=0>N/A</option>
					</select>
					<span id="promo.errors" class="help-block"></span>
				</div>
					
				<button type="submit" class="btn btn-default">Siguiente</button>
			</fieldset>
		</form>
	</div>
</div>