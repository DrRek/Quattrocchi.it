<!DOCTYPE html>
<head>
   <title>
      Quattrocchi.it - index
   </title>
   <!-- styles -->
   <link href="web_pages/css/bootstrap.css" rel="stylesheet">
</head>
<body>
<%@ include file="../view/Header.jsp"%>
   <div id="myCarousel" class="carousel slide" data-ride="carousel">
  <!-- Indicators -->
  <ol class="carousel-indicators">
    <li data-target="#myCarousel" data-slide-to="0" class="active"></li>
    <li data-target="#myCarousel" data-slide-to="1"></li>
    <li data-target="#myCarousel" data-slide-to="2"></li>
  </ol>

  <!-- Wrapper for slides -->
  <div class="carousel-inner">
    <div class="item active">
      <img src="../image/home2.jpg" alt="rayban">
      <div class="carousel-caption">
        <h1><a style="color:white"  href="">Rayban</a></h1>
        <p>Guarda le ultime uscite</p>
      </div>
    </div>

    <div class="item">
      <img src="../image/home3.jpg" alt="oakley">
      <div class="carousel-caption">
        <h1><a style="color:white" href="">Oakley</a></h1>
        <p>Scegli lo stile californiano</p>
      </div>
    </div>

    <div class="item">
      <img src="../image/home1.jpg" alt="persol">
      <div class="carousel-caption">
        <h1><a style="color:white"  href="">Persol</a></h1>
        <p>La marca eco-frienldy</p>
        </div>
    </div>
  </div>

  <!-- Left and right controls -->
  <a class="left carousel-control" href="#myCarousel" data-slide="prev">
    <span class="glyphicon glyphicon-chevron-left"></span>
    <span class="sr-only">Previous</span>
  </a>
  <a class="right carousel-control" href="#myCarousel" data-slide="next">
    <span class="glyphicon glyphicon-chevron-right"></span>
    <span class="sr-only">Next</span>
  </a>
</div>
      <div class="container">
         <h2>Benvenuto su Quattrocchi.it</h2>
         <p>Quattrocchi.it è un e-commerce innovativo dedicato esclusivamente alla vendita di occhiali da sole.</p>
      </div>
      <!-- /.container -->
   <br>
   <div class="container" align="center">
		<input class="btn btn-outline-secondary " type="submit"
			name="inserireNOME" value="Vai al catalogo" />
	</div>
	<%@ include file="Footer.jsp"%>
	<script> 
	$('.carousel').carousel({
		  interval: 2000
	})
	</script>
	<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script
	src="web_pages/js/bootstrap.js"></script>
</body>
</html>