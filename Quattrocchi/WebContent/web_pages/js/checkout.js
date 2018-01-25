$(document).ready(function() {
	
	$("#submit_order").click(function(event) {
		var CreditCardID = $('select#credit_card_select').val();
		var ShippingAddressID = $('select#shipping_address_select').val();
		
		if(CreditCardID==null||CreditCardID==""){
			alert("Carta di credito non valida!");
			return;
		}
		if(ShippingAddressID==null||ShippingAddressID==""){
			alert("Indirizzo di spedizione non valido!");
			return;
		}
		window.location.href = '/Quattrocchi/checkout?CreditCardID='+CreditCardID+'&ShippingAddressID='+ShippingAddressID;
		/*$.ajax({
			type : "POST",
			url : "checkout",
			data : {
				CreditCardID:CreditCardID,
				ShippingAddressID:ShippingAddressID
			},
			async : false,
			error : function(xhr, status, errorThrown) {
				console.log(JSON.stringify(xhr));
				console.log("AJAX error: " + status + ' : ' + errorThrown);
				showNotification("Errore durante l'esecuzione del checkout! Se il problema persiste contattaci");
			}
		});*/
	});
});