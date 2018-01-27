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
		<div style="background-color:#bc9185" align="center">
			<div id="myCarousel" class="carousel slide" data-ride="carousel">
				<!-- Indicators -->
				<ol class="carousel-indicators">
					<li data-target="#myCarousel" data-slide-to="0" class="active"></li>
					<li data-target="#myCarousel" data-slide-to="1"></li>
					<li data-target="#myCarousel" data-slide-to="2"></li>
				</ol>
				
				<!-- Wrapper for slides -->
				<a href="/Quattrocchi/visualizza_catalogo" >
					<div class="carousel-inner">
						<div class="item active">
							<img src="web_pages/image/home1.jpg" alt="home1">
						</div>
						<div class="item">
							<img src="web_pages/image/home2.jpg" alt="home2">
						</div>
						<div class="item">
							<img src="web_pages/image/home3.jpg" alt="home3">
						</div>
					</div>	
				</a>
			</div>
    </div>
    <br>
    <div class="container box">
    <p align="center" style="font-size: 2.5em;"><strong>BENVENUTO SU QUATTROCCHI.IT</strong></p>
    </div>
    <br>
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