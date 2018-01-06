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

import it.unisa.quattrocchi.entity.Article;
import it.unisa.quattrocchi.model.ArticleModel;

@WebServlet("/ricerca_prodotto")

public class RicercaProdotto extends HttpServlet{
	
	private static final long serialVersionUID = 1L;
	
	static ArticleModel model = new ArticleModel();

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String action = request.getParameter("action");
		if(action!=null && action.equalsIgnoreCase("search")) {
			try {
				String toSearch = request.getParameter("toSearch");
				response.setContentType("application/json");
				response.setHeader("Cache-Control", "no-cache");
				if(toSearch!=null && !toSearch.equalsIgnoreCase("")) {
					response.getWriter().write(new Gson().toJson(model.doRetrieveSimpleSearch(toSearch)));
				} else {
					response.getWriter().write(new Gson().toJson(model.doRetrieveAllInStock()));
				}
			} catch(SQLException e) {
				e.printStackTrace();
			}
			return;
		}

		request.setAttribute("toSearch", request.getParameter("toSearch"));
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/web_pages/view/ArticleView.jsp");
		dispatcher.forward(request, response);
		return;
	}
	
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		doGet(request, response);
		return;
	}
}
