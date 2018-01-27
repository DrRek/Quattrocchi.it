package it.unisa.quattrocchi.control.gestione_articoli;

import javax.servlet.http.HttpServlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import it.unisa.quattrocchi.model.ArticoloInStockModel;
import javax.servlet.RequestDispatcher;
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
	 * 
	 * @precondition	La richiesta è sincrona.
	 * 					La richiesta non è effettuata da un gestore.
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
			
			if(request.getSession().getAttribute("gestoreOrdini") != null) {
				request.setAttribute("notification", "Sei un gestore, è necessario che tu faccia il logout prima di poter utilizzare il sito come utente normale.");
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/welcome");
				dispatcher.forward(request, response);
				return;
			}
			
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/web_pages/view/ArticleView.jsp");
			dispatcher.forward(request, response);
		} catch(Exception e) {
			System.out.println("Errore in visualizza catalogo");
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
