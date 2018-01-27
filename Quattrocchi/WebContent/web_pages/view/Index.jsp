<!DOCTYPE html>
<head>
<title>Quattrocchi.it</title>
<!-- styles -->
<link href="web_pages/css/bootstrap.css" rel="stylesheet">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script src="web_pages/js/bootstrap.js"></script>
</head>
<body>
	<%@ include file="../view/Header.jsp"%>
	<div class="container">
		<div id="myCarousel" class="carousel slide" data-ride="carousel">
			<!-- Indicators -->
			<ol class="carousel-indicators">
				<li data-target="#myCarousel" data-slide-to="0"></li>
				<li data-target="#myCarousel" data-slide-to="1" class="active"></li>
				<li data-target="#myCarousel" data-slide-to="2"></li>
			</ol>

			<!-- Wrapper for slides -->
			<div class="carousel-inner">
				<div class="item">
					<img src="web_pages/image/home1.jpg" alt="persol">
					<div class="carousel-caption">
						<h1>
							<a style="color: white; text-decoration: none;"
								href="ricerca_da_pagina_esterna?toSearch=persol">Persol</a>
						</h1>
						<p>La marca eco-frienldy</p>
					</div>
				</div>
				<div class="item active">
					<img src="web_pages/image/home2.jpg" alt="rayban">
					<div class="carousel-caption">
						<h1>
							<a style="color: white; text-decoration: none;"
								href="ricerca_da_pagina_esterna?toSearch=rayban">Rayban</a>
						</h1>
						<p>Guarda le ultime uscite</p>
					</div>
				</div>

				<div class="item">
					<img src="web_pages/image/home3.jpg" alt="oakley">
					<div class="carousel-caption">
						<h1>
							<a style="color: white; text-decoration: none;"
								href="ricerca_da_pagina_esterna?toSearch=oakley">Oakley</a>
						</h1>
						<p>Scegli lo stile californiano</p>
					</div>
				</div>
			</div>

		</div>
	</div>
	<br><br>
	<div class="container box"  style="text-align:center">
		<h1>Benvenuto su Quattrocchi.it</h1>
	</div>
	<!-- /.container -->
	<br>
	<div class="container" align="center">
	</div>
	<%@ include file="../view/Footer.jsp"%>
	<script>
		$('.carousel').carousel({
			interval : 2000
		})
	</script>
	<style>
		.box {
			background: #fff;
			border: solid 1px #e6e6e6;
			-webkit-box-sizing: border-box;
			-moz-box-sizing: border-box;
			box-sizing: border-box;
			padding: 20px;
			-webkit-box-shadow: 0 1px 5px rgba(0, 0, 0, 0.1);
			box-shadow: 0 1px 5px rgba(0, 0, 0, 0.1);
		}
	</style>
</body>
</html>