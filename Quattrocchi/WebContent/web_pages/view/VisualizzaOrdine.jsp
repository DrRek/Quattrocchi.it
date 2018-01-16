<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	GestoreOrdini gestoreOrdini = (GestoreOrdini) request.getSession().getAttribute("gestoreOrdini");
	Order ordine = (Order) request.getSession().getAttribute("ordineDaGestire");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%@ page contentType="text/html; charset=UTF-8"
	import="java.util.*,it.unisa.quattrocchi.control.*, it.unisa.quattrocchi.entity.*"%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Visualizza Ordine</title>
<link href="https://fonts.googleapis.com/icon?family=Material+Icons"
	rel="stylesheet">

<link href="web_pages/css/bootstrap.css" type="text/css" rel="stylesheet"
	media="screen,projection" />
<link href="web_pages/css/CartView.css" type="text/css" rel="stylesheet"
	media="screen,projection" />
<!--Let browser know website is optimized for mobile-->
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
</head>
<body>
<%@ include file="../view/Header.jsp"%>
	<br><br><br><br>
<% if(gestoreOrdini != null && ordine != null) { 
%>
	<div class="container">
		<h1>Ordine n. <%=ordine.getCodice()%> eseguito da <%=ordine.getAcquirente().getUsername() %></h1>
	</div>
	<div class="container">
	<table id="cartElements"class="table table-hover table-condensed table-striped">
		<thead>
			<th style="width:40%" id="prod">Prodotto</th>
			<th style="width:20%" id="qta" >Quantità</th>
			<th style="width:20%" >Prezzo</th>
		</thead>
		<tbody>
		<%
		int i = 0;
		while(i < ordine.getListaArticoliInOrdine().size()) {
			System.out.println(ordine.getListaArticoliInOrdine().size());
		%>
			<tr>
				<td class="prodotto">
					<h3 class="nomargin nomeArt"><%=ordine.getListaArticoliInOrdine().get(i).getModello() %></h3><p class="marcaArt"><%=ordine.getListaArticoliInOrdine().get(i).getMarca() %></p></td>
				<td>
					<h3 class="nomargin nomeArt"><%=ordine.getListaArticoliInOrdine().get(i).getQuantità() %></h3>
				</td>
				<td class="prezzoArt "><%=ordine.getListaArticoliInOrdine().get(i).getPrezzo() * ordine.getListaArticoliInOrdine().get(i).getQuantità() %></td>
			</tr>
		<%
			i++;
			}
		%>
		</tbody>
	</table>
	</div>
<% } %>
</body>
</html>