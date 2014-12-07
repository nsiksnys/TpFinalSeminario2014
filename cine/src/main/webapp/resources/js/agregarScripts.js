//Inserta scripts segun la url de ese momento
$( document ).ready(function() {
    var pathname = document.location.pathname;
    
    if ((pathname.search("alta") != -1) || 
	(pathname.search("modificar") != -1) ||
	(pathname.search("singup") != -1))
    {
	agregarScript('jquery.meiomask');
	agregarScript('MeioMaskStart');
	agregarScript('validateHelper');
	agregarScript('validateHelperFecha');
    }
  
    if((pathname.search("usuario/alta") != -1) || pathname.search("usuario/modificar") != -1)
//	(pathname.search("usuario/modificar") != -1))
    {
	agregarScript('Usuario');
    }

    if (pathname.search("stats") != -1)
    {
	agregarScript('googleCharts');
    }
    
    if (pathname.search("funcion/alta") != -1 || pathname.search("funcion/modificar") != -1)
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
    
    if (pathname.search("pelicula/alta") != -1 || pathname.search("pelicula/modificar") != -1)
    {
	agregarScript('pelicula');
    }
    
    if (pathname.search("promocion/alta") !=-1 || pathname.search("promocion/modificar") != -1)
    {
    agregarScript('promocion');
    }
    
    if (pathname.search("precio/alta") !=-1)
    {
    agregarScript('precio');
    }
    if (pathname.search("complejo/alta") !=-1 || pathname.search("complejo/modificar") != -1)
    {
    agregarScript('complejo');
    }
    
    if (pathname.search("recuperar") != -1){
	agregarScript('validateHelper');
	agregarScript('recuperar');
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