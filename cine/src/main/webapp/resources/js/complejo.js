$( document ).ready(function() {    
    //chequeo que sea la pagina correcta, si no se sale del script
    if (getCarpeta() != "complejo"){
	console.log(getCarpeta() + " != complejo");
	return;
   }
    
  //necesario para que el validateHelper funcione correctamente
    validacion = new validateHelper();
    validacion.setInputs(this);
    validacion.agregarExcluido('salas');
    
    validacion.validarTodos();
    
  //cuando cambia un input
    $( ":input" ).change(function() {
	validacion.validarTodosMenosExcluidos();
	
	if (this.id == 'salas' && getAccion() == 'alta'){
	    validacion.validar('salas', this.value, 'integer');
	}
    });
    
    $("[type='submit']" ).click(function(event) {
	validacion.validarTodosSubmit(event);
    });
});