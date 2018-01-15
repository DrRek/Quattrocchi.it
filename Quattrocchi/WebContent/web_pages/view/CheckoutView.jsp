<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
<%@ page contentType="text/html; charset=UTF-8"
	import="java.util.*,it.unisa.quattrocchi.control.*, it.unisa.quattrocchi.entity.*"%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>quattrocchi.it</title>

<!-- CSS  -->
<link href="https://fonts.googleapis.com/icon?family=Material+Icons"
	rel="stylesheet">

<link href="web_pages/css/bootstrap.css" type="text/css"
	rel="stylesheet" media="screen,projection" />
<link href="web_pages/css/CheckoutView.css" type="text/css"
	rel="stylesheet" media="screen,projection" />
<!--Let browser know website is optimized for mobile-->
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script src="web_pages/js/checkout.js"></script>

</head>
<body>

	<%@ include file="../view/Header.jsp"%>
	<%
		Cart cart = usr.getCart();
	%>
	<br>
	<br>
	<div class="container">
		<h2>Carrello</h2>
	</div>
	<%
		if (cart == null) {
	%>
	<div class="container">
		<h3>Carrello vuoto</h3>
	</div>
	<%
		} else if(cart!=null && usr !=null) {
	%>
	<div class="container" id="divCartElements">
		<table id="cartElements"
			class="table table-hover table-condensed table-striped">
			<thead>
				<th style="width: 40%" id="prod">Prodotto</th>
				<th style="width: 20%" id="qta">Quantità</th>
				<th style="width: 20%">Prezzo</th>
				<th style="width: 20%"></th>
			</thead>
			<%
				Map<ArticoloInStock, Integer> map = cart.getArticoli();
				for(ArticoloInStock a : map.keySet()){
			%>
			<tbody>
				<tr>
					<td class="prodotto">
						<h3 class="nomargin nomeArt"><%=a.getModello() %></h3>
						<p class="marcaArt"><%=a.getMarca() %></p>
					</td>
					<td><%=map.get(a)%></td>
					<td class="prezzoArt "><%=a.getPrezzo()%>€</td>
				</tr>
			</tbody>
			<%
				}
			%>
		</table>
		<h5>
			Total:
			<%=cart.getPrezzo()%>€
		</h5>
	</div>

	<div>
		<!-- Uso questo per la carta di credito -->
		<%
			List<CreditCard> cc = usr.getCc();
			if(cc==null || cc.size()==0){
		%>
		<h5>Nessuna carta di credito disponibile</h5>
		<form action="/Quattrocchi/profilo" method="post">
			<input class="btn btn-outline-secondary " type="submit" value="Aggiungi una carta di credito" />
		</form>
		<%
			}else{
		%>
		<h5>Seleziona una carta di credito: </h5>
		<select>
			<%
				for(CreditCard c : cc){
			%>
			<option value="<%=c.getIdCarta()%>">XXXX-XXXX-XXXX-<%=c.getLastCC()%> | <%=c.getIntestatario()%></option>
			<%
				}
			%>
		</select>
		<%
			}
		%>
		<form action="/Quattrocchi/profilo" method="post">
			<input class="btn btn-outline-secondary " type="submit" value="Aggiungi una carta di credito" />
		</form>
	</div>
	
	<div>
		<!-- Uso questo per l'indirizzo di spedizione -->
		<%
			List<ShippingAddress> sas = usr.shipAdd();
			if(sas==null || sas.size()==0){
		%>
		<h5>Nessun indirizzo di spedizione disponibile</h5>
		<form action="/Quattrocchi/profilo" method="post">
			<input class="btn btn-outline-secondary " type="submit" value="Aggiungi un indirizzo di spedizione" />
		</form>
		<%
			}else{
		%>
		<h5>Seleziona un indirizzo di spedizione: </h5>
		<select>
			<%
				for(ShippingAddress sa : sas){
			%>
			<option value="<%=sa.getCodice()%>"><%=sa.getIndirizzo()%>, <%=sa.getNC() %> | <%=sa.getCap()%></option>
			<%
				}
			%>
		</select>
		<%
			}
		%>
		<form action="/Quattrocchi/profilo" method="post">
			<input class="btn btn-outline-secondary " type="submit" value="Aggiungi un indirizzo di spedizione" />
		</form>
	</div>
	
	<input class="btn btn-outline-secondary " type="submit" value="Completa l'acquisto" />

	<%
		}
	%>
	<%@ include file="Footer.jsp"%>
</body>
</html>