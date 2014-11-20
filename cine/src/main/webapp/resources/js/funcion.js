$( document ).ready(function() {
    var complejo;
    var pelicula;
 /*   
  //cuando la verificacion falla y se recarga la pagina con el select vacio
    if ($( "#peliculas option:selected" ).val() == "0")
    {
	getPeliculasDisponibles();
    }
    
  //cuando la verificacion falla y se recarga la pagina con el select vacio
    if ($( "#complejos option:selected" ).val() == "0")
    {
	getComplejosDisponibles();
    }
*/    
    $("#buscarHorario").click(function () {
	complejo = $( "#complejos option:selected" ).val();
	pelicula = $( "#peliculas option:selected" ).val();
	
	if (complejo != 0 && pelicula != 0){
	    getHorariosDisponibles(complejo, pelicula);
	}
    });

    function getHorariosDisponibles(complejo, pelicula){
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

    function getPeliculasDisponibles(){
        var host =document.location.host;
        var url = "http://" + host + "/" + getContext() + "/pelicula/getpeliculas";//url de la funcion getPeliculasDisponibles en controller de Peliculas
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
    
    function getComplejosDisponibles(){
        var host =document.location.host;
        var url = "http://" + host + "/" + getContext() + "/complejo/getcomplejos";//url de la funcion getComplejosDisponibles en controller de Complejos
        return $.getJSON(url, function(json) {
            if (typeof json === "undefined" || json=="" || json.length == 0)
            {
        	return;
            }
    	
            $("#complejos").empty();
            
            $.each(json, function (key, value) {
        	$("#complejos").append(parseOpcion(key, value));
            });
        });
    };
    
    function parseOpcion(key, value){
        return '<option value="' + key + '">' + value + '</option>';
    };
});
