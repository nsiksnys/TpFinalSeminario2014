/**
 * Contiene metodos que usan para validar los distintos tipos de input
 */
function validateHelper(){
    //VARIABLES GLOBALES
	var self = this;//para diferenciar las variables de las funciones
	
	this.excluidos = []; //aca se guardan los inputs que no se validan. FORMA [element.id]
	this.inputs = [];//aca se guardan los selects e inputs. FORMA: [element.id, element.required, tipo]
	this.button;//aca se guarda el boton que se usa para submitear el formulario
	this.claseError = 'has-error';//clase que se aplica cuando hay un error en un input
	
	//arma un map de los input que hay en la pagina
	this.setInputs = function (pagina){
	    var elements = $(pagina).find( ":input" );//guardo todos los elementos de la pagina
	    
	    for(var i=0; i< elements.length; i++) 
	    {//itero sobre la lista obtenida
		if (elements[i].id != "")//si el id no es nulo
		{//lo agrego
		    if (elements[i].tagName == "INPUT")
			self.inputs.push( [elements[i].id, elements[i].required, elements[i].alt] );//tipo = alt
		    else
			self.inputs.push( [elements[i].id, elements[i].required, elements[i].tagName.toLowerCase()] );//selects y checkboxes
		}
	    }
	    
	    button = $(pagina).find( $("[type='submit']" ));
	    console.log('Guardados ' + self.inputs.length + ' inputs' );
	};
	
	this.agregarExcluido = function(id){
	    self.excluidos.push(id);
	};
	
	this.isExcluido = function(id){
	    for (var i=0; i< self.excluidos.length; i++){
		if (self.excluidos[i] == id)
		    return true;
	    }
	    return false;
	};
	
	//valida todos los input existentes
	this.validarTodosMenosExcluidos = function(){
	    this.resetErrores();
    	    
	    for(var i=0; i<self.inputs.length; i++){
    	    	var element= document.getElementById(self.inputs[i][0]);
    	    	if (!this.isExcluido(element.id))
    	    	    this.validar(element.id, element.value, self.inputs[i][2]);
    	    }
	};
	
	//valida todos los input existentes
	this.validarTodosMenosActual = function(actual){
	    this.resetErrores();
    	    
	    for(var i=0; i<self.inputs.length; i++){
    	    	var element= document.getElementById(self.inputs[i][0]);
    	    	if (element.id != actual && !this.isExcluido(element.id))
    	    	    this.validar(element.id, element.value, self.inputs[i][2]);
    	    }
	};
	
	//Cuando se dispara un submit busca los divs con clase has-error
	this.validarTodosSubmit = function(event){
	    var cuenta = 0;
	  //itero sobre la lista de inputs
	    for(var i=0; i< self.inputs.length; i++)
	    {
		//si el div que engloba al elemento tiene la clase has-errors porque el contenido no es valido
		if ( $(document.getElementById(self.inputs[i][0])).parent().hasClass( self.claseError ) )
		    cuenta++;
	    }
	    
	    if (cuenta > 0){
		console.log('El formulario tiene errores y no puede submitearse');
		event.preventDefault();//evito que se envie al servidor
	    }
	    else {
		console.log('El formulario esta bien y puede submitearse');
	    }
	};
	
	//verifica cada elemento de la lista de inputs, incluyendo los excluidos
	this.validarTodos = function(){	    
    		this.resetErrores();
    	    
        	for(var i=0; i<self.inputs.length; i++){
        	    var element= document.getElementById(self.inputs[i][0]);
        	    this.validar(self.inputs[i][0], element.value, self.inputs[i][2]);
        	}
	};
	
	//recibe los valores del input y devuelve un boolean con la respuesta
	this.validar = function(id, value, type){
	    //si el tipo no es integer, email, avg, decimal ni select
	    if (typeof type === "undefined")
		type = '';
	    
		var isValido = true;	
		
		switch (type) {
		case 'integer': 
			isValido = self.isNum(value) && value>0;
			break;
		case 'email': 
			isValido = self.validateFormatEmail(value);
			break;
		case 'avg': 
			isValido = self.isNum(value) && value<=100;
			break;
		case 'select':
		    	isValido = self.isNum(value) && value>0;
		    	break;
		case 'date':
		    	isValido = this.isDate(value);
		    	break;
		case 'url':
		    	isValido = this.isUrl(value);
		    	break;
		default:		
		    	isValido = (!(typeof value === "undefined")) && value != '' && value.length > 0;
		    	break;
		}
		
		//actualiza la lista de errores
		if(this.isNull(id, value)){
		    this.showError(id, 'required');
		    this.agregarError('required');
		}
		else if(!isValido){
		    this.showError(id, type);
		    this.agregarError(type);
		}
		else {
		    this.hideErrorMessage(id);
		}
		    
		return isValido;
	};
	
	//verifica que sea un mail
	this.validateFormatEmail = function(email) { 
	    var re = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;
	    return re.test(email);
	};

	//verifica que sea un atributo valido
	this.validateAtribute = function(atribute){
		var valido = true;
		
		if(atribute.length == 0 || /^\s+$/.test(atribute)){
			valido = false;
		}
		
		return valido;
	};
	
	//verifica que sea un entero
	this.isNum = function (valor){ 
	     return !isNaN(valor.replace(",","."));
	};
	
	//verifica que siga la forma DD/MMM/YYYY, sin compararla contra la fecha del dia (esta funcion tambien esta en la clase ValidateHelperFecha)
	this.isDate = function (value){
	    var regrex = /(\d+)(-|\/)(\d+)(?:-|\/)(?:(\d+)\s+(\d+):(\d+)(?::(\d+))?(?:\.(\d+))?)?/;
	    return regrex.test(value);
	};
	
	this.isUrl = function(value){
	    var youtube = /^(?:https?:\/\/)?(?:www\.)?youtube\.com\/watch\?(?=.*v=((\w|-){11}))(?:\S+)?$/;
	    var vimeo =  /^.*(vimeo\.com\/)((channels\/[A-z]+\/)|(groups\/[A-z]+\/videos\/))?([0-9]+)/;
	    return youtube.test(value) || vimeo.test(value);
	};
	
	//avisa hay un valor nulo en un campo requerido
	this.isNull = function(id, value){
	  if (this.isRequired(id) && value == "")
	      return true;
	  return false;
	};
	
	//avisa si el contenido del input puede ser nulo o no
	this.isRequired = function(id){
	    for(var i=0; i< self.inputs.length; i++) {
		if (self.inputs[i][0] == id)
		    return self.inputs[i][1];
	    }
	};

	//selecciona el mensaje de error que corresponde y lo manda a la funcion showErrorMessage
	this.showError=function(id, type){
	    var mensaje;
	    
	    switch (type)
	    {
	    	case 'email':
	    	    mensaje= 'Error en formato de mail ej:yo@dominio.com';
	    	    break;
	    	case 'integer':
	    	    mensaje= 'El numero no es valido.';
	    	    break;
	    	case 'required':
	    	    mensaje= 'El campo no puede ser nulo';
	    	    break;
	    	case 'select':
	    	    mensaje= 'La opcion elegida no es valida.';
	    	    break;
	    	case 'date':
	    	    mensaje= 'La fecha ingresada no sigue el formato dia/mes/año';
	    	    break;
		default:
		    mensaje= 'Por favor revise el campo';
		    break;
	    }
	    
	    this.showErrorMessage(id, mensaje);		    
	};
	
	//resetea el map de errores
	this.resetErrores = function() {
	    self.errores = {email:0,integer:0,required:0,select:0};	    
	};
	
	//modifica el map de errores
	this.agregarError = function (type){
	    $(self.errores).attr(type, $(self.errores).attr(type)+1);
	};
	
	//muestra el mensaje de error
	this.showErrorMessage = function(id, mensaje){
	    $(document.getElementById(id + '.errors')).text(mensaje);
	    $(document.getElementById(id + '.errors')).show();
	    $(document.getElementById(id)).parent().addClass(self.claseError);
	    console.log('ValidateHelper: error en campo ' + id);
	};
	
	//oculta el mensaje de error
	this.hideErrorMessage = function(id){
	    $(document.getElementById(id + '.errors')).text('');
	    $(document.getElementById(id + '.errors')).hide();
	    $(document.getElementById(id)).parent().removeClass(self.claseError);
	};
	
	this.disableSubmit =function(){
	   $(self.button).attr('disabled','');
	};
	
	this.enableSubmit = function(){
	   $(self.button).removeAttr('disabled');
	};
};