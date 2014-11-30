$(document).ready(function() {
    //chequeo que sea la pagina correcta, si no se sale del script
     if (getCarpeta() != "reserva"){
	console.log(getCarpeta() + ' != reserva');
	return;
    }
   
   //necesario para que el validateHelper funcione correctamente
    validaciones = new validateHelper();
    validaciones.setInputs(this);
    validaciones.agregarExcluido("funcion");//este campo solo se evalua si se lo indica
    
    $(function() {
	$(':checkbox').click(checked);
	$(":checkbox:checked").each(checked);
    });
    
    getFuncion($( "#complejo option:selected" ).val(), $( "#pelicula option:selected" ).val());
    validaciones.validarTodosMenosExcluidos();

    //cuando cambia un input (incluye selects)
    $( ":input" ).change(function (){
	var element = this;//guardo el elemento en cuestion
	        
	validaciones.validarTodosMenosActual(element.id);

	if (element.id == "complejo" || element.id == "pelicula")
	    changeFuncion();
	
	//casos especiales
	if (element.tagName == "SELECT" && element.id != "funcion"){//el caso de funciones no se evalua
	    validaciones.validar(element.id, element.value, 'select');
	}
	else{
	    validaciones.validar(element.id, element.value, element.alt);
	}
    });
    
    $("[type='submit']" ).click(function(event) {
	validaciones.validarTodosSubmit(event);
    });
    		
    function changeFuncion(){
    	var pelicula = $( "#pelicula option:selected" ).val();
    	var complejo = $( "#complejo option:selected" ).val();
    	
    	
    	if (complejo != "" && !(typeof complejo === "undefined") && pelicula !="" && !(typeof pelicula === "undefined")){
    		getFuncion(complejo, pelicula);
    	}
     };
    
    function getFuncion(complejo, pelicula){
    	var host =document.location.host;
        var url = "http://" + host + "/" + getContext() + "/funcion/getfunciones?complejo=" + complejo + "&pelicula=" + pelicula;
        $.getJSON(url, function(json) {
            if (typeof json === "undefined" || json=="" || json.length == 0)
            {
        	return;
            }
    	
            $("#funcion").empty();
            
            $.each(json, function (key, value) {
            	$("#funcion").append(parseOpcion(key, value));
            });
            validaciones.hideErrorMessage('funcion');
        }).fail (function() {
            	$("#funcion").empty();
    		$("#funcion").append(parseOpcion('0', 'N/A'));
    		validaciones.showError('funcion', 'select');
        });
    };
    
    function checked() {
        if ($(this).attr('checked') == true) {
    	$(this).parent().attr('style', 'background:#ff0000;');
        } else {
    	$(this).parent().removeAttr('style');
    	// o $(this).parent().attr('style','background:#ff0000;') y elejis el color si no esta seleccionado
        }
    }
    
    function parseOpcion(key, value){
        return '<option value="' + key + '">' + value + '</option>';
    };
});
