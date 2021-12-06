package cliente;

import java.io.IOException;

import java.net.InetAddress;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Vector;

import comun.MyStreamSocket;

/**
 * Esta clase es un modulo que proporciona la logica de aplicacion
 * para el Cliente del sevicio de alquiler usando sockets de tipo stream
 */

public class AuxiliarClienteSubastasRMI {

	private MyStreamSocket mySocket; // Socket de datos para comunicarse con el servidor
	private InetAddress serverHost;  // IP del servidor
	private int serverPort;          // Puerto asociado al servicio en el servidor

	/**
	 * Construye un objeto auxiliar asociado a un cliente del servicio.
	 * Crea un socket para conectar con el servidor.
	 * @param	hostName	nombre de la maquina que ejecuta el servidor
	 * @param	portNum		numero de puerto asociado al servicio en el servidor
	 */
	AuxiliarClienteSubastasRMI(String hostName, String portNum) 
			throws SocketException, UnknownHostException, IOException {

		this.serverHost = InetAddress.getByName(hostName);
		this.serverPort = Integer.parseInt(portNum);
		//Instantiates a stream-mode socket and wait for a connection.
		this.mySocket = new MyStreamSocket(this.serverHost, this.serverPort); 
		/**/  System.out.println("Hecha peticion de conexion");
	} // end constructor

	/**
	 * Devuelve los articulos de un determinado tipo no vendidos
	 * 
	 * @param codcli
	 * @return vector de articulos de un tipo. vector vacio si no tiene ninguna
	 * @throws	IOException
	 */
	public Vector<String> consultaTipo(String tipo) throws IOException {
        // POR IMPLEMENTAR
		Vector<String> articulosDeUnTipo = new Vector<String>();
		mySocket.sendMessage("1#" + tipo);
		String respuesta = mySocket.receiveMessage();	//devuelve un primer mensaje con el número de artículos del tipo no vendidos
		while(!respuesta.equals("")) {					//Si recibimos una cadena vacía, significa que ya no quedan más artículos
			articulosDeUnTipo.add(respuesta);
			respuesta = mySocket.receiveMessage();
		}return articulosDeUnTipo;
		
		        // cambiar por el retorno correcto
	} // end consultaTipo

	/**
	 * Devuelve los articulos por los que ha pujado el cliente
	 * 
	 * @param codcli
	 * @return vector de articulos pujados. vector vacio si no tiene ninguna
	 */
	public Vector<String> consultaPujas(String codcli)  throws IOException {
        // POR IMPLEMENTAR
		Vector<String> articulosPujados = new Vector<String>();
		mySocket.sendMessage("2#" + codcli);
		String respuesta = mySocket.receiveMessage();
		if(respuesta.equals(""))
			return articulosPujados;
		String[] articulosRecibidos = respuesta.split(";");
		for(String articulo: articulosRecibidos) {
			articulosPujados.add(articulo);
		}
        return articulosPujados; // cambiar por el retorno correcto
	} // end consultaPujas

	/**
	 * El cliente codcli pone en venta un articulo
	 * 
	 * @param codcli	codigo del cliente que pone el articulo en venta
	 * @param tipo		tipo del articulo
	 * @param descr		descripcion del articulo
	 * @param precio	precio de salida
	 * @return	articulo puesto en venta. null si no se ha podido poner en venta
	 */
	public String poneEnVenta(String codcli, String tipo, String descr, int precio) throws IOException {
        // POR IMPLEMENTAR
		mySocket.sendMessage("3#" + codcli + "#" + tipo + "#" + descr + "#" + precio);
		String respuesta = mySocket.receiveMessage();
        return respuesta; // cambiar por el retorno correcto
	} // end poneEnVenta
	
	/**
	 * El cliente codcli puja por el articulo codart
	 * 
	 * @param codcli	codigo del cliente que puja
	 * @param codart	codigo del articulo por el que puja
	 * @return	articulo por el que se ha pujado. null si no se ha completado la puja
	 */
	public String puja(String codcli, String codart, int puja) throws IOException {
        // POR IMPLEMENTAR
		mySocket.sendMessage("4#" + codcli + "#" + codart + "#" + puja);
		String respuesta = mySocket.receiveMessage();
        return respuesta; // cambiar por el retorno correcto
	} // end puja
	
	/**
	 * Vende un articulo con un codigo dado
	 * 
	 * @param codcli	codigo del cliente vendedor
	 * @param codart	codigo del articulo
	 * @return	articulo vendido. null si no la ha podido vender
	 */
	public String vende(String codcli, String codart)  throws IOException {
        // POR IMPLEMENTAR
		mySocket.sendMessage("5#" + codcli + "#" + codart);
		String respuesta = mySocket.receiveMessage();
        return respuesta; // cambiar por el retorno correcto
	} // end vende

	/**
	 * Finaliza la conexion con el servidor
	 * @throws	IOException
	 */
	public void cierraGestor( ) throws IOException{
        // POR IMPLEMENTAR
		mySocket.sendMessage("0");
		System.out.println("Sesión finalizada");
		mySocket.close();
	} // end done 
} //end class
