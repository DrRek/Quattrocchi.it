package it.unisa.quattrocchi.control.gestione_utenti;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import it.unisa.quattrocchi.entity.Acquirente;
import it.unisa.quattrocchi.entity.ShippingAddress;
import it.unisa.quattrocchi.model.ShippingAddressModel;

@WebServlet("/inserisci_indirizzo")

public class InserireIndirizzo extends HttpServlet{

	private static final long serialVersionUID = 1L;
	
	private ShippingAddressModel saModel = new ShippingAddressModel();
	
	@Override
	/**
	 * 
	 * @precondition l'utente � loggato, indirizzo != null, civico != null, cap!= null,
	 * 				provincia != null, stato != null.
	 * @postcondition Il numero di indirizzi associati all'utente loggato � incrementato di 1.
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response) {

		try {
			String indirizzo = request.getParameter("indirizzo");
			String civico = request.getParameter("civico");
			String cap = request.getParameter("cap");
			String provincia = request.getParameter("provincia");
			String stato = request.getParameter("stato");
			Acquirente usr = (Acquirente) request.getSession().getAttribute("acquirente");
			
			if(usr!=null && indirizzo!=null && !indirizzo.equals("") && civico!=null && !civico.equals("") && cap!=null && !cap.equals("") && provincia!=null && !provincia.equals("") && stato!=null && !stato.equals("")) {
				ShippingAddress sa = new ShippingAddress(stato, indirizzo, Integer.parseInt(cap), provincia, Integer.parseInt(civico), usr);
				usr.addShippingAddress(sa);
				sa.setCodice(saModel.createShippingAddress(sa));

				response.setContentType("application/json");
				response.getWriter().write(new Gson().toJson(sa.getCodice()));
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
