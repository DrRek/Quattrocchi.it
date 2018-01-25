package it.unisa.quattrocchi.control.gestione_ordini;

import java.util.Date;
import java.text.SimpleDateFormat;
import javax.servlet.RequestDispatcher;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import it.unisa.quattrocchi.entity.GestoreOrdini;
import it.unisa.quattrocchi.entity.Order;
import it.unisa.quattrocchi.model.OrderModel;

@WebServlet("/inserisciDatiDiSpedizione")


/**
 * 
 * @author quattrocchi.it
 * Questa classe è una servlet che si occupa di gestire l'aggiunta del numero
 * di tracking e la data della consegna relativa all'ordine da gestire.
 */
public class InserisciDatiDiSpedizione extends HttpServlet{

	private static final long serialVersionUID = 1L;
	static OrderModel orderModel = new OrderModel();

	
	/**
	 * Questo metodo si occupa di effetttuare l'aggiunta del numero di tracking,
	 * la data della consegna e dello stato relativi all'ordine da gestire.
	 * @precondition 	La richiesta è sincrona.
	 * 					L'utente connesso è un gestore degli ordini.
	 * 					orderId != null, è trasformabile in un intero e corrisponde ad un ordine nel database,
	 * 					corriere != null && corriere.matches("[A-Za-z ]{3,10}")
	 * 					tracking != null && tracking.marches("[A-Za-z0-9]{5,15}")
	 * 					statoOrdine è uguale a Order.DA_SPEDIRE, Order.IN_CORSO o Order.TERMINATO
	 * 					parsed != null && parsed.matches("[0-9]{4}[-]{1}[0-9]{2}[-]{1}[0-9]{2}")
	 * @postcondition orderToUpdate viene aggiornato e le modifiche vengono propagate al database.
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

			int orderId=0;
			try {
				orderId = Integer.parseInt(idS);
			} catch(Exception e) {
				request.setAttribute("notification", "Identificativo dell'ordine non valido.");
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/gestoreOrdini");
				dispatcher.forward(request, response);
				return;
			}
			if(orderId==0) {
				request.setAttribute("notification", "Identificativo dell'ordine non valido.");
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/gestoreOrdini");
				dispatcher.forward(request, response);
				return;
			}
			
			Order orderToUpdate = orderModel.doRetrieveById(orderId);
			if(orderToUpdate==null) {
				request.setAttribute("notification", "Ordine con questo identificativo non trovato");
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/gestoreOrdini");
				dispatcher.forward(request, response);
				return;
			}
			
			String corriere = request.getParameter("corriere");
			if(corriere==null || !corriere.matches("[A-Za-z ]{3,10}")) {
				request.setAttribute("notification", "Corriere inserito non valido.");
				request.setAttribute("ordineId", idS);
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/gestioneOrdineDaSpedire");
				dispatcher.forward(request, response);
				return;
			}
			
			String tracking = request.getParameter("tracking");
			if(tracking==null || !tracking.matches("[A-Za-z0-9]{5,15}")) {
				request.setAttribute("notification", "Numero di tracking inserito non valido.");
				request.setAttribute("ordineId", idS);
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/gestioneOrdineDaSpedire");
				dispatcher.forward(request, response);
				return;
			}
			
			String statoOrdine = request.getParameter("statoOrdine");
			if(statoOrdine==null || 
					(!statoOrdine.equalsIgnoreCase(Order.DA_SPEDIRE) && !statoOrdine.equalsIgnoreCase(Order.IN_CORSO) && !statoOrdine.equalsIgnoreCase(Order.TERMINATO))) {
				request.setAttribute("notification", "Stato ordine inserito non valido.");
				request.setAttribute("ordineId", idS);
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/gestioneOrdineDaSpedire");
				dispatcher.forward(request, response);
				return;
			}
			
			java.sql.Date dataConsegna;
			try {
				SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
				String strDate = request.getParameter("dataDiConsegna");
			    Date parsed = format.parse(strDate);
			    dataConsegna = new java.sql.Date(parsed.getTime());
			} catch (Exception e){
				request.setAttribute("notification", "Data di consegna inserita non valida. Formato valido: yyyy-MM-dd");
				request.setAttribute("ordineId", idS);
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/gestioneOrdineDaSpedire");
				dispatcher.forward(request, response);
				return;
			}
			
			orderToUpdate.setCorriere(corriere);
			orderToUpdate.setNumeroTracking(tracking);
			orderToUpdate.setStatoOrdine(statoOrdine);
			orderToUpdate.setDataConsegna(dataConsegna);
			orderModel.updateOrder(orderToUpdate);
			request.getSession().setAttribute("ordini", orderModel.doRetrieveAll());	
			
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/web_pages/view/GestoreOrdiniView.jsp");
			dispatcher.forward(request, response);		
		} catch (Exception e) {
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
