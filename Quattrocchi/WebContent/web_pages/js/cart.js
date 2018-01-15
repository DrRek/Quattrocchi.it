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
				success : updateCartNumber()
			});
		}
	});
});

function updateCartNumber(){
	var sum = 0;
	$("input[name=quantitaPezzi]").each(function(){
		sum += parseInt(this.value);
	});
	$("a#count").html(sum);
}