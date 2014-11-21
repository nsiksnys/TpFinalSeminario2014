//Inserta scripts segun la url de ese momento
$( document ).ready(function() {
    var pathname = document.location.pathname;
    
    if ((pathname.search("alta") != -1) || 
	(pathname.search("modificar") != -1) ||
	(pathname.search("singup") != -1))
    {
	agregarScript('MeioMaskStart');
	agregarScript('jquery.meiomask');
    }
  
    if((pathname.search("usuario/alta") != -1) ||
	(pathname.search("usuario/modificar") != -1))
    {
	agregarScript('Usuario');
    }

    if (pathname.search("stats") != -1)
    {
	agregarScript('googleCharts');
    }
    
    if (pathname.search("funcion/alta") != -1)
    {
	agregarScript('funcion');
    }
    
    if (pathname.search("reserva/alta") != -1)
    {
	agregarScript('reserva');
	agregarScript('jquery.countdown');
    }
    
    if (pathname.search("cartelera/alta") != -1 || pathname.search("cartelera/modificar") != -1)
    {
	agregarScript('cartelera');
    }
});

function agregarScript(nombre){
    	var context = getContext();
	var script = document.createElement( 'script' );
	script.type = 'text/javascript';
	script.media= 'screen';
	script.rel= 'stylesheet';
	script.src = "/" + context + "/resources/js/" + nombre + ".js";
	$("body").append( script );
}