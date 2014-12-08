<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>

<h3>Indique los tipos de entrada</h3>

<form action="entradas" method="post">
	<fieldset>
		<div class="form-group">
			<label for="cantidad">Menor</label>
			<select id="menor" name="menor">
				<c:forEach begin="0" end="${cantidad}" varStatus="i">
					<option value="${i.count}"> ${i.count}</option> 
				</c:forEach>
			</select>
		</div>
		<div class="form-group">
			<label for="cantidad">Mayor</label>
			<select id="mayor" name="mayor">
				<c:forEach begin="0" end="${cantidad}" varStatus="i">
					<option value="${i.count}"> ${i.count}</option> 
				</c:forEach>
			</select>
		</div>
		<div class="form-group">
			<label for="cantidad">General</label>
			<select id="general" name="general">
				<c:forEach begin="0" end="${cantidad}" varStatus="i">
					<option value="${i.count}"> ${i.count}</option> 
				</c:forEach>
			</select>
		</div>
		<button type="submit" class="btn btn-default">Siguiente</button>
	</fieldset>
</form>
