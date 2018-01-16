package it.unisa.quattrocchi.control.gestione_articoli;

import javax.servlet.http.HttpServlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.google.gson.Gson;
import it.unisa.quattrocchi.model.ArticoloInStockModel;
import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;

@WebServlet("/visualizza_catalogo")


/**
 * 
 * @author quattrocchi.it
 * Questa classe è una servlet che si occupa di gestire la visualizzazione del catalogo dei prodotti.
 */
public class VisualizzaCatalogo extends HttpServlet{

	private static final long serialVersionUID = 1L;
	
	static ArticoloInStockModel articoloInStockModel = new ArticoloInStockModel();
	
	
	/**
	 * Questo metodo si occupa di effettuare la visualizzazione dell'intero catalogo dei prodotti.
	 */
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String action = request.getParameter("action");
		if(action!=null && action.equalsIgnoreCase("retrieveAll")) {
			try {
				response.setContentType("application/json");
				response.setHeader("Cache-Control", "no-cache");
				response.getWriter().write(new Gson().toJson(articoloInStockModel.doRetrieveAllInStock()));
			} catch(SQLException e) {
				System.out.println("Errore in visualizza catalogo:");
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
