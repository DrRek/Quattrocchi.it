package it.unisa.quattrocchi.control.gestione_articoli;

import java.io.IOException;

import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import it.unisa.quattrocchi.model.ArticoloInStockModel;

@WebServlet("/ricerca_prodotto")

public class RicercaProdotto extends HttpServlet{

	private static final long serialVersionUID = 1L;

	static ArticoloInStockModel model = new ArticoloInStockModel();

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) {

		try {
			String action = request.getParameter("action");
			if(action!=null && action.equalsIgnoreCase("search")) {
				String toSearch = request.getParameter("toSearch");
				response.setContentType("application/json");
				if(toSearch!=null && !toSearch.equalsIgnoreCase("")) {
					response.getWriter().write(new Gson().toJson(model.doRetrieveSimpleSearch(toSearch)));
				}
				return;
			}

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
