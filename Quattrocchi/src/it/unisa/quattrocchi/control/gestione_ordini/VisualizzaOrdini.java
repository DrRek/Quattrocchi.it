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

import it.unisa.quattrocchi.entity.GestoreOrdini;
import it.unisa.quattrocchi.entity.Order;
import it.unisa.quattrocchi.model.OrderModel;

@WebServlet("/gestoreOrdini")

/**
 * 
 * @author quattrocchi.it
 * Questa classe è una servlet che si occupa di gestire la visualizzazione degli ordini.
 */
public class VisualizzaOrdini extends HttpServlet{
	
	private static final long serialVersionUID = 1L;
	
	static OrderModel orderModel = new OrderModel();

	/**
	 * 
	 * @precondition 	La richiesta è sincrona
	 * 					L'utente loggato è un gestore ordini
	 */
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response)  {
		try {
			
			//Per controllare che la richiesta sia del tipo giusto
			if("XMLHttpRequest".equals(request.getHeader("X-Requested-With"))) {
				response.setContentType("application/json");
				response.setHeader("Cache-Control", "no-cache");
				response.getWriter().write(new Gson().toJson("Errore generato dalla richiesta! Se il problema persiste contattaci."));
				return;
			}
			
			GestoreOrdini gestoreOrdini = (GestoreOrdini) request.getSession().getAttribute("gestoreOrdini");
			if(gestoreOrdini==null) {
				request.setAttribute("notification", "Errore nell'eseguire la richiesta. Permessi insufficienti.");
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/welcome");
				dispatcher.forward(request, response);
				return;
			}
			
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
