package it.unisa.quattrocchi.control.gestione_ordini;

import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import it.unisa.quattrocchi.entity.Acquirente;
import it.unisa.quattrocchi.entity.Order;
import it.unisa.quattrocchi.model.OrderModel;

@WebServlet("/visualizza_storico_ordini")

/**
 * 
 * @author quattrocchi.it
 * Questa classe è una servlet che si occupa di gestire la visualizzazione dello storico degli ordini.
 */
public class VisualizzaStoricoOrdini extends HttpServlet{
	
	private static final long serialVersionUID = 1L;
	
	static OrderModel model = new OrderModel();

	/**
	 * 
	 * @precondition 	La richiesta è sincrona.
	 * 					L'utente è loggato.
	 * @postcondition ordini contiene tutti gli ordini sottomessi da parte dell'utente
	 */
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) {
		try {

			//Per controllare che la richiesta sia del tipo giusto
			if("XMLHttpRequest".equals(request.getHeader("X-Requested-With"))) {
				response.setContentType("application/json");
				response.setHeader("Cache-Control", "no-cache");
				response.getWriter().write(new Gson().toJson("Errore generato dalla richiesta! Se il problema persiste contattaci."));
				return;
			}
			
			Acquirente a = (Acquirente) request.getSession().getAttribute("acquirente");
			if(a==null) {
				request.setAttribute("notification", "Devi essere loggato per poter effettuare il checkout.");
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/login");
				dispatcher.forward(request, response);
				return;
			}
			
			List<Order> ordini = model.doRetrieveByAcquirente(a);
			
			response.getWriter().write(new Gson().toJson(ordini));
			
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/web_pages/view/UserView.jsp");
			dispatcher.forward(request, response);
		}catch(Exception e) {
			System.out.println("Errore in Inserisci dati di spedizione:");
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
