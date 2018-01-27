<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%
	GestoreOrdini gestoreOrdini = (GestoreOrdini) request.getSession().getAttribute("gestoreOrdini");
	List<Order> ordini = (List<Order>) request.getSession().getAttribute("ordini");
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<%@ page contentType="text/html; charset=UTF-8"
	import="java.util.*,it.unisa.quattrocchi.control.*, it.unisa.quattrocchi.entity.*"%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Gestione Ordini</title>
</head>
<link href="web_pages/css/bootstrap.css" type="text/css" rel="stylesheet"
	media="screen,projection" />
<link href="web_pages/css/gestoreordini.css" type="text/css" rel="stylesheet"
	media="screen,projection" />
	<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
</head>
<body>
<%@ include file="../view/Header.jsp"%>
<br>
<% if(gestoreOrdini != null) { 
%>
	<div class="container">
		<h1>Gestione ordini</h1>
	</div>
	<div class="container">
	<%
		if (ordini != null && ordini.size() != 0) { 
		%>
		<h3>Da spedire</h3>
		<table id="inserireID"
			class="table table-hover table-condensed table-striped">
			<tbody>
			<%
				int i = 0;
				while(i < ordini.size()) {
					if(ordini.get(i).getStatoOrdine().equalsIgnoreCase("Da spedire")) {
			%>
				<tr>
					<td><%=ordini.get(i).getCodice()%></td>
					<td>
					<form action="/Quattrocchi/gestioneOrdineDaSpedire" method="post">
                            <input type="hidden" name="ordineId" value="<%=ordini.get(i).getCodice()%>">
                            <input class="btn btn-outline-secondary " type="submit" name="inserireNAME" value="Gestisci" />
                    </form>
					</td>
				</tr>
			<% 		}
					i++; 
				}
			%>
			</tbody>
		</table>
		<hr>
		<h3>In corso</h3>
		<table id="inserireID"
			class="table table-hover table-condensed table-striped">
			<tbody>
			<% 	i = 0;
				while(i < ordini.size()) {
					if(ordini.get(i).getStatoOrdine().equalsIgnoreCase("In corso")) {
			%>
				<tr>
					<td><%=ordini.get(i).getCodice() %></td>
					<td>
					<form action = "/Quattrocchi/gestioneOrdineDaSpedire" method="post">
                            <input type="hidden" name="ordineId" value="<%=ordini.get(i).getCodice()%>">
                            <input class="btn btn-outline-secondary " type="submit" name="inserireNAME" value="Gestisci" />
                    </form>
					</td>
				</tr>
			<% 		}
					i++; 
				}
			%>
			</tbody>
		</table>
		<h3>Consegnati</h3>
		<table id="inserireID"
			class="table table-hover table-condensed table-striped">
			<tbody>
			<% 	i = 0;
				while(i < ordini.size()) {
					if(ordini.get(i).getStatoOrdine().equalsIgnoreCase("Consegnato")) {
			%>
				<tr>
					<td><%=ordini.get(i).getCodice()%></td>
					<td><form action="/Quattrocchi/gestioneOrdineDaSpedire" method="post">
                            <input type="hidden" name="ordineId" value="<%=ordini.get(i).getCodice()%>">
                            <input class="btn btn-outline-secondary " type="submit" name="inserireNAME" value="Gestisci" />
                    </form></td>
				</tr>
			<% 		}
					i++; 
				}
			%>
			<% } else { %>
			<p> Nessun ordine da visualizzare </p>
			<% } %>
			</tbody>
		</table>
	</div>
	<% } else { %>
	<h1> Devi effettuare il login da gestore! </h1>
	<% } %>
</body>
</html>