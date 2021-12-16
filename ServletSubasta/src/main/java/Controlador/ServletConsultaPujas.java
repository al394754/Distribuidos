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
 * Servlet implementation class ServletConsultaPujas
 */
@WebServlet("/ServletConsultaPujas")
public class ServletConsultaPujas extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletConsultaPujas() {
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
		String cliente = (String) contexto.getAttribute("codcli");
		if(cliente == null) {
			RequestDispatcher vista = request.getRequestDispatcher("index.html");
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
