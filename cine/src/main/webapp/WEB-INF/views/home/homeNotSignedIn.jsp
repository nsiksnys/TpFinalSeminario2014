<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>

<div class="hero-unit">
    <h1>Cine</h1>
    <p>
        Bienvenido.
        Inicie sesion o cree un usuario nuevo.
    </p>
    <p>
        <a href='<s:url value="/signup" />'class="btn btn-large btn-success">Registrarse</a>
    </p>
</div>
<div>
	<img src="<c:url value="/resources/imagenes/slide33.jpg"/>"/>
</div>
