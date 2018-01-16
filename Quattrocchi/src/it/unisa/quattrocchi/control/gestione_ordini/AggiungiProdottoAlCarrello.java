package it.unisa.quattrocchi.control.gestione_ordini;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import it.unisa.quattrocchi.entity.Acquirente;
import it.unisa.quattrocchi.entity.ArticoloInStock;
import it.unisa.quattrocchi.entity.Cart;
import it.unisa.quattrocchi.model.AcquirenteModel;
import it.unisa.quattrocchi.model.ArticoloInStockModel;

@WebServlet("/aggiungi_al_carrello")


/**
 * 
 * @author quattrocchi.it
 * Questa classe è una servlet che si occupa di gestire l'aggiunta di un articolo al carrello.
 */
public class AggiungiProdottoAlCarrello extends HttpServlet{
	
	private static final long serialVersionUID = 1L;
	
	static ArticoloInStockModel articoloInStockModel = new ArticoloInStockModel();
	static AcquirenteModel acquirenteModel = new AcquirenteModel();

	/**
	 * Questo metodo si occupa di effettuare l'aggiunta di un articolo al carrello.
	 */
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) {
		try {
			String articoloId = request.getParameter("articoloId");
			Cart carrello = ((Acquirente)request.getSession().getAttribute("acquirente")).getCart();
			
			ArticoloInStock articolo;
			articolo = articoloInStockModel.doRetrieveByIdInStock(articoloId);
			carrello.addArticle(articolo);

			acquirenteModel.updateCart((Acquirente)request.getSession().getAttribute("acquirente"));
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
