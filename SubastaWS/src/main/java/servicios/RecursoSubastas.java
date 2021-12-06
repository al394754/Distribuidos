package servicios;

import java.io.BufferedReader;


import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URI;
import java.util.Map;
import java.util.Vector;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;

import modelo.Articulo;
import modelo.GestorSubastas;




@Path("subasta")
public class RecursoSubastas {
	private GestorSubastas gestor = new GestorSubastas();

	
	public RecursoSubastas() {
		System.out.println("construyo RecursoSubasta");
	}
	@GET
	@Path("{codcli}")
	@Produces("text/plain")
	public Response consultaPujas(@PathParam("codcli") String codcli) {
		ResponseBuilder builder;
		try {
			String respuesta = "";
			Vector<String> articulos = gestor.consultaPujas(codcli);
			if(articulos.size() == 0) {
				builder = Response.status(Response.Status.NOT_FOUND);
			    return builder.build();
			}
			if(articulos.size() == 1)
				respuesta = articulos.firstElement();
			else {
				for(int i = 0; i < articulos.size() - 1; i++) {  //Para asegurarme de no meter ; al final de la cadena
					respuesta += articulos.get(i) + ";";
				}
			}
			respuesta += articulos.lastElement();
			builder = Response.ok(respuesta);
		    return builder.build();
		}catch(Exception e) {
			builder = Response.status(Response.Status.INTERNAL_SERVER_ERROR);
		    return builder.build();
		}
	}
	
	@GET
	@Produces("text/plain")
	public Response consultaTipo(@QueryParam("tipo") String tipo) {
		ResponseBuilder builder;
		try {
			String respuesta = "";
			Vector<String> articulos = gestor.consultaTipo(tipo);
			if(articulos.size() == 0) {
				builder = Response.status(Response.Status.NOT_FOUND);
				return builder.build();
			}
			if(articulos.size() == 1)	//Este caso es por el bucle siguiente, ya que para un articulos i = 0 < 0, por tanto no entra
				respuesta = articulos.firstElement();
			else {
				for(int i = 0; i < articulos.size() - 1; i++) {  //Para asegurarme de no meter ; al final de la cadena
					respuesta += articulos.get(i) + ";";
				}
				respuesta += articulos.lastElement();	//Para asegurarme de no meter ; al final de la cadena
			}
			builder = Response.ok(respuesta);
			return builder.build();
		}catch(Exception e) {
			builder = Response.status(Response.Status.INTERNAL_SERVER_ERROR);
		    return builder.build();
		}
	}
	
	@POST		
	@Path("{codcli}")
	@Consumes("text/plain")
	public Response poneEnVenta(@PathParam("codcli") String codcli,  @QueryParam("tipo") String tipo, @QueryParam("descr") String descr, @QueryParam("precio") int precio) throws IOException {
		ResponseBuilder builder;
		try {
			String articuloEnVenta = gestor.poneEnVenta(codcli, tipo, descr, precio);
			if(articuloEnVenta.length() == 0) {
				builder = Response.status(Response.Status.NOT_FOUND);
			    return builder.build();
			}
			builder = Response.ok(articuloEnVenta);
		    return builder.build();
		}catch(Exception e) {
			builder = Response.status(Response.Status.INTERNAL_SERVER_ERROR);
		    return builder.build();
		}
	}
	
	
	@PUT
	@Path("{codcli}")
	public Response puja(@PathParam("codcli") String codcli, @QueryParam("codart") String codart, @QueryParam("puja") int puja) {
		ResponseBuilder builder;
		try {
			String respuesta = gestor.puja(codcli, codart, puja);
			if(respuesta == null) {
				builder = Response.status(Response.Status.NOT_FOUND);
			    return builder.build();
			}
			builder = Response.ok(respuesta);
		    return builder.build();
		}catch(Exception e) {
			builder = Response.status(Response.Status.INTERNAL_SERVER_ERROR);
		    return builder.build();			
		}
	}
	
	@DELETE
	@Path("{codcli}")
	public Response vende(@PathParam("codcli") String codcli, @QueryParam("codart") String codart) {
		ResponseBuilder builder;
		try {
			String respuesta = gestor.vende(codcli, codart);
			if(respuesta == null) {
				builder = Response.status(Response.Status.NOT_FOUND);
			    return builder.build();
			}
			builder = Response.ok(respuesta);
		    return builder.build();
		}catch(Exception e) {
			builder = Response.status(Response.Status.INTERNAL_SERVER_ERROR);
		    return builder.build();			
		}
	}
	
	
	
	
	

}
