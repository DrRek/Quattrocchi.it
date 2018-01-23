package it.unisa.quattrocchi.control.gestione_articoli;

import javax.servlet.RequestDispatcher;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import it.unisa.quattrocchi.model.ArticoloInStockModel;

@WebServlet("/visualizza_tutti")


/**
 * 
 * @author quattrocchi.it
 * Questa classe è una servlet che si occupa di gestire la ricerca di tutti i prodotti.
 */
public class RicercaTutti extends HttpServlet {

	private static final long serialVersionUID = 1L;

	static ArticoloInStockModel articoloInStockModel = new ArticoloInStockModel();


	/**
	 * Questo metodo si occupa di effettuare la visualizzazione dell'intero catalogo dei prodotti.
	 * 
	 * @precondition	La richiesta è asincrona.
	 * @postcondition Viene scritto in response una lista di articoli non vuota.
	 */
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response){
		try {
			if(!"XMLHttpRequest".equals(request.getHeader("X-Requested-With"))) {
				request.setAttribute("error", "Errore generato dalla richiesta! Se il problema persiste contattaci.");
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/welcome");
				dispatcher.forward(request, response);
				return;
			}
			
			response.setContentType("application/json");
			response.setHeader("Cache-Control", "no-cache");
			response.getWriter().write(new Gson().toJson(articoloInStockModel.doRetrieveAllInStock()));
		} catch(Exception e) {
			System.out.println("Errore in ricerca tutti:");
			e.printStackTrace();
		}
		return;
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response){
		doGet(request, response);
		return;
	}
}
