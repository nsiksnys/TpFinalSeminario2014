$( document ).ready(function() {
    var funcion = $("#funcion").val();
    var fecha  = $("#fecha").val();
    
    deshabilitarCheckboxes();
    
    function deshabilitarCheckboxes(){
        $( ":checkbox" ).each(function() {
        	isCheckboxActivo(this.id, funcion,fecha)
        });
    };
    
    function getIdFuncion(){
	var host =document.location.host;
	var url = "http://" + host + "/" + getContext() + "/reserva/getidfuncion";
	$.getJSON(url)
	.success(function(json) {
	    console.log(json);
	    return json;
	});
    };
    
    function isCheckboxActivo(asiento, funcion,fecha){
	//console.log("asiento=" + asiento + " funcion=" + funcion);
	
	if(asiento === 'undefined' || funcion === 'undefined')
	    return;
	
	var host =document.location.host;
	var url = "http://" + host + "/" + getContext() + "/reserva/getasientostatus?asiento=" + asiento + "&funcion=" + funcion + "&fecha=" + fecha;
	$.getJSON(url)
	.success(function(json) {
	    if (json === true){
		console.log("checkbox " + asiento + " activo");
	    	inhabilitar(asiento);
	    }
	});
    };
    
    function inhabilitar(elementId){
	var element = document.getElementById(elementId);
	$(element).attr( "disabled", "" );
	$(element).parent().css("background-color","red");
    }
});