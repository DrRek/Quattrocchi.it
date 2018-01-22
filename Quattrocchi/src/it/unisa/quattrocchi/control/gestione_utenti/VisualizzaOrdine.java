package it.unisa.quattrocchi.control.gestione_utenti;

import javax.servlet.RequestDispatcher;
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

	protected void doGet(HttpServletRequest request, HttpServletResponse response) {
		try {
			int orderId = Integer.parseInt(request.getParameter("ordineId"));
			if(orderId != 0) {
				Order ordine = orderModel.doRetrieveById(orderId);
				request.getSession().setAttribute("ordineDaGestire", ordine);
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/web_pages/view/VisualizzaOrdine.jsp");
				dispatcher.forward(request, response);
				return;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) {
		doGet(request, response);
	}

}
