package it.unisa.quattrocchi.control.gestione_articoli;

import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.google.gson.Gson;

import it.unisa.quattrocchi.entity.ArticoloInStock;
import it.unisa.quattrocchi.model.ArticoloInStockModel;

@WebServlet("/ricerca_prodotto")


/**
 * 
 * @author quattrocchi.it
 * Questa classe è una servlet che si occupa di gestire la ricerca di articoli dal catalogo.
 */
public class RicercaProdotto extends HttpServlet{

	private static final long serialVersionUID = 1L;

	static ArticoloInStockModel model = new ArticoloInStockModel();


	/**
	 * Questo metodo si occupa di effettuare la ricerca dei prodotti all'interno del catalogo
	 * utilizzando la stringa inserita dall'utente nell'apposita barra di ricerca.
	 * 
	 * @precondition 	La richiesta è asincrona.
	 * 					toSearch==null || !toSearch.matches("[A-Za-z0-9 ]{1,20}").
	 * 					Esiste almeno un articolo in database che corrisponde alla ricerca.
	 * @postcondition Viene scritto in response una lista di articoli non vuota.
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
			
			String toSearch = request.getParameter("toSearch");
			if(toSearch==null || !toSearch.matches("[A-Za-z0-9 ]{1,20}")) {
				response.getWriter().write(new Gson().toJson("Formato parametri non valido."));
				return;
			}
			
			List<ArticoloInStock> articoli = model.doRetrieveSimpleSearch(toSearch);
			if(articoli==null || articoli.size()==0) {
				response.getWriter().write(new Gson().toJson("Nessun articolo trovato."));
				return;
			}
			
			response.getWriter().write(new Gson().toJson(articoli));
			return;

		}catch (Exception e) {
			System.out.println("Errore in Ricerca prodotto:");
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
