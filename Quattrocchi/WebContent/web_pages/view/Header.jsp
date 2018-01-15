<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ page contentType="text/html; charset=UTF-8"
	import="java.util.*, it.unisa.quattrocchi.entity.*"%>

<%
	Acquirente usr = (Acquirente) request.getSession().getAttribute("acquirente");
	GestoreOrdini gestore = (GestoreOrdini) request.getSession().getAttribute("gestoreOrdini");
%>
<%@ page contentType="text/html; charset=UTF-8"
	import="java.util.*"%>

<link href="web_pages/css/header.css" type="text/css" rel="stylesheet"
	media="screen,projection" />
<nav class="navbar navbar-default navbar-fixed-top">
	<div class="container">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle" data-toggle="collapse"
				data-target="#myNavbar">
				<span class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
			<a class="navbar-brand  navbar-brand-left" href="/Quattrocchi/">Quattrocchi.it</a>
		</div>
		<%
						if (gestore == null) {
					%>
		<div class="collapse navbar-collapse" id="myNavbar">
			<ul class="nav navbar-nav navbar-centered">
				<li><a href="visualizza_catalogo">Catalogo</a>
				<li>
					<form class="navbar-form" action="ricerca_prodotto" method="get">
						<div class="input-group">
							<input type="hidden" name="action" value="searchFromOtherPage">
							<input name="toSearch" type="text" class="form-control"
								placeholder="Cerca"/>
								<span class="input-group-btn">
						<button type="submit" class="btn btn-default">Submit</button></span>
						</div>
					</form>
			</ul>
			<ul class="nav navbar-nav navbar-right">
				
				<li style="margin-right:10px"><span><img src="web_pages/image/cart.png" alt="carrello:"
					style="max-height: 50px;"></span>
				<%
					if(usr != null){
						if (usr.getCart() == null) {
					%>
				<span style="color:white"><a id="count" href="checkout">0</a></span></li>
				<%
						} else {
					%>
				<span style="color:white"><a id="count" href="visualizza_carrello"><%=usr.getCart().getNumeroDiArticoli()%></span></a></li>
				<%
							}
						}
					}
					if (usr == null && gestore == null) {
				%>
				<li><a href="access">Login / Register</a></li>
				<%
					} else if (usr != null) {
				%>
				<li><a href="profilo">Benvenuto, <%=usr.getUsername()%></a></li>
				<li><a href="logout">logout</a></li>
				<%
						} else if (gestore != null) {
					%>
					<div class="collapse navbar-collapse" id="myNavbar">
			<ul class="nav navbar-nav navbar-centered">
				<li><a href="visualizza_ordini">Ordini</a>
			</ul>
			<ul class="nav navbar-nav navbar-right">
				<li><a href="logout">logout</a></li>
				<%
						}
					%>
			</ul>
		</div>
	</div>
	<script src="js/bootstrap.js"></script>
</nav>
