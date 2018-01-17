<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%
	ArticoloInStock articolo = (ArticoloInStock) request.getAttribute("articolo");
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

<link href="web_pages/css/ArticlePageView.css" type="text/css" rel="stylesheet"
	media="screen,projection" />

<!--Let browser know website is optimized for mobile-->
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>

<style type="text/css">
.enlarge:hover {
	-webkit-transform: scale(1.1);
	-moz-transform: scale(1.1);
	-o-transform: scale(1.1);
	transform: scale(1.1);
}</style>
</head>

<body>
	<%@ include file="../view/Header.jsp"%>
	<script>
		$(document).ready(function() {
			$("#oAddCart").click(function(event) {
				$.ajax({
					type : "POST",
					url : "articlePage",
					data : {
						action : "addCart",
						nome : $("#oNome").html(),
						marca : $("#oMarca").html(),
					},
					success : function() {
						var s = $("#count").html();
						var num = parseInt(s);
						$("#count").html(num + 1);
					}

				});
			});

		})
	</script>
	<div class="container-fluid">
		<div class="content-wrapper">
			<div class="item-container">
			<% 
				String img1 = articolo.getImg1(), img2=articolo.getImg2(), img3=articolo.getImg3();
				if(img1==null) img1 = "/Quattrocchi/web_pages/occhiali_placeholder.jpg";
				if(img2==null) img2 = "/Quattrocchi/web_pages/occhiali_placeholder.jpg";
				if(img3==null) img3 = "/Quattrocchi/web_pages/occhiali_placeholder.jpg";
			%>
				<div class="container">
					<div>
						<div class="enlarge product col-sm-3 service-image-left">
							<center>
								<img class="enlarge" src="<%=img1.replaceAll(" ", "_").trim()%>" alt="pic1"></img>
							</center>
						</div>

						<div class="container service1-items col-sm-2 col-sm-2 pull-left">
							<center>
								<a class="service1-item"> <img 
									src="<%=img2.replaceAll(" ", "_").trim()%>" alt="img"></img>
								</a> <a class="service1-item"> <img 
									src="<%=img3.replaceAll(" ", "_").trim()%>" alt="img"></img>
								</a>
							</center>
						</div>
					</div>

					<div class="col-sm-6">
						<input type="hidden" id="codice" value="<%=articolo.getCodice()%>"/>
						<div class="product-title" id="oNome"><%=articolo.getModello()%></div>
						<h4 id="oMarca"><%=articolo.getMarca()%></h4>
						<hr>
						<div class="product-desc"><%=articolo.getDescrizione()%></div>

						<div> <span class="product-price"><%=articolo.getPrezzo()%> € </span>
						</div>
						<br><br>
						<div style="float:right">
						<span class="product-stock" id="oDisp">
							<%= articolo.getDisponibilità()%>
							disponibili
						</span>
						<div style="margin-left:10px"class="btn-group cart" >
							<button type="button" id="addCart" class="btn btn-outline-secondary">Add to cart</button>
						</div>
						</div>
					</div>
				</div>
			</div>

		</div>
	</div>
	<%@ include file="../view/Footer.jsp"%>	
	<script src="web_pages/js/article-page.js"></script>
</body>
</html>