$( document ).ready(function() {    
    //cuando la verificacion falla y se recarga la pagina con el select vacio
    if ($( "#pelicula option:selected" ).val() == "0")
    {
	getPeliculasDisponibles();
    }
    
    //cuando se ingresa una fecha de inicio
    $("#inicio").change(function() {
	var fecha = $("#inicio").val();
	
	if (fecha === "undefined" || fecha == "")
	    return;
	
	if (isFechaInicioOk(fecha))
	{
	    $("#inicio").parent('div').removeClass("has-error");
	}
	else
	{
	    $("#inicio").parent('div').addClass("has-error");
	}
   });
    
  //cuando se ingresa una fecha de fin
    $("#fin").change(function() {
	var fecha = $("#fin").val();
	
	if (fecha === "undefined" || fecha == "")
	    return;
	
	if (isFechaFinOk($("#inicio").val(), fecha))
	{
	    $("#fin").parent('div').removeClass("has-error");
	}
	else
	{
	    $("#fin").parent('div').addClass("has-error");
	}
   }); 
    
    /**
     * Verifica que la fecha de inicio sea correcta, ie, que sea un jueves posterior al dia de la fecha
     * @param fecha String correspondiente a la fecha de inicio
     */
    function isFechaInicioOk(fecha)
    {
	var fechaInicio = new Date(getAnio(fecha), getMes(fecha)-1, getDia(fecha));
	
	if (fechaInicio.getDay() != 4)//si la fecha igresada no corresponde a un jueves
	    return false;
	
	if (getDiferenciaMilis(fechaInicio, new Date()) <= 0)//si la fecha ingresada es igual o anterior al dia de la fecha
	    return false;
	
	return true;
    }
    
    /**
     * Verifica que la fecha de fin sea correcta, ie, que sea posterior al dia de la fecha y con seis dias de diferencia con la fecha de inicio
     * @param fechaInicio String correspondiente a la fecha de inicio
     * @param fechaFin String correspondiente a la fecha de fin
     */
    function isFechaFinOk(fechaInicio, fechaFin)
    {
	var fechaFinDate = new Date(getAnio(fechaFin), getMes(fechaFin)-1, getDia(fechaFin));
	var fechaInicioDate = new Date(getAnio(fechaInicio), getMes(fechaInicio)-1, getDia(fechaInicio));
	
	if (getDiferenciaMilis(fechaFinDate, new Date() ) <= 0)//si la fecha ingresada es igual o anterior al dia de la fecha
	    return false;
	
	if (getDiferenciaMilis(fechaFinDate, fechaInicioDate) <= 0)//si la fecha de fin es igual o anterior a la fecha de inicio
	    return false;
	
	if (getDiferenciaMilis(fechaFinDate, fechaInicioDate) < getDiaEnMilis(6))//si la fecha de fin es igual o anterior a la fecha de inicio
	    return false;
	
	return true;
    }
    
    function getDiaEnMilis(dias)
    {
	return dias * 24 * 60 * 60 * 1000;//dias * horas * minutos * segundos * 1000
    }
    
    /**
     * Calcula la diferencia en milisegundos que existe entre dos fechas
     * @param mayor Date mayor
     * @param menor Date menor
     */
    function getDiferenciaMilis(mayor, menor)
    {
	var diferencia = mayor.getTime() - menor.getTime(); /*milisegundos*/
	
	console.log('Diferencia entre ' + mayor + ' y ' + menor + ': ' + diferencia + ' milisegundo(s).');
	return diferencia;
    }
        
    /**
     * Calcula la diferencia en dias que existe entre dos fechas
     * @param mayor Date mayor
     * @param menor Date menor
     */
    function getDiferenciaDia(mayor, menor)
    {
	var diferencia = mayor.getTime() - menor.getTime(); /*milisegundos*/
	var milisegundosDia = 24 * 60 * 60 * 1000;//horas * minutos * segundos * 1000
	var dias = Math.floor( diferencia / milisegundosDia );
	
	console.log('Diferencia entre ' + mayor + ' y ' + menor + ': ' + dias + ' dias.');
	return dias;
    }
    
    /**
     * Calcula la diferencia en meses que existe entre dos fechas. Se supone por mes un conjunto de 30 dias.
     * @param mayor Date mayor
     * @param menor Date menor
     */
    function getDiferenciaMes(mayor, menor)
    {
	var diferencia = mayor.getTime() - menor.getTime(); /*milisegundos*/
	var milisegundosDia = 24 * 60 * 60 * 1000;
	var milisegundosMes = 30 * milisegundosDia ;    
	var meses = Math.floor( diferencia / milisegundosMes );
	
	return meses;
    }
    
    function getDia(fecha)
    {
	return fecha.split('/')[0];
    }
    
    function getMes(fecha)
    {
	return fecha.split('/')[1];
    }
    
    function getAnio(fecha)
    {
	return fecha.split('/')[2];
    }
    
    function getPeliculasDisponibles(){
        var host =document.location.host;
        var url = "http://" + host + "/" + getContext() + "/pelicula/getpeliculas";//url de la funcion getPeliculasDisponibles en controller de Peliculas
        return $.getJSON(url, function(json) {
            if (typeof json === "undefined" || json=="" || json.length == 0)
            {
        	return;
            }
    	
            $("#pelicula").empty();
            
            $.each(json, function (key, value) {
        	$("#pelicula").append(parseOpcion(key, value));
            });
        });
    };
    
    function parseOpcion(key, value){
        return '<option value="' + key + '">' + value + '</option>';
    };
});