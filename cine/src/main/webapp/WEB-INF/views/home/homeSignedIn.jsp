<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib prefix="security" uri="http://www.springframework.org/security/tags" %>

<div class="alert alert-success">
     <button type="button" class="close" data-dismiss="alert">×</button>
     	 Bienvenido <security:authentication property="principal.username" />
</div>
<div>
	<img src="<c:url value="imagenes/slide33.jpg"/>"/>
</div>

