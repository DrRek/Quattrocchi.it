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

@WebServlet("/aggiungi_al_carrello")


/**
 * 
 * @author quattrocchi.it
 * Questa classe è una servlet che si occupa di gestire l'aggiunta di un articolo al carrello.
 */
public class AggiungiProdottoAlCarrello extends HttpServlet{

	private static final long serialVersionUID = 1L;

	static ArticoloInStockModel articoloInStockModel = new ArticoloInStockModel();
	static AcquirenteModel acquirenteModel = new AcquirenteModel();

	/**
	 * Questo metodo si occupa di effettuare l'aggiunta di un articolo al carrello.
	 * @precondition 	La richiesta è asincrona.
	 * 					articoloId != null, è trasformabile in un intero e corrisponde ad un id di un articolo nel database.
	 * @postcondition cart.size() == cart.size()+1 
	 */
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) {
		try {

			//Per controllare che la richiesta sia del tipo giusto
			if(!"XMLHttpRequest".equals(request.getHeader("X-Requested-With"))) {
				request.setAttribute("notification", "Errore generato dalla richiesta! Se il problema persiste contattaci.");
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/welcome");
				dispatcher.forward(request, response);
				return;
			}

			response.setContentType("application/json");
			response.setHeader("Cache-Control", "no-cache");

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

			String idS = request.getParameter("articoloId");
			if(idS==null || idS.equals("")) {
				response.getWriter().write(new Gson().toJson("Necessario fornire identificativo del prodotto."));
				return;
			}

			int id=0;
			try {
				id = Integer.parseInt(idS);
			} catch(Exception e) {
				response.getWriter().write(new Gson().toJson("Formato identificativo del prodotto non valido."));
				return;
			}

			if(id!=0) {
				ArticoloInStock articolo;
				articolo = articoloInStockModel.doRetrieveByIdInStock(id);
				if(articolo==null) {
					response.getWriter().write(new Gson().toJson("Nessun articolo trovato."));
				}

				carrello.addArticle(articolo);
				if(a!= null) {
					acquirenteModel.updateCart(a);
				}
				response.getWriter().write(new Gson().toJson(""));
			}

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
