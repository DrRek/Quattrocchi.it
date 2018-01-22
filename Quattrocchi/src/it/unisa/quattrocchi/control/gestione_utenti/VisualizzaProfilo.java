package it.unisa.quattrocchi.control.gestione_utenti;

import javax.servlet.RequestDispatcher;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import it.unisa.quattrocchi.entity.Acquirente;
import it.unisa.quattrocchi.model.OrderModel;

@WebServlet("/profilo")

public class VisualizzaProfilo extends HttpServlet{

	private static final long serialVersionUID = 1L;
	
	static OrderModel model = new OrderModel();
	
	/**
	 * 
	 * @precondition 	La richiesta è sincrona.
	 * 					L'utente è loggato.
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
			
			Acquirente usr = (Acquirente) request.getSession().getAttribute("acquirente");
			if(usr==null) {
				request.setAttribute("error", "E' neccessario effettuare il login!");
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/login");
				dispatcher.forward(request, response);
				return;
			}

			request.setAttribute("storico_ordini", model.doRetrieveByAcquirente(usr));
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
