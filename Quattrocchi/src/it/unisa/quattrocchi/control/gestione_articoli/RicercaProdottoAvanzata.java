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
	 * @precondition La richiesta è asincrona.
	 * if(toSearch!=null) !toSearch.matches("[A-Za-z0-9 ]{1,20}")
	 * if(marca!=null) !marca.matches("[A-Za-z0-9 ]{1,20}")
	 * if(prezzoMinS!=null) !prezzoMinS.matches("[0-9]{1,5}")
	 * if(prezzoMaxS!=null) !prezzoMaxS.matches("[0-9]{1,5}")
	 * if(prezzoMinS!=null && prezzoMaxS!=null) Integer.parseInt(prezzoMinS)<=Integer.parseInt(prezzoMaxS)
	 * if(colore!=null) !colore.matches("[A-Za-z0-9 ]{1,20}")
	 * Esiste almeno un articolo in database che corrisponde ai parametri non vuoti.
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
			if(toSearch == null || toSearch.equals("")) {
				toSearch = "";
			} else if(!toSearch.matches("[A-Za-z0-9 ]{1,20}")) {
				response.getWriter().write(new Gson().toJson("Formato parametri non valido."));
				return;
			}

			String marca = request.getParameter("marca");
			if(marca == null || marca.equals("")) {
				marca = "";
			} else if(!marca.matches("[A-Za-z0-9 ]{1,20}")) {
				response.getWriter().write(new Gson().toJson("Formato parametri non valido."));
				return;
			}

			String prezzoMinS = request.getParameter("prezzoMin");
			int prezzoMin;
			if(prezzoMinS==null || prezzoMinS.equals("")) {
				prezzoMin = 0;
			} else if(!prezzoMinS.matches("[0-9]{1,5}")){
				response.getWriter().write(new Gson().toJson("Formato parametri non valido."));
				return;
			} else {
				prezzoMin = Integer.parseInt(prezzoMinS);
			}

			String prezzoMaxS = request.getParameter("prezzoMax");
			int prezzoMax;
			if(prezzoMaxS==null || prezzoMaxS.equals("")) {
				prezzoMax = 9999999;
			} else if(!prezzoMaxS.matches("[0-9]{1,5}")){
				response.getWriter().write(new Gson().toJson("Formato parametri non valido."));
				return;
			} else {
				prezzoMax = Integer.parseInt(prezzoMaxS);
			}

			if(prezzoMin>prezzoMax) {
				response.getWriter().write(new Gson().toJson("Formato parametri non valido."));
				return;
			}

			String colore = request.getParameter("colore");
			if(colore == null || colore.equals("")) {
				colore = "";
			} else if(!colore.matches("[A-Za-z0-9 ]{1,20}")) {
				response.getWriter().write(new Gson().toJson("Formato parametri non valido."));
				return;
			}

			List<ArticoloInStock> articoli = model.doRetrieveAdvancedSearch(toSearch, marca, prezzoMin, prezzoMax, colore);
			if(articoli==null || articoli.size()==0) {
				response.getWriter().write(new Gson().toJson("Nessun articolo trovato."));
				return;
			}

			response.getWriter().write(new Gson().toJson(articoli));
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
