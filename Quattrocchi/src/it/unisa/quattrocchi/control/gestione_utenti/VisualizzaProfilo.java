package it.unisa.quattrocchi.control.gestione_utenti;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import it.unisa.quattrocchi.entity.Acquirente;

@WebServlet("/profilo")

public class VisualizzaProfilo extends HttpServlet{

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/web_pages/view/UserView.jsp");
		dispatcher.forward(request, response);
		return;
	}
	
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
		return;
	}
}
