$(document).ready(function() {
	$("body").on("change","input[name='quantitaPezzi']",function() {
		var valore = $(this).val();
		var val = parseInt(valore);
		if (val > 0) {
			var modello = $(this).parent().siblings(".forForm").find(".formForRemove").find(".articoloId").val();

			$.ajax({
				type : "POST",
				url : "modifica_nel_carrello",
				data : {
					articoloId : modello,
					quantita : val
				},
				dataType : "json",
				error : function(xhr, status, errorThrown) {
					console.log(JSON.stringify(xhr));
					console.log("AJAX error: " + status + ' : ' + errorThrown);
					showError("Errore durante il cambio della quantità dell'articolo nel carrello! Se il problema persiste contattaci");
				},
				success : function(responseText) {
					if(responseText!=null && responseText!=""){
						showError(responseText)
					}
					updateCartNumber()
				}
			});
		}
	});
});

function updateCartNumber(){
	var sum = 0;
	$("input[name=quantitaPezzi]").each(function(){
		sum += parseInt(this.value);
	});
	$("span#count").html(sum);
}