package it.unisa.quattrocchi.control.gestione_articoli;

import javax.servlet.RequestDispatcher;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import it.unisa.quattrocchi.model.ArticoloInStockModel;

@WebServlet("/ricerca_da_pagina_esterna")


/**
 * 
 * @author quattrocchi.it
 * Questa classe è una servlet che si occupa di gestire la ricerca da una pagina esterna.
 */
public class RicercaProdottoDaPaginaEsterna extends HttpServlet {

	private static final long serialVersionUID = 1L;

	static ArticoloInStockModel model = new ArticoloInStockModel();

	
	/**
	 * TODO
	 * @precondition	La richiesta è sincrona.
	 * 					toSearch!=null && toSearch.matches("[A-Za-z0-9 ]{1,20}")
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
			
			String toSearch = (String) request.getParameter("toSearch");
			if(toSearch==null || !toSearch.matches("[A-Za-z0-9 ]{1,20}")) {
				request.setAttribute("error", "Formato parametro non valido.");
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/visualizza_catalogo");
				dispatcher.forward(request, response);
				return;
			}
			
			request.setAttribute("toSearch", toSearch);
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/web_pages/view/ArticleView.jsp");
			dispatcher.forward(request, response);
			
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
