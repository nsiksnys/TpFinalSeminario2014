$( document ).ready(function() {
    var complejo;
    var pelicula;
    
    //validaciones
    var validacion = new validateHelper(); 
    validacion.setInputs(this);
    validacion.agregarExcluido("salas");

    //cargamos los select peliculas y complejos
    getPeliculas();
    
    if (getAccion().search("modificar") == -1)
    {
	getComplejos();
    }
    else
    {
	complejo = $("#complejos").attr("value");
	pelicula = $("#pelicula").attr("value");
		
	getHorarios(complejo, pelicula);
	
	$("#horarios").val( $("#horario").attr("value") );
    }
    	
    //cuando cambia cualquier select
    $( ":input" ).change(function(){
	//CASO ESPECIAL: COMPLEJOS
	//cuando se elige un complejo, que cargue la lista de salas
	if (this.id == "complejos"){
	    var idcomplejo = this.value;
	    if (idcomplejo != 0){
	    	getSalas(idcomplejo);
	    }
	}
	
	//Validaciones
	validacion.validarTodosNormal();//valido todos
	validacion.validar(this.id, this.value, "select");//valido este select
    });

    $("#buscarHorario").click(function () {
	complejo = $( "#complejos option:selected" ).val();
	pelicula = $( "#peliculas option:selected" ).val();
	
	if (complejo != 0 && pelicula != 0){
	    getHorarios(complejo, pelicula);
	}
    });

    $("[type='submit']" ).click(function(event) {
	validacion.validarTodosSubmit(event);
    });


    //llamadas por ajax
    function getComplejos(){
        var host =document.location.host;
        var url = "http://" + host + "/" + getContext() + "/complejo/getcomplejos";//url de la funcion getComplejos en controller de Complejos
        return $.getJSON(url, function(json) {
            if (typeof json === "undefined" || json=="" || json.length == 0)
            {
        	return;
            }
    	
            $("#complejos").empty();
            
            $.each(json, function (key, value) {
        	$("#complejos").append(parseOpcion(key, value));
            });
        })
        .success(function() {
            $("#complejos").change();
        });
    };
    
    function getPeliculas(){
        var host =document.location.host;
        var url = "http://" + host + "/" + getContext() + "/pelicula/getpeliculas";//url de la funcion getPeliculas en controller de Peliculas
        return $.getJSON(url, function(json) {
            if (typeof json === "undefined" || json=="" || json.length == 0)
            {
        	return;
            }
    	
            $("#peliculas").empty();
            
            $.each(json, function (key, value) {
        	$("#peliculas").append(parseOpcion(key, value));
            });
        });
    };
    
    function getSalas(complejo){
        var host =document.location.host;
        var url = "http://" + host + "/" + getContext() + "/sala/getsalas?complejo=" + complejo;
        return $.getJSON(url, function(json) {
            if (typeof json === "undefined" || json=="" || json.length == 0)
            {
        	return;
            }
    	
            $("#salas").empty();
            
            $.each(json, function (key, value) {
           	$("#salas").append(parseOpcion(key, value));
            });
        });
    };
    
    function getHorarios(complejo, pelicula){
        var host =document.location.host;
        var url = "http://" + host + "/" + getContext() + "/funcion/gethorarios?complejo=" + complejo + "&pelicula=" + pelicula;//url de la funcion getHorarios en controller de Funciones
        return $.getJSON(url, function(json) {
            if (typeof json === "undefined" || json=="" || json.length == 0)
            {
        	return;
            }
    	
            $("#horarios").empty();
            
            $.each(json, function (key, value) {
        	$("#horarios").append(parseOpcion(key, value));
            });
        });
    };

   function parseOpcion(key, value){
        return '<option value="' + key + '">' + value + '</option>';
    };
});
