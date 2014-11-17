$( document ).ready(function() {
    $("title").append(getPathname);
});
function getPathname(){
    var pathname = document.location.pathname;
    var carpeta = pathname.split('/')[2];
    var accion = pathname.split('/')[3];
    var respuesta ="";
    
    if (!(typeof carpeta === "undefined"))
	respuesta += carpeta;
    
    if (!(typeof accion === "undefined"))
	respuesta += " - " + accion;
    
    return respuesta;
};

function getContext(){
    var pathname = document.location.pathname;
    var context = pathname.split('/')[1];
    if (!(typeof context === "undefined"))
    {	
	return context;
    }
    else
    {
	return "";
    }
}

function getCarpeta(){
    var pathname = document.location.pathname;
    var carpeta = pathname.split('/')[2];
    if (!(typeof carpeta === "undefined"))
    {	
	return carpeta;
    }
    else
    {
	return "";
    }
}

function getAccion(){
    var pathname = document.location.pathname;
    var accion = pathname.split('/')[3];
    if (!(typeof accion === "undefined"))
    {	
	return accion;
    }
    else
    {
	return "";
    }
}