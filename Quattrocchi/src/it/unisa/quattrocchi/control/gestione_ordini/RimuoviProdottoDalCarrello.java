package it.unisa.quattrocchi.control.gestione_ordini;

import java.util.HashMap;

import javax.servlet.RequestDispatcher;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import it.unisa.quattrocchi.entity.Acquirente;
import it.unisa.quattrocchi.entity.ArticoloInStock;
import it.unisa.quattrocchi.entity.Cart;
import it.unisa.quattrocchi.model.AcquirenteModel;
import it.unisa.quattrocchi.model.ArticoloInStockModel;


@WebServlet("/rimuovi_dal_carrello")

/**
 * 
 * @author quattrocchi.it
 * Questa classe è una servlet che si occupa di gestire la rimozione di un articolo dal carrello.
 */
public class RimuoviProdottoDalCarrello extends HttpServlet{
	
	private static final long serialVersionUID = 1L;
	
	static ArticoloInStockModel articoloInStockModel = new ArticoloInStockModel();
	static AcquirenteModel acquirenteModel = new AcquirenteModel();

	/**
	 * 
	 * @precondition 	La richiesta è asincrona
	 * 					articoloId!=null, è trasformabile in un interno e corrisponde ad un articolo presente nel carrello.
	 * @postcondition articoloId non corrisponde ad un articolo presente nel carrello.
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
			
			String idS = request.getParameter("articoloId");
			if(idS==null || idS.equals("")) {
				request.setAttribute("notification", "E' necessario fornire l'id dell'articolo da cancellare dal carrello.");
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/visualizza_carrello");
				dispatcher.forward(request, response);
				return;
			}
			
			int articoloId=0;
			try {
				articoloId = Integer.parseInt(idS);
			} catch(Exception e) {
				request.setAttribute("notification", "Identificativo articolo non valido.");
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/visualizza_carrello");
				dispatcher.forward(request, response);
				return;
			}
			if(articoloId==0) {
				request.setAttribute("notification", "Identificativo articolo non valido.");
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/visualizza_carrello");
				dispatcher.forward(request, response);
				return;
			}
			
			Cart carrello = null;
			Acquirente a = (Acquirente) request.getSession().getAttribute("acquirente");
			if(a != null) {
				carrello = a.getCart();
			}
			else {
				
				if(request.getSession().getAttribute("carrello")!= null) {
					carrello = (Cart)request.getSession().getAttribute("carrello");
				}
				else {
					carrello = new Cart(new HashMap<ArticoloInStock,Integer>());
					request.getSession().setAttribute("carrello", carrello);
				}
			}
			
			ArticoloInStock articolo;
			articolo = articoloInStockModel.doRetrieveByIdInStock(articoloId);
			if(articolo==null) {
				request.setAttribute("notification", "L'identificativo fornito non corrisponde a nessun articolo nel carrello.");
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/visualizza_carrello");
				dispatcher.forward(request, response);
				return;
			}
			
			carrello.removeArticle(articolo);
			if(a != null) {
				acquirenteModel.updateCart((Acquirente)request.getSession().getAttribute("acquirente"));
			}
			
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/visualizza_carrello");
			dispatcher.forward(request, response);
		} catch (Exception e) {
			System.out.println("Errore in aggiungi prodotto al carrello");
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
