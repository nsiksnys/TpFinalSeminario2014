$( document ).ready(function () {
	
	
	var idfecha1 = document.getElementById('idfecha1').value;
	var idfecha2 = document.getElementById('idfecha2').value;
	
	
	puntoR(idfecha1,idfecha2);
	Punto2(idfecha1,idfecha2);
	Punto3(idfecha1,idfecha2);
	Punto4(idfecha1,idfecha2);
	Punto5(idfecha1,idfecha2);
	Punto6(idfecha1,idfecha2);
	Punto7(idfecha1,idfecha2);
	
	
	function puntoR(idfecha1,idfecha2){
		var host =document.location.host;
	    var url = "http://" + host + "/" + getContext() + "/stats/puntoR1?idfecha1="+idfecha1+"&idfecha2="+idfecha2;//url de la funcion del PUNTO 1 DEL CONTROLLER
		var processed_json = new Array();  
        $.getJSON(url, function(data) { 
        	$.each( data, function( key, value ) {   		
        		 processed_json.push([key,parseInt(value)]);  
        	});
	    
        	 // ACA TODO LO QUE TENGO QUE HACER PARA GRAFICAR
        	 $('#container1').highcharts({
                 chart: {
                     type: "column"
                 },
                 title: {
                     text: "Cantidad de reservas por pelicula"
                 },
                 xAxis: {
                 	type: 'category',
                     labels: {
                         rotation: -45,
                         style: {
                             fontSize: '13px',
                             fontFamily: 'Verdana, sans-serif'
                         }
                     }
                 },
                 yAxis: {
                     title: {
                         text: "Asistencia"
                     }
                 },
                 legend: {
                     enabled: false
                 },
                 tooltip: {
                     pointFormat: 'Cantidad de personas: <b>{point.y}</b>'
                 },
                 series: [{
                     data: processed_json // LA VARIABLE QUE CONTIENE TODAS LAS PELICULAS
                 }]
                 
                 
             }); // FIN DE $('#container')        	
        }); // FIN DE GETSJON        
	};// FIN DE puntoR
	
	//=====================================================
	//=====================================================
	//=====================================================
function Punto2(idfecha1,idfecha2){
		
		var host =document.location.host;
	    var url = "http://" + host + "/" + getContext() + "/stats/puntoR2?idfecha1="+idfecha1+"&idfecha2="+idfecha2;//url de la funcion del PUNTO 1 DEL CONTROLLER
		var processed_json = new Array();  
		
        $.getJSON(url, function(data) { 
        	
        	$.each( data, function( key, value ) {   		
        		 processed_json.push([key,parseInt(value)]);  
        	});
	    
        	 // ACA TODO LO QUE TENGO QUE HACER PARA GRAFICAR
        	 $('#container2').highcharts({
                 chart: {
                     type: "column"
                 },
                 title: {
                     text: "Cantidad de cancelaciones por pelicula"
                 },
                 xAxis: {
                 	type: 'category',
                     labels: {
                         rotation: -45,
                         style: {
                             fontSize: '13px',
                             fontFamily: 'Verdana, sans-serif'
                         }
                     }
                 },
                 yAxis: {
                     title: {
                         text: "Numero de cancelaciones"
                     }
                 },
                 legend: {
                     enabled: false
                 },
                 tooltip: {
                     pointFormat: 'Cantidad de personas: <b>{point.y}</b>'
                 },
                 series: [{
                     data: processed_json // LA VARIABLE QUE CONTIENE TODAS LAS PELICULAS
                 }]
                 
                 
             }); // FIN DE $('#container2')        	
        }); // FIN DE GETSJON        
	};// FIN DE PUNTO2
	//=====================================================
	//=====================================================
	//=====================================================
function Punto3(idfecha1,idfecha2){
		
		var host =document.location.host;
	    var url = "http://" + host + "/" + getContext() + "/stats/puntoR3?idfecha1="+idfecha1+"&idfecha2="+idfecha2;//url de la funcion del PUNTO 1 DEL CONTROLLER
		var processed_json = new Array();  
		
        $.getJSON(url, function(data) { 
        	
        	$.each( data, function( key, value ) {   		
        		 processed_json.push([key,parseInt(value)]);  
        	});
	    
        	 // ACA TODO LO QUE TENGO QUE HACER PARA GRAFICAR
        	 $('#container3').highcharts({
                 chart: {
                     type: "column"
                 },
                 title: {
                     text: "Porcentaje de reservas contra cancelaciones"
                 },
                 xAxis: {
                 	type: 'category',
                     labels: {
                         rotation: -45,
                         style: {
                             fontSize: '13px',
                             fontFamily: 'Verdana, sans-serif'
                         }
                     }
                 },
                 yAxis: {
                     title: {
                         text: "Porcentaje"
                     }
                 },
                 legend: {
                     enabled: false
                 },
                 tooltip: {
                     pointFormat: 'Cantidad de personas: <b>{point.y:.1f}</b>'
                 },
                 series: [{
                     data: processed_json // LA VARIABLE QUE CONTIENE TODAS LAS PELICULAS
                 }]
                 
                 
             }); // FIN DE $('#container3')        	
        }); // FIN DE GETSJON        
	};// FIN DE PUNTO3	
	//=====================================================
	//=====================================================
	//=====================================================
function Punto4(idfecha1,idfecha2){
		
		var host =document.location.host;
	    var url = "http://" + host + "/" + getContext() + "/stats/puntoR4?idfecha1="+idfecha1+"&idfecha2="+idfecha2;//url de la funcion del PUNTO 1 DEL CONTROLLER
		var processed_json = new Array();  
		
        $.getJSON(url, function(data) { 
        	
        	$.each( data, function( key, value ) {   		
        		 processed_json.push([key,parseInt(value)]);  
        	});
	    
        	 // ACA TODO LO QUE TENGO QUE HACER PARA GRAFICAR
        	 $('#container4').highcharts({
                 chart: {
                     type: "column"
                 },
                 title: {
                     text: "Horarios más solicitados"
                 },
                 xAxis: {
                 	type: 'category',
                     labels: {
                         rotation: -45,
                         style: {
                             fontSize: '13px',
                             fontFamily: 'Verdana, sans-serif'
                         }
                     }
                 },
                 yAxis: {
                     title: {
                         text: "Mayor cantidad de solicitudes"
                     }
                 },
                 legend: {
                     enabled: false
                 },
                 tooltip: {
                     pointFormat: 'Cantidad de reservas: <b>{point.y}</b>'
                 },
                 series: [{
                     data: processed_json // LA VARIABLE QUE CONTIENE TODAS LAS PELICULAS
                 }]
                 
                 
             }); // FIN DE $('#container4')        	
        }); // FIN DE GETSJON        
	};// FIN DE PUNTO4	
	//=====================================================
	//=====================================================
	//=====================================================
function Punto5(idfecha1,idfecha2){
		
		var host =document.location.host;
	    var url = "http://" + host + "/" + getContext() + "/stats/puntoR5?idfecha1="+idfecha1+"&idfecha2="+idfecha2;//url de la funcion del PUNTO 1 DEL CONTROLLER
		var processed_json = new Array();  
		
        $.getJSON(url, function(data) { 
        	
        	$.each( data, function( key, value ) {   		
        		 processed_json.push([key,parseInt(value)]);  
        	});
	    
        	 // ACA TODO LO QUE TENGO QUE HACER PARA GRAFICAR
        	 $('#container5').highcharts({
                 chart: {
                     type: "column"
                 },
                 title: {
                     text: "Complejos más solicitados"
                 },
                 xAxis: {
                 	type: 'category',
                     labels: {
                         rotation: -45,
                         style: {
                             fontSize: '13px',
                             fontFamily: 'Verdana, sans-serif'
                         }
                     }
                 },
                 yAxis: {
                     title: {
                         text: "Complejos"
                     }
                 },
                 legend: {
                     enabled: false
                 },
                 tooltip: {
                     pointFormat: 'Cantidad de reservas: <b>{point.y}</b>'
                 },
                 series: [{
                     data: processed_json // LA VARIABLE QUE CONTIENE TODAS LAS PELICULAS
                 }]
                 
                 
             }); // FIN DE $('#container5')        	
        }); // FIN DE GETSJON        
	};// FIN DE PUNTO5	
	//=====================================================
	//=====================================================
	//=====================================================
function Punto6(idfecha1,idfecha2){
		
		var host =document.location.host;
	    var url = "http://" + host + "/" + getContext() + "/stats/puntoR6?idfecha1="+idfecha1+"&idfecha2="+idfecha2;//url de la funcion del PUNTO 1 DEL CONTROLLER
		var processed_json = new Array();  
		
        $.getJSON(url, function(data) { 
        	
        	$.each( data, function( key, value ) {   		
        		 processed_json.push([key,parseInt(value)]);  
        	});
	    
        	 // ACA TODO LO QUE TENGO QUE HACER PARA GRAFICAR
        	 $('#container6').highcharts({
                 chart: {
                     type: "column"
                 },
                 title: {
                     text: "Géneros más solicitados"
                 },
                 xAxis: {
                 	type: 'category',
                     labels: {
                         rotation: -45,
                         style: {
                             fontSize: '13px',
                             fontFamily: 'Verdana, sans-serif'
                         }
                     }
                 },
                 yAxis: {
                     title: {
                         text: "Generos"
                     }
                 },
                 legend: {
                     enabled: false
                 },
                 tooltip: {
                     pointFormat: 'Cantidad de personas: <b>{point.y}</b>'
                 },
                 series: [{
                     data: processed_json // LA VARIABLE QUE CONTIENE TODAS LAS PELICULAS
                 }]
                 
                 
             }); // FIN DE $('#container6')        	
        }); // FIN DE GETSJON        
	};// FIN DE PUNTO6	
	//=====================================================
	//=====================================================
	//=====================================================
function Punto7(idfecha1,idfecha2){
		
		var host =document.location.host;
	    var url = "http://" + host + "/" + getContext() + "/stats/puntoR7?idfecha1="+idfecha1+"&idfecha2="+idfecha2;//url de la funcion del PUNTO 1 DEL CONTROLLER
		var processed_json = new Array();  
		
        $.getJSON(url, function(data) { 
        	
        	$.each( data, function( key, value ) {   		
        		 processed_json.push([key,parseInt(value)]);  
        	});
	    
        	 // ACA TODO LO QUE TENGO QUE HACER PARA GRAFICAR
        	 $('#container7').highcharts({
                 chart: {
                     type: "column"
                 },
                 title: {
                     text: "Día de la semana con mayor cantidad de asistencia de menores"
                 },
                 xAxis: {
                 	type: 'category',
                     labels: {
                         rotation: -45,
                         style: {
                             fontSize: '13px',
                             fontFamily: 'Verdana, sans-serif'
                         }
                     }
                 },
                 yAxis: {
                     title: {
                         text: "Semana"
                     }
                 },
                 legend: {
                     enabled: false
                 },
                 tooltip: {
                     pointFormat: 'Cantidad de personas: <b>{point.y}</b>'
                 },
                 series: [{
                     data: processed_json // LA VARIABLE QUE CONTIENE TODAS LAS PELICULAS
                 }]
                 
                 
             }); // FIN DE $('#container7')        	
        }); // FIN DE GETSJON        
	};// FIN DE PUNTO7	
});