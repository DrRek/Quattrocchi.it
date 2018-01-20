package it.unisa.quattrocchi.control.gestione_ordini;


import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import it.unisa.quattrocchi.entity.Acquirente;

import it.unisa.quattrocchi.entity.ArticoloInOrder;
import it.unisa.quattrocchi.entity.ArticoloInStock;
import it.unisa.quattrocchi.entity.Cart;

import it.unisa.quattrocchi.entity.CreditCard;
import it.unisa.quattrocchi.entity.Order;
import it.unisa.quattrocchi.entity.ShippingAddress;
import it.unisa.quattrocchi.model.AcquirenteModel;
import it.unisa.quattrocchi.model.ArticoloInOrderModel;
import it.unisa.quattrocchi.model.OrderModel;

@WebServlet("/checkout")


/**
 * 
 * @author quattrocchi.it
 * Questa classe è una servlet che si occupa di gestire la procedura di checkout.
 */
public class Checkout extends HttpServlet{

	private static final long serialVersionUID = 1L;
	
	static OrderModel model = new OrderModel();
	static AcquirenteModel acModel = new AcquirenteModel();
	static ArticoloInOrderModel aInOrderModel = new ArticoloInOrderModel();

	
	/**
	 * Questo metodo si occupa di effettuare la procedura di checkout.
	 */
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) {
		try {
			Acquirente usr = (Acquirente) request.getSession().getAttribute("acquirente");
			String CreditCardID = request.getParameter("CreditCardID");
			String ShippingAddressID = request.getParameter("ShippingAddressID");
			if(usr!=null && CreditCardID!=null && !CreditCardID.equals("") && ShippingAddressID!=null && !ShippingAddressID.equals("")) {
				CreditCard cc = usr.checkCC(CreditCardID);
				ShippingAddress sa = usr.checkSA(ShippingAddressID);
				if(cc!=null && sa!=null) {
					Order nuovo = new Order(usr, sa, cc);
					model.createOrder(nuovo);
					usr.resetCart();
					acModel.updateCart(usr);
				}
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/profilo");
				dispatcher.forward(request, response);
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
