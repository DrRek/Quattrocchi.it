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

import com.google.gson.Gson;

import it.unisa.quattrocchi.entity.Order;
import it.unisa.quattrocchi.model.OrderModel;

@WebServlet("/gestoreOrdini")

public class VisualizzaOrdini extends HttpServlet{
	
	static OrderModel orderModel = new OrderModel();

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		try {
			System.out.println("Try find order");
			List<Order> ordini = orderModel.doRetrieveAll();
			System.out.println("numero ordini " + ordini.size());
			request.getSession().setAttribute("ordini", ordini);
			System.out.println("Settato attributo");
		} catch(SQLException e) {
			e.printStackTrace();
		}
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/web_pages/view/GestoreOrdiniView.jsp");
		dispatcher.forward(request, response);
		return;
	}
	
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		doGet(request, response);
		return;
	}
}
