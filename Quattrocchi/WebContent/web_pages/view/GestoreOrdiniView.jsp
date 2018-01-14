<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	GestoreOrdini gestoreOrdini = (GestoreOrdini) request.getSession().getAttribute("gestoreOrdini");
	System.out.println("Preso gestore");
	List<Order> ordini = (List<Order>) request.getSession().getAttribute("ordini");
	System.out.println("ordini nella JSP " + ordini.size());
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%@ page contentType="text/html; charset=UTF-8"
	import="java.util.*,it.unisa.quattrocchi.control.*, it.unisa.quattrocchi.entity.*"%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>gestione_ordini</title>
</head>
<link href="web_pages/css/bootstrap.css" type="text/css" rel="stylesheet"
	media="screen,projection" />
<link href="web_pages/css/gestoreordini.css" type="text/css" rel="stylesheet"
	media="screen,projection" />
</head>
<body>
<% if(gestoreOrdini != null) { 
	System.out.println("Gestore != null ");
%>
	<div class="container">
		<h1>Gestione ordini</h1>
	</div>
	<div class="container">
	<%
		if (ordini != null && ordini.size() != 0) { 
			System.out.println("Ordini != null e Ordini.size() != 0");
		%>
		<h3>Da spedire</h3>
		<table id="inserireID"
			class="table table-hover table-condensed table-striped">
			<%
				int i = 0;
				System.out.println("Inizializzata i");
				while(i < ordini.size()) {
					System.out.println("Ciclo");
					if(ordini.get(i).getStatoOrdine().equals("Da spedire")) {
						System.out.println("Ordine da spedire");
						
			%>
			<tbody>
				<tr>
					<td><%=ordini.get(i).getCodice()%></td>
					<td>
					<form action="/Quattrocchi/gestioneOrdineDaSpedire" method="post">
                            <input type="hidden" name="ordineId" value="<%=ordini.get(i).getCodice()%>">
                            <input class="btn btn-outline-secondary " type="submit" name="inserireNAME" value="Gestisci" />
                    </form>
					</td>
				</tr>
			</tbody>
			<% 		}
					i++; 
				}
			%>
		</table>
		<hr>
		<h3>In corso</h3>
		<table id="inserireID"
			class="table table-hover table-condensed table-striped">
			<% 	i = 0;
				while(i < ordini.size()) {
					if(ordini.get(i).getStatoOrdine().equals("In corso")) {
			%>
			<tbody>
				<tr>
					<td><%=ordini.get(i).getCodice() %></td>
					<td>
					<form action="/Quattrocchi/gestioneOrdiniInCorso" method="post">
                            <input type="hidden" name="ordineId" value="<%=ordini.get(i).getCodice()%>">
                            <input class="btn btn-outline-secondary " type="submit" name="inserireNAME" value="Gestisci" />
                    </form>
					</td>
				</tr>
			</tbody>
			<% 		}
					i++; 
				}
			%>
		</table>
		<h3>Consegnati</h3>
		<table id="inserireID"
			class="table table-hover table-condensed table-striped">
			<% 	i = 0;
				while(i < ordini.size()) {
					if(ordini.get(i).getStatoOrdine().equals("Consegnato")) {
			%>
			<tbody>
				<tr>
					<td><%=ordini.get(i).getCodice()%></td>
					<td><input class="btn btn-outline-secondary "
						type="submit" name="inserireNAME" value="Gestisci" /></td>
				</tr>
			</tbody>
			<% 		}
					i++; 
				}
			%>
			<% } else { %>
			<p> Nessun ordine da visualizzare </p>
			<% } %>
		</table>
	</div>
	<% } else { %>
	<h1> Devi effettuare il login da gestore! </h1>
	<% } %>
</body>
</html>