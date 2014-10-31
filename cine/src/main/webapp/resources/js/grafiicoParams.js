function DashBoardParams(celulasSelector, estadoSelector, usuarioSelector, frenteSelector) {
	var self = this;
	
	// Opciones para grafico de torta
	this.getOpcionesParaGraficoDeTorta = function() {
		var opcionesParaGraficoDeTorta = {
			animation : {
				duration : 500,
				easing : 'linear'
			},
			sliceVisibilityThreshold:0,
//			legend:{position: 'top'},
			chartArea:{left:20,top:15,width:'90%',height:'100%'},
			is3D : true
		};
		return opcionesParaGraficoDeTorta;
	};
	
	// Asignamos el valor seleccionado en true al filtro deseado.
	this.selectAcum = function(selecciones) {
		$.map(self.acumLineSelections,function(value,key){
			value.select = $.inArray(key,selecciones) > -1;									  	
		});
	};
	this.getArrayAcumLine = function(response) {
		var data = [ [] ];
		var i = 0;
		$.map(response, function(value, key) {
			var date = new Date(key);
			var mes = date.getMonth() + 1;
			if (mes < 10) {
				mes = "0" + mes;
			}
			var text = date.getFullYear() + "-" + mes;
			// $.map para devolver un array con los valores del objeto
			var rowTable = [text];
			$.map(self.acumLineSelections,function(v,k){
				var x = value[k]?value[k]:0;
				rowTable.push(x);
				//pongo un null este es el espacio donde va a ir el tooltip
				rowTable.push(null);
			});
			data[i++] = rowTable;
			// data[i++] = $.merge(date, objValue);
		});
		return data;
	};

	this.getIndexDataTable = function(dataTable, label) {
		var size = dataTable.getNumberOfColumns();
		for ( var i = 0; i < size; i++) {
			if (dataTable.getColumnLabel(i) === label) {
				return i;
			}
		}
	};
	
	//necesito saber que posicion es el indicador, no en la matriz sino por orden
	this.getIndexDataTableForAxis = function(dataTable, label) {
		var size = dataTable.getNumberOfColumns();
		var relativePosition = 0;
		//salteo la primer columna ('rango') y el primer indicador, si no coincide con ninguno,
		//devolvera 0, con lo cual sera el primer indicador el que quede seleccionado
		for ( var i = 1; i < size; i++) {
			var columnLabel = dataTable.getColumnLabel(i);
			if (columnLabel === label) {
				return relativePosition;
			}
			if(columnLabel && columnLabel!==""){
				relativePosition++;
			}
		}
	};
	
	this.getOptionsSeries = function(dataTable) {
		var series = {};
		$.map(self.acumLineSelections, function(e){
			if(e.type=="line"){
				var index = self.getIndexDataTableForAxis(dataTable, e.name);
				if (index!=undefined) {
					series[index] = {
						type : "line",
						targetAxisIndex : 1
					};
				}				
			}
		});
		return series;
	};
	this.getDataToAcumLine = function(response) {
		if($.isEmptyObject(self.acumLineSelections)){
			self.acumLineSelections = response.metaInformacion;
		}
		var dataTable = undefined;
		if(!$.isEmptyObject(response)){
			self.series = {};
			var arrayTitle = ["Mes"];
			$.map(self.acumLineSelections, function(value, key) {
				arrayTitle.push(value.name);
			});
			dataTable = self.dataAcumToDataTable(response.datos, arrayTitle);
			if(dataTable){
				var titleToRemove = [];
				$.map(self.acumLineSelections, function(value, key) {
					if (!value.select) {
						titleToRemove.push(value.name);
					}
				});
				if (titleToRemove.length < arrayTitle.length-1) {
					$.each(titleToRemove, function(i, title) {
						var index = self.getIndexDataTable(dataTable, title,
								dataTable.getNumberOfColumns());
						dataTable.removeColumn(index);
						//remuevo tambien el tooltip
						dataTable.removeColumn(index);
					});
				}
				self.oppcionesComboLine.series = self.getOptionsSeries(dataTable);
			}
		}
		return dataTable;

	};
	this.dataAcumToDataTable = function(datos,titles){
		var dataTable = undefined;
		//le pido la matriz con todos los datos
		if (!$.isEmptyObject(datos)){//verificacion
			dataTable = new google.visualization.DataTable();
			var data = self.getArrayAcumLine(datos);
			dataTable.addColumn('string', titles[0]);
			for(var i=1;i<titles.length;i++){
				dataTable.addColumn('number', titles[i]);
				dataTable.addColumn({type: 'string', role: 'tooltip', 'p':{'html':true}});
			}
			data.sort();
			dataTable.addRows(data);
			for (var i = 0, rows = dataTable.getNumberOfRows(), month; i < rows; i++) {
			    month = dataTable.getValue(i, 0);
			    for (var j = 1, cols = dataTable.getNumberOfColumns(), label; j < cols; j += 2) {
			        label = dataTable.getColumnLabel(j);
			        var metaInf = $.map(dashBoardParams.acumLineSelections, function(n, i){
			             if (n.name == label){
			            	 return n;
			             }
			          })[0];
			        // set the tooltip for this column
			        dataTable.setValue(i, j + 1, ["<table><tr><td>En el mes ", month, "</td></tr>",
			        		"<tr><td> Hubo <span style='font-weight:bold'>" , label ,"</span> Por: ",dataTable.getValue(i, j),"</td></tr>",
			        		"<tr><td><span style='font-weight:bold'>Descripcion: </span>" , metaInf.descripcion ,"</td></tr>",
			        		"</table>"].join(""));
			    }
		    }
		}
		
		return dataTable;
	};
	this.getPorcentaje = function(total, part, decimals) {
		var dif = total - part;
		var result = (dif * 100) / total;
		if(!decimals){
			decimals=2;
		}
		return parseFloat(result.toFixed(decimals));
	};

	return this;
};