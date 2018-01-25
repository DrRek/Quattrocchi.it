function showNotification(notification){
	if(notification != null && notification != ""){
		$('#notificationInfoDiv').show();
		$('#notificationInfoDiv').css("color", "#8a6d3b");
		$('#notificationInfoDiv').css("background-color", "#fcf8e3");
		$('#notificationInfoDiv').css("border-color", "#faebcc");
		$('#notificationText').html(notification);
	}
}

function showSuccess(notification){
	if(notification != null && notification != ""){
		$('#notificationInfoDiv').show();
		$('#notificationInfoDiv').css("color", "#5c5c5c");
		$('#notificationInfoDiv').css("background-color", "#88ff08");
		$('#notificationInfoDiv').css("border-color", "#a5d71d");
		$('#notificationText').html(notification);
	}
}