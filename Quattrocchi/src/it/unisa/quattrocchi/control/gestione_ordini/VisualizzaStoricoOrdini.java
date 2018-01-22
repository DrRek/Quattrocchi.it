package it.unisa.quattrocchi.control.gestione_ordini;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import it.unisa.quattrocchi.model.OrderModel;

@WebServlet("/visualizza_storico_ordini")

public class VisualizzaStoricoOrdini extends HttpServlet{
	
	private static final long serialVersionUID = 1L;
	
	static OrderModel model = new OrderModel();

	/**
	 * 
	 */
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) {
		try {
			response.getWriter().write(new Gson().toJson(model.doRetrieveAll()));
			
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/web_pages/view/UserView.jsp");
			dispatcher.forward(request, response);
		}catch(IOException | ServletException | SQLException e) {
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
