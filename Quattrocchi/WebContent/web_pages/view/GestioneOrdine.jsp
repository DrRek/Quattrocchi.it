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
<link href="web_pages/css/bootstrap.css" type="text/css" rel="stylesheet"
	media="screen,projection" />
<link href="web_pages/css/gestoreordini.css" type="text/css" rel="stylesheet"
	media="screen,projection" />
	<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<title>Gestione Ordine</title>
</head>
<body>
<%@ include file="../view/Header.jsp"%>
<% if(gestoreOrdini != null && ordine != null) { 
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
		<p><%=ordine.getShippingAddress().getIndirizzo() %>, <%=ordine.getShippingAddress().getStato() %> <%=ordine.getShippingAddress().getProvincia() %></p>
	</div>
	
	<form action="/Quattrocchi/inserisciDatiDiSpedizione" method="post">
    	<input type="hidden" name="ordineId" value="<%=ordine.getCodice()%>">
		<div class="container">
			<h3>Corriere</h3>
			<select class="form-control" name = "corriere">
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
        	 <h3>Data di consegna</h3>
         	<p>
         	<% 	
         		String strDataConsegna;
         		if(ordine.getDataConsegna() == null){
         			strDataConsegna = "1970-01-01";
         		}
         		else{
         			strDataConsegna = ordine.getDataConsegna().toString();
         		} 
         	%>
         	<input type="date" class="form-control" value="<%=strDataConsegna%>" name="dataDiConsegna">
         </p>
    	</div>
		<div class="container">
			<h3>Stato ordine</h3>
			<%
				if(ordine.getStatoOrdine().equalsIgnoreCase("Da spedire")) {
			%>
				<label class="radio-inline"><input type="radio"
					name="statoOrdine" value="Da spedire" checked>Da spedire</label> <label
					class="radio-inline"><input type="radio" name="statoOrdine"
					value="In corso">In corso</label> <label class="radio-inline"><input
					type="radio" name="statoOrdine" value="consegnato">Terminato</label>
			<%
				} else if(ordine.getStatoOrdine().equalsIgnoreCase("In corso")) {
			%>	
				<label class="radio-inline"><input type="radio"
					name="statoOrdine" value="Da spedire">Da spedire</label> <label
					class="radio-inline"><input type="radio" name="statoOrdine"
					value="In corso" checked>In corso</label> <label class="radio-inline"><input
					type="radio" name="statoOrdine" value="consegnato">Terminato</label>
			<%
				} else {
			%>
			<label class="radio-inline"><input type="radio"
					name="statoOrdine" value="Da spedire">Da spedire</label> <label
					class="radio-inline"><input type="radio" name="statoOrdine"
					value="In corso">In corso</label> <label class="radio-inline"><input
					type="radio" name="statoOrdine" value="consegnato" checked>Terminato</label>
			<%
				}
			%>
		</div>
		<br>
		<div class="container">
	      <input class="btn btn-outline-secondary " type="submit" name="inserireNOME" value="Conferma" style="float: right;"/>
		</div>
	</form>
<% } %>
</body>
	<script type="text/javascript" src="web_pages/js/validation.js"></script>
	<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
    <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
 
</html>