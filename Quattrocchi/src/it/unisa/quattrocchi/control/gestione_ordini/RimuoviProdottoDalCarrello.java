package it.unisa.quattrocchi.control.gestione_ordini;

import javax.servlet.RequestDispatcher;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.unisa.quattrocchi.entity.Acquirente;
import it.unisa.quattrocchi.entity.ArticoloInStock;
import it.unisa.quattrocchi.entity.Cart;
import it.unisa.quattrocchi.model.ArticoloInStockModel;


@WebServlet("/rimuovi_dal_carrello")

public class RimuoviProdottoDalCarrello extends HttpServlet{
	
	private static final long serialVersionUID = 1L;
	
	static ArticoloInStockModel articoloInStockModel = new ArticoloInStockModel();

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) {
		try {
			String articoloId = request.getParameter("articoloId");
			Cart carrello = ((Acquirente)request.getSession().getAttribute("acquirente")).getCart();
			
			ArticoloInStock articolo;
			articolo = articoloInStockModel.doRetrieveByIdInStock(articoloId);
			carrello.removeArticle(articolo);
			
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/web_pages/view/CheckoutView.jsp");
			dispatcher.forward(request, response);
		} catch (Exception e) {
			System.out.println("Errore in aggiungi prodotto al carrello");
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
