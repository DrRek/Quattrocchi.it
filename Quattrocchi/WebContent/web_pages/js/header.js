function showNotification(notification){
	if(notification != null && notification != ""){
		$('#notificationInfoDiv').show();
		$('#notificationText').html(notification);
	}
}