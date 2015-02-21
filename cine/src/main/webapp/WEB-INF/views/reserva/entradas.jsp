<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>

<h3>Seleccione el tipo de entrada</h3>

<form class="form" action="entradas" method="post">
	<fieldset>
		<div class="form-group">
			<br><label for="cantidadEntradas">Menor</label>
			<select id="menor" class="form-group" name="menor">
				<c:forEach begin="0" end="${cantidadEntradas}" varStatus="i">
					<option value="${i.index}"> ${i.index}</option> 
				</c:forEach>
			</select>
		</div>
		<div class="form-group">
			<label for="cantidadEntradas">Mayor</label>
			<select id="mayor" class="form-group" name="mayor">
				<c:forEach begin="0" end="${cantidadEntradas}" varStatus="i">
					<option value="${i.index}"> ${i.index}</option> 
				</c:forEach>
			</select>
		</div>
		<div class="form-group">
			<label for="cantidadEntradas">General</label>
			<select id="general" class="form-group" name="general">
				<c:forEach begin="0" end="${cantidadEntradas}" varStatus="i">
					<option value="${i.index}"> ${i.index}</option> 
				</c:forEach>
			</select>
		</div>
		<button type="submit" class="btn btn-default">Siguiente</button>
	</fieldset>
</form>
