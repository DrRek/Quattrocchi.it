package it.unisa.quattrocchi.control.gestione_articoli;


import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.google.gson.Gson;
import it.unisa.quattrocchi.model.ArticoloInStockModel;

@WebServlet("/ricerca_prodotto_avanzata")


/**
 * 
 * @author quattrocchi.it
 * Questa classe è una servlet che si occupa di gestire la ricerca avanzata di prodotti dal catalogo.
 */
public class RicercaProdottoAvanzata extends HttpServlet{

	private static final long serialVersionUID = 1L;

	static ArticoloInStockModel model = new ArticoloInStockModel();
	
	
	/**
	 * Questo metodo si occupa di effettuare la ricerca avanza di prodotti all'interno del catalogo
	 * utilizzando i vari parametri inseriti dall'utente nell'apposita form.
	 * 
	 * @precondition Esiste almeno un articolo in database che corrisponde ai parametri non vuoti.
	 * @postcondition viene scritto in response una lista di articoli non vuota.
	 */
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) {

		try {
			response.setContentType("application/json");
			response.setHeader("Cache-Control", "no-cache");
			String toSearch, marca, colore;
			double prezzoMin, prezzoMax;
			
			toSearch = request.getParameter("toSearch");
			if(toSearch == null) {
				toSearch = "";
			}

			marca = request.getParameter("marca");
			if(marca == null) {
				marca = "";
			}

			String prezzoMinS = request.getParameter("prezzoMin");
			if(prezzoMinS!=null && !prezzoMinS.equals("")) {
				prezzoMin = Double.parseDouble(prezzoMinS);
			}
			else {
				prezzoMin = 0;
			}
			
			String prezzoMaxS = request.getParameter("prezzoMax");
			if(prezzoMaxS!=null && !prezzoMaxS.equals("")) {
				prezzoMax = Double.parseDouble(prezzoMaxS);
			}
			else {
				prezzoMax = 99999;
			}

			colore = request.getParameter("colore");
			if(colore == null) {
				colore = "";
			}
			
			response.getWriter().write(new Gson().toJson(model.doRetrieveAdvancedSearch(toSearch, marca, prezzoMin, prezzoMax, colore)));
		} catch (Exception e) {
			System.out.println("Errore in Ricerca prodotto avanzata:");
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
