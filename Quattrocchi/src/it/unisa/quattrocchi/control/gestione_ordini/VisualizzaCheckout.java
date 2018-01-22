package it.unisa.quattrocchi.control.gestione_ordini;

import javax.servlet.RequestDispatcher;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.unisa.quattrocchi.model.AcquirenteModel;
import it.unisa.quattrocchi.model.ArticoloInOrderModel;
import it.unisa.quattrocchi.model.OrderModel;

@WebServlet("/visualizza_checkout")

public class VisualizzaCheckout extends HttpServlet {

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
