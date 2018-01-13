<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%
	Acquirente acquirente = (Acquirente) request.getSession().getAttribute("acquirente");
	GestoreOrdini admin = (GestoreOrdini) request.getSession().getAttribute("gestoreOrdini");
%>


<!DOCTYPE html>
<html>
<%@ page contentType="text/html; charset=UTF-8"
	import="java.util.*,it.unisa.quattrocchi.control.*, it.unisa.quattrocchi.entity.*"%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>quattrocchi.it</title>

<!-- CSS  -->
<link href="https://fonts.googleapis.com/icon?family=Material+Icons"
	rel="stylesheet">

<link href="web_pages/css/bootstrap.css" type="text/css" rel="stylesheet"
	media="screen,projection" />

<!--Let browser know website is optimized for mobile-->
<meta name="viewport" content="width=device-width, initial-scale=1.0" />

<script	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>

</head>

<body>
	<%@ include file="../view/Header.jsp"%>
	<br><br><br><br>
	<% if(acquirente != null || admin !=  null) {%>

	<div class="container">
		<h2>effettua prima il logout</h2>
	</div>

	<%}
		else{%>
	<div class="container">
		<h1>Login</h1>
		<div class="form-group">
		<form name='login'
			action='access' method="post">

			<input type="hidden" name="action" value="login"> 
			<div class="form-group">
			<br><label for="userid">Username:</label>
			<input class="form-control" type="text" name="userid" size="12" />
			<span class="help-block" id="userid"></span><br>
			</div>
			
			<div class="form-group">
			<label for="passid">Password:</label>
			<input class= "form-control" type="password" name="passid" size="12" />
			<span class="help-block" id="passid"></span><br>
			</div>

			<%
				if(request.getAttribute("loginFailed") != null){
			%>
			<h5>Parametri errati.</h5>
			<%
				}
			%>

			<input class= "btn btn-outline-secondary" name="submit" value="Login" type="submit">
		</form>
		</div>
	</div>
	<% } %>
	<%@ include file="../view/Footer.jsp"%>
	<script type="text/javascript" src="js/access-validation.js"></script>
	<script src="js/util.js"></script>
</body>
</html>