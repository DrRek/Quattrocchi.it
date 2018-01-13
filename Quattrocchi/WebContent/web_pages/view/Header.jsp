<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ page contentType="text/html; charset=UTF-8"
	import="java.util.*, it.unisa.quattrocchi.entity.*"%>

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
		</div>
	</div>
	<script src="js/bootstrap.js"></script>
</nav>
