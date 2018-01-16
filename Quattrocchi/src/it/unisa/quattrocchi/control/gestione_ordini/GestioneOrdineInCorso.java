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

@WebServlet("/gestioneOrdiniInCorso")

/**
 * 
 * @author quattrocchi.it
 * Questa classe è una servlet che si occupa di effettuare la gestione dell'ordine 
 * che si trova nello stato "In Corso" e deve andare nello stato "Consegnato".
 */
public class GestioneOrdineInCorso extends HttpServlet{

	private static final long serialVersionUID = 1L;
	static OrderModel orderModel = new OrderModel();
	
	/**
	 * Questo metodo si occupa di aggiornare lo stato di un ordine in "Consegnato".
	 */
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			Order toUpdateOrder = orderModel.doRetrieveById(request.getParameter("ordineId"));
			toUpdateOrder.setStatoOrdine("Consegnato");
			orderModel.updateOrder(toUpdateOrder);
			request.getSession().setAttribute("ordini", orderModel.doRetrieveAll());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/web_pages/view/GestoreOrdiniView.jsp");
		dispatcher.forward(request, response);
		return;
	}
	
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
		return;
	}
}
