package it.unisa.quattrocchi.control.gestione_utenti;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.unisa.quattrocchi.entity.Acquirente;
import it.unisa.quattrocchi.model.CreditCardModel;

@WebServlet("/rimuovere_carta")

public class RimuovereCarta extends HttpServlet{

	private static final long serialVersionUID = 1L;
	
	private CreditCardModel saModel = new CreditCardModel();
	
	/**
	 * 
	 * @precondition L'utente è loggato e l'id corrisponde ad una carta dell'utente.
	 * @postcondition L'id non corrisponde ad una carta dell'utente.
	 */
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) {

		try {
			String id = request.getParameter("id");
			Acquirente usr = (Acquirente) request.getSession().getAttribute("acquirente");
			
			if(usr!=null && id!=null && !id.equals("")) {
				
				saModel.deleteCreditCard(id);
				usr.removeCartAddress(id);
			}
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
