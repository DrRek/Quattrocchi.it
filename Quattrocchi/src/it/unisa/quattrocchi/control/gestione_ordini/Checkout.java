package it.unisa.quattrocchi.control.gestione_ordini;

import javax.servlet.RequestDispatcher;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.unisa.quattrocchi.entity.Acquirente;
import it.unisa.quattrocchi.entity.Order;

@WebServlet("/checkout")

public class Checkout extends HttpServlet{

	private static final long serialVersionUID = 1L;

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) {
		try {
			Acquirente usr = (Acquirente) request.getSession().getAttribute("acquirente");
			String CreditCardID = request.getParameter("CreditCardID");
			String ShippingAddressID = request.getParameter("ShippingAddressID");
			if(usr!=null && CreditCardID!=null && !CreditCardID.equals("") && ShippingAddressID!=null && !ShippingAddressID.equals("")) {
				if(usr.checkCC(CreditCardID) && usr.checkSA(ShippingAddressID)) {
					//Order nuovo = new Order();
				}
				return;
			}
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/web_pages/view/CheckoutView.jsp");
			dispatcher.forward(request, response);
		} catch (Exception e) {
			System.out.println("Errore in Checkout:");
			e.printStackTrace();
		}
		return;
	}
	
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) {
		doGet(request, response);
		return;
	}
}
