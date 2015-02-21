<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<h3>Calcular las estadistica del Cine</h3>
<br>
<div class="row">
	<div class="col-6"> <!-- por alguna razon class="col-md-6" no funciona -->

		<form class="form" action="guardarAll" method="post">
			<fieldset>
			<h4>Evaluar Todo</h4>
				<div class="form-group">
					<label for="descripcion">Se generara las estadistica a partir de todos los registros.</label>
					<button type="submit" class="btn btn-default">Calcular</button>
				</div>
			</fieldset>
		</form>
		
						
		
		
		<form class="form" action="guardarRango" method="post">
			<fieldset>
			<input type="hidden" value="1" name="id" />
			<h4>Evaluar periodos</h4>
				<div class="form-group">
 					<label for="exampleSelectFecha">Fecha inicial</label>
							</br>
							<select id="fechaInicial" name="fechaInicial" style='width:100%;'>
							
							
							<c:if test="${not empty lista}">
							<c:forEach items="${lista}" var="reserva">
								<option value="${reserva.id}">${reserva.fechaReserva}</option>
							</c:forEach>
							</c:if>
							<c:if test="${empty lista}">
								<option value="0">No hay reservas</option>
							</c:if>
							
								
							</select>
				</div>
				<div class="form-group">
				<label for="exampleSelectFecha">Fecha final</label>
							</br>
							<select id="fechaFinal" name="fechaFinal" style='width:100%;'>
									<c:if test="${not empty lista}">
									<c:forEach items="${lista}" var="reserva">
										<option value="${reserva.id}">${reserva.fechaReserva}</option>
									</c:forEach>
									</c:if>
									<c:if test="${empty lista}">
										<option value="0">No hay reservas</option>
									</c:if>
							
							</select>
				</div>
				<button type="submit" class="btn btn-default">Calcular</button>
			</fieldset>
		</form>



	</div>
</div>	