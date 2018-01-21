package it.unisa.quattrocchi.control.gestione_ordini;

import java.io.IOException;
import java.util.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import it.unisa.quattrocchi.entity.Order;
import it.unisa.quattrocchi.model.OrderModel;

@WebServlet("/inserisciDatiDiSpedizione")


/**
 * 
 * @author quattrocchi.it
 * Questa classe � una servlet che si occupa di gestire l'aggiunta del numero
 * di tracking e la data della consegna relativa all'ordine da gestire.
 */
public class InserisciDatiDiSpedizione extends HttpServlet{

	private static final long serialVersionUID = 1L;
	static OrderModel orderModel = new OrderModel();

	
	/**
	 * Questo metodo si occupa di effetttuare l'aggiunta del numero di tracking,
	 * la data della consegna e dello stato relativi all'ordine da gestire.
	 */
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		try {
			int orderId = Integer.parseInt(request.getParameter("ordineId"));
			String corriere = request.getParameter("corriere");
			String tracking = request.getParameter("tracking");
			String statoOrdine = request.getParameter("statoOrdine");
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
			String strDate = request.getParameter("dataDiConsegna");
		    Date parsed = format.parse(strDate);
		    java.sql.Date dataConsegna = new java.sql.Date(parsed.getTime());
		    
			if(orderId == 0 || 
					corriere == null || !(corriere.matches("[A-Za-z]{3,10}")) || 
					tracking == null || !(tracking.matches("[A-Za-z0-9]{5,15}")) ||
					statoOrdine == null || (!(statoOrdine.equals("Da spedire")) && !(statoOrdine.equals("In corso")) && !(statoOrdine.equals("consegnato")))) {
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/web_pages/view/GestioneOrdine.jsp");
				dispatcher.forward(request, response);
				return;
			}
			
			Order orderToUpdate = orderModel.doRetrieveById(orderId);
			orderToUpdate.setCorriere(corriere);
			orderToUpdate.setNumeroTracking(tracking);
			orderToUpdate.setStatoOrdine(statoOrdine);
			orderToUpdate.setDataConsegna(dataConsegna);
			orderModel.updateOrder(orderToUpdate);
			request.getSession().setAttribute("ordini", orderModel.doRetrieveAll());			
		} catch (SQLException | ParseException e) {
			e.printStackTrace();
		}
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/web_pages/view/GestoreOrdiniView.jsp");
		dispatcher.forward(request, response);
		return;
	}
	
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		doGet(request, response);
		return;
	}
}
