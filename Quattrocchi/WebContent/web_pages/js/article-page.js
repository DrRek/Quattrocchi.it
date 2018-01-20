$(document).ready(function() {

	$("#addCart").click(function(event) {
		var codice = document.getElementById('codice').value;
		$.ajax({
			type : "GET",
			url : "aggiungi_al_carrello",
			data : {
				articoloId:codice
			},
			error : function(xhr, status, errorThrown) {
				console.log(JSON.stringify(xhr));
				console.log("AJAX error: " + status + ' : ' + errorThrown);
				showError("Errore durante l'aggiunta al carrello! Se il problema persiste contattaci");
			},
			success : function(responseText) {
				$("a#count").html(parseInt($("a#count").html()) + 1);
			}
		});
	});
});