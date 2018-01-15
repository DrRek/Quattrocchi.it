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
		
		$.ajax({
			type : "POST",
			url : "checkout",
			data : {
				CreditCardID:CreditCardID,
				ShippingAddressID:ShippingAddressID
			},
			dataType : "json"
		});
	});
});