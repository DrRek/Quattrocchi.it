package it.unisa.quattrocchi.control.gestione_utenti;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.unisa.quattrocchi.entity.GestoreOrdini;

@WebServlet("/welcome")

public class Welcome extends HttpServlet{

	private static final long serialVersionUID = 1L;
	
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		GestoreOrdini gestore = (GestoreOrdini) request.getSession().getAttribute("gestoreOrdini");
		if(gestore != null) {
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/gestoreOrdini");
			dispatcher.forward(request, response);
			return;
		}
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/web_pages/view/index.jsp");
		dispatcher.forward(request, response);	
			
	}
	
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
		return;
	}
}

