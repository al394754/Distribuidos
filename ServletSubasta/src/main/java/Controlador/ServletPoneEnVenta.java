package Controlador;

import java.io.IOException;


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
 * Servlet implementation class ServletPoneEnVenta
 */
@WebServlet("/ServletPoneEnVenta")
public class ServletPoneEnVenta extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletPoneEnVenta() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		ServletContext contexto = getServletContext();
		GestorSubastas gestor = (GestorSubastas) contexto.getAttribute("gestor");
		HttpSession sesion = request.getSession();
		String cliente = (String) sesion.getAttribute("codcli");
		if(cliente == null) {
			RequestDispatcher vista = request.getRequestDispatcher("index.html");
			vista.forward(request, response);
		}else {
			String tipo = (String) request.getParameter("tipo");
			String descr = (String) request.getParameter("descr");
			int precio = Integer.parseInt(request.getParameter("precio"));
			String respuesta = gestor.poneEnVenta(cliente, tipo, descr, precio);
			request.setAttribute("codcli", cliente);
			request.setAttribute("venta", respuesta);
			RequestDispatcher vista = request.getRequestDispatcher("poneEnVenta.jsp");
			vista.forward(request, response);
		}
	}

}
