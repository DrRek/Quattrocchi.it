package it.unisa.quattrocchi.control.gestione_articoli;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import it.unisa.quattrocchi.model.ArticoloInStockModel;

@WebServlet("/visualizza_tutti")

public class RicercaTutti extends HttpServlet {

	private static final long serialVersionUID = 1L;

	static ArticoloInStockModel articoloInStockModel = new ArticoloInStockModel();


	/**
	 * Questo metodo si occupa di effettuare la visualizzazione dell'intero catalogo dei prodotti.
	 * 
	 * @postcondition Viene scritto in response una lista di articoli non vuota.
	 */
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response){
		try {
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
