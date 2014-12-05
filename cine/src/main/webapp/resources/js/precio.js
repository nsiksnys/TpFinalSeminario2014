$( document ).ready(function() {    
    //chequeo que sea la pagina correcta, si no se sale del script
    if (getCarpeta() != "precio"){
	console.log(getCarpeta() + " != precio");
	return;
   }
    
  //necesario para que el validateHelper funcione correctamente
    validacion = new validateHelper();
    validacion.setInputs(this);
    
    	validacion.validarTodos();
       
    
  //cuando cambia un input (incluye selects)
    $( ":input" ).change(function() {
	validacion.validarTodosMenosActual(this.id);
	validacion.validar(this.id, this.value, 'float');
   });
    
    
    $("[type='submit']" ).click(function(event) {
    	validacion.validarTodosSubmit(event);
        });
    
    function parseOpcion(key, value){
        return '<option value="' + key + '">' + value + '</option>';
    };
    
});