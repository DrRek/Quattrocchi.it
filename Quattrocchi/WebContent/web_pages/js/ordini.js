
function checkForErrorGestioneOrdine(){
	var stato = $("input[name='statoOrdine']:checked");
	
	if(!/^([A-Za-z]{3,10})$/.test($('select[name=corriere]').val())){
		showError("Inserire un valore valido per il corriere!");
		return true;
	}
	
	if(!/^([A-Za-z0-9]{5,15})$/.test($('input[name=tracking]').val())){
		showError("Inserire un valore valido per il numero di tracking!");
		return true;
	}
	
	if(!stato.val().equals("Da spedire") && !stato.val().equals("In corso") && !stato.val().equals("consegnato")){
		showError("Inserire un valore valido per il cap!");
		return true;
	}

	return false;
}