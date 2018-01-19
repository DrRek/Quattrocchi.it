$(document).ready(function() {

	$("#addCart").click(function(event) {
		var codice = document.getElementById('codice').value;
		$.ajax({
			type : "GET",
			url : "aggiungi_al_carrello",
			data : {
				articoloId:codice
			},
			dataType : "json",
			error : function(xhr, status, errorThrown) {
				console.log(JSON.stringify(xhr));
				console.log("AJAX error: " + status + ' : ' + errorThrown);
				showError("<div class='alert alert-danger container'><h3>Errore durante l'aggiunta al carrello! Se il problema persiste contattaci<h3></div>");
			},
			success : function(responseText) {
				$("a#count").html(parseInt($("a#count").html()) + 1);
			}
		});
	});
});