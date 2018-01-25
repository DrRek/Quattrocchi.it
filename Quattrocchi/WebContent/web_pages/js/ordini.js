function checkForErrorGestioneOrdine(){
	var stato = $("input[name='statoOrdine']:checked");
	
	if(!/^([A-Za-z]{3,10})$/.test($('select[name=corriere]').val())){
		showNotification("Inserire un valore valido per il corriere!");
		return false;
	}
	
	if(!/^([A-Za-z0-9]{5,15})$/.test($('input[name=tracking]').val())){
		showNotification("Inserire un valore valido per il numero di tracking!");
		return false;
	}
	
	if(!stato.val().equals("Da spedire") && !stato.val().equals("In corso") && !stato.val().equals("consegnato")){
		showNotification("Inserire un valore valido per lo stato dell'ordine!");
		return false;
	}

	return true;
}