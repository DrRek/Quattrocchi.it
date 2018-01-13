<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ page contentType="text/html; charset=UTF-8"
	import="java.util.*, it.unisa.quattrocchi.entity.*"%>

<%
	User usr = null; //(UserBean) request.getSession().getAttribute("user");
	Admin adm = null; //(AdminBean) request.getSession().getAttribute("admin");
	Cart crt = null; //(Cart) request.getSession().getAttribute("cart");
%>
<%@ page contentType="text/html; charset=UTF-8"
	import="java.util.*"%>

<link href="css/header.css" type="text/css" rel="stylesheet"
	media="screen,projection" />
<nav class="navbar navbar-default navbar-fixed-top">
	<div class="container">
		<div class="navbar-header">
			<button type="button" class="navbar-toggle" data-toggle="collapse"
				data-target="#myNavbar">
				<span class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
			<a class="navbar-brand  navbar-brand-left" href="article">Quattrocchi.it</a>
		</div>
		<div class="collapse navbar-collapse" id="myNavbar">
			<ul class="nav navbar-nav navbar-centered">
				<li><a href="article?toDo=searchOcchiale">Occhiali</a>
				<li><a href="article?toDo=searchLente">Lentine</a>
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
				<%
						if (adm == null) {
					%>
				<li style="margin-right:10px"><span><img src="image/cart.png" alt="carrello:"
					style="max-height: 50px;"></span>
				<%
						if (crt == null) {
					%>
				<span style="color:white"><a id="count" href="checkout">0</a></span></li>
				<%
						} else {
					%>
				<span style="color:white"><a id="count" href="checkout"><%=crt.getNumeroDiArticoli()%></span></a></li>
				<%
						}
						}
						if (usr == null && adm == null) {
					%>
				<li><a href="access">Login / Register</a></li>
				<%
						} else if (usr != null) {
					%>
				<li><a href="user">Benvenuto, <%=usr.getUsername()%></a></li>
				<li><a href="user?action=logout">logout</a></li>
				<%
						} else if (adm != null) {
					%>
				<li><a href="user">Benvenuto, <%=adm.getUsername()%></a></li>
				<li><a href="user?action=logout">logout</a></li>
				<%
						}
					%>
			</ul>
		</div>
	</div>
	<script src="js/bootstrap.js"></script>
</nav>
