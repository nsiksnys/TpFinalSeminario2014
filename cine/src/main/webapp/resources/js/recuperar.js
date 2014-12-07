$( document ).ready(function() {    
    //chequeo que sea la pagina correcta, si no se sale del script
    if (getCarpeta() != "recuperar"){
	console.log(getCarpeta() + " != recuperar");
	return;
   }
    
  //necesario para que el validateHelper funcione correctamente
    validacion = new validateHelper();
    validacion.setInputs(this);
    
    validacion.validarTodos();
    
  //cuando cambia un input
    $( ":input" ).change(function() {
	validacion.validarTodos();
    });
    
    $("[type='submit']" ).click(function(event) {
	validacion.validarTodosSubmit(event);
    });
});