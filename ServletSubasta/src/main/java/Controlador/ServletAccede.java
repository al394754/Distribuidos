package Controlador;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Modelo.GestorSubastas;

/**
 * Servlet implementation class ServletAccede
 */
@WebServlet("/ServletAccede")
public class ServletAccede extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private GestorSubastas gestor;
	private ServletContext contexto;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletAccede() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		contexto = getServletContext();
        gestor = (GestorSubastas) contexto.getAttribute("gestor");
		if(gestor == null) {
			gestor = new GestorSubastas();
			contexto.setAttribute("gestor", gestor);
		}
		response.setContentType("text/html;charset=UTF-8");
		String codcli = (String) request.getParameter("codcli");
		contexto.setAttribute("codcli", codcli);
		request.setAttribute("codcli", codcli);
		RequestDispatcher vista = request.getRequestDispatcher("Menu.jsp");
		vista.forward(request, response);
		
	}


}