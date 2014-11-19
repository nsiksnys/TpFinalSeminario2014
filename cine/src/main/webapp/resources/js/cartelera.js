$( document ).ready(function() {    
    $("#inicioSpan").hide();
    $("#finSpan").hide();
    
    //cuando se ingresa una fecha de inicio
    $("#inicio").change(function() {
	var fecha = $("#inicio").val();
	
	if (fecha === "undefined" || fecha == "")
	    return;
	
	if (isFechaInicioOk(fecha))
	{
	    $("#inicioSpan").text("");
	    $("#inicioSpan").hide();
	}
	else
	{
	    $("#inicioSpan").text("Por favor revise la fecha.");
	    $("#inicioSpan").show();
	}
   });
    
  //cuando se ingresa una fecha de fin
    $("#fin").change(function() {
	var fecha = $("#fin").val();
	
	if (fecha === "undefined" || fecha == "")
	    return;
	
	if (isFechaFinOk($("#inicio").val(), fecha))
	{
	    $("#finSpan").text("");
	    $("#finSpan").hide();
	}
	else
	{
	    $("#finSpan").text("Por favor revise la fecha.");
	    $("#finSpan").show();
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
	
	if (getDiferenciaDia(fechaInicio, new Date()) <= 0)//si la fecha ingresada es igual o anterior al dia de la fecha
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
	
	if (getDiferenciaDia(fechaFinDate, new Date() ) <= 0)//si la fecha ingresada es igual o anterior al dia de la fecha
	    return false;
	
	if (getDiferenciaDia(fechaFinDate, fechaInicioDate) <= 0)//si la fecha de fin es igual o anterior a la fecha de inicio
	    return false;
	
	if (getDiferenciaDia(fechaFinDate, fechaInicioDate) < 6)//si la fecha de fin es igual o anterior a la fecha de inicio
	    return false;
	
	return true;
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
});