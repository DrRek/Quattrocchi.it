<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%
	GestoreOrdini gestoreOrdini = (GestoreOrdini) request.getSession().getAttribute("gestoreOrdini");
	System.out.println("Preso gestore");
	Order ordine = (Order) request.getSession().getAttribute("ordineDaGestire");
	System.out.println("ordini nella JSP " + ordine.getCodice());
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%@ page contentType="text/html; charset=ISO-8859-1"
	import="java.util.*,it.unisa.quattrocchi.control.*, it.unisa.quattrocchi.entity.*"%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="web_pages/css/bootstrap.css" type="text/css" rel="stylesheet"
	media="screen,projection" />
<link href="web_pages/css/gestoreordini.css" type="text/css" rel="stylesheet"
	media="screen,projection" />
<title>Gestione Ordine</title>
</head>
<body>
<% if(gestoreOrdini != null && ordine != null) { 
	System.out.println("Gestore != null ");
%>
	<div class="container">
		<h1>Info <%=ordine.getCodice() %></h1>
	</div>
	<div class="container">
		<h3>Cliente</h3>
		<p><%=ordine.getAcquirente().getNome() %> <%=ordine.getAcquirente().getCognome() %> : <%=ordine.getAcquirente().getUsername() %></p>
	</div>
	<div class="container">
		<h3>Prodotti acquistati</h3>
	</div>
	<div class="container">
		<table id="ordinati"
			class="table table-hover table-condensed table-striped">
			<thead>
				<th style="width: 40%" id="prod">Prodotto</th>
				<th style="width: 20%">Quantità</th>
				<th style="width: 40%">Prezzo</th>
			</thead>
			<tbody>
			<%  
				int i = 0;
				while(i < ordine.getListaArticoliInOrdine().size()) {
			%>
				<tr>
					<td class="prodotto">
						<h3 class="nomargin nomeArt"><%=ordine.getListaArticoliInOrdine().get(i).getModello() %></h3>
						<p class="marcaArt"><%=ordine.getListaArticoliInOrdine().get(i).getMarca() %></p>
					</td>
					<td><%=ordine.getListaArticoliInOrdine().get(i).getQuantità() %></td>
					<td class="prezzoArt "><%=ordine.getListaArticoliInOrdine().get(i).getPrezzo() %></td>
				</tr>
			<%
				i++;
				}
			%>
			</tbody>
		</table>
	</div>
	<div class="container">
		<h3>Indirizzo di consegna</h3>
		<p>Via Gramsci 9 Aversa 81031 CE Italia</p>
	</div>
	<div class="container">
		<h3>Corriere</h3>
		<select class="form-control">
			<option value="sda">SDA</option>
			<option value="dhl">DHL</option>
			<option value="bartolini">Bartolini</option>
			<option value="poste">Poste</option>
		</select>
	</div>
	<div class="container">
		<h3>Numero tracking</h3>
		<input class="form-control" name="tracking" type="text"
			value="123456789" />
	</div>
	<div class="container">
		<h3>Stato ordine</h3>
		<form>
			<label class="radio-inline"><input type="radio"
				name="statoOrdine" value="daSpedire" checked>Da spedire</label> <label
				class="radio-inline"><input type="radio" name="statoOrdine"
				value="inCorso">In corso</label> <label class="radio-inline"><input
				type="radio" name="statoOrdine" value="terminato">Terminato</label>
		</form>
	</div>
	<br>
	<div class="container">
		<input class="btn btn-outline-secondary " type="submit"
			name="inserireNOME" value="Conferma" style="float: right;"/>
	</div>
<% } %>
</body>
</html>