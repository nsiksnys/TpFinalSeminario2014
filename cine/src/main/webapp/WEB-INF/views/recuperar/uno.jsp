<form class="form-narrow form-horizontal" action="uno" method="post">
	<fieldset>
        <legend>Recuperacion de cuenta</legend>
        <div class="form-group">
        	<label>Por favor ingrese los siguientes datos</label>
        </div>
        <div class="form-group">
            <label for="Email" class="col-lg-2 control-label">Email</label>
            <div class="col-lg-10">
                <input class="form-control" id="email" name="email" alt="email" placeholder="yo@ejemplo.com" required>
                <span id="email.errors" class="help-block"></span>
            </div>
        </div>
        <div class="form-group">
            <label for="dni" class="col-lg-2 control-label">DNI</label>
            <div class="col-lg-10">
                <input type="text" class="form-control" id="dni" name="dni" placeholder="DNI" alt="integer" required>
                <span id="dni.errors" class="help-block"></span>
            </div>
        </div>
        <div class="form-group">
            <div class="col-lg-offset-2 col-lg-10">
                <button type="submit" class="btn btn-default">Enviar</button>
            </div>
        </div>
	</fieldset>
</form>