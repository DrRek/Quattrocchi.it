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
			success : function(responseText) {
				//Need to update header
			}
		});
		
		$("a#count").html(parseInt($("a#count").html()) + 1);
	});
});