<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<form:form class="form-narrow form-horizontal" method="post" modelAttribute="signupClientForm">

    <fieldset>
        <legend>Cree su cuenta</legend>
        <form:errors path="" element="p" class="text-error"/>
        <div class="form-group">
            <label for="email" class="col-lg-2 control-label">Email</label>
            <div class="col-lg-10">
                <form:input path="email" class="form-control" cssErrorClass="form-control" id="email" placeholder="email"/>
                <form:errors path="email" element="span" class="help-block"/>
            </div>
        </div>
        <div class="form-group">
            <label for="password" class="col-lg-2 control-label">Clave</label>
            <div class="col-lg-10">
                <form:password path="password" class="form-control" id="password" placeholder="Clave"/>
                <form:errors path="password" element="span" class="help-block"/>
            </div>
        </div>
        <div class="form-group">
            <label for="nombre" class="col-lg-2 control-label">Nombre</label>
            <div class="col-lg-10">
                <form:input path="nombre" class="form-control" cssErrorClass="form-control" id="nombre" placeholder="Nombre"/>
                <form:errors path="nombre" element="span" class="help-block"/>
            </div>
        </div>
        <div class="form-group">
            <label for="apellido" class="col-lg-2 control-label">Apellido</label>
            <div class="col-lg-10">
                <form:input path="apellido" class="form-control" cssErrorClass="form-control" id="apellido" placeholder="Apellido"/>
                <form:errors path="apellido" element="span" class="help-block"/>
            </div>
        </div>
        <div class="form-group">
            <label for="dni" class="col-lg-2 control-label">DNI</label>
            <div class="col-lg-10">
                <form:input path="dni" class="form-control" cssErrorClass="form-control" id="dni" placeholder="DNI"/>
                <form:errors path="dni" element="span" class="help-block"/>
            </div>
        </div>
        <div class="form-group">
            <div class="col-lg-offset-2 col-lg-10">
                <button type="submit" class="btn btn-default">Registrarse</button>
            </div>
        </div>
        <div class="form-group">
            <div class="col-lg-offset-2 col-lg-10">
                <p>¿Ya tiene una cuenta? <a href='<s:url value="/signin"/>'>Inicie sesion.</a></p>
            </div>
        </div>
    </fieldset>
</form:form>
