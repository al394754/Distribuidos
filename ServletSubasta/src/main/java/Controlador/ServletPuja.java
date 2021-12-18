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
 * Servlet implementation class ServletPuja
 */
@WebServlet("/ServletPuja")
public class ServletPuja extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServletPuja() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPut(HttpServletRequest, HttpServletResponse)
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
			String codart = (String) request.getParameter("codart");
			int puja = Integer.parseInt(request.getParameter("puja"));
			String respuesta = gestor.puja(cliente, codart, puja);
			if(respuesta == null) {
				respuesta = "";
			}
			else {
				String[] articulo = respuesta.split("#");
				request.setAttribute("codart", codart);
				request.setAttribute("descr", articulo[2]);
				request.setAttribute("puja", puja);
				request.setAttribute("pujador", cliente);
				request.setAttribute("vendedor", articulo[3]);
			}
			request.setAttribute("operacionRealizada", respuesta);
			RequestDispatcher vista = request.getRequestDispatcher("puja.jsp");
			vista.forward(request, response);
		}
	}

}
