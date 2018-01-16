function gestioneOrdiniValidation()
{
	var corriere = $("select[name='corriere']");
	var tracking = $("input[name='tracking']");
	var stato = $("input[name='statoOrdine']:checked");

	if (trackingControl(tracking,5,15) && corriereSelect(corriere, 3, 10) && statoControl(stato))
		return true;
	else 
		return false;
}

function trackingControl(inp, x, y)
{ 
	var lettersAndNumbers = /^[A-Za-z0-9]+$/;
	if(inp.val().match(lettersAndNumbers) && inp.val().length >= x && inp.val().length <= y)
	{
		$("#tracking").empty();
		return true;
	}
	else
	{
		$("#tracking").html("E' consentito utilizzare soltanto valori numerici o alfabetici (lunghezza compresa tra 5-15)");
		inp.focus();
		return false;
	}
}

function corriereSelect(inp, x, y)
{
	var lettersAndNumbers = /^[A-Za-z]+$/;
	if(inp.val().match(lettersAndNumbers) && inp.val().length >= x && inp.val().lenght <= y)
	{
		$("#corriere").empty();
		return true;
	}
	else{
		$("#corriere").html("Seleziona un corriere tra quelli selezionabili");
		inp.focus();
		return false;
	}
}

function statoControl(inp) {
	if(inp.val().equals("Da spedire") || inp.val().equals("In corso") || inp.val().equals("consegnato"))
		return true;
	else
		return false;
}

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
		$("#userid").html("This username is not valid!");
		uid.focus();
		return false;
	}
	{
		$("#userid").empty();
		return true;
	}
}

function passid_validation(pass,x,y)
{
	var lettersAndNumbers = /^[A-Za-z0-9]+$/;
	var pass_len = pass.val().length;
	if (pass_len == 0 ||pass_len >= y || pass_len < x || !(uid.val().match(lettersAndNumbers)))
	{
		$("#passid").html("This password is not valid!");
		pass.focus();
		return false;
	}
	else{
		$("#passid").empty();
		return true;
	}
}