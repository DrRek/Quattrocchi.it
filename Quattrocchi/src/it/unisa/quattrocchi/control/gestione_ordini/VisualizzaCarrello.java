package it.unisa.quattrocchi.control.gestione_ordini;

import javax.servlet.RequestDispatcher;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.unisa.quattrocchi.entity.ArticoloInStock;

@WebServlet("/visualizza_carrello")

public class VisualizzaCarrello extends HttpServlet{

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) {
		try {
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/web_pages/view/CheckoutView.jsp");
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
