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
import javax.servlet.http.HttpSession;

import Modelo.GestorSubastas;

/**
 * Servlet implementation class ServletConsultaPuja
 */
@WebServlet("/ServletConsultaPuja")
public class ServletConsultaPuja extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletConsultaPuja() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		ServletContext contexto = getServletContext();
		HttpSession sesion = request.getSession();
		GestorSubastas gestor = (GestorSubastas) contexto.getAttribute("gestor");
		String cliente = (String) sesion.getAttribute("codcli");
		if(cliente == null) {
			RequestDispatcher vista = request.getRequestDispatcher("hgggggggf");
			vista.forward(request, response);
		}else {
		Vector<String> respuesta = gestor.consultaPujas(cliente);
		request.setAttribute("pujas", respuesta);
		request.setAttribute("codcli", cliente);
		RequestDispatcher vista = request.getRequestDispatcher("consultaPujas.jsp");
		vista.forward(request, response);
		}
	}

}
