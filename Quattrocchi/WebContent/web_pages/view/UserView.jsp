<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	
<%
	List<Order> storicoOrdini = (List<Order>) request.getAttribute("storico_ordini");
%>

<!DOCTYPE html>
<html>
<%@ page contentType="text/html; charset=UTF-8"
	import="java.util.*, it.unisa.quattrocchi.control.*, it.unisa.quattrocchi.entity.*, it.unisa.quattrocchi.*, java.util.Date, java.text.DateFormat, java.text.SimpleDateFormat"%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>quattrocchi.it</title>

<!-- CSS  -->
<link href="https://fonts.googleapis.com/icon?family=Material+Icons"
	rel="stylesheet">

<link href="web_pages/css/bootstrap.css" type="text/css" rel="stylesheet"
	media="screen,projection" />

<link href="web_pages/css/UserView.css" type="text/css" rel="stylesheet"
	media="screen,projection" />
	
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>

<!--Let browser know website is optimized for mobile-->
<meta name="viewport" content="width=device-width, initial-scale=1.0" />

</head>

<body>
	<%@ include file="../view/Header.jsp"%>
	<%
		if (usr == null) {
	%>
	<div class="container">
		<a href="access">Login is required!</a>
	</div>
	<%
		} else if (usr != null) {
	%>
	<div class="container">

		<h2>Ciao, <%=usr.getUsername() %></h2>
		<div class="row">
			<div class="col-sm-3">
				<br> <span class="etichetta">Email:</span> <span class="value"><%=usr.getEmail()%></span>
			</div>
			<div class="col-sm-3">
				<br> <span class="etichetta">Nome:</span> <span class="value"><%=usr.getNome()%></span>
			</div>
			<div class="col-sm-3">
				<br> <span class="etichetta">Cognome:</span> <span
					class="value"><%=usr.getCognome() %></span>
			</div>
			<div class="col-sm-3">
				<br> <span class="etichetta">Data di nascita:</span> <span
					class="value"><%=usr.getDataNascita()%></span>
			</div>

		</div>

		<hr>
	</div>
	
	<div class="container">
		<h2>Carte di credito</h2>
		<table class="table table-condensed table-striped" id="cards">
			<thead>
				<tr>
					<th>Numero carta</th>
					<th>Intestatario</th>
					<th>Circuito</th>
					<th>Scadenza</th>
					<th>cvv</th>
					<th></th>
				</tr>
			</thead>
			<tbody>
				<%
					DateFormat df = new SimpleDateFormat("MM/yyyy");
					for(CreditCard cc : usr.getCc()){
				%>
				<tr>
					<td>xxxx-xxxx-xxxx-<%=cc.getLastCC()%></td>
					<td ><%=cc.getIntestatario()%></td>
					<td ><%=cc.getCircuito()%></td>
					<td ><%=df.format(cc.getDataScadenza())%></td>
					<td >xxx</td>
					<td>
						<input type="hidden" class="cardCode" value="<%=cc.getIdCarta()%>"/>
						<input type="submit" class="removeCard btn btn-outline-secondary" name="removeCard" value="rimuovi" />
					</td>
				</tr>
				<%} %>
				<tr id="lastCreditCard">
					<td><input name="numcc" type="text" class="form-control" placeholder="Numero carta"/></td>
					<td><input name="intestatario" type="text" class="form-control" placeholder="Intestatario"/></td>
					<td><input name="circuito" type="text" class="form-control" placeholder="Circuito"/></td>
					<td><input name="scadenza" type="text" class="form-control" placeholder="Data scadenza" /></td>
					<td><input name="cvv" type="text" class="form-control" placeholder="Cvv" /></td>
					<td><input type="submit"
						class="addCard btn btn-outline-secondary" name="addCard"
						value="aggiungi" /></td>
				</tr>
			</tbody>
		</table>
	</div>
	
	<div class="container">
		<h2>Indirizzi di spedizione</h2>
		<table class="table table-condensed table-striped" id="indirizzi">
			<thead>
				<tr>
					<th>Indirizzo</th>
					<th>Numero civico</th>
					<th>CAP</th>
					<th>Provincia</th>
					<th>Stato</th>
					<th></th>
				</tr>
			</thead>
			<tbody>
				<%for(ShippingAddress sa : usr.shipAdd()){%>
				<tr>
					<td><%=sa.getIndirizzo() %></td>
					<td><%=sa.getNC() %></td>
					<td ><%=sa.getCap() %></td>
					<td><%=sa.getProvincia() %></td>
					<td><%=sa.getStato() %></td>
					<td>
						<input type="hidden" class="addressCode" value="<%=sa.getCodice()%>"/>
						<input type="submit" class="btn btn-outline-secondary" name="removeAddress" value="remove" />
					</td>
				</tr>
				<%} %>
				<tr id="lastShipAdd">
					<td><input name="indirizzo" type="text" class="form-control" placeholder="Indirizzo"/></td>
					<td><input name="numeroCivico" type="text" class="form-control" placeholder="Numero civico"/></td>
					<td><input name="cap" type="text" class="form-control" placeholder="CAP"/></td>
					<td><input name="provincia" type="text" class="form-control" placeholder="Provincia"/></td>
					<td><input name="stato" type="text" class="form-control" placeholder="Stato" /></td>
					<td>
						<input type="submit" class="addAddress btn btn-outline-secondary" name="addAddress" value="aggiungi" />
					</td>
				</tr>
			</tbody>
		</table>
	</div>
	
	<div class="container">
		<h2>Storico ordini</h2>
		<table class="table table-condensed table-striped">
			<tbody>
				<%for(Order o : storicoOrdini){%>
				<tr>
					<td class="ordine">ordine: <%=o.getCodice() %></td>
					<td class="ordine"><%=o.getDataEsecuzione() %></td>
					<td><form action="/Quattrocchi/GestioneOrdineTerminato" method="post">
                            <input type="hidden" name="ordineId" value="<%=o.getCodice()%>">
                            <input class="btn btn-outline-secondary " type="submit" name="inserireNAME" value="Gestisci" />
                    </form></td>
                    </tr>
				<%}%>
			</tbody>
		</table>
	</div>
	
	<%} %>

	<%@ include file="../view/Footer.jsp"%>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
	<script src="web_pages/js/user.js"></script>
	<script src="js/util.js"></script>
</body>
</html>