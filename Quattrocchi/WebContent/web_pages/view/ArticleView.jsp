<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%
	String toSearch = (String) request.getAttribute("toSearch");
	String cercaPerTipo = (String) request.getAttribute("cercaPerTipo");
	//Cart cart = (Cart) request.getSession().getAttribute("cart");
	//UserBean user = (UserBean) request.getSession().getAttribute("user");
	//AdminBean admin = (AdminBean) request.getSession().getAttribute("admin");
%>

<!DOCTYPE html>
<html>
<%@ page contentType="text/html; charset=UTF-8" import="java.util.*"%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>quattrocchi.it</title>

<!-- CSS  -->
<link href="https://fonts.googleapis.com/icon?family=Material+Icons"
	rel="stylesheet">

<link href="web_pages/css/bootstrap.css" type="text/css"
	rel="stylesheet" media="screen,projection" />

<link href="web_pages/css/ArticleView.css" type="text/css"
	rel="stylesheet" media="screen,projection" />

<!--Let browser know website is optimized for mobile-->
<meta name="viewport" content="width=device-width, initial-scale=1.0" />

<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>


</head>

<body>
	<%@ include file="../view/Header.jsp"%>
	<script>
		$(document).ready(function() {
			$("#azione").click(function() {
				$("#show").slideToggle();
				if ($("#azione").html() == "mostra")
					$("#azione").html("nascondi");
				else
					$("#azione").html("mostra");

			});
		});
	</script>
	<!--  search.js gestisce questa parte-->
	<div class="row container-fluid">
		<!-- devo ancora ricercare nelle impostazioni iniziali anche in base alla parola cercata e implementare il sort -->
		<div id='daMettereASinistra' class='col-sm-4 col-md-3 col-lg-2 '>
			<div align="center">
				<br><br><br>
				<h3>Advanced Search</h3>
				<button class="btn btn-outline-secondary" id="azione">nascondi</button>
			</div>
			<hr>



			<div id="show" style="padding: 15px;">
				<form>
					<div class="form-group">
						<h4>Nome:</h4>
						<input type="text" class="form-control" name="daCercare"
							<%if (toSearch != null) {%> value="<%=toSearch%>" <%}%> />
					</div>
					<hr>
					<div class="form-group">
						<h4>Marca:</h4>
						<select class="form-control" name="marca">
							<option selected value="">All</option>
							<option value="GreenVision">GreenVision</option>
							<option value="Lindberg">Lindberg</option>
							<option value="Oakley">Oakley</option>
							<option value="Persol">Persol</option>
							<option value="RayBan">RayBan</option>
						</select>
					</div>
					<hr>
					<div class="form-group">
						<h4>Prezzo:</h4>
						<div>
							<input class="form-control" type="number" step="1.00" min="0.00"
								name="prezzoMin" placeholder="min" /><br> <input
								class="form-control" type="number" step="1.00" min="0.00"
								name="prezzoMax" placeholder="max" />
						</div>
					</div>
					<hr>
					<div class="form-group">
						<h4>Colore:</h4>
						<input class="form-control" type="text" name="colore" value="" />
					</div>
					<hr>
					<input class="form-control" id="advancedSearch" type='button'
						value='Cerca' />
				</form>
			</div>
		</div>

		<div class="container-fluid col-sm-8 col-md-9 col-lg-10 products"></div>
	
		<div id="demos" class="products"></div>
	</div>
	<%@ include file="../view/Footer.jsp"%>
	<script src="web_pages/js/article.js"></script>

</body>
</html>