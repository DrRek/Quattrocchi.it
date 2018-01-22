package it.unisa.quattrocchi.control.gestione_utenti;

import javax.servlet.RequestDispatcher;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

@WebServlet("/login")

public class VisualizzaLogin extends HttpServlet {

	private static final long serialVersionUID = 1L;

	/**
	 * 
	 * @precondition 	La richiesta è sincrona.
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
			
			request.setAttribute("error", "Username o password non valida.");
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/web_pages/view/AccessView.jsp");
			dispatcher.forward(request, response);
			return;
		} catch(Exception e) {
			System.out.println("Errore in visualizza login.");
			e.printStackTrace();
		}
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) {
		doGet(request, response);
		return;
	}
}
