package it.unisa.quattrocchi.control.gestione_articoli;

import javax.servlet.RequestDispatcher;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import it.unisa.quattrocchi.entity.ArticoloInStock;
import it.unisa.quattrocchi.model.ArticoloInStockModel;

@WebServlet("/visualizza_prodotto")

/**
 * 
 * @author quattrocchi.it
 * Questa classe è una servlet che si occupa di gestire la visualizzazione della scheda di un prodotto.
 * Prende l'id di tipo <strong>String</strong> dell'articolo dalla request di tipo <strong>HttpServletRequest</strong>,
 * chiama il metodo <strong>doRetrieveByIdInStock</strong> per controllare se il prodtto è presente nel databse,
 * esegue il dispatch della pagina.
 */
public class VisualizzaProdotto extends HttpServlet{

	private static final long serialVersionUID = 1L;
	
	static ArticoloInStockModel articoloInStockModel = new ArticoloInStockModel();
	
	/**
	 * Questo metodo si occupa di far visualizzare la scheda di un prodotto.
	 * 
	 * @precondition 	La richiesta è sincrona.
	 * 					idS!=null, idS è trasformabile in un intero e l'id corrisponde ad un articolo presente nel database.
	 * @postcondition Viene inserito in response un articolo.
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
			
			String idS = request.getParameter("id");
			if(idS==null || idS.equals("")) {
				request.setAttribute("notification", "Necessario fornire identificativo del prodotto.");
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/visualizza_catalogo");
				dispatcher.forward(request, response);
				return;
			}
			
			int id=0;
			try {
				id = Integer.parseInt(idS);
			} catch(Exception e) {
				request.setAttribute("notification", "Formato identificativo del prodotto non valido.");
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/visualizza_catalogo");
				dispatcher.forward(request, response);
				return;
			}

			if(id!=0) {
				ArticoloInStock a = articoloInStockModel.doRetrieveByIdInStock(id);
				if(a==null) {
					request.setAttribute("notification", "Nessun articolo trovato.");
					RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/visualizza_catalogo");
					dispatcher.forward(request, response);
					return;
				}
				
				request.setAttribute("articolo", a);
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/web_pages/view/ArticlePageView.jsp");
				dispatcher.forward(request, response);
			}
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
