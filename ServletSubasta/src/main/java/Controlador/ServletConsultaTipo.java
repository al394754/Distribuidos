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
import javax.servlet.http.HttpSessionContext;

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
		HttpSession sesion = request.getSession();
		//String cliente = (String) contexto.getAttribute("codcli");
		String cliente = (String) sesion.getAttribute("codcli");
		if(cliente == null) {
			RequestDispatcher vista = request.getRequestDispatcher("index.html");
			vista.forward(request, response);
		}else {
			GestorSubastas gestor = (GestorSubastas) contexto.getAttribute("gestor");
			String tipo = (String) request.getParameter("tipo");
			Vector<String> respuesta = gestor.consultaTipo(tipo);
			switch(tipo) {
				case("LI"):
					request.setAttribute("tipo", "Libros y cómics");
					break;
				case("EL"):
					request.setAttribute("tipo", "Electrónica e Informática");
					break;
				case("RO"):
					request.setAttribute("tipo", "Ropa y complementos");
					break;
				case("OT"):
					request.setAttribute("tipo", "Otros");
					break;
			}
			request.setAttribute("articulosDeUnTipo", respuesta);
			RequestDispatcher vista = request.getRequestDispatcher("consultaTipo.jsp");
			vista.forward(request, response);
		}
	}

}
