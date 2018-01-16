package it.unisa.quattrocchi.control.gestione_ordini;

import javax.servlet.RequestDispatcher;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/visualizza_carrello")

/**
 * 
 * @author quattrocchi.it
 * Questa classe è una servlet che si occupa di gestire la visualizzazione del carrello.
 */
public class VisualizzaCarrello extends HttpServlet{

	private static final long serialVersionUID = 1L;

	
	/**
	 * Questo metodo si occupa di effettuare la visualizzazione del carrello.
	 */
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) {
		try {
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/web_pages/view/CartView.jsp");
			dispatcher.forward(request, response);
		} catch (Exception e) {
			System.out.println("Errore in Visualizza prodotto:");
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
