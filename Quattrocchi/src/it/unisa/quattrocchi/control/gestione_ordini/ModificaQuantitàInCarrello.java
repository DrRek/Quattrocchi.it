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

@WebServlet("/modifica_nel_carrello")

/**
 * 
 * @author quattrocchi.it
 * Questa classe Ë una servlet che si occupa di modificare la quantitÚ
 * degli articoli presenti nel carrello.
 */
public class ModificaQuantit‡InCarrello extends HttpServlet{
	
	private static final long serialVersionUID = 1L;
	
	static ArticoloInStockModel articoloInStockModel = new ArticoloInStockModel();
	static AcquirenteModel acquirenteModel = new AcquirenteModel();

	/**
	 * 
	 * @precondition 	La richiesta Ë asincrona.
	 * 					articoloId != null, trasformabile in un intero e corrisponde ad un articolo nel carrello
	 * 					quantit‡S != null ed Ë trasformabile in un intero > 0.
	 * @postcondition L'articolo corrispondente all' articoloId presente nel carrello ha una quantit‡ pari a "quantit‡".
	 */
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) {
		try {
			
			//Per controllare che la richiesta sia del tipo giusto
			if(!"XMLHttpRequest".equals(request.getHeader("X-Requested-With"))) {
				request.setAttribute("error", "Errore generato dalla richiesta! Se il problema persiste contattaci.");
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/welcome");
				dispatcher.forward(request, response);
				return;
			}
			
			response.setContentType("application/json");
			response.setHeader("Cache-Control", "no-cache");
			
			String idS = request.getParameter("articoloId");
			if(idS==null || idS.equals("")) {
				response.getWriter().write(new Gson().toJson("E' necessario fornire l'id dell'articolo di cui modificare la quantit‡."));
				return;
			}
			
			int articoloId=0;
			try {
				articoloId = Integer.parseInt(idS);
			} catch(Exception e) {
				response.getWriter().write(new Gson().toJson("Identificativo articolo non valido."));
				return;
			}
			if(articoloId==0) {
				response.getWriter().write(new Gson().toJson("Identificativo articolo non valido."));
				return;
			}
			
			String quantit‡S = request.getParameter("quantita");
			if(quantit‡S==null || quantit‡S.equals("")) {
				response.getWriter().write(new Gson().toJson("E' necessario fornire la quantit‡ dell'articolo nel carrello."));
				return;
			}
			
			int quantit‡=0;
			try {
				quantit‡ = Integer.parseInt(quantit‡S);
			} catch(Exception e) {
				response.getWriter().write(new Gson().toJson("Formato della quantit‡ non valido."));
				return;
			}
			if(quantit‡==0) {
				response.getWriter().write(new Gson().toJson("Formato della quantit‡ non valido."));
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
				response.getWriter().write(new Gson().toJson("L'identificativo fornito non corrisponde a nessun articolo nel carrello."));
				return;
			}
			
			carrello.setArticle(articolo, quantit‡);
			if(a != null) {
				acquirenteModel.updateCart((Acquirente)request.getSession().getAttribute("acquirente"));
			}
			response.getWriter().write(new Gson().toJson(""));
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
