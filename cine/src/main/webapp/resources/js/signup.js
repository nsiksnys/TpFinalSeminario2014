$( document ).ready(function() {
 //necesario para que el validateHelper funcione correctamente
    validacion = new validateHelperFecha();
    validacion.setInputs(this);
    validacion.agregarExcluido('sexo');
    
    validacion.validarTodosMenosExcluidos();
 
    validacion.agregarExcluido('dni');
   
  //cuando cambia un input
    $( ":input" ).change(function() {
	validacion.validarTodosMenosExcluidos();

	if (this.id == 'dni')
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
});