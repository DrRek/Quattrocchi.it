package it.unisa.quattrocchi.control.gestione_articoli;

import javax.servlet.RequestDispatcher;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.unisa.quattrocchi.model.ArticoloInStockModel;

@WebServlet("/ricerca_da_pagina_esterna")

public class RicercaProdottoDaPaginaEsterna extends HttpServlet {

	private static final long serialVersionUID = 1L;

	static ArticoloInStockModel model = new ArticoloInStockModel();

	
	/**
	 * TODO
	 */
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) {

		try {
			request.setAttribute("toSearch", request.getParameter("toSearch"));
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
