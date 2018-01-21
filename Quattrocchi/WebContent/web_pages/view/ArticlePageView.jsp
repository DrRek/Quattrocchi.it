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
	<div class="container-fluid">
		<div class="content-wrapper">
			<div class="item-container">
			<% 
				String path = "/Quattrocchi/web_pages/image/";
				String img1 = path+articolo.getImg1(), img2=path+articolo.getImg2(), img3=path+articolo.getImg3();
			%>
				<div class="container">
					<div>
						<div class="enlarge product col-sm-3 service-image-left">
							<center>
								<img class="enlarge" src="<%=img1.trim()%>" alt="pic1"></img>
							</center>
						</div>

						<div class="container service1-items col-sm-2 col-sm-2 pull-left">
							<center>
								<a class="service1-item"> <img 
									src="<%=img2.trim()%>" alt="img"></img>
								</a> <a class="service1-item"> <img 
									src="<%=img3.trim()%>" alt="img"></img>
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

						<div> <span class="product-price">Prezzo: € <%=articolo.getPrezzo()%> </span>
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