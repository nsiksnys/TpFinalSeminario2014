$( document ).ready(function() {    
    //chequeo que sea la pagina correcta, si no se sale del script
    if (getCarpeta() != "complejo"){
	console.log(getCarpeta() + " != complejo");
	return;
   }
    
  //necesario para que el validateHelper funcione correctamente
    validacion = new validateHelper();
    validacion.setInputs(this);
    validacion.agregarExcluido('cantidad');
    
    validacion.validarTodosMenosExcluidos();
    
  //cuando cambia un input
    $( ":input" ).change(function() {
	validacion.validarTodosMenosExcluidos();
    });
    
    $("[type='submit']" ).click(function(event) {
	validacion.validarTodosSubmit(event);
    });
});