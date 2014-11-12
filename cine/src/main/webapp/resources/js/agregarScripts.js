//Inserta scripts segun la url de ese momento
$( document ).ready(function() {
    $pathname = document.location.pathname;
    $context = $pathname.split('/')[1];
    
    if (($pathname.search("alta") != -1) || 
	($pathname.search("modificar") != -1) ||
	($pathname.seach("singup") != -1))
    {
	agregarScript('MeioMaskStart');
	agregarScript('jquery.meiomask');
    }
  
    if(($pathname.search("usuario/alta") != -1) ||
	($pathname.search("usuario/modificar") != -1))
    {
	agregarScript('Usuario');
    }

    if ($pathname.search("stats") != -1)
    {
	agregarScript('googleCharts');
    }
    	
    
    function agregarScript($nombre){
	var script = document.createElement( 'script' );
	script.type = 'text/javascript';
	script.media= 'screen';
	script.rel= 'stylesheet';
	script.src = "/" + $context + "/resources/js/" + $nombre + ".js";
	$("body").append( script );
    };
});