$( document ).ready(function() {
//function selectOptionCliente(){
    if (document.location.pathname.split('/')[2] == "usuario")
    {
	$( "#role" ).change(function() {
	    if ($( "#role option:selected" ).val() == 'C')//si la opcion elegida es la de cliente
	    {
	 	$("[name='genero']").removeAttr("disabled");
	 	$("[name='direccion']").removeAttr("disabled");
	 	$("[name='genero']").show();
	 	$("[name='direccion']").show();
	 	$("[for='genero']").show();
	 	$("[for='direccion']").show();
		$("#direccionDiv").show();//muestro
		$("#generoDiv").show();
	    }
	    else
	    {
		$("#direccionDiv").hide();
		$("#generoDiv").hide();
		$("[name='genero']").attr("disabled", "");
	 	$("[name='direccion']").attr("disabled", "");
		$("[name='genero']").hide();
	 	$("[name='direccion']").hide();
	 	$("[for='genero']").hide();
	 	$("[for='direccion']").hide();
	    }
	});
    }
//}
});