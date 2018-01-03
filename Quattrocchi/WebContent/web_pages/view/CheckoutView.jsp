<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%
	Cart cart = (Cart) request.getSession().getAttribute("cart");
	UserBean user = (UserBean) request.getSession().getAttribute("user");
%>

<!DOCTYPE html>
<html>
<%@ page contentType="text/html; charset=UTF-8"
	import="java.util.*,it.quattrocchi.support.*, it.quattrocchi.*"%>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

<title>quattrocchi.it</title>

<!-- CSS  -->
<link href="https://fonts.googleapis.com/icon?family=Material+Icons"
	rel="stylesheet">

<link href="css/bootstrap.css" type="text/css" rel="stylesheet"
	media="screen,projection" />
<link href="  css/CheckoutView.css" type="text/css" rel="stylesheet"
	media="screen,projection" />
<!--Let browser know website is optimized for mobile-->
<meta name="viewport" content="width=device-width, initial-scale=1.0" />
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script src="js/checkout.js"></script>

</head>
<body>
	​​​​​​​​​​​​
	<%@ include file="header.jsp"%>
	<br>
	<br>
	<div class="container">
		<h2>Cart</h2>
	</div>
	<%
		if (cart == null) {
	%>
	<div class="container">
		<h3>empty cart</h3>
	</div>
	<%
		} else if(cart!=null && user !=null) {
	%>
	<div class="container" id="divCartElements"></div>
	<%
		}
	%>
	<div class="container">
		<h3 id="tot"></h3>
	</div>
	<div class="container">
		<%
			ArrayList<CreditCardBean> creditCards = user.getCards();
			if (creditCards != null) {
		%>
		<form action="checkout" method="post">
			<input type="hidden" name="action" value="submit"/>
			<label>seleziona una carta:</label><br><select class="form-control" name="carta">
				<%
					for (CreditCardBean c : creditCards) {
				%>
				<option value="<%=c.getNumeroCC()%>"><%=c.getNumeroCC()%></option>
				<%
					}
				%>
	
			</select> <br>
			<a href="user">aggiungi una carta</a><br><br>
			<input class= "btn btn-outline-secondary " id="completeOrder" type="submit" value="Paga" />
		</form>
		<%
			}
		%>
	</div>
		<%@ include file="../jsp/footer.jsp"%>
	
</body>
</html>