package it.unisa.quattrocchi.control.gestione_utenti;

import javax.servlet.RequestDispatcher;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import it.unisa.quattrocchi.entity.Acquirente;
import it.unisa.quattrocchi.entity.GestoreOrdini;
import it.unisa.quattrocchi.entity.Order;
import it.unisa.quattrocchi.model.OrderModel;


@WebServlet("/VisualizzaOrdineUtente")

/**
 * 
 * @author quattrocchi.it
 * Questa classe è una servlet che si occupa di gestire la visualizzazione di un ordine.
 */
public class VisualizzaOrdine extends HttpServlet {

	static OrderModel orderModel = new OrderModel();
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 * @precondition 	La richiesta è sincrona.
	 * 					orderId != null, è trasformabile in un intero e corrisponde ad un ordine nel database.
	 * 					Un utente è loggato.
	 * 					L'ordine è associato all'utente o l'utente è un gestore degli ordini.
	 */
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) {
		try {
			//Per controllare che la richiesta sia del tipo giusto
			if("XMLHttpRequest".equals(request.getHeader("X-Requested-With"))) {
				response.setContentType("application/json");
				response.setHeader("Cache-Control", "no-cache");
				response.getWriter().write(new Gson().toJson("Errore generato dalla richiesta! Se il problema persiste contattaci."));
				return;
			}

			String idS = request.getParameter("ordineId");
			if(idS==null || idS.equals("")) {
				request.setAttribute("notification", "Necessario fornire un identificativo dell'ordine.");
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/gestoreOrdini");
				dispatcher.forward(request, response);
				return;
			}

			int id=0;
			try {
				id = Integer.parseInt(idS);
			} catch(Exception e) {
				request.setAttribute("notification", "Identificativo dell'ordine non valido.");
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/gestoreOrdini");
				dispatcher.forward(request, response);
				return;
			}
			if(id==0) {
				request.setAttribute("notification", "Identificativo dell'ordine non valido.");
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/gestoreOrdini");
				dispatcher.forward(request, response);
				return;
			}

			Order ordineDaGestire = orderModel.doRetrieveById(id);
			if(ordineDaGestire==null) {
				request.setAttribute("notification", "Nessun ordine ritrovato con il dato identificativo.");
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/gestoreOrdini");
				dispatcher.forward(request, response);
				return;
			}

			GestoreOrdini gestoreOrdini = (GestoreOrdini) request.getSession().getAttribute("gestoreOrdini");
			Acquirente usr = (Acquirente) request.getSession().getAttribute("acquirente");
			if(gestoreOrdini==null && (usr==null || !ordineDaGestire.getAcquirente().equals(usr))) {
				request.setAttribute("notification", "Errore nell'eseguire la richiesta. Permessi insufficienti.");
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/welcome");
				dispatcher.forward(request, response);
				return;
			}

			request.setAttribute("ordineDaGestire", ordineDaGestire);
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/web_pages/view/VisualizzaOrdine.jsp");
			dispatcher.forward(request, response);

		} catch (Exception e) {
			System.out.println("Errore in Gestione Ordini:");
			e.printStackTrace();
		}
		return;
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) {
		doGet(request, response);
	}

}
