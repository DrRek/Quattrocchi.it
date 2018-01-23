package it.unisa.quattrocchi.control.gestione_ordini;

import javax.servlet.RequestDispatcher;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import it.unisa.quattrocchi.entity.Acquirente;
import it.unisa.quattrocchi.entity.Cart;
import it.unisa.quattrocchi.model.AcquirenteModel;
import it.unisa.quattrocchi.model.ArticoloInOrderModel;
import it.unisa.quattrocchi.model.OrderModel;

@WebServlet("/visualizza_checkout")

/**
 * 
 * @author quattrocchi.it
 * Questa classe è una servlet che si occupa di gestire la visualizzazione del checkout.
 */
public class VisualizzaCheckout extends HttpServlet {

	private static final long serialVersionUID = 1L;
	
	static OrderModel model = new OrderModel();
	static AcquirenteModel acModel = new AcquirenteModel();
	static ArticoloInOrderModel aInOrderModel = new ArticoloInOrderModel();

	
	/**
	 * Questo metodo si occupa di effettuare la procedura di checkout.
	 * 
	 * @precondition 	La richiesta è sincrona
	 * 					L'utente è loggato.
	 * 					Il carrello non è vuoto.
	 */
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) {
		try {
			
			//Per controllare che la richiesta sia del tipo giusto
			if("XMLHttpRequest".equals(request.getHeader("X-Requested-With"))) {
				response.setContentType("application/json");
				response.setHeader("Cache-Control", "no-cache");
				response.getWriter().write(new Gson().toJson("Errore generato dalla richiesta! Se il problema persiste contattaci."));
				return;
			}
			
			Acquirente a = (Acquirente) request.getSession().getAttribute("acquirente");
			if(a==null) {
				request.setAttribute("error", "Devi essere loggato per poter effettuare il checkout.");
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/login");
				dispatcher.forward(request, response);
			}
			
			Cart carrello = a.getCart();
			if(carrello==null || carrello.getNumeroDiArticoli()==0) {
				request.setAttribute("error", "Aggiungi articoli al carrello prima di poter effettuare il checkout.");
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/visualizza_catalogo");
				dispatcher.forward(request, response);
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
