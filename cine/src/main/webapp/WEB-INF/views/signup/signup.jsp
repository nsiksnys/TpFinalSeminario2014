<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<form:form class="form-narrow form-horizontal" method="post" modelAttribute="signupClientForm">

    <fieldset>
        <legend>Cree su cuenta</legend>
        <div>
           <label for="dni">DNI</label>
           <br><div>
               <input id="dni" name="dni" class="form-control" type="text" placeholder="DNI" alt="integer" required />
               <span id="dni.errors" class="help-block"></span>
           </div>
       </div>
       <br><div>
           <label for="nombre">Nombre</label>
           <br><div>
               <input name="nombre" id="nombre" class="form-control" type="text" placeholder="Nombre" required />
               <span id="nombre.errors" class="help-block"></span>
           </div>
       </div>
       <br><div>
           <label for="apellido">Apellido</label>
           <br><div>
               <input name="apellido" class="form-control" type="text" id="apellido" placeholder="Apellido" required />
               <span id="apellido.errors" class="help-block"></span>
           </div>
       </div>
       <br><div>
           <label for="sexo">Sexo</label>
           <br><div>
               <select class="form-control" id="sexo" name="sexo">
					<option value="M">Masculino</option>
					<option value="F">Femenino</option>
			</select>
           </div>
       </div>
       <br><div>
           <label for="email">Email</label>
           <br><div>
               <input name="email" class="form-control" id="email" placeholder="email" alt="email" required />
               <span id="email.errors" class="help-block"></span>
           </div>
        </div>
        <br><div>
            <label for="pasada">Fecha de Nacimiento</label>
            <br><div>
                <input name="fechaNacimiento" class="form-control" type="text" id="fechaNacimiento" placeholder="dd/mm/aaaa" alt="date"/>
                <span id="pasada.errors" class="help-block"></span>
            </div>
        </div>
        <br><div>
            <label for="password">Clave</label>
            <br><div>
                <input name="password" type="password" class="form-control" type="text" id="password" placeholder="Clave" required />
                <span id="password.errors" class="help-block"></span>
            </div>
        </div>
        <br><div>
            <label for="direccion">Direccion</label>
            <br><div>
                <input id="direccion" class="form-control" type="text" name="direccion" placeholder="direccion"/>
                <span id="direccion.errors" class="help-block"></span>
            </div>
        </div>
         <br><div>
            <label for="preguntaSeguridad">Pregunta de seguridad</label>
            <input name="preguntaSeguridad" class="form-control" id="preguntaSeguridad" value="${registro.preguntaSeguridad}" placeholder="Pregunta de seguridad" required/>
            <span id="preguntaSeguridad.errors" class="help-block"></span>
        </div>
        <br><div>
            <label for="respuestaSeguridad">Respuesta a la pregunta de seguridad</label>
            <input name="respuestaSeguridad" class="form-control" id="respuestaSeguridad" placeholder="Respuesta a la pregunta de seguridad" requried/>
               <span id="respuestaSeguridad.errors" class="help-block"></span>
        </div>
        <br><div>
            <label for="genero">Genero preferido (opcional)</label>
            <br><div>
                <input name="genero" class="form-control" type="text" id="genero" placeholder="genero"/>
                <span id="genero.errors" class="help-block"></span>
            </div>
        </div>
        <br>
        <br><div>
            <div class="col-lg-offset-2 col-lg-10">
                <button type="submit" class="btn btn-default">Registrarse</button>
            </div>
        </div>
        <br><div>
            <div class="col-lg-offset-2 col-lg-10">
                <p>¿Ya tiene una cuenta? <a href='<s:url value="/signin"/>'>Inicie sesion.</a></p>
            </div>
        </div>
    </fieldset>
</form:form>
