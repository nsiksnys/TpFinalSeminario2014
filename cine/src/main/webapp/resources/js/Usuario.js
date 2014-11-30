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
    
  //cuando cambia un input
    $( ":input" ).change(function() {
	validacion.validarTodosMenosExcluidos();
	
	if (this.id == 'role')
	{
	    if ($(this).val() == 'C'){
		mostrarOpcionCliente();
	    }
	    else{
		ocultarOpcionCliente();
	    }
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
	validacion.validarTodosSubmit(event);
    });
      
    
    function mostrarOpcionCliente(){
	$("#direccion").parent().parent().show();//muestro
	$("#genero").parent().parent().show();
    }
    
    function ocultarOpcionCliente(){
	$("#direccion").parent().parent().hide();
	$("#genero").parent().parent().hide();
    }
});