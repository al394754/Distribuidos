package Controlador;

import java.io.IOException;

import java.util.Vector;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Modelo.GestorSubastas;

/**
 * Servlet implementation class consultaPuja
 */
@WebServlet("/ServletConsultaTipo")
public class ServletConsultaTipo extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletConsultaTipo() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		ServletContext contexto = getServletContext();
		GestorSubastas gestor = (GestorSubastas) contexto.getAttribute("gestor");
		Vector<String> respuesta = gestor.consultaTipo(request.getParameter("tipo"));
		request.setAttribute("articulosDeUnTipo", respuesta);
		RequestDispatcher vista = request.getRequestDispatcher("consultaTipo.jsp");
		vista.forward(request, response);
	}

}
