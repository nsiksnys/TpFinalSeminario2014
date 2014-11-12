function validateHelper(){
	var self = this;
	this.errores = {email:0,integer:0,required:0};
	
	this.validateFormatEmail = function(email) { 
	    var re = /^\w+([\.-]?\w+)*@\w+([\.-]?\w+)*(\.\w{2,3})+$/;
	    return re.test(email);
	};

	this.validateAtribute = function(atribute){
		var valido = true;
		
		if(atribute.length == 0 || /^\s+$/.test(atribute)){
			valido = false;
		}
		
		return valido;
	};
	
	this.isNum = function validarEntero(valor){ 
	     return !isNaN(valor.replace(",","."));
	}; 

	this.validar = function(value, type, isRequired){
		var isValido = true;	
		
		switch (type) {
		case 'integer': 
			isValido = self.isNum(value);
			break;
		case 'email': 
			isValido = self.validateFormatEmail(value);
			break;
		case 'avg': 
			isValido = self.isNum(value) && value<=100;
			break;
		case 'decimal': 
			isValido = self.validateFormatEmail(value);
			break;

		default:		
		}
		if(!isValido){
			$(self.errores).attr(type, $(self.errores).attr(type)+1);
		}else if(isRequired){
			if(!self.validateAtribute(value)){
				$(self.errores).attr("required", $(self.errores).attr("required")+1);
			}
			
		}
		return isValido;
	};
	
	this.showErrors=function(){
		//recorres los errores ,armas mensaje y mostras alert
		$.map(self.errores, function( i, val ){
			if(val == 'email'&& i>0){
				alert('error en formato de mail ej:yo@dominio.com' );
			}else if(val == 'integer'&& i>0){
				alert('es necesario poner un numero en el campo, cantidad: '+ i);
			}else if(val == 'required'&& i>0){
				alert('no se aceptan campo vacio, cantidad: '+ i);
			}			
		});
	};
};