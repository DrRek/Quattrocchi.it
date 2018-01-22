function showError(error){
	if(error != null && error != ""){
		$('#errorInfoDiv').show();
		$('#errorText').html(error);
	}
}