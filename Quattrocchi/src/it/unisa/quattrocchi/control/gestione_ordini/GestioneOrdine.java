package it.unisa.quattrocchi.control.gestione_ordini;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.unisa.quattrocchi.entity.Order;
import it.unisa.quattrocchi.model.OrderModel;

@WebServlet("/gestioneOrdineDaSpedire")

/**
 * 
 * @author quattrocchi.it
 * Questa classe � una servlet che si occupa di effettuare la gestione dell'ordine da spedire.
 */
public class GestioneOrdine extends HttpServlet{

	private static final long serialVersionUID = 1L;
	static OrderModel orderModel = new OrderModel();
	
	
	/**
	 * Questo metodo si occupa di fornire la funzionalit� di gestione di un ordine
	 * da spedire. 
	 * @precondition ordineId corrisponde veramente ad un id di un ordine in database e l'utente connesso � un gestore di ordini.
	 */
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) {
		try {
			String orderId = request.getParameter("ordineId");
			if(orderId == null) {				
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/web_pages/view/GestoreOrdiniView.jsp");
				dispatcher.forward(request, response);
				return;
			}
			Order ordineDaGestire = orderModel.doRetrieveById(Integer.parseInt(request.getParameter("ordineId")));
			request.getSession().setAttribute("ordineDaGestire", ordineDaGestire);
			
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/web_pages/view/GestioneOrdine.jsp");
			dispatcher.forward(request, response);
		} catch (ServletException | IOException | SQLException e) {
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
