<form class="form-narrow form-horizontal" action="dos" method="post">
	<fieldset>
        <legend>Recuperacion de cuenta</legend>
        <label>Por favor ingrese la respuesta a la pregunta de seguridad</label>
        <div class="form-group">
            <label for="pregunta" class="col-lg-2 control-label">Pregunta</label>
            <div class="col-lg-10">
                <input type="text" class="form-control" value="${pregunta}" disabled>
            </div>
        </div>
        <div class="form-group">
            <label for="respuesta" class="col-lg-2 control-label">Respuesta</label>
            <div class="col-lg-10">
                <input type="text" class="form-control" id="respuesta" name="respuesta" placeholder="Respuesta" required>
                <span id="dni.errors" class="help-block"></span>
            </div>
        </div>
        <!-- inputs necesarios para recuperar -->
        <input type="hidden" name="email" value="${email}" />
        <input type="hidden" name="dni" value="${dni}" />
        <div class="form-group">
            <div class="col-lg-offset-2 col-lg-10">
                <button type="submit" class="btn btn-default">Enviar</button>
            </div>
        </div>
	</fieldset>
</form>