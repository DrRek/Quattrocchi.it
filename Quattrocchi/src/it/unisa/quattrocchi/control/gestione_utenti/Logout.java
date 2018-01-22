package it.unisa.quattrocchi.control.gestione_utenti;

import javax.servlet.RequestDispatcher;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;

@WebServlet("/logout")

public class Logout extends HttpServlet{

	private static final long serialVersionUID = 1L;
	
	/**
	 * 
	 * @precondition	La richiesta � sincrona.
	 * @postcondition La sessione � invalidata.
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
			
			HttpSession session = request.getSession(false);
			if(session!=null)
				session.invalidate();
	
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/welcome");
			dispatcher.forward(request, response);
		} catch (Exception e) {
			System.out.println("Errore in logout:");
			e.printStackTrace();
		}
			
	}
	
	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) {
		doGet(request, response);
		return;
	}
}
