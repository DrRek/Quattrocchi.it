package it.unisa.quattrocchi.control.gestione_utenti;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.unisa.quattrocchi.entity.Acquirente;
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

	public Login() {
		super();
	}

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String userid = request.getParameter("userid");
		String passid = request.getParameter("passid");
		Acquirente acquirente;
		GestoreOrdini gestoreOrdini;
		boolean isGestore = false;

		if(userid==null||userid.equalsIgnoreCase("")){
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/web_pages/view/AccessView.jsp");
			dispatcher.forward(request, response);
			return;
		}

		else {
			try {
				gestoreOrdini = gModel.checkLogin(userid, passid);
				request.getSession().setAttribute("gestoreOrdini", gestoreOrdini);
				if(gestoreOrdini != null) {
					RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/web_pages/view/GestoreOrdiniView.jsp");
					dispatcher.forward(request, response);
					return;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}

			if(!isGestore) {
				try {
					acquirente = aModel.checkLogin(userid, passid);
					if(acquirente != null) {
						acquirente.setCc(ccModel.doRetrieveByUser(userid));
						acquirente.setShipAdd(saModel.doRetrieveByUser(userid));
						acquirente.setCart(aModel.doRetrieveCartByUser(userid));
						request.getSession().setAttribute("acquirente", acquirente);
					} else {
						request.setAttribute("loginFailed", true);
						RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/web_pages/view/AccessView.jsp");
						dispatcher.forward(request, response);
						return;
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}

			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/web_pages/view/Index.jsp");
			dispatcher.forward(request, response);	

		}
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
		return;
	}
}
