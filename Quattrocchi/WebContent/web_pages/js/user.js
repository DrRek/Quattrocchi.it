$(document).ready(function() {

	$(document).on("click", 'input[name=addAddress]', function(event){
		if(checkForErrorAddAddress()) {
			return;
		}
		
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
			dataType : "json",
			success : function(responseText) {
				$("#lastShipAdd").before('<tr><td>'+indirizzo+'</td><td>'+civico+'</td><td >'+cap+'</td><td>'+provincia+'</td><td>'+stato+'</td><td><input type="hidden" class="addressCode" value="'+responseText+'"/><input type="submit"class="btn btn-outline-secondary" name="removeAddress"value="remove" /></td></tr>');
				$("input[name=indirizzo]").val("");
				$("input[name=numeroCivico]").val("");
				$("input[name=cap]").val("");
				$("input[name=provincia]").val("");
				$("input[name=stato]").val("");
			}
		});
	})
	
	$(document).on("click", 'input[name=addCard]', function(event){
		var numcc = $("input[name=numcc]").val();
		var intestatario = $("input[name=intestatario]").val();
		var circuito = $("input[name=circuito]").val();
		var scadenza = $("input[name=scadenza]").val();
		var cvv = $("input[name=cvv]").val();
		
		$.ajax({
			type : "POST",
			url : "inserisci_carta",
			data : {
				numcc : numcc,
				intestatario : intestatario,
				circuito : circuito,
				scadenza : scadenza,
				cvv : cvv
			},
			success : function(responseText) {
				$("#lastCreditCard").before('<tr><td>'+numcc.substring(12)+'</td><td>'+intestatario+'</td><td >'+circuito+'</td><td>'+scadenza+'</td><td>XXX</td><td><input type="hidden" class="cardCode" value="'+responseText+'"/><input type="submit"class="btn btn-outline-secondary" name="removeAddress"value="remove" /></td></tr>');
				$("input[name=numcc]").val("");
				$("input[name=intestatario]").val("");
				$("input[name=circuito]").val("");
				$("input[name=scadenza]").val("");
				$("input[name=cvv]").val("");
			}
		});
	})
	
	$(document).on("click", 'input[name=removeAddress]', function(event){
		var idToRemove = $(this).siblings(".addressCode").val();
		$.ajax({
			type : "POST",
			url : "rimuovere_indirizzo",
			data : {
				id : idToRemove
			}
		});
		$(this).parent().parent().remove();
	})

	$(document).on("click", 'input[name=removeCard]', function(event){
		var idToRemove = $(this).siblings(".cardCode").val();
		$.ajax({
			type : "POST",
			url : "rimuovere_carta",
			data : {
				id : idToRemove
			}
		});
		$(this).parent().parent().remove();
	})
});

function showError(error){
	$('#errorInfoDiv').show();
	$('#errorText').html(error);
}

function checkForErrorAddAddress(){
	if(!/^([A-Za-z0-9 ]{5,40})$/.test($('input[name=indirizzo]').val())){
		showError("Inserire un valore valido per l'indirizzo!");
		return true;
	}
	
	if(!/^([0-9]{1,4})$/.test($('input[name=numeroCivico]').val())){
		showError("Inserire un valore valido per il numero civico!");
		return true;
	}
	
	if(!/^([0-9]{5})$/.test($('input[name=cap]').val())){
		showError("Inserire un valore valido per il cap!");
		return true;
	}
	
	if(!/^([A-Z]{2})$/.test($('input[name=provincia]').val())){
		showError("Inserire un valore valido per la provincia (sigla in maiuscolo)!");
		return true;
	}

	if(!/^([A-Za-z ]{5,30})$/.test($('input[name=stato]').val())){
		showError("Inserire un valore valido per lo stato!");
		return true;
	}
	return false;
}