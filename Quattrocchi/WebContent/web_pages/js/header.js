function showNotification(notification){
	if(notification != null && notification != ""){
		$('#notificationInfoDiv').show();
		$('#notificationInfoDiv').removeClass("alert-success");
		$('#notificationInfoDiv').addClass("alert-warning");
		$('#notificationText').html(notification);
	}
}
