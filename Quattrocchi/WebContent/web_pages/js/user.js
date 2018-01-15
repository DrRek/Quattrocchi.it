$(document).ready(function() {

	$("input[name=addAddress]").click(function(event){
		var indirizzo = $("input[name=indirizzo]").val();
		var civico = $("input[name=numeroCivico]").val();
		var cap = $("input[name=cap]").val();
		var provincia = $("input[name=provincia]").val();
		var stato = $("input[name=stato]").val();
		
		$.ajax({
			type : "POST",
			url : "inserisci_indirizzo",
			data : {
				indirizzo : indirizzo,
				civico : civico,
				cap : cap,
				provincia : provincia,
				stato : stato
			},
			success : function(responseText) {
				$("#lastShipAdd").before('<tr><td>'+indirizzo+'</td><td>'+civico+'</td><td >'+cap+'</td><td>'+provincia+'</td><td>'+stato+'</td><td><input type="submit"class="btn btn-outline-secondary" name="removeAddress"value="remove" /></td></tr>');
				$("input[name=indirizzo]").val("");
				$("input[name=numeroCivico]").val("");
				$("input[name=cap]").val("");
				$("input[name=provincia]").val("");
				$("input[name=stato]").val("");
			}
		});
	})

});