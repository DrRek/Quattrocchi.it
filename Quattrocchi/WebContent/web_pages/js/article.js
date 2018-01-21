var toDoD = 'asyncGenericSearch';
var sortD = 'nome';
$(document).ready(function() {

	initialize();
	setSearchField();


	$("#advancedSearch").on("click", function(event) {
		advancedSearch();
	});
});

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
	var toSearch = $('input[name=daCercare1]').val();
	if (toSearch == null || toSearch.length == 0) {
		toSearch = $('input[name=daCercare]').val();
	}
	var marca = $('select[name=marca]').val();
	var prezzoMin = $('input[name=prezzoMin]').val();
	var prezzoMax = $('input[name=prezzoMax]').val();
	var colore = $('input[name=colore]').val();
	$.ajax({
		type : "GET",
		url : "ricerca_prodotto_avanzata",
		data : {
			toSearch : toSearch,
			marca : marca,
			prezzoMin : prezzoMin,
			prezzoMax : prezzoMax,
			colore : colore,
			sort : sortD
		},
		dataType : "json",
		error : function(xhr, status, errorThrown) {
			console.log(JSON.stringify(xhr));
			console.log("AJAX error: " + status + ' : ' + errorThrown);
			showError("Errore durante l'esecuzione della ricerca avanzata! Se il problema persiste contattaci");
		},
		success : function(responseText) {
			formatData(responseText);
		}
	})
}

function retrieveAll() {
	$.ajax({
		type : "GET",
		url : "visualizza_tutti",
		dataType : "json",
		error : function(xhr, status, errorThrown) {
			console.log(JSON.stringify(xhr));
			console.log("AJAX error: " + status + ' : ' + errorThrown);
			showError("Errore durante l'esecuzione della ricerca globale! Se il problema persiste contattaci");
		},
		success : function(responseText) {
			formatData(responseText);
		}
	})
}

function simpleSearch(toSearch) {
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
			showError("Errore durante l'esecuzione della ricerca semplice! Se il problema persiste contattaci");
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

function articlePageViewFunction(idProdotto){
	window.location.href = '/Quattrocchi/visualizza_prodotto?id='+idProdotto;
	//$.get("/Quattrocchi/visualizza_prodotto", {id:idProdotto});
	/*
	$.ajax({
		type : "GET",
		url : "visualizza_prodotto",
		data : {
			id : idProdotto
		},
		async: false
	})*/
}

function formatData(responseText){
	var toAppend = '';
	$.each(responseText, function(i, articleObject) {
		console.log("elemento trovato")
		if(articleObject.disponibilità > 0){
			toAppend += '<div class="block enlarge" onClick="articlePageViewFunction('+articleObject.codice+')">' //funzione che viene chiamata per ArticlePageView
				+'<div class="top">'
				+ '<ul>'
				+ '<li><a href="#"><i class="fa fa-star-o" aria-hidden="true"></i></a></li>'
				+ '<li><span class="prodotto">' + articleObject.marca + '</span></li>'
				+ '<li><a href="#"><i class="fa fa-shopping-basket" aria-hidden="true"></i> </a></li>'
				+ '</ul>'
				+'</div>';
			toAppend += '<div class="middle">';
			toAppend += '<img src="/Quattrocchi/web_pages/image/'+articleObject.img1+'" alt="pic" />';
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
			toAppend +='</div>'
			+'</div>';
		}
	});
	$("#demos").html(toAppend);
}