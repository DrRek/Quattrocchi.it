package it.unisa.quattrocchi.control.gestione_utenti;

import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import javax.servlet.RequestDispatcher;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import it.unisa.quattrocchi.entity.Acquirente;
import it.unisa.quattrocchi.entity.CreditCard;
import it.unisa.quattrocchi.model.CreditCardModel;

@WebServlet("/inserisci_carta")

/**
 * 
 * @author quattrocchi.it
 * Questa classe è una servlet che si occupa di gestire l'inserimento di una carta di credito.
 */
public class InserireCarta  extends HttpServlet{

	private static final long serialVersionUID = 1L;
	
	private CreditCardModel ccModel = new CreditCardModel();
	
	/**
	 * 
	 * @precondition	La richiesta è asincrona
	 * 					L'utente è loggato
	 * 					numcc!=null && numcc.matches("[0-9]{16}"),
	 * 					intestatario != null && intestatario.matches("[A-Za-z0-9 ]{4,40}"),
	 * 					circuito != null && circuito.matches("[A-Za-z ]{4,20}"),
	 * 					scadenza != null && scandenza.matches("[0-9]{2}[/]{1}[0-9]{4}"),
	 * 					cvv != null && cvv.matches("[0-9]{3}")
	 * @postcondition Il numero di carta di credito associate all'utente loggato è incrementato di 1.
	 */
	@Override
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
			
			String numcc = request.getParameter("numcc");
			if(numcc==null || !numcc.matches("[0-9]{16}")) {
				response.getWriter().write(new Gson().toJson("Formato carta non valido."));
				return;
			}
			
			String intestatario = request.getParameter("intestatario");
			if(intestatario==null || !intestatario.matches("[A-Za-z0-9 ]{4,40}")) {
				response.getWriter().write(new Gson().toJson("Formato intestatario non valido."));
				return;
			}
			
			String circuito = request.getParameter("circuito");
			if(circuito==null || !circuito.matches("[A-Za-z ]{4,20}")) {
				response.getWriter().write(new Gson().toJson("Formato circuito non valido."));
				return;
			}
			
			String scadenza = request.getParameter("scadenza");
			if(scadenza==null || !scadenza.matches("[0-9]{2}[/]{1}[0-9]{4}")) {
				response.getWriter().write(new Gson().toJson("Formato scadenza non valido."));
				return;
			}
			
			String cvv = request.getParameter("cvv");
			if(cvv==null || !cvv.matches("[0-9]{3}")) {
				response.getWriter().write(new Gson().toJson("Formato cvv non valido."));
				return;
			}
			
			Date scadenzaDate;
			try {
				DateFormat df = new SimpleDateFormat("MM/yyyy");
				scadenzaDate = df.parse(scadenza);
			} catch(Exception e) {
				response.getWriter().write(new Gson().toJson("Formato scadenza non valido."));
				return;
			}
			
			CreditCard cc = new CreditCard(numcc, intestatario, circuito, scadenzaDate, Integer.parseInt(cvv), usr);
			usr.addCreditCard(cc);
			ccModel.createCreditCard(cc);
			response.getWriter().write(new Gson().toJson(cc.getIdCarta()));
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
