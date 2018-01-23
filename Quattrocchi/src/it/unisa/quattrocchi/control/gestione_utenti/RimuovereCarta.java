package it.unisa.quattrocchi.control.gestione_utenti;

import javax.servlet.RequestDispatcher;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import it.unisa.quattrocchi.entity.Acquirente;
import it.unisa.quattrocchi.model.CreditCardModel;

@WebServlet("/rimuovere_carta")


/**
 * 
 * @author quattrocchi.it
 * Questa classe � una servlet che si occupa di gestire la rimozione di una carta di credito.
 */
public class RimuovereCarta extends HttpServlet{

	private static final long serialVersionUID = 1L;

	private CreditCardModel saModel = new CreditCardModel();

	/**
	 * 
	 * @precondition 	La richiesta � asincrona
	 * 					L'utente � loggato.
	 * 					L'id corrisponde ad una carta dell'utente.
	 * @postcondition L'id non corrisponde ad una carta dell'utente.
	 */
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) {
		try {

			//Per controllare che la richiesta sia del tipo giusto
			if(!"XMLHttpRequest".equals(request.getHeader("X-Requested-With"))) {
				request.setAttribute("error", "Errore generato dalla richiesta! Se il problema persiste contattaci.");
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/welcome");
				dispatcher.forward(request, response);
				return;
			}

			response.setContentType("application/json");
			response.setHeader("Cache-Control", "no-cache");

			String idS = request.getParameter("id");
			if(idS==null || idS.equals("")) {
				response.getWriter().write(new Gson().toJson("E' necessario fornire l'id della carta da cancellare."));
				return;
			}

			int id=0;
			try {
				id = Integer.parseInt(idS);
			} catch(Exception e) {
				response.getWriter().write(new Gson().toJson("Identificativo carta non valido."));
				return;
			}
			if(id==0) {
				response.getWriter().write(new Gson().toJson("Identificativo carta non valido."));
				return;
			}

			Acquirente usr = (Acquirente) request.getSession().getAttribute("acquirente");
			if(usr==null) {
				response.getWriter().write(new Gson().toJson("E' necessario effettuare prima il login."));
				return;
			}

			if(usr.removeCartAddress(id)) {
				saModel.deleteCreditCard(id);
			}
			response.getWriter().write(new Gson().toJson(""));

		} catch(Exception e) {
			System.out.println("Errore in Inserisci indirizzo:");
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
