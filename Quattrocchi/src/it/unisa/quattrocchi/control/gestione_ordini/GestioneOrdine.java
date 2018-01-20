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
 * Questa classe è una servlet che si occupa di effettuare la gestione dell'ordine da spedire.
 */
public class GestioneOrdine extends HttpServlet{

	private static final long serialVersionUID = 1L;
	static OrderModel orderModel = new OrderModel();
	
	
	/**
	 * Questo metodo si occupa di fornire la funzionalità di gestione di un ordine
	 * da spedire. 
	 */
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String orderId = request.getParameter("ordineId");
			if(orderId == null) {				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/web_pages/view/GestoreOrdiniView.jsp");
				dispatcher.forward(request, response);
				return;
			}
			Order ordineDaGestire = orderModel.doRetrieveById(Integer.parseInt(request.getParameter("ordineId")));
			request.getSession().setAttribute("ordineDaGestire", ordineDaGestire);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/web_pages/view/GestioneOrdine.jsp");
		dispatcher.forward(request, response);
		return;
	}
	
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
		return;
	}
}
