package cliente;



import java.util.Vector;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.Entity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.WebApplicationException;


public class AuxiliarClienteWS {

	// URI del recurso que permite acceder al servicio casa de subastas
	final private String baseURI = "http://localhost:8080/SubastaWS/servicios/subasta/";
	Client cliente = null;


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor de la clase
	 * Crea el cliente
	 */
	public AuxiliarClienteWS()  {
		this.cliente = ClientBuilder.newClient();
	}

	/**
	 * Devuelve los articulos por los que ha pujado el cliente
	 * Realiza a una peticion GET a la URI {baseURI}/{codcli}
	 * 
	 * @param codcli
	 * @return vector de articulos pujados. vector vacio si no tiene ninguna puja
	 */
	public Vector<String> consultaPujas(String codcli) throws WebApplicationException  {
        // POR IMPLEMENTAR
        Response respuesta = cliente.target(baseURI)
        							.path(""+ codcli) //Esto es @PathParam si es añade algo aparte de ""
        							.request(MediaType.TEXT_PLAIN)
        							.get(); //Enviamos la solicitud y buscamos una respuesta en forma de texto
        int estado = respuesta.getStatus();
        if( estado == 200) {
        	String mensaje = respuesta.readEntity(String.class);
        	Vector<String> pujas = new Vector<>();
        	String[] listaPujas = mensaje.split(";");
        	for(String puja: listaPujas) {
        		pujas.add(puja);
        	}respuesta.close();
        	return pujas;	
        }else if(estado == 404) {
        	respuesta.close();
        	return new Vector<>();
        }else {
        	respuesta.close();
        	throw new WebApplicationException("Error detectado al obtener las pujas: " + codcli);
        }
	}

	/**
	 * Devuelve los articulos de un determinado tipo
	 *
	 * @param codcli
	 * @return vector de articulos de un tipo. vector vacio si no existe ninguno
	 */
	public Vector<String> consultaTipo(String tipo) throws WebApplicationException{ // PUEDEN FALTAR THROWS
        // POR IMPLEMENTAR
		Response respuesta = cliente.target(baseURI).path("")
									.queryParam("tipo", tipo)
									.request(MediaType.TEXT_PLAIN)
									.get();
        int estado = respuesta.getStatus();
        if( estado == 200) {
        	String mensaje = respuesta.readEntity(String.class);
        	Vector<String> pujas = new Vector<>();
        	String[] listaPujas = mensaje.split(";");
        	for(String puja: listaPujas) {
        		pujas.add(puja);
        	}respuesta.close();
        	return pujas;	
        }else if(estado == 404) {
        	respuesta.close();
        	return new Vector<>();
        }else {
        	respuesta.close();
        	throw new WebApplicationException("Error detectado al obtener las pujas: " + tipo);
        }
	}


	/**
	 * El cliente codcli pone en venta un articulo
	 * 
	 * @param codcli	codigo del cliente que pone el articulo en venta
	 * @param tipo		tipo del articulo
	 * @param descr		descripcion del articulo
	 * @param precio	precio de salida
	 * @return	articulo puesto en venta. null si no se ha podido poner en venta
	 */
	public String poneEnVenta(String codcli, String tipo, String descr, int precio) { // PUEDEN FALTAR THROWS
        // POR IMPLEMENTAR
        String articulo = tipo + "#" + descr + "#" + precio;
        Response respuesta = cliente.target(baseURI).path("" + codcli)
													.queryParam("tipo", tipo)
													.queryParam("descr", descr)
													.queryParam("precio", precio)
													.request()
													.post(Entity.text(articulo));
        int estado = respuesta.getStatus();
        if(estado == 200) {
        	String mensaje = respuesta.readEntity(String.class);
        	respuesta.close();
        	return mensaje;
        }
        else if(estado == 404) {
        	respuesta.close();
        	return "";
        }
        else {
			respuesta.close();
			throw new WebApplicationException("Error detectado crear una nueva venta: " + codcli );
		}
	}
	
	/**
	 * El cliente codcli puja por el articulo codart
	 * 
	 * @param codcli	codigo del cliente que puja
	 * @param codart	codigo del articulo por el que puja
	 * @return	articulo por el que se ha pujado. NOT_FOUND si no ha podido pujar
	 */
	public String puja(String codcli, String codart, int puja) throws NotFoundException, WebApplicationException{ // PUEDEN FALTAR THROWS
        // POR IMPLEMENTAR
        String cadena = codart + "#" + puja;
		Response respuesta = cliente.target(baseURI).path("" + codcli)
        											.queryParam("codart", codart)
        											.queryParam("puja", puja)
        											.request(MediaType.TEXT_PLAIN)
        											.put(Entity.text(cadena));
        											
		int estado = respuesta.getStatus();
		if(estado == 200) {
			String mensaje = respuesta.readEntity(String.class);
			respuesta.close();
			return mensaje;
		} 
		else if (estado == 404) {
			respuesta.close();
			return "";
		} 
		else {
			respuesta.close();
			throw new WebApplicationException("Error detectado realizar la puja: " + codcli );
		}
	}

	
	/**
	 * Vende un articulo con un codigo dado al cliente con la mayor puja
	 * 
	 * @param codcli	codigo del cliente vendedor
	 * @param codart	codigo del articulo
	 * @return	articulo vendido. null si no la ha podido vender
	 */
	public String vende(String codcli, String codart) throws NotFoundException, WebApplicationException{ //No es un delete ya que no eliminamos una puja sin más, solo la actualizamos para que se indique que ha sido vendida
        // POR IMPLEMENTAR;
		Response respuesta = cliente.target(baseURI).path("" + codcli)
        											.queryParam("codart", codart)
        											.request()
        											.delete();
        											
		int estado = respuesta.getStatus();
		if(estado == 200) {
			String mensaje = respuesta.readEntity(String.class);
			respuesta.close();
			return mensaje;
		} 
		else if (estado == 404) {
			respuesta.close();
			return "";
		} 
		else {
			respuesta.close();
			throw new WebApplicationException("Error detectado al vender la puja: " + codcli );
		}
	}




} // fin clase
