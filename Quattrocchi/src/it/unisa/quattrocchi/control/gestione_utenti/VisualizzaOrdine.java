package it.unisa.quattrocchi.control.gestione_utenti;

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


@WebServlet("/VisualizzaOrdineUtente")
public class VisualizzaOrdine extends HttpServlet {
	
	static OrderModel orderModel = new OrderModel();
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int orderId = Integer.parseInt(request.getParameter("ordineId"));
		if(orderId != 0) {
			try {
				Order ordine = orderModel.doRetrieveById(orderId);
				request.getSession().setAttribute("ordineDaGestire", ordine);
			} catch (SQLException e) {
				e.printStackTrace();
			}
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/web_pages/view/VisualizzaOrdine.jsp");
			dispatcher.forward(request, response);
			return;
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
