package it.unisa.quattrocchi.control.gestione_ordini;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.unisa.quattrocchi.entity.Order;
import it.unisa.quattrocchi.model.OrderModel;

@WebServlet("/gestoreOrdini")

public class VisualizzaOrdini extends HttpServlet{
	
	private static final long serialVersionUID = 1L;
	
	static OrderModel orderModel = new OrderModel();

	/**
	 * 
	 * @precondition l'utente loggato � un gestore ordini
	 */
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)  {
		try {
			List<Order> ordini = orderModel.doRetrieveAll();
			request.getSession().setAttribute("ordini", ordini);
			
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/web_pages/view/GestoreOrdiniView.jsp");
			dispatcher.forward(request, response);
		} catch(IOException | ServletException | SQLException e) {
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
