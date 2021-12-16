package Controlador;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Modelo.GestorSubastas;

/**
 * Servlet implementation class ServletVenta
 */
@WebServlet("/ServletVenta")
public class ServletVenta extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletVenta() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		ServletContext contexto = getServletContext();
		GestorSubastas gestor = (GestorSubastas) contexto.getAttribute("gestor");
		String cliente = (String) contexto.getAttribute("codcli");
		if(cliente == null) {
			RequestDispatcher vista = request.getRequestDispatcher("index.html");
			vista.forward(request, response);
		}else {
			String codart = (String) request.getParameter("codart");
			String respuesta = gestor.vende(cliente, codart);
			if(respuesta == null) {
				respuesta = "";
			}
			else {
				String[] articulo = respuesta.split("#");
				request.setAttribute("codart", codart);
				request.setAttribute("descr", articulo[2]);
				int puja = Integer.parseInt(articulo[5]);
				request.setAttribute("puja", puja);
				request.setAttribute("pujador", articulo[4]);
			}
			request.setAttribute("operacionRealizada", respuesta);
			RequestDispatcher vista = request.getRequestDispatcher("venta.jsp");
			vista.forward(request, response);
		}
	}
}
