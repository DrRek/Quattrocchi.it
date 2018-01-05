package it.unisa.quattrocchi.control.gestione_articoli;

import javax.servlet.http.HttpServlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import it.unisa.quattrocchi.entity.Article;
import it.unisa.quattrocchi.model.ArticleModel;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;

@WebServlet("/visualizza_catalogo")

public class VisualizzaCatalogo extends HttpServlet{

	static ArticleModel model = new ArticleModel();
	private static Logger logger = Logger.getLogger("classname");
	
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String action = request.getParameter("action");
		if(action!=null && action.equalsIgnoreCase("retrieveAll")) {
			try {
				response.setContentType("application/json");
				response.setHeader("Cache-Control", "no-cache");
				List<Article> mod = model.doRetrieveAllInStock();
				response.getWriter().write(new Gson().toJson(model.doRetrieveAllInStock()));
			} catch(SQLException e) {
				e.printStackTrace();
			}
			return;
		}
			
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/web_pages/view/ArticleView.jsp");
		dispatcher.forward(request, response);
		return;
	}
	
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
		return;
	}
}
