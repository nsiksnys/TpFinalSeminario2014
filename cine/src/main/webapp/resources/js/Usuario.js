$( document ).ready(function() {
 //necesario para que el validateHelper funcione correctamente
    validacion = new validateHelperFecha();
    validacion.setInputs(this);
    validacion.agregarExcluido('role');
    validacion.agregarExcluido('sexo');
    validacion.agregarExcluido('direccion');
    validacion.agregarExcluido('genero');
    validacion.agregarExcluido('preguntaSeguridad');
    validacion.agregarExcluido('respuestaSeguridad');
    
    validacion.validarTodosMenosExcluidos();
    selectRoles($("#role"));
    
  //cuando cambia un input
    $( ":input" ).change(function() {
	validacion.validarTodosMenosExcluidos();
	
	if (this.id == 'role')
	{
	    selectRoles(this);
	}
	else if (this.id == 'dni')
	{
	    this.value = this.value.replace(/\./g, "");
	    console.log(this.value);
	    validacion.validar(this.id, this.value, 'integer');
	}
	else if (this.id == 'fechaNacimiento')
	{
	    validacion.validar(this.id, this.value, 'pasada');
	}
    });
    
    $("[type='submit']" ).click(function(event) {
	if (getAccion() == "actual" && $("[type='submit']" ).text() == "Modificar")
	{
	    console.log("habilitando inputs");
	    event.preventDefault();
	    $( ":input" ).each(function() {
		    $(this).removeAttr("readonly");
	    });
	    
	    $("[type='submit']" ).text("Guardar");
	}
	
	validacion.validarTodosSubmit(event);
    });
      
    function selectRoles(element){
	if ($(element).val() == 'C'){
		mostrarOpcionCliente();
	    }
	    else{
		ocultarOpcionCliente();
	    }
    }
    
    function mostrarOpcionCliente(){
	$("#direccion").parent().parent().show();//muestro
	$("#genero").parent().parent().show();
    }
    
    function ocultarOpcionCliente(){
	$("#direccion").parent().parent().hide();
	$("#genero").parent().parent().hide();
    }
});