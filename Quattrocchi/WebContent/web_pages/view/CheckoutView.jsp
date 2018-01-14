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

<link href="web_pages/css/bootstrap.css" type="text/css" rel="stylesheet"
	media="screen,projection" />
<link href="web_pages/css/CheckoutView.css" type="text/css" rel="stylesheet"
	media="screen,projection" />
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
		<table id="cartElements"class="table table-hover table-condensed table-striped">
			<thead>
				<th style="width:40%" id="prod">Prodotto</th>
				<th style="width:20%" id="qta" >Quantità</th>
				<th style="width:20%" >Prezzo</th>
				<th style="width:20%"></th>
			</thead>
			<%
				Map<ArticoloInStock, Integer> map = cart.getArticoli();
				for(ArticoloInStock a : map.keySet()){
			%>
			<tbody>
				<tr>
					<td class="prodotto">
						<h3 class="nomargin nomeArt"><%=a.getModello() %></h3><p class="marcaArt"><%=a.getMarca() %></p>
					</td>
					<td><input  data-th="Numero Prodotti" name="quantitaPezzi" class="form-control"  type="number" min="1"value="<%=map.get(a) %>"></td>
					<td class="prezzoArt "><%=a.getPrezzo() %>€</td>
					<td class="forForm">
						<form class="formForRemove"action="/Quattrocchi/rimuovi_dal_carrello" method="post">
							<input type="hidden" class="articoloId" name="articoloId" value="<%=a.getCodice()%>">
							<input class= "btn btn-outline-secondary " type="submit" value="rimuovi" />
						</form>
					</td>
				</tr>
			</tbody>
			<%
				}
			%>
		</table>
	</div>
	<%
		}
	%>
	<%@ include file="Footer.jsp"%>
</body>
</html>