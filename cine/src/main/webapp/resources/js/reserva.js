$(document).ready(function() {
    $(function() {
	$(':checkbox').click(checked);
	$(":checkbox:checked").each(checked);
    });
    
    getFuncion($( "#complejo option:selected" ).val(), $( "#pelicula option:selected" ).val());
    		
    		
    $("#pelicula").change(function () {
    	var pelicula = $( "#pelicula option:selected" ).val();
    	var complejo = $( "#complejo option:selected" ).val();
    	
    	
    	if (complejo != "" && !(typeof complejo === "undefined") && pelicula !="" && !(typeof pelicula === "undefined")){
    		getFuncion(complejo, pelicula);
    	}
     });
    
    function getFuncion(complejo, pelicula){
    	var host =document.location.host;
        var url = "http://" + host + "/" + getContext() + "/funcion/getfunciones?complejo=" + complejo + "&pelicula=" + pelicula;
        return $.getJSON(url, function(json) {
            if (typeof json === "undefined" || json=="" || json.length == 0)
            {
        	return;
            }
    	
            $("#funcion").empty();
            
            $.each(json, function (key, value) {
//            	alert(key);
//            	alert(value);
            	$("#funcion").append(parseOpcion(key, value));
            });
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
