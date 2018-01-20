package it.unisa.quattrocchi.control.gestione_utenti;

import javax.servlet.RequestDispatcher;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.unisa.quattrocchi.entity.Acquirente;
import it.unisa.quattrocchi.model.OrderModel;

@WebServlet("/profilo")

public class VisualizzaProfilo extends HttpServlet{

	private static final long serialVersionUID = 1L;
	
	static OrderModel model = new OrderModel();
	
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) {
		try {
			request.setAttribute("storico_ordini", model.doRetrieveByAcquirente((Acquirente) request.getSession().getAttribute("acquirente")));
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/web_pages/view/UserView.jsp");
			dispatcher.forward(request, response);
		} catch (Exception e) {
			System.out.println("Errore in Visualizza profilo:");
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
