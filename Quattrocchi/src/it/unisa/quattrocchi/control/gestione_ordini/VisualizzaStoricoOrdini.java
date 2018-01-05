package it.unisa.quattrocchi.control.gestione_ordini;

import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.unisa.quattrocchi.model.OrderModel;

@WebServlet("/visualizza_storico_ordini")

public class VisualizzaStoricoOrdini extends HttpServlet{
	
	static OrderModel model = new OrderModel();

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) {
		try {
			response.getWriter().write(new Gson().toJson(model.doRetrieveAll()));
		}catch(SQLException e) {
			e.printStackTrace();
		}
		
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/web_pages/view/GestoreOrdiniView.jsp");
		dispatcher.forward(request, response);
		return;
	}
	
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) {
		doGet(request, response);
		return;
	}
}
