$( document ).ready(function() {
	


    $("#fechaInicial").change(function () {
    	var fechaInicial = $( "#fechaInicial option:selected" ).val();
   
    	if (fechaInicial != 0){
    		getHoras(fechaInicial);
    	}
     });

    function getHoras(fechaInicial){
        var host =document.location.host;
        var url = "http://" + host + "/" + getContext() + "/stats/gethora?fechaInicial=" + fechaInicial;//url de la funcion getComplejosDisponibles en controller de Complejos
        
        return $.getJSON(url, function(json) {
            if (typeof json === "undefined" || json=="" || json.length == 0)
            {
        	return;
            }
            
            $("#fechaFinal").empty();
            
            $.each(json, function (key, value) {

            	$("#fechaFinal").append(parseOpcion(key, value));
            });
        });
    };    
    function parseOpcion(key, value){
        return '<option value="' + key + '">' + value + '</option>';
    };
});