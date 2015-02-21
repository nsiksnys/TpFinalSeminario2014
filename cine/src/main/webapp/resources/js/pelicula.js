$( document ).ready(function() {    
    //chequeo que sea la pagina correcta, si no se sale del script
    if (getCarpeta() != "pelicula"){
	console.log(getCarpeta() + " != pelicula");
	return;
   }
    
  //necesario para que el validateHelper funcione correctamente
    validacion = new validateHelper();
    validacion.setInputs(this);
    validacion.agregarExcluido('trailer');
    
    validacion.validarTodosMenosExcluidos();
    
  //cuando cambia un input
    $( ":input" ).change(function() {
	validacion.validarTodosMenosExcluidos();
	
	if (this.id == 'trailer'){
	    if (this.val == '')
		validacion.hideErrorMessage(this.id);
	    else
		validacion.validar(this.id, this.value, 'url');
	}
    });
    
    $("[type='submit']" ).click(function(event) {
	validacion.validarTodosSubmit(event);
    });
});