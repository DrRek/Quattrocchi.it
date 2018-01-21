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

<link href="web_pages/css/bootstrap.css" type="text/css"
	rel="stylesheet" media="screen,projection" />

<link href="web_pages/css/ArticlePageView.css" type="text/css"
	rel="stylesheet" media="screen,projection" />

<!--Let browser know website is optimized for mobile-->
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>

</head>

<body>
	<%@ include file="../view/Header.jsp"%>
	<div class="container" id="all">
		<% 
				String path = "/Quattrocchi/web_pages/image/";
				String img1 = path+articolo.getImg1(), img2=path+articolo.getImg2(), img3=path+articolo.getImg3();
			%>


		<div class="col-md-12">

			<div class="row" id="productMain">
				<div class="col-sm-6">
					<div class="enlarge" id="mainImage">
						<img src="<%=img1.trim()%>" alt="" class="img-responsive">
					</div>

				</div>
				<div class="col-sm-6">
					<br><br>
					<div class="box">
						<input type="hidden" id="codice" value="<%=articolo.getCodice()%>" />
						<h1 class="text-center"><%=articolo.getModello()%></h1>
						<h4><%=articolo.getMarca()%></h4>
						<p class="goToDescription">
							<a href="#details" class="scroll-to">Mostra descrizione</a>
						</p>
						<h2>
							€
							<%=articolo.getPrezzo()%></h2>

						<p class="text-center buttons">
							<button class="btn btn-outline-secondary" id="addCart"> Add to cart</button>
						</p>


					</div>

					<div class="row" id="thumbs">
						<div class="col-xs-6">
							<img src="<%=img2.trim()%>" " alt=""
								class="img-responsive enlarge">
						</div>
						<div class="col-xs-6">
							<img src="<%=img3.trim()%>" alt="" class="img-responsive enlarge">
						</div>
					</div>
				</div>

			</div>


			<div class="box" id="details">
				<p>
					<h4>Dettagli prodotto</h4>
                            <p><%=articolo.getDescrizione()%></p>
            </div>

                  

  


                </div>
                <!-- /.col-md-9 -->
            </div>
            <!-- /.container -->
        </div>
        <!-- /#content -->

	<%@ include file="../view/Footer.jsp"%>
					<script src="web_pages/js/article-page.js"></script>

				</body>
</html>