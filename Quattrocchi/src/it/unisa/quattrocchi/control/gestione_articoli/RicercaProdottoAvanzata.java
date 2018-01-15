package it.unisa.quattrocchi.control.gestione_articoli;


import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.google.gson.Gson;
import it.unisa.quattrocchi.model.ArticoloInStockModel;

@WebServlet("/ricerca_prodotto_avanzata")

public class RicercaProdottoAvanzata extends HttpServlet{

	private static final long serialVersionUID = 1L;

	static ArticoloInStockModel model = new ArticoloInStockModel();
	
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

			if(!request.getParameter("prezzoMin").equals("")) {
				prezzoMin = Double.parseDouble(request.getParameter("prezzoMin"));
			}
			else {
				prezzoMin = 0;
			}
			
			if(!request.getParameter("prezzoMax").equals("")) {
				prezzoMax = Double.parseDouble(request.getParameter("prezzoMax"));
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
