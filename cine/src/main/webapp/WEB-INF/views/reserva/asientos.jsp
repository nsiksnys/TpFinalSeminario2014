<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="security"%>

<h3>Por favor seleccione su(s) asiento(s)</h3>

<div class="row">
	<div class="col-6">
		<!-- por alguna razon class="col-md-6" no funciona -->
		<input type="hidden" id="funcion" value="${funcion}" />
		<form class="form" action="asientos" method="post">
			<fieldset>
				<div class="form-group">
					<label for="pantalla">PANTALLA</label>
				</div>
				<br> <label>Primeras filas</label>
				<div class="form-group"></div>
				<div class="asientos">
					<table id="t01">
						<c:forEach begin="0" end="9" varStatus="fila">
							<tr>
								<c:forEach begin="1" end="10" varStatus="col">
									<td onchange="this.style.backgroundColor='#006600';">
										<input type="checkbox" name="checkbox" id="${fila.index * 10 + col.index}" value="${fila.count},${col.index}">
									</td>
								</c:forEach>
							</tr>
						</c:forEach>
					</table>
					<label>Ultimas filas</label>
				</div>
				<button type="submit" class="btn btn-default">Seleccionar</button>
			</fieldset>
		</form>
	</div>
</div>