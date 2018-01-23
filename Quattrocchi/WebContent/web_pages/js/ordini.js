function checkForErrorGestioneOrdine(){
	var stato = $("input[name='statoOrdine']:checked");
	var dataDiConsegna = $("input[name='dataDiConsegna']");
	var dataDiConsegnaParsed = new Date(dataDiConsegna);
	var dataDiOggi = new Date();
	
	if(!/^([A-Za-z]{3,10})$/.test($('select[name=corriere]').val())){
		showError("Inserire un valore valido per il corriere!");
		return false;
	}
	
	if(!/^([A-Za-z0-9]{5,15})$/.test($('input[name=tracking]').val())){
		showError("Inserire un valore valido per il numero di tracking!");
		return false;
	}
	
	if(!stato.val().equals("Da spedire") && !stato.val().equals("In corso") && !stato.val().equals("consegnato")){
		showError("Inserire un valore valido per lo stato dell'ordine!");
		return false;
	}
	
	if(dataDiConsegna.getTime() <= dataDiOggi.getTime()) {
		showError("Inserire un valore valido per la data di consegna dell'ordine!");
		return false;
	}

	return true;
}