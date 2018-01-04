$(document).ready(function() {
	
	setSearchField();
	$(".cumulativePr").hide();
	
	$("input[name=tipoPr]").on("click", function(){
		if(this.value=="s")
			$(".cumulativePr").show();
		else{
			$(".cumulativePr").hide();
			$('input.cumulativePr').prop('checked', false);
		}
	});
	
	$("#addCard").on("click", function(event) {
		if (ccValidation())
			addCard();
	});

	$("#addPrescription").on("click", function(event) {
		if (presValidation()) {
			addPrescription();
		}
	});

	$("#submitPr").click(function(event) {
		$("span.help-block").html("");
		if(promotionValidation())
			addPromotion();
	});

	$("table").on("click", ".removeCard", function(event) {
		var numcc = $(this).parent().siblings(".numcc").html();
		delCard(numcc);
	});

	$("table").on("click", ".removePrescription", function(event) {
		var codice = $(this).parent().siblings(".pCodice").html();
		delPrescription(codice);
	});

});

function delCard(numcc) {
	$.ajax({
		type : "POST",
		url : "user",
		async : true,
		data : {
			action : "delCard",
			numcc : numcc
		},
		dataType : "json",
		error : function(xhr, status, errorThrown) {
			console.log(JSON.stringify(xhr));
			console.log("AJAX error: " + status + ' : ' + errorThrown);
		},
		success : function(responseText) {
			formatDataCards(responseText);
		}
	});
}

function delPrescription(codice) {
	$.ajax({
		type : "POST",
		url : "user",
		async : true,
		data : {
			action : "delPres",
			codice : codice
		},
		dataType : "json",
		error : function(xhr, status, errorThrown) {
			console.log(JSON.stringify(xhr));
			console.log("AJAX error: " + status + ' : ' + errorThrown);
		},
		success : function(responseText) {
			formatDataPrescriptions(responseText);
		}
	});
}

function setSearchField() {
	if ($('select[name=tipo]').val() == "O") {
		$(".specificiPerOcchiali").show();
		$(".specificiPerLentine").hide();
	} else {
		$(".specificiPerOcchiali").hide();
		$(".specificiPerLentine").show();
	}
}

function addCard() {
	var numcc = $("input[name=numcc]").val();
	var intestatario = $("input[name=intestatario]").val();
	var circuito = $("input[name=circuito]").val();
	var scadenza = $("input[name=scadenza]").val();
	var cvv = $("input[name=cvv]").val();
	$.ajax({
		type : "POST",
		url : "user",
		async : true,
		data : {
			action : 'addCard',
			numcc : numcc,
			intestatario : intestatario,
			circuito : circuito,
			scadenza : scadenza,
			cvv : cvv
		},
		dataType : "json",
		error : function(xhr, status, errorThrown) {
			console.log(JSON.stringify(xhr));
			console.log("AJAX error: " + status + ' : ' + errorThrown);
		},
		success : function(responseText) {
			formatDataCards(responseText);
		}
	});
}

function addPrescription() {
	var tipoP = $("input[name='nomeP']").val();
	var sferaSX = $("input[name='sferaSX']").val();
	var cilindroSX = $("input[name='cilindroSX']").val();
	var asseSX = $("input[name='asseSX']").val();
	var sferaDX = $("input[name='sferaDX']").val();
	var cilindroDX = $("input[name='cilindroDX']").val();
	var asseDX = $("input[name='asseDX']").val();
	var addVicinanza = $("input[name='addVicinanza']").val();
	var prismaOrizSX = $("input[name='prismaOrizSX']").val();
	var prismaOrizSXBD = $("select[name='prismaOrizSXBD']").val();
	var prismaOrizDX = $("input[name='prismaOrizDX']").val();
	var prismaOrizDXBD = $("select[name='prismaOrizDXBD']").val();
	var prismaVertSX = $("input[name='prismaVertSX']").val();
	var prismaVertSXBD = $("select[name='prismaVertSXBD']").val();
	var prismaVertDX = $("input[name='prismaVertDX']").val();
	var prismaVertDXBD = $("select[name='prismaVertDXBD']").val();
	var pdSX = $("input[name='pdSX']").val();
	var pdDX = $("input[name='pdDX']").val();
	$.ajax({
		type : "POST",
		url : "user",
		data : {
			action : 'addPres',
			nomeP : tipoP,
			sferaSX : sferaSX,
			cilindroSX : cilindroSX,
			asseSX : asseSX,
			sferaDX : sferaDX,
			cilindroDX : cilindroDX,
			asseDX : asseDX,
			addVicinanza : addVicinanza,
			prismaOrizSX : prismaOrizSX,
			prismaOrizSXBD : prismaOrizSXBD,
			prismaOrizDX : prismaOrizDX,
			prismaOrizDXBD : prismaOrizDXBD,
			prismaVertSX : prismaVertSX,
			prismaVertSXBD : prismaVertSXBD,
			prismaVertDX : prismaVertDX,
			prismaVertDXBD : prismaVertDXBD,
			pdSX : pdSX,
			pdDX : pdDX
		},
		dataType : "json",
		error : function(xhr, status, errorThrown) {
			console.log(JSON.stringify(xhr));
			console.log("AJAX error: " + status + ' : ' + errorThrown);
		},
		success : function(responseText) {
			formatDataPrescriptions(responseText);
		}
	});
}

function formatDataCards(responseText) {
	var toAppend = '<thead><tr><th>Numero carta</th><th>Intestatario</th><th>Circuito</th><th>Scadenza</th><th></th></tr></thead><tbody>';
	$.each(responseText, function(i, cardsObject) {
		console.log(cardsObject);
		toAppend += '<tr><td class="numcc" data-th="Numero carta">' + cardsObject.numeroCC + '</td>';
		toAppend += '<td data-th="Intestatario">' + cardsObject.intestatario + '</td>';
		toAppend += '<td data-th="Circuito">' + cardsObject.circuito + '</td>';
		toAppend += '<td data-th="Scadenza">' + cardsObject.dataScadenza + '</td>';
		toAppend += '<td data-th=""><input type="submit" class="removeCard btn btn-outline-secondary" name="removeCard" value="remove" /></td></tr>';
	});
	$("#cards").html(toAppend);
};

function formatDataPrescriptions(responseText) {
	var toAppend = '<thead><tr><th>Codice</th><th>Nome</th><th>Sfera dx</th><th>Sfera sx</th><th></th></tr></thead>';
	$.each(responseText, function(i, prescriptionsObject) {
		toAppend += '<tr><td class="pCodice" data-th="Codice">' + prescriptionsObject.codice+ '</td>';
		toAppend += '<td data-th="Nome">' + prescriptionsObject.nome + '</td>';
		toAppend += '<td data-th="Sfera dx">' + prescriptionsObject.sferaDx + '</td>';
		toAppend += '<td data-th="Sfera sx">' + prescriptionsObject.sferaSx + '</td>';
		toAppend += '<td data-th=""><input type="submit" class="removePrescription btn btn-outline-secondary" name="removePrescription" value="remove" /></td></tr>';
	});
	$("#prescriptions").html(toAppend);
};

// di seguito le funzioni per il validate

// carta di credito
function ccValidation() {
	var numcc = $("input[name='numcc']");
	var intestatario = $("input[name='intestatario']");
	var circuito = $("input[name='circuito']");
	var scadenza = $("input[name='scadenza']");
	var cvv = $("input[name='cvv']");

	if (numcc_validation(numcc) && intestatario_validation(intestatario)
			&& circuito_validation(circuito) && scadenza_validation(scadenza)
			&& cvv_validation(cvv))
		return true;

	return false;
}

function numcc_validation(numcc) {
	var numbers = /^[0-9]+$/;
	if (numcc.val().length != 16) {
		$("#numcc")
				.html("Il numero CC deve contenere esattamente 16 caratteri");
		numcc.focus();
		return false;
	} else if (!numcc.val().match(numbers)) {
		$("#numcc").html("Il numero CC può contenere solo numeri");
		numcc.focus();
		return false;
	} else {
		$("#numcc").empty();
		return true;
	}
}

function intestatario_validation(intestatario) {
	var letters = /^[A-Za-z ]+$/; // da aggiungere che si deve inserire sia
	// nome che cognome
	if (!intestatario.val().match(letters)) {
		$("#intestatario").html("È consentito usare solo caratteri alfabetici");
		intestatario.focus();
		return false;
	} else {
		$("#intestatario").empty();
		return true;
	}
}
function circuito_validation(circuito) {
	if (circuito.val().toLowerCase() == "visa"
			|| circuito.val().toLowerCase() == "mastercard"
			|| circuito.val().toLowerCase() == "american express") {
		$("#circuito").empty();
		return true;
	} else {
		$("#circuito").html(
				"Si accettano solo Visa, Mastercard e American Express");
		circuito.focus();
		return false;
	}
}

function scadenza_validation(scadenza) {
	var regEx = /^\d{4}-\d{2}-\d{2}$/;
	if (scadenza.val().match(regEx)) {
		$("#scadenza").empty();
		return true;
	}
	$("#scadenza").html("Il formato della data non è valido. (yyyy-MM-dd)"); // TO
	// DO:
	// deve
	// controllare
	// date
	// impossibili
	// e
	// date
	// passate
	return false;
}

function cvv_validation(cvv) {
	var numbers = /^[0-9]+$/;
	if ((cvv.val().match(numbers)) && (cvv.val().length == 3)) {
		$("#cvv").empty();
		return true;
	} else {
		$("#cvv").html("Il CVV deve essere composto da 3 numeri");
		cvv.focus();
		return false;
	}
}

// prescrizione

function presValidation() {
	var tipoP = $("input[name='nomeP']");
	var sferaSX = $("input[name='sferaSX']");
	var cilindroSX = $("input[name='cilindroSX']");
	var asseSX = $("input[name='asseSX']");
	var sferaDX = $("input[name='sferaDX']");
	var cilindroDX = $("input[name='cilindroDX']");
	var asseDX = $("input[name='asseDX']");
	var addVicinanza = $("input[name='addVicinanza']");
	var prismaOrizSX = $("input[name='prismaOrizSX']");
	var prismaOrizDX = $("input[name='prismaOrizDX']");
	var prismaVertSX = $("input[name='prismaVertSX']");
	var prismaVertDX = $("input[name='prismaVertDX']");
	var pdSX = $("input[name='pdSX']");
	var pdDX = $("input[name='pdSX']");
	if (allLetterOrSpace(tipoP, "#tipoP") 
			&& isNumber(sferaSX, -20, 12, "#sferaSX")
			&& isNumber(cilindroSX, -6, 6, "#cilindroSX")
			&& isNumber(asseSX, 0, 180, "#asseSX")
			&& isNumber(sferaDX, -20, 12, "#sferaDX")
			&& isNumber(cilindroDX, -6, 6, "#cilindroDX")
			&& isNumber(asseDX, 0, 180, "#asseDX")
			&& isNumber(addVicinanza, 0, 3.5, "#addVicinanza")
			&& isNumber(prismaOrizSX, 0, 5, "#prismaOrizSX")
			&& isNumber(prismaOrizDX, 0, 5, "#prismaOrizDX")
			&& isNumber(prismaVertSX, 0, 5, "#prismaVertSX")
			&& isNumber(prismaVertDX, 0, 5, "#prismVertDX")
			&& isNumber(pdSX, 17.5, 40, "#pdSX")
			&& isNumber(pdDX, 17.5, 40, "#pdDX"))
		return true;
	else
		return false;
}

function isNumber(input, min, max, id) {
	var numbers = /^-?\d+(\.\d+)?$/;
	if (input.val().match(numbers)) {
		if (input.val() >= min && input.val() <= max && input.val().length>0) {
			$(id).empty();
			return true
		} else {
			$(id).html("I valori consentiti sono tra" + min + " e " + max);
			input.focus();
			return false;
		}
	} else {
		$(id).html("Sono consentiti solo numeri");
		input.focus();
		return false;
	}
}

function allLetterOrSpace(input, id) {
	var letters = /^[A-Za-z ]+$/;
	if (input.val().match(letters)) {
		$(id).empty();
		return true;
	} else {
		$(id).html("È consentito usare solo caratteri alfabetici");
		input.focus();
		return false;
	}
}

function addPromotion() {
	var nome = $("input[name=nomePr]").val();
	var descrizione = $("input[name=descrizionePr]").val();
	var sconto = $("input[name=scontoPr]").val();
	var tipo = $("input[name=tipoPr]:checked").val();
	var inizio = $("input[name=inizioPr]").val();
	var fine = $("input[name=finePr]").val();
	if ($("input[name=cumulabilePr]").is(':checked')) {
		var cumulabile = true;
	} else {
		var cumulabile = false;
	}
	$.ajax({
		type : "POST",
		url : "promotion",
		async : true,
		data : {
			action : 'addPromotion',
			nome : nome,
			descrizione : descrizione,
			sconto : sconto,
			tipo : tipo,
			inizio : inizio,
			fine : fine,
			cumulabile : cumulabile
		},
		dataType : "json",
		error : function(xhr, status, errorThrown) {
			console.log(JSON.stringify(xhr));
			console.log("AJAX error: " + status + ' : ' + errorThrown);
		},
		success : function(responseText) {
			formatDataPromotion(responseText);
		}
	});
}

