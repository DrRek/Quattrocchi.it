package it.unisa.quattrocchi.control.gestione_ordini;


import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import it.unisa.quattrocchi.entity.Acquirente;
import it.unisa.quattrocchi.entity.ArticoloInStock;
import it.unisa.quattrocchi.entity.CreditCard;
import it.unisa.quattrocchi.entity.Order;
import it.unisa.quattrocchi.entity.ShippingAddress;
import it.unisa.quattrocchi.model.AcquirenteModel;
import it.unisa.quattrocchi.model.ArticoloInOrderModel;
import it.unisa.quattrocchi.model.ArticoloInStockModel;
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
	static ArticoloInStockModel aInStockModel = new ArticoloInStockModel();


	/**
	 * Questo metodo si occupa di effettuare la procedura di checkout.
	 * @precondition 	La richiesta è sincrona.
	 * 					usr != null, CreditCardID != null e ShippingAddress != null
	 * 					ShippingAddressID e CreditCardID devono corrispondere ad un indirizzo ed una carta di usr.
	 * @postcondition 	usr.getCart().size() == 0 e il numero degli ordini passati di usr è incrementato di uno.
	 * 					La disponibilità di tutti gli articoli in ordine viene decrementata per i relativi articoli in stock.
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

			Acquirente usr = (Acquirente) request.getSession().getAttribute("acquirente");
			if(usr==null) {
				request.setAttribute("notification", "E' neccessario effettuare il login!");
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/login");
				dispatcher.forward(request, response);
				return;
			}

			String creditCardIDS = request.getParameter("CreditCardID");
			if(creditCardIDS==null || creditCardIDS.equals("")) {
				request.setAttribute("notification", "E' neccessario scegliere una carta di credito valida!");
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/visualizza_checkout");
				dispatcher.forward(request, response);
				return;
			}

			int creditCardID=0;
			try {
				creditCardID = Integer.parseInt(creditCardIDS);
			} catch(Exception e) {
				request.setAttribute("notification", "E' neccessario scegliere una carta di credito valida!");
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/visualizza_checkout");
				dispatcher.forward(request, response);
				return;
			}

			String shippingAddressIDS = request.getParameter("ShippingAddressID");
			if(shippingAddressIDS==null || shippingAddressIDS.equals("")) {
				request.setAttribute("notification", "E' neccessario scegliere un indirizzo di spedizione valido!");
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/visualizza_checkout");
				dispatcher.forward(request, response);
				return;
			}

			int shippingAddressID=0;
			try {
				shippingAddressID = Integer.parseInt(shippingAddressIDS);
			} catch(Exception e) {
				request.setAttribute("notification", "E' neccessario scegliere un indirizzo di spedizione valido!");
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/visualizza_checkout");
				dispatcher.forward(request, response);
				return;
			}

			if(creditCardID!=0 && shippingAddressID!=0) {
				CreditCard cc = usr.checkCC(creditCardID);
				ShippingAddress sa = usr.checkSA(shippingAddressID);
				if(cc==null || sa==null) {
					request.setAttribute("notification", "Errore nella scelta della carta di credito o dell'indirizzo!");
					RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/visualizza_checkout");
					dispatcher.forward(request, response);
					return;
				}
				
				Order nuovo = new Order(usr, sa, cc);
				model.createOrder(nuovo);
				
				Map<ArticoloInStock,Integer> articoli = usr.getCart().getArticoli();
				for(ArticoloInStock a : articoli.keySet()) {
					int d = articoli.get(a);
					
					a.setDisponibilità(a.getDisponibilità() - d);
					
					aInStockModel.updateDisponibilita(a);
				}
				
				
				for(int i=0; i < usr.getCart().getArticoli().size(); i++) {
					
				}
				
				usr.resetCart();
				acModel.updateCart(usr);
				

				request.setAttribute("notification", "L'ordine è stato sottomesso con successo!");
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/profilo");
				dispatcher.forward(request, response);
				return;
			}
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
