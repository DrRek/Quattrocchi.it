<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%
	Acquirente acquirente = (Acquirente) request.getSession().getAttribute("acquirente");
%>

<!DOCTYPE html>
<html>
<%@ page contentType="text/html; charset=UTF-8"
	import="java.util.*, it.unisa.quattrocchi.control.*, it.unisa.quattrocchi.entity.*, it.unisa.quattrocchi.*"%>
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

<!--Let browser know website is optimized for mobile-->
<meta name="viewport" content="width=device-width, initial-scale=1.0" />

</head>

<body>
	<%@ include file="../view/FutureHeader.jsp"%>
	<br><br><br><br>
	<%
		if (acquirente == null) {
	%>
	<div class="container">
		<a href="access">Login is required!</a>
	</div>
	<%
		} else if (acquirente != null) {
	%>
	<div class="container">

		<h2>
			Ciao,
			<%=acquirente.getUsername()%></h2>
		<div class="row">
			<div class="col-sm-3">
				<br>
				<span class="etichetta">Email:</span>
				<span class="value"><%=acquirente.getEmail()%></span>
			</div>
			<div class="col-sm-3">
				<br>
				<span class="etichetta">Nome:</span>
				<span class="value"><%=acquirente.getNome()%></span>
			</div>
			<div class="col-sm-3">
				<br>
				<span class="etichetta">Cognome:</span>
				<span class="value"><%=acquirente.getCognome()%></span>
			</div>
			<div class="col-sm-3">
				<br>
				<span class="etichetta">Data di nascita:</span>
				<span class="value"><%=acquirente.getDataNascita()%></span>
			</div>

		</div>

		<hr>
	</div>
	<div class="container">
		<h2>Carte di credito</h2>
		<table class="table table-condensed" id="cards">
			<thead><tr>
					<th>Numero carta</th>
					<th>Intestatario</th>
					<th>Circuito</th>
					<th>Scadenza</th>
					<th></th></tr>
			</thead>
			<tbody>
			<%
				Collection<?> cc = (Collection<?>) acquirente.getCc();
					if (cc != null && cc.size() != 0) {
						Iterator<?> it = cc.iterator();
						while (it.hasNext()) {
							CreditCard bean = (CreditCard) it.next();
			%>
			<tr>
				<td class="numcc" data-th="Numero carta"><%=bean.getNumeroCC()%></td>
				<td data-th="Intestatario"><%=bean.getIntestatario()%></td>
				<td data-th="Circuito"><%=bean.getCircuito()%></td>
				<td data-th="Scadenza"><%=bean.getDataScadenza()%></td>
				<td data-th=""><input type="submit" class="removeCard btn btn-outline-secondary" name="removeCard"
					value="remove" /></td>
			</tr>
			<%
					}
				}
		}
			%>
			</tbody>
		</table>
	</div>

	<%@ include file="../view/Footer.jsp"%>
	<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
	<script src="js/user.js"></script>
	<script src="js/util.js"></script>
</body>
</html>