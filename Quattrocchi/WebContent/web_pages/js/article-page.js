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
				showNotification("Errore durante l'aggiunta al carrello! Se il problema persiste contattaci");
			},
			success : function(responseText) {
				$("span#count").html(parseInt($("span#count").html()) + 1);
			}
		});
	});
});