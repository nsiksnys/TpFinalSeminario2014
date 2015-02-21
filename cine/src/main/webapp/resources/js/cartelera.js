$( document ).ready(function() {    
    //chequeo que sea la pagina correcta, si no se sale del script
    if (getCarpeta() != "cartelera"){
	console.log(getCarpeta() + " != cartelera");
	return;
   }
    
  //necesario para que el validateHelper funcione correctamente
    validacion = new validateHelperFecha();
    validacion.setInputs(this);
    validacion.agregarExcluido("version");//este campo solo se evalua si se lo indica
    
    if (getAccion() == "alta")
	getPeliculas();
    validacion.validarTodos();
    
  //cuando cambia un input (incluye selects)
    $( ":input" ).change(function() {
	var element = this;//guardo el elemento en cuestion
	
	validacion.validarTodos();
	
	//casos
	if (element.id == "inicio" || element.id == "fin"){//fecha de inicio o fin
	    validacion.validar(element.id, element.value, element.id);
	}
	else if(element.id == "version" && !(element.value == "2D" || element.value == "3D") ){
	    validacion.showError('version', '');
	}
	else{//el resto
	    validacion.validar(element.id, element.value, element.alt);
	}
   });
    
    $("[type='submit']" ).click(function(event) {
	validacion.validarTodosSubmit(event);
    });

    function getPeliculas(){
        var host =document.location.host;
        var url = "http://" + host + "/" + getContext() + "/pelicula/getpeliculas";//url de la funcion getPeliculas en controller de Peliculas
        return $.getJSON(url, function(json) {
            if (typeof json === "undefined" || json=="" || json.length == 0)
            {
        	return;
            }
    	
            $("#pelicula").empty();
            
            $.each(json, function (key, value) {
        	$("#pelicula").append(parseOpcion(key, value));
            });
            validacion.hideErrorMessage('pelicula');
        }).fail (function() {
            	$("#pelicula").empty();
    		$("#pelicula").append(parseOpcion('0', 'N/A'));
    		validacion.showError('pelicula', 'select');
        });
    };
    
    function parseOpcion(key, value){
        return '<option value="' + key + '">' + value + '</option>';
    };
});