package it.unisa.quattrocchi.control.errore;
import javax.servlet.RequestDispatcher;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

@WebServlet("/notfound")


/**
 * 
 * @author quattrocchi.it
 * Questa classe è una servlet che si occupa di gestire la pagina 404 NotFound.
 */
public class NotFound extends HttpServlet{

	private static final long serialVersionUID = 1L;
	
	/**
	 * 
	 * @precondition la richiesta è sincrona
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
			
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher("/web_pages/view/NotFound.jsp");
			dispatcher.forward(request, response);
		} catch(Exception e) {
			System.out.println("Errore in 404 page:");
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