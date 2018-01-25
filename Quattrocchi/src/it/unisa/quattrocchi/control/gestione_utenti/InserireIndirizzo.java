package it.unisa.quattrocchi.control.gestione_utenti;

import javax.servlet.RequestDispatcher;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import it.unisa.quattrocchi.entity.Acquirente;
import it.unisa.quattrocchi.entity.ShippingAddress;
import it.unisa.quattrocchi.model.ShippingAddressModel;

@WebServlet("/inserisci_indirizzo")

/**
 * 
 * @author quattrocchi.it
 * Questa classe è una servlet che si occupa di gestire l'inserimento di un indirizzo di spedizione.
 */
public class InserireIndirizzo extends HttpServlet{

	private static final long serialVersionUID = 1L;

	private ShippingAddressModel saModel = new ShippingAddressModel();

	@Override
	/**
	 * 
	 * @precondition 	La richiesta è asincrona
	 * 					L'utente è loggato
	 * 					indirizzo!=null && indirizzo.matches("[A-Za-z0-9 ]{5,40}"),
	 * 					civico != null && civico.matches("[0-9]{1,4}"),
	 * 					cap != null && cap.matches("[0-9]{5}"),
	 * 					provincia != null && provincia.matches("[A-Z]{2}"),
	 * 					stato != null && stato.matches("[A-Za-z ]{5,30}")
	 * @postcondition Il numero di indirizzi associati all'utente loggato è incrementato di 1.
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response) {

		try {

			//Per controllare che la richiesta sia del tipo giusto
			if(!"XMLHttpRequest".equals(request.getHeader("X-Requested-With"))) {
				request.setAttribute("notification", "Errore generato dalla richiesta! Se il problema persiste contattaci.");
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/welcome");
				dispatcher.forward(request, response);
				return;
			}

			response.setContentType("application/json");
			response.setHeader("Cache-Control", "no-cache");

			Acquirente usr = (Acquirente) request.getSession().getAttribute("acquirente");
			if(usr==null) {
				response.getWriter().write(new Gson().toJson("E' necessario essere loggati."));
				return;
			}

			String indirizzo = request.getParameter("indirizzo");
			if(indirizzo==null || !indirizzo.matches("[A-Za-z0-9 ]{5,40}")) {
				response.getWriter().write(new Gson().toJson("Formato indirizzo non valido."));
				return;
			}

			String civico = request.getParameter("civico");
			if(civico==null || !civico.matches("[0-9]{1,4}")) {
				response.getWriter().write(new Gson().toJson("Formato civico non valido."));
				return;
			}

			String cap = request.getParameter("cap");
			if(cap==null || !cap.matches("[0-9]{5}")) {
				response.getWriter().write(new Gson().toJson("Formato cap non valido."));
				return;
			}

			String provincia = request.getParameter("provincia");
			if(provincia==null || !provincia.matches("[A-Z]{2}")) {
				response.getWriter().write(new Gson().toJson("Formato provincia non valido."));
				return;
			}

			String stato = request.getParameter("stato");
			if(stato==null || !stato.matches("[A-Za-z ]{5,30}")) {
				response.getWriter().write(new Gson().toJson("Formato stato non valido."));
				return;
			}


			ShippingAddress sa = new ShippingAddress(stato, indirizzo, Integer.parseInt(cap), provincia, Integer.parseInt(civico), usr);
			usr.addShippingAddress(sa);
			saModel.createShippingAddress(sa);

			response.setContentType("application/json");
			response.getWriter().write(new Gson().toJson(sa.getCodice()));

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
