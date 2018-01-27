var toDoD = 'asyncGenericSearch';
var sortD = 'nome';
$(document).ready(function() {

	initialize();


	$("#advancedSearch").on("click", function(event) {
		if(checkAdvancedSearchRegex()){
			advancedSearch();
		}
	});
});



function checkAdvancedSearchRegex(){
	var toSearch = $('input[name=daCercare]').val();
	if(toSearch != null && toSearch != "" && !/^([A-Za-z0-9 ]{1,20})$/.test(toSearch)){
		showNotification("Parametro da cercare non valido.")
		return false;
	}
	
	var marca = $('select[name=marca]').val();
	if(marca != null && marca != "" && !/^([A-Za-z0-9 ]{1,20})$/.test(marca)){
		showNotification("Parametro marca non valido.")
		return false;
	}
	
	var prezzoMin = $('input[name=prezzoMin]').val();
	if(prezzoMin != null && prezzoMin != ""){
		try{
			parseInt(prezzoMin);
		} catch(e){
			showNotification("Parametro prezzo minimo non valido.");
			return false;
		}
	}
	
	var prezzoMax = $('input[name=prezzoMax]').val();
	if(prezzoMax != null && prezzoMax != ""){
		try{
			parseInt(prezzoMax);
		} catch(e){
			showNotification("Parametro prezzo massimo non valido.");
			return false;
		}
	}
	
	var colore = $('input[name=colore]').val();
	if(colore != null && colore != "" && !/^([A-Za-z0-9 ]{1,20})$/.test(colore)){
		showNotification("Parametro colore non valido.");
		return false;
	}
	
	return true;
}

function advancedSearch() {
	toSearch = $('input[name=daCercare]').val();
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
			showNotification("Errore durante l'esecuzione della ricerca avanzata! Se il problema persiste contattaci");
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
			showNotification("Errore durante l'esecuzione della ricerca globale! Se il problema persiste contattaci");
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
			showNotification("Errore durante l'esecuzione della ricerca semplice! Se il problema persiste contattaci");
		},
		success : function(responseText) {
			formatData(responseText);
		}
	})
}

function initialize(){
	var toSearch = $('input[name=daCercare]').val();
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
	try{
		$.each(responseText, function(i, articleObject) {
			if(articleObject.disponibilità > 0){
				toAppend += '<div class="block enlarge" onClick="articlePageViewFunction('+articleObject.codice+')">' //funzione che viene chiamata per ArticlePageView
					+'<div class="top">'
					+ '<ul>'
					+ '<li><a href="#"><i class="fa fa-star-o" aria-hidden="true"></i></a></li>'
					+ '<li><span class="prodotto">' + articleObject.marca + '</span></li>'
					+ '<li><a href="#"><i class="fa fa-shopping-basket" aria-hidden="true"></i> </a></li>'
					+ '</ul>'
					+'</div>'
					+'<div class="middle">'
					+'<img src="/Quattrocchi/web_pages/image/'+articleObject.img1+'" alt="pic" />'
					+'</div>'
					+ '<div class="bottom">'
					+ '<div class="info">Disponibilità immediata</div>'
					+ '<div class="heading">'+ articleObject.modello +'</div>'
					+'<div class="price">' + parseInt(articleObject.prezzo*100)/100 + '€</div>'
					+'</div>'
					+'</div>';
			}
		});
		$("#demos").html(toAppend);
	} catch (exception){
		$("#demos").empty();
		showNotification(responseText);
	}
}