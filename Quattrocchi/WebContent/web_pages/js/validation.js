
function loginValidation(){
	var uid = $("input[name='userid']");
	var pass = $("input[name='passid']");
	if(userid_validation(uid,5,15) && passid_validation(pass,5,15))
		return true;
	else 
		return false;
}

function userid_validation(uid,x,y){
	var lettersAndNumbers = /^[A-Za-z0-9]+$/;
	var uid_len = uid.val().length;
	if (uid_len == 0 || uid_len >= y || uid_len < x || !(uid.val().match(lettersAndNumbers)))
	{
		showNotification("Username o password errati.");
		return false;
	}
	{
		return true;
	}
}

function passid_validation(pass,x,y)
{
	var lettersAndNumbers = /^[A-Za-z0-9]+$/;
	var pass_len = pass.val().length;
	if (pass_len == 0 ||pass_len >= y || pass_len < x || !(uid.val().match(lettersAndNumbers)))
	{
		showNotification("Username o password errati.");
		return false;
	}
	else{
		return true;
	}
}