function formatDataPromotion(responseText) {
	var toAppend = '<thead><tr><th>Name</th><th>Description</th><th>Value</th><th>Type</th><th>Start</th><th>End</th><th>Cumulative</th><th></th></tr></thead>';
	$.each(responseText, function(i, promotionObject) {
		toAppend += '<tr><td data-th="Name">' + promotionObject.nome + '</td>';
		toAppend += '<td data-th="Description">' + promotionObject.descrizione + '</td>';
		toAppend += '<td data-th="Value">' + promotionObject.sconto + '</td>';
		toAppend += '<td data-th="Type">' + promotionObject.tipo + '</td>';
		toAppend += '<td data-th="Start">' + promotionObject.dataInizio + '</td>';
		toAppend += '<td data-th="End">' + promotionObject.dataFine + '</td>';
		toAppend += '<td data-th="Cumulative">' + promotionObject.cumulabile + '</td>';
		toAppend += '<td><a href="promotion?nome=' + promotionObject.nome
				+ '">info/edit</a></td></tr>';
	});
	$("#tablePromotion").html(toAppend);
}

function promotionValidation(){
	var toCheck = $("input[name=nomePr]").val();
	if(toCheck.length<=0||toCheck.length>=21){
		$("#nomePr").html("Enter minimum 0 characters and maximum 20 characters!")
		return false;
	}
	toCheck = $("input[name=descrizionePr]").val();
	if(toCheck.length<=0||toCheck.length>=51){
		$("#descrizionePr").html("Enter minimum 0 characters and maximum 50 characters!")
		return false;
	}
	toCheck = $("input[name=scontoPr]").val();
	if(toCheck==""){
		$("#scontoPr").html("Enter a positive number!")
		return false;
	} else if(parseFloat(toCheck) <= 0 || parseFloat(toCheck) >= 100) {
		$("#scontoPr").html("Enter a positive number between 0 and 100!")
		return false;
	}
	toCheck = $("input[name=inizioPr]").val();
	if(toCheck.length != 10){
		$("#inizioPr").html("Enter a valid date! (yyy-MM-dd)")
		return false;
	}
	toCheck = $("input[name=finePr]").val();
	if(toCheck.length != 10){
		$("#finePr").html("Enter a valid date! (yyy-MM-dd)")
		return false;
	}
	return true;
}

function isDouble(n) {
	return parseFloat(n) == n
};

function validate_insert_glass(){
	$("span").html("");
	var toCheck = $("input[name=nomeOc]").val();
	if(toCheck.length<=0||toCheck.length>=40){
		$("#nomeOc").html("Enter a valid name between 0 and 40 character!")
		return false;
	}
	toCheck = $("input[name=prezzoOc]").val();
	if(toCheck.length<=0){
		$("#prezzoOc").html("Enter a valid price!")
		return false;
	}
	toCheck = $("input[name=quantitaOc]").val();
	if(toCheck.length<=0){
		$("#quantitaOc").html("Enter a valid quantity!")
		return false;
	}
	toCheck = $("input[name=descrizioneOc]").val();
	if(toCheck.length<=0||toCheck.length>=200){
		$("#descrizioneOc").html("Enter a valid description between 0 and 200 character!")
		return false;
	}
	return true
}

function validate_insert_contact(){
	$("span").html("");
	var toCheck = $("input[name=nomeCo]").val();
	if(toCheck.length<=0||toCheck.length>=40){
		$("#nomeCo").html("Enter a valid name between 0 and 40 character!")
		return false;
	}
	toCheck = $("input[name=prezzoCo]").val();
	if(toCheck.length<=0){
		$("#prezzoCo").html("Enter a valid price!")
		return false;
	}
	toCheck = $("input[name=quantitaL]").val();
	if(toCheck.length<=0){
		$("#quantitaL").html("Enter a valid quantity!")
		return false;
	}
	toCheck = $("input[name=raggioL]").val();
	if(toCheck.length<=0){
		$("#raggioL").html("Enter a radius!")
		return false;
	}
	toCheck = $("input[name=diametroL]").val();
	if(toCheck.length<=0){
		$("#diametroL").html("Enter a diameter!")
		return false;
	}
	toCheck = $("input[name=pezziPerPacco]").val();
	if(toCheck.length<=0){
		$("#pezziPerPacco").html("Enter the number of contact lenses in a single box!")
		return false;
	}
	return true;
}