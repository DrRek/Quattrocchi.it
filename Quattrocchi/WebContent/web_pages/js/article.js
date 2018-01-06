var toDoD = 'asyncGenericSearch';
var sortD = 'nome';
$(document).ready(function() {

	initialize();
	setSearchField();
	
	    
	$("#advancedSearch").on("click", function(event) {
		toDoD = 'asyncSpecificSearch'
		advancedSearch();
	});
});

function formatData(responseText)
{
	var toAppend = '';
	$.each(responseText, function(i, articleObject) {
		if(articleObject.disponibilità > 0){
			toAppend += '<div class="block enlarge">'
							+'<div class="top">'
								+ '<ul>'
								+ '<li><a href="#"><i class="fa fa-star-o" aria-hidden="true"></i></a></li>'
								+ '<li><span class="prodotto">' + articleObject.marca + '</span></li>'
								+ '<li><a href="#"><i class="fa fa-shopping-basket" aria-hidden="true"></i> </a></li>'
								+ '</ul>'
								+'</div>';
				toAppend += '<div class="middle">';
				toAppend += '<img src="/Quattrocchi/web_pages/image/occhiali_placeholder.jpg" alt="pic" />';
				toAppend += '</div>'
						 +  '<div class="bottom">'
	    	   			 + '<div class="heading">'+ articleObject.modello +'</div>'
	    	   			 + '<div class="info">' + articleObject.disponibilità + ' pezzi disponibili</div>';
				if(articleObject.sconto > 0){
					if(articleObject.tipoSconto == "%"){
						var sconto = parseInt(((articleObject.prezzo)-(articleObject.prezzo*articleObject.sconto/100))*100)/100;
					}else{
						var sconto = parseInt(articleObjec.prezzo-articleObject.sconto*100)/100;
					}
					toAppend += '<div class="price">' +sconto + ' € <span class="old-price">'+parseInt(articleObject.prezzo*100)/100+'€</span></div>';
				} else {
					toAppend += '<div class="price">' + parseInt(articleObject.prezzo*100)/100 + '€</div>';
				}
	    	   	toAppend +='<div><a href="articlePage?nome=' + articleObject.nome
	    	   			+ '&marca=' + articleObject.marca + '">Mostra</a></div>'
	    	   			+ '</div>'
						+'</div>';
		}
	});
	$("#demos").html(toAppend);
}

function setSearchField() {
	if ($('select[name=tipo]').val() == "O") {
		$(".specificiPerOcchiali").show();
		$(".specificiPerLentine").hide();
	} else {
		$(".specificiPerOcchiali").hide();
		$(".specificiPerLentine").show();
	}
}

function orderByName() {
	sortD = 'nome';
	if (toDoD == "asyncGenericSearch") {
		asyncGenericSearch();
	}
	else {
		advancedSearch();
	}
}

function orderByMarca() {
	sortD = 'marca';
	if (toDoD == "asyncGenericSearch") {
		asyncGenericSearch();
	}
	else {

		advancedSearch();
	}
}

function orderByTipo() {
	sortD = 'tipo';
	if (toDoD == "asyncGenericSearch") {
		asyncGenericSearch();
	}
	else {
		advancedSearch();
	}
}

function orderByPrezzo() {
	sortD = 'prezzo';
	if (toDoD == "asyncGenericSearch") {
		asyncGenericSearch();
	}
	else {
		advancedSearch();
	}
}

function advancedSearch() {
	var tipo = $('select[name=tipo]').val();
	var daCercare = $('input[name=daCercare1]').val();
	if (daCercare == null || daCercare.length == 0) {
		daCercare = $('input[name=daCercare]').val();
	}
	var marca = $('select[name=marca]').val();
	var prezzoMin = $('input[name=prezzoMin]').val();
	var prezzoMax = $('input[name=prezzoMax]').val();
	if (tipo == "O") {
		var sesso = $('select[name=sesso]').val();
		var colore = $('input[name=colore]').val();
		$.ajax({
			type : "GET",
			url : "article",
			data : {
				toDo : 'asyncSpecificSearch',
				daCercare : daCercare,
				tipo : tipo,
				marca : marca,
				prezzoMin : prezzoMin,
				prezzoMax : prezzoMax,
				sesso : sesso,
				colore : colore,
				sort : sortD
			},
			dataType : "json",
			error : function(xhr, status, errorThrown) {
				console.log(JSON.stringify(xhr));
				console.log("AJAX error: " + status + ' : ' + errorThrown);
			},
			success : function(responseText) {
				formatData(responseText);
			}
		})
	} else {
		var gradazione = $('select[name=gradazione]').val();
		var tipologia = $('select[name=tipologia]').val();
		var raggio = $('input[name=raggio]').val();
		var diametro = $('input[name=diametro]').val();
		var colore = $('select[name=colore]').val();
		$.ajax({
			type : "GET",
			url : "article",
			data : {
				toDo : 'asyncSpecificSearch',
				daCercare : daCercare,
				tipo : tipo,
				marca : marca,
				prezzoMin : prezzoMin,
				prezzoMax : prezzoMax,
				gradazione : gradazione,
				tipologia : tipologia,
				raggio : raggio,
				diametro : diametro,
				colore : colore,
				sort : sortD
			},
			dataType : "json",
			success : function(responseText) {
				formatData(responseText);
			}
		})
	}
}

function retrieveAll() {
	toDoD = 'asyncGenericSearch'
		$.ajax({
			type : "GET",
			url : "visualizza_catalogo",
			data : {
				action : 'retrieveAll'
			},
			dataType : "json",
			error : function(xhr, status, errorThrown) {
				console.log(JSON.stringify(xhr));
				console.log("AJAX error: " + status + ' : ' + errorThrown);
			},
			success : function(responseText) {
				formatData(responseText);
			}
		})
}

function simpleSearch(toSearch) {
	console.log(toSearch)
		$.ajax({
			type : "GET",
			url : "ricerca_prodotto",
			data : {
				action : 'search',
				toSearch : toSearch
			},
			dataType : "json",
			error : function(xhr, status, errorThrown) {
				console.log(JSON.stringify(xhr));
				console.log("AJAX error: " + status + ' : ' + errorThrown);
			},
			success : function(responseText) {
				formatData(responseText);
			}
		})
}

function initialize(){
	var toSearch = $('input[name=daCercare1]').val();
	if(toSearch != null && toSearch != undefined && toSearch != ""){
		simpleSearch(toSearch);
	}
	else{
		retrieveAll();
	}
}