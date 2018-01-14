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

@WebServlet("/inserisciDatiDiSpedizione")

public class InserisciDatiDiSpedizione extends HttpServlet{
	
	static OrderModel orderModel = new OrderModel();

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		try {
			Order orderToUpdate = orderModel.doRetrieveById(request.getParameter("ordineId"));
			orderToUpdate.setCorriere(request.getParameter("corriere"));
			String tracking = request.getParameter("tracking");
			if(tracking == null) { //aggiungere controlli
				return;
			}
			orderToUpdate.setNumeroTracking(tracking);
			orderToUpdate.setStatoOrdine(request.getParameter("statoOrdine"));
			orderModel.updateOrder(orderToUpdate);
			request.getSession().setAttribute("ordini", orderModel.doRetrieveAll());			
		} catch (SQLException e) {
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
