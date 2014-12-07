$( document ).ready(function() {    
    //chequeo que sea la pagina correcta, si no se sale del script
    if (getCarpeta() != "promocion"){
	console.log(getCarpeta() + " != promocion");
	return;
   }
    
  //necesario para que el validateHelper funcione correctamente
    validacion = new validateHelperFecha();
    validacion.setInputs(this);
    
    	validacion.validarTodos();
       
    
  //cuando cambia un input (incluye selects)
    $( ":input" ).change(function() {
	var element = this;//guardo el elemento en cuestion
	
	validacion.validarTodos();
	
	//casos
	if (element.id == "inicio" || element.id == "fin"){//fecha de inicio o fin
	    validacion.validar(element.id, element.value, element.id);
	}
	else{ //el resto
	    validacion.validar(element.id, element.value, element.alt);
	}
   });
    
    
    $("[type='submit']" ).click(function(event) {
    	validacion.validarTodosSubmit(event);
        });
    
    function parseOpcion(key, value){
        return '<option value="' + key + '">' + value + '</option>';
    };
    
});