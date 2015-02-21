/**
* Contiene funciones para validar fechas
*/
function validateHelperFecha(){
    var self = this;
    this.validacionComun = new validateHelper();
    this.tipos = ["inicio", "fin", "pasada", "futura", "hoy"];//los tipos de fecha permitidos
    
    //sobreescribo las funciones propias de validateHelper
    this.setInputs = function(pagina){
	self.validacionComun.setInputs(pagina);
    };
    
    this.agregarExcluido = function(id){
	self.validacionComun.agregarExcluido(id);
    };
    
    this.isExcluido = function(id){
	return self.validacionComun.isExcluido(id);
    };
    
    this.showError = function(id, tipo){
	self.validacionComun.showError(id, tipo);
    };
    
    this.hideErrorMessage = function(id){
	self.validacionComun.hideErrorMessage(id);
    };
    
    this.isNull = function(id, valor){
	return self.validacionComun.isNull(id, valor);
    };
    
    this.validarTodosMenosExcluidos = function(){
	for(var i=0; i<self.validacionComun.inputs.length; i++)
	{
	    var element= document.getElementById(self.validacionComun.inputs[i][0]);
	    
	    if (!this.isExcluido(element.id))
	    {
        	    if (this.isTipoFecha(element.id)){
        		validacion.validar(element.id, element.value, element.id);
        	    }
        	    else if (element.tagName == "SELECT"){//si es un select
        		validacion.validar(element.id, element.value, 'select');
        	    }
        	    else{//el resto
        		validacion.validar(element.id, element.value, element.alt);
        	    }
	    }
	}
    };
    
    this.validarTodosMenosActual = function(actual){
	for(var i=0; i<self.validacionComun.inputs.length; i++)
	{
	    var element= document.getElementById(self.validacionComun.inputs[i][0]);
	    
	    if (element.id != actual && !this.isExcluido(actual))
	    {
        	    if (this.isTipoFecha(element.id)){
        		validacion.validar(element.id, element.value, element.id);
        	    }
        	    else if (element.tagName == "SELECT"){//si es un select
        		validacion.validar(element.id, element.value, 'select');
        	    }
        	    else{//el resto
        		validacion.validar(element.id, element.value, element.alt);
        	    }
	    }
	}
    };
    
    this.validarTodos = function(){	   
	for(var i=0; i<self.validacionComun.inputs.length; i++)
	{
	    var element= document.getElementById(self.validacionComun.inputs[i][0]);
	    
	    if (this.isTipoFecha(element.id)){
		validacion.validar(element.id, element.value, element.id);
	    }
	    else if (element.tagName == "SELECT"){//si es un select
		validacion.validar(element.id, element.value, 'select');
	    }
	    else{//el resto
		validacion.validar(element.id, element.value, element.alt);
	    }
	}
    };
    
    this.validarTodosSubmit = function(event){
	self.validacionComun.validarTodosSubmit(event);
    };
    
   /**
     * validacion principal
     */
    this.validar = function(id, valor, tipo)
    {
	var isValido = true;
	if (typeof tipo === "undefined")
	    tipo = '';
	
	switch(tipo) {
	case 'inicio':
	    isValido = this.isDate(valor) && this.isFechaInicioOk(valor);
	    break;
	case 'fin':
	    isValido = this.isDate(valor) && this.isFechaFinOk(valor);
	    break;
	case 'pasada':
	    isValido = this.isDate(valor) && this.isFechaPasada(valor);
	    break;
	case 'futura':
	    isValido = this.isDate(valor) && this.isFechaFutura(valor);
	    break;
	case 'hoy':
	    isValido = this.isDate(valor) && this.isFechaActual(valor);
	    break;
	    
	default:
	    return self.validacionComun.validar(id, valor, tipo);//termina aca
	}
	
	//actualiza la lista de errores
	if(this.isNull(id, valor)){
	    this.showError(id, 'required');
	    self.validacionComun.agregarError('required');
	}
	else if(!isValido){
	    this.showError(id, tipo);
	    self.validacionComun.agregarError(tipo);
	}
	else {
	    this.hideErrorMessage(id);
	}
	
	return isValido;
    };
    
    //busca el parametro en el array de tipos y avisa si lo encontro o no
    this.isTipoFecha = function(valor){
	for (var i=0; i<self.tipos.length; i++){
	    if (self.tipos[i] == valor)
		return true;
	}
	return false;
    };
    
    /**
     * Verifica que la fecha de inicio sea correcta, ie, que sea un jueves posterior al dia de la fecha
     * @param fecha String correspondiente a la fecha de inicio
     */
    this.isFechaInicioOk = function (fecha)
    {
	var fechaInicio = new Date(this.getAnio(fecha), this.getMes(fecha)-1, this.getDia(fecha));
	
	if (fechaInicio.getDay() != 4)//si la fecha igresada no corresponde a un jueves
	    return false;
	
	if (this.getDiferenciaMilis(fechaInicio, new Date()) <= 0)//si la fecha ingresada es igual o anterior al dia de la fecha
	    return false;
	
	return true;
    };
    
    /**
     * Verifica que la fecha de fin sea correcta, ie, que sea posterior al dia de la fecha y con seis dias de diferencia con la fecha de inicio
     * @param fechaInicio String correspondiente a la fecha de inicio
     * @param fechaFin String correspondiente a la fecha de fin
     */
    this.isFechaFinOk = function (fechaFin)
    {
	var fechaInicio = $(document.getElementById('inicio')).val();
	var fechaFinDate = new Date(this.getAnio(fechaFin), this.getMes(fechaFin)-1, this.getDia(fechaFin));
	var fechaInicioDate = new Date(this.getAnio(fechaInicio), this.getMes(fechaInicio)-1, this.getDia(fechaInicio));
	
	if (this.getDiferenciaMilis(fechaFinDate, new Date() ) <= 0)//si la fecha ingresada es igual o anterior al dia de la fecha
	    return false;
	
	if (this.getDiferenciaMilis(fechaFinDate, fechaInicioDate) <= 0)//si la fecha de fin es igual o anterior a la fecha de inicio
	    return false;
	
	if (this.getDiferenciaMilis(fechaFinDate, fechaInicioDate) < this.getDiaEnMilis(6))//si la fecha de fin es igual o anterior a la fecha de inicio
	    return false;
	
	return true;
    };
    
    //verifica que la fecha ingresada sea posterior a la fecha actual
    this.isFechaFutura =function(fecha){
	//convierto la fecha al formato date
	var date = new Date(this.getAnio(fecha), this.getMes(fecha)-1, this.getDia(fecha));
	
	//si la diferencia entre la fecha ingresada y la actual es positiva
	if (this.getDiferenciaMilis(date, new Date() ) > 0)
	    return true;
	return false;
    };
    
    //verifica que la fecha ingresada sea anterior a la fecha actual
    this.isFechaPasada =function(fecha){
	//convierto la fecha al formato date
	var date = new Date(this.getAnio(fecha), this.getMes(fecha)-1, this.getDia(fecha));
	
	//si la diferencia entre la fecha ingresada y la actual es positiva
	if (this.getDiferenciaMilis(date, new Date() ) < 0)
	    return true;
	return false;
    };
    
    //verifica que la fecha ingresada sea igual a la fecha actual
    this.isFechaActual =function(fecha){
	//convierto la fecha al formato date
	var date = new Date(this.getAnio(fecha), this.getMes(fecha)-1, this.getDia(fecha));
	
	//si la diferencia entre la fecha ingresada y la actual es cero
	if (this.getDiferenciaMilis(date, new Date() ) == 0)
	    return true;
	return false;
    };
    
    this.getDiaEnMilis = function (dias)
    {
	return dias * 24 * 60 * 60 * 1000;//dias * horas * minutos * segundos * 1000
    };
    
    /**
     * Calcula la diferencia en milisegundos que existe entre dos fechas
     * @param mayor Date mayor
     * @param menor Date menor
     */
    this.getDiferenciaMilis = function (mayor, menor)
    {
	var diferencia = mayor.getTime() - menor.getTime(); /*milisegundos*/
	
	//console.log('Diferencia entre ' + mayor + ' y ' + menor + ': ' + diferencia + ' milisegundo(s).');
	return diferencia;
    };
        
    /**
     * Calcula la diferencia en dias que existe entre dos fechas
     * @param mayor Date mayor
     * @param menor Date menor
     */
    this.getDiferenciaDia = function (mayor, menor)
    {
	var diferencia = mayor.getTime() - menor.getTime(); /*milisegundos*/
	var milisegundosDia = 24 * 60 * 60 * 1000;//horas * minutos * segundos * 1000
	var dias = Math.floor( diferencia / milisegundosDia );
	
	//console.log('Diferencia entre ' + mayor + ' y ' + menor + ': ' + dias + ' dias.');
	return dias;
    };
    
    /**
     * Calcula la diferencia en meses que existe entre dos fechas. Se supone por mes un conjunto de 30 dias.
     * @param mayor Date mayor
     * @param menor Date menor
     */
    this.getDiferenciaMes = function (mayor, menor)
    {
	var diferencia = mayor.getTime() - menor.getTime(); /*milisegundos*/
	var milisegundosDia = 24 * 60 * 60 * 1000;
	var milisegundosMes = 30 * milisegundosDia ;    
	var meses = Math.floor( diferencia / milisegundosMes );
	
	return meses;
    };
    
    this.getDia = function (fecha)
    {
	return fecha.split('/')[0];
    };
    
    this.getMes = function (fecha)
    {
	return fecha.split('/')[1];
    };
    
    this.getAnio = function (fecha)
    {
	return fecha.split('/')[2];
    };
    
    this.isDate = function(valor){
	if (valor === "undefined" || valor == "")
	    return false;
	
	var regrex = /(\d+)(-|\/)(\d+)(?:-|\/)(?:(\d+)\s+(\d+):(\d+)(?::(\d+))?(?:\.(\d+))?)?/;
	return regrex.test(valor);
    };
};