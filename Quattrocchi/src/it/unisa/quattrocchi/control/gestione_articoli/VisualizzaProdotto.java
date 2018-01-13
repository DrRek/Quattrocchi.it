package it.unisa.quattrocchi.control.gestione_articoli;

import javax.servlet.RequestDispatcher;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.unisa.quattrocchi.entity.ArticoloInStock;
import it.unisa.quattrocchi.model.ArticoloInStockModel;

@WebServlet("/visualizza_prodotto")

public class VisualizzaProdotto extends HttpServlet{

	private static final long serialVersionUID = 1L;
	
	static ArticoloInStockModel articoloInStockModel = new ArticoloInStockModel();
	
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) {
		try {
			String id = request.getParameter("id");
			System.out.println(id);
			ArticoloInStock a = articoloInStockModel.doRetrieveByIdInStock(id);
			
			request.setAttribute("articolo", a);
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/web_pages/view/ArticlePageView.jsp");
			dispatcher.forward(request, response);
		} catch (Exception e) {
			System.out.println("Errore in Visualizza prodotto:");
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
