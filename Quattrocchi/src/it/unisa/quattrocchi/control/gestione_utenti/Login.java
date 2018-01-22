package it.unisa.quattrocchi.control.gestione_utenti;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import it.unisa.quattrocchi.entity.Acquirente;
import it.unisa.quattrocchi.entity.Cart;
import it.unisa.quattrocchi.entity.GestoreOrdini;
import it.unisa.quattrocchi.model.AcquirenteModel;
import it.unisa.quattrocchi.model.CreditCardModel;
import it.unisa.quattrocchi.model.GestoreOrdiniModel;
import it.unisa.quattrocchi.model.ShippingAddressModel;


@WebServlet("/access")

public class Login extends HttpServlet{

	private static final long serialVersionUID = 1L;
	private static AcquirenteModel aModel = new AcquirenteModel();
	private static GestoreOrdiniModel gModel = new GestoreOrdiniModel();
	private static CreditCardModel ccModel = new CreditCardModel();
	private static ShippingAddressModel saModel = new ShippingAddressModel();

	/**
	 * 
	 * @precondition 	La richiesta è sincrona.
	 * 					userid != null, passid != null, l'username è presente nel database e la password corrisponde a quella associata all'username nel database.
	 * @postcondition L'utente è loggato.
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

			String userid = request.getParameter("userid");
			if(userid==null || !userid.matches("[A-Za-z0-9]{5,15}")) {
				request.setAttribute("error", "Username o password errati.");
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/login");
				dispatcher.forward(request, response);
				return;
			}

			String passid = request.getParameter("passid");
			if(passid==null || !passid.matches("[A-Za-z0-9]{5,15}")) {
				request.setAttribute("error", "Username o password errati.");
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/login");
				dispatcher.forward(request, response);
				return;
			}

			Acquirente acquirente;
			GestoreOrdini gestoreOrdini;

			gestoreOrdini = gModel.checkLogin(userid, passid);
			request.getSession().setAttribute("gestoreOrdini", gestoreOrdini);
			if(gestoreOrdini != null) {
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/gestoreOrdini");
				dispatcher.forward(request, response);
				return;
			}

			acquirente = aModel.checkLogin(userid, passid);
			if(acquirente == null) {
				request.setAttribute("error", "Username o password errati.");
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/login");
				dispatcher.forward(request, response);
				return;
			}

			acquirente.setCc(ccModel.doRetrieveByUser(userid));
			acquirente.setShipAdd(saModel.doRetrieveByUser(userid));
			acquirente.setCart(aModel.doRetrieveCartByUser(userid));

			if(request.getSession().getAttribute("carrello") != null) {
				acquirente.getCart().mergeCart((Cart)request.getSession().getAttribute("carrello"));
				request.getSession().setAttribute("carrello", null);
				aModel.updateCart(acquirente);
			}
			request.getSession().invalidate();
			request.getSession().setAttribute("acquirente", acquirente);
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/welcome");
			dispatcher.forward(request, response);	
		} catch(Exception e) {
			System.out.println("Errore in login");
			e.printStackTrace();
		}
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
		return;
	}
}
