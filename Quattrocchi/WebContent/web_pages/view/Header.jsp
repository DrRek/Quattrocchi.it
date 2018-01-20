<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ page contentType="text/html; charset=UTF-8"
	import="java.util.*, it.unisa.quattrocchi.entity.*, java.util.HashMap"%>

<%
	Acquirente usr = (Acquirente) request.getSession().getAttribute("acquirente");
	GestoreOrdini gestore = (GestoreOrdini) request.getSession().getAttribute("gestoreOrdini");
	Cart cart = null;
	if(gestore == null){
		if(usr == null){
			if(request.getSession().getAttribute("carrello")== null){
				cart = new Cart(new HashMap<ArticoloInStock, Integer>());
			}
			else{
				cart = (Cart) request.getSession().getAttribute("carrello");
			}
		}
		else{
			cart = usr.getCart();
		}
	}
%>
<%@ page contentType="text/html; charset=UTF-8" import="java.util.*"%>

<link href="web_pages/css/header.css" type="text/css" rel="stylesheet"
	media="screen,projection" />
<nav class="navbar navbar-default navbar-fixed-top">
	<div class="container">
		<%
		//SE GESTORE è NULL
			if (gestore == null) {
		%>
		<!-- Quattrocchi.it -->
		<div class="navbar-header">
			<button type="button" class="navbar-toggle" data-toggle="collapse"
				data-target="#myNavbar">
				<span class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
			<ul class="nav navbar-nav navbar-left">
						  <li><a class="navbar-brand" id="fix" rel="home" href="/Quattrocchi/" >Quattrocchi.it</a><li>
						  </ul>
		</div>
		<!-- /Quattrocchi.it -->
		
		<div class="collapse navbar-collapse" id="myNavbar">
		<!-- CENTRO NAVBAR -->
		<div>
			<ul class="nav navbar-nav navbar-centered">
		<!-- Visualizza Catalogo -->
				<li><a href="visualizza_catalogo">Catalogo</a>
		<!-- /Visualizza Catalogo -->
		<!-- Ricerca -->
				<li>
					<form class="navbar-form" action="ricerca_prodotto" method="get">
						<div class="input-group">
							<input type="hidden" name="action" value="searchFromOtherPage">
							<input name="toSearch" type="text" class="form-control"
								placeholder="Cerca" /> <span class="input-group-btn">
								<button type="submit" class="btn btn-default">Submit</button>
							</span>
						</div>
					</form>
				</li>
		<!-- /Ricerca -->	
			</ul>
		</div>
		<!-- /CENTRO NAVBAR -->
		<!-- DESTRA NAVBAR -->
		<div>	
			<ul class="nav navbar-nav navbar-right">
				<!-- Carrello -->
				<li  style="margin-right: 10px"><a href="visualizza_carrello"><span><img
						src="web_pages/image/cart.png" alt="carrello:"
						style="max-height: 20px;"></span> 
				<span id="count"><%=cart.getNumeroDiArticoli()%></span></a></li>
				<%
					
				if (usr == null) {
				%>
						<li><a href="access">Login</a></li>
				<%
					} else {
				%>
						<li><a href="profilo">Benvenuto, <%=usr.getUsername()%></a></li>
						<li><a href="logout">logout</a></li>
				<%
					}
				%>
					</ul>
				</div>
				<!-- DESTRA NAVBAR -->
				</div>
			<%
			//SE GESTORE NON è NULL
			} else {
			%>
			
					<div class="navbar-header">
						<button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#navb">
							<span class="icon-bar"></span> <span class="icon-bar"></span> <span class="icon-bar"></span>
						</button>
						<ul class="nav navbar-nav navbar-left">
						  <li><a class="navbar-brand" id="fix" rel="home" href="/Quattrocchi/gestoreOrdini" >Quattrocchi.it</a><li>
						  </ul>
				</div>
				<!-- CONTENUTO QUANDO COLLAPSE -->
				<div class="collapse navbar-collapse" id="navb">
				<div>
						<ul class="nav navbar-nav navbar-centered">
							<li><a href="gestoreOrdini">Ordini</a>
						</ul>
					</div><div>
						<ul class="nav navbar-nav navbar-right">
							<li><a href="logout">logout</a></li>
						</ul>
						</div>
				</div>
						<%
							}
						%>
						</div>
		<script src="web_pages/js/bootstrap.js"></script>
		<script src="web_pages/js/header.js"></script>
</nav>
<br>
<br>
<br>
<br>
<div id="errorInfoDiv" class="alert alert-danger container" style="text-align: center; display: none;">
	<h3 id="errorText">Test</h3>
	<input type="button" class="btn btn-outline-secondary" onClick='$("#errorInfoDiv").hide()' value="OK" />
</div>

