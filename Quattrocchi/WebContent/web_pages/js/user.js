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
			error : function(xhr, status, errorThrown) {
				console.log(JSON.stringify(xhr));
				console.log("AJAX error: " + status + ' : ' + errorThrown);
				showNotification("Errore durante l'aggiunta dell'indirizzo! Se il problema persiste contattaci");
			},
			success : function(responseText) {
				try{ 
					var id = parseInt(responseText)
					if(isNaN(id) || id<=0){
						throw "Not a number";
					}
					$("#lastShipAdd").before('<tr><td>'+indirizzo+'</td><td>'+civico+'</td><td >'+cap+'</td><td>'+provincia+'</td><td>'+stato+'</td><td><input type="hidden" class="addressCode" value="'+id+'"/><input type="submit"class="btn btn-outline-secondary" name="removeAddress"value="rimuovi" /></td></tr>');
					$("input[name=indirizzo]").val("");
					$("input[name=numeroCivico]").val("");
					$("input[name=cap]").val("");
					$("input[name=provincia]").val("");
					$("input[name=stato]").val("");
					showNotification("Indirizzo inserito con successo");
				} catch(exception){
					showNotification(responseText)
				}
			}
		});
	})

	$(document).on("click", 'input[name=addCard]', function(event){
		if(checkForErrorAddCard()){
			return;
		}

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
			error : function(xhr, status, errorThrown) {
				console.log(JSON.stringify(xhr));
				console.log("AJAX error: " + status + ' : ' + errorThrown);
				showNotification("Errore durante l'aggiunta della carta! Se il problema persiste contattaci");
			},
			success : function(responseText) {
				try{
					var id = parseInt(responseText)
					if(isNaN(id) || id<=0){
						throw "Not a number";
					}
					console.log(responseText + id);
					$("#lastCreditCard").before('<tr><td>xxxx-xxxx-xxxx-'+numcc.substring(12)+'</td><td>'+intestatario+'</td><td >'+circuito+'</td><td>'+scadenza+'</td><td>xxx</td><td><input type="hidden" class="cardCode" value="'+id+'"/><input type="submit"class="btn btn-outline-secondary" name="removeCard" value="rimuovi" /></td></tr>');
					$("input[name=numcc]").val("");
					$("input[name=intestatario]").val("");
					$("input[name=circuito]").val("");
					$("input[name=scadenza]").val("");
					$("input[name=cvv]").val("");
					showNotification("Carta aggiunta con successo");
				} catch(exception){
					showNotification(responseText)
				}
			}
		});
	})

	$(document).on("click", 'input[name=removeAddress]', function(event){
		var idToRemove = $(this).siblings(".addressCode").val();
		$.ajax({
			type : "POST",
			url : "rimuovere_indirizzo",
			error : function(xhr, status, errorThrown) {
				console.log(JSON.stringify(xhr));
				console.log("AJAX error: " + status + ' : ' + errorThrown);
				showNotification("Errore durante la rimozione dell'indirizzo! Se il problema persiste contattaci");
			},
			data : {
				id : idToRemove
			},
			error : function(xhr, status, errorThrown) {
				console.log(JSON.stringify(xhr));
				console.log("AJAX error: " + status + ' : ' + errorThrown);
				showNotification("Errore durante l'aggiunta della carta! Se il problema persiste contattaci");
			},
			success : function(responseText) {
				showNotification(responseText)
			}
		});
		$(this).parent().parent().remove();
	})

	$(document).on("click", 'input[name=removeCard]', function(event){
		var idToRemove = $(this).siblings(".cardCode").val();
		$.ajax({
			type : "POST",
			url : "rimuovere_carta",
			error : function(xhr, status, errorThrown) {
				console.log(JSON.stringify(xhr));
				console.log("AJAX error: " + status + ' : ' + errorThrown);
				showNotification("Errore durante la rimozione della carta! Se il problema persiste contattaci");
			},
			data : {
				id : idToRemove
			},
			error : function(xhr, status, errorThrown) {
				console.log(JSON.stringify(xhr));
				console.log("AJAX error: " + status + ' : ' + errorThrown);
				showNotification("Errore durante l'aggiunta della carta! Se il problema persiste contattaci");
			},
			success : function(responseText) {
				showNotification(responseText)
			}
		});
		$(this).parent().parent().remove();
	})
});

function checkForErrorAddCard(){
	if(!/^([0-9]{16})$/.test($('input[name=numcc]').val())){
		showNotification("Inserire un valore valido per il numero della carta!");
		return true;
	}

	if(!/^([A-Za-z ]{5,40})$/.test($('input[name=intestatario]').val())){
		showNotification("Inserire un valore valido per l'intestatario!");
		return true;
	}

	if(!/^([A-Za-z ]{4,20})$/.test($('input[name=circuito]').val())){
		showNotification("Inserire un valore valido per il circuito!");
		return true;
	}

	if(!/^([0-9]{2}[/]{1}[0-9]{4})$/.test($('input[name=scadenza]').val())){
		showNotification("Inserire un valore valido per la data (MM/yyyy)!");
		return true;
	}

	if(!/^([0-9]{3})$/.test($('input[name=cvv]').val())){
		showNotification("Inserire un valore valido per il cvv!");
		return true;
	}
}

function checkForErrorAddAddress(){
	if(!/^([A-Za-z0-9 ]{5,40})$/.test($('input[name=indirizzo]').val())){
		showNotification("Inserire un valore valido per l'indirizzo!");
		return true;
	}

	if(!/^([0-9]{1,4})$/.test($('input[name=numeroCivico]').val())){
		showNotification("Inserire un valore valido per il numero civico!");
		return true;
	}

	if(!/^([0-9]{5})$/.test($('input[name=cap]').val())){
		showNotification("Inserire un valore valido per il cap!");
		return true;
	}

	if(!/^([A-Z]{2})$/.test($('input[name=provincia]').val())){
		showNotification("Inserire un valore valido per la provincia (sigla in maiuscolo)!");
		return true;
	}

	if(!/^([A-Za-z ]{5,30})$/.test($('input[name=stato]').val())){
		showNotification("Inserire un valore valido per lo stato!");
		return true;
	}
	return false;
}