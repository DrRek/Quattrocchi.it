package it.unisa.quattrocchi.control.gestione_utenti;

import javax.servlet.RequestDispatcher;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.unisa.quattrocchi.entity.GestoreOrdini;

@WebServlet("/welcome")

public class Welcome extends HttpServlet{

	private static final long serialVersionUID = 1L;
	
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) {
		try {
			GestoreOrdini gestore = (GestoreOrdini) request.getSession().getAttribute("gestoreOrdini");
			if(gestore != null) {
				RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/gestoreOrdini");
				dispatcher.forward(request, response);
				return;
			}
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/web_pages/view/Index.jsp");
			dispatcher.forward(request, response);
		} catch(Exception e) {
			System.out.println("Errore in welcome page:");
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

