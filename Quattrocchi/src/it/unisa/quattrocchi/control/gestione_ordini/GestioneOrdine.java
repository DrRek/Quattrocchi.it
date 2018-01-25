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

import it.unisa.quattrocchi.entity.GestoreOrdini;
import it.unisa.quattrocchi.entity.Order;
import it.unisa.quattrocchi.model.OrderModel;

@WebServlet("/gestioneOrdineDaSpedire")

/**
 * 
 * @author quattrocchi.it
 * Questa classe è una servlet che si occupa di effettuare la gestione dell'ordine da spedire.
 */
public class GestioneOrdine extends HttpServlet{

	private static final long serialVersionUID = 1L;
	static OrderModel orderModel = new OrderModel();
	
	
	/**
	 * Questo metodo si occupa di fornire la funzionalità di gestione di un ordine
	 * da spedire. 
	 * @precondition	La richiesta è sincrona.
	 * 					L'utente connesso è un gestore degli ordini.
	 * 					ordineId corrisponde veramente ad un id di un ordine in database.
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
			
			GestoreOrdini gestoreOrdini = (GestoreOrdini) request.getSession().getAttribute("gestoreOrdini");
			if(gestoreOrdini==null) {
				request.setAttribute("notification", "Errore nell'eseguire la richiesta. Permessi insufficienti.");
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/welcome");
				dispatcher.forward(request, response);
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
			
			request.setAttribute("ordineDaGestire", ordineDaGestire);
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/web_pages/view/GestioneOrdine.jsp");
			dispatcher.forward(request, response);
			
		} catch (ServletException | IOException | SQLException e) {
			System.out.println("Errore in Gestione Ordini:");
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
