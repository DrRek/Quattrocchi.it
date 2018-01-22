package it.unisa.quattrocchi.control.gestione_utenti;

import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.unisa.quattrocchi.entity.Acquirente;
import it.unisa.quattrocchi.entity.CreditCard;
import it.unisa.quattrocchi.model.CreditCardModel;

@WebServlet("/inserisci_carta")

public class InserireCarta  extends HttpServlet{

	private static final long serialVersionUID = 1L;
	
	private CreditCardModel ccModel = new CreditCardModel();
	
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) {

		try {
			String numcc = request.getParameter("numcc");
			String intestatario = request.getParameter("intestatario");
			String circuito = request.getParameter("circuito");
			String scadenza = request.getParameter("scadenza");
			String cvv = request.getParameter("cvv");
			Acquirente usr = (Acquirente) request.getSession().getAttribute("acquirente");
			
			if(usr!=null && numcc!=null && !numcc.equals("") && intestatario!=null && !intestatario.equals("") && circuito!=null && !circuito.equals("") && scadenza!=null && !scadenza.equals("") && cvv!=null && !cvv.equals("")) {
				DateFormat df = new SimpleDateFormat("MM/yyyy");
				Date scadenzaDate = df.parse(scadenza);
				
				CreditCard cc = new CreditCard(numcc, intestatario, circuito, scadenzaDate, Integer.parseInt(cvv), usr);
				
				usr.addCreditCard(cc);
				ccModel.createCreditCard(cc);
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
