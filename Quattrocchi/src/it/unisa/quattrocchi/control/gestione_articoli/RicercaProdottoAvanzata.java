package it.unisa.quattrocchi.control.gestione_articoli;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import it.unisa.quattrocchi.model.ArticleModel;

@WebServlet("/ricerca_prodotto_avanzata")

public class RicercaProdottoAvanzata extends HttpServlet{

	private static final long serialVersionUID = 1L;

	static ArticleModel model = new ArticleModel();
	
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) {

		try {
			response.setContentType("application/json");
			response.setHeader("Cache-Control", "no-cache");
			String toSearch, marca, colore;
			double prezzoMin, prezzoMax;
			
			
			if(request.getParameter("toSearch") != null) {
				toSearch = request.getParameter("toSearch");
			}
			else {
				toSearch = "";
			}
			
			if(request.getParameter("marca") != null) {
				marca = request.getParameter("marca");
			}
			else {
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
			
			if(request.getParameter("colore") != null) {
				colore = request.getParameter("colore");
			}
			else {
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
