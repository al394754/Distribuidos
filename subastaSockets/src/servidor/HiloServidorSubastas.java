package servidor;

import java.io.IOException;
import java.net.SocketException;
import java.util.Vector;

import comun.MyStreamSocket;
import gestor.GestorSubastas;

/**
 * Clase ejecutada por cada hebra encargada de servir a un cliente del servicio de alquileres.
 * El metodo run contiene la logica para gestionar una sesion con un cliente.
 */

class HiloServidorSubastas implements Runnable {

	private MyStreamSocket myDataSocket;
	private GestorSubastas gestor;

	/**
	 * Construye el objeto a ejecutar por la hebra para servir a un cliente
	 * @param	myDataSocket	socket stream para comunicarse con el cliente
	 * @param	ioStream		stream asociado al fichero con los datos de los cï¿½mics
	 */
	HiloServidorSubastas(MyStreamSocket myDataSocket, GestorSubastas unGestor) {
        // POR IMPLEMENTAR
		this.myDataSocket = myDataSocket;
		this.gestor = unGestor;
	}

	/**
	 * Gestiona una sesion con un cliente	
	 */
	public void run( ) {
		int operacion = 0;
		boolean done = false;
		String respuesta = "";
		// ...
		try {
			while (!done) {
				// Recibe una peticion del cliente
				// Extrae la operacion y los parametros
				respuesta = myDataSocket.receiveMessage();
				String[] campos = respuesta.split("#");
				operacion = Integer.parseInt(campos[0]);
				
				switch (operacion) {
				case 0:
					myDataSocket.close();
					done = true;
					break;
				case 1: { // Consulta los articulos de un determinado tipo
					String tipo = campos[1];
					Vector<String> articulosDeUnTipo = gestor.consultaTipo(tipo);
					myDataSocket.sendMessage(Integer.toString(articulosDeUnTipo.size())); 	//Enviamos la cantidad de artículos del tipo dado
					for(String articulo: articulosDeUnTipo) {		//Enviamos de uno en uno los artículos
						myDataSocket.sendMessage(articulo);
					}
					myDataSocket.sendMessage("");
					break;
				} 
				case 2: { // Consulta las pujas de un cliente
					String codcli = campos[1];
					Vector<String> articulosDeUnCliente = gestor.consultaPujas(codcli);
					String pujados = "";			//Almacenamos todos los articulos pujados en una única cadena, cada artículo se separará con un ";"
					int cantidadArticulosRestantes = articulosDeUnCliente.size(); //Cantidad de articulos pujados
					if(cantidadArticulosRestantes == 0)
						myDataSocket.sendMessage(pujados);
					for(String articulo: articulosDeUnCliente) {
						pujados += articulo;		//Concatenamos
						cantidadArticulosRestantes --;
						if(cantidadArticulosRestantes > 0)	//Simplemente para evitar que el último artículo vaya precedido de un ";"
							pujados += ";";
					}
					myDataSocket.sendMessage(pujados);
					break;
				}             
				case 3: { // Pone en venta un articulo
					String codcli = campos[1];
					String tipo = campos[2];
					String descr = campos[3];
					int precio = Integer.parseInt(campos[4]);
					String articulo = gestor.poneEnVenta(codcli, tipo, descr, precio);
					myDataSocket.sendMessage(articulo);
					break;
				}
				case 4: { // Puja por un articulo
					String codcli = campos[1];
					String codart = campos[2];
					int puja = Integer.parseInt(campos[3]);
					String pujar = gestor.puja(codcli, codart, puja);
					myDataSocket.sendMessage(pujar);
					break;
				}
				case 5: { // vende un articulo
					String codcli = campos[1];
					String codart = campos[2];
					String venta = gestor.vende(codcli, codart);
					myDataSocket.sendMessage(venta);
					break;
				}

				} // fin switch
			} // fin while   
		} // fin try
		catch (SocketException ex) {
			// System.out.println("Capturada SocketException");
		}
		catch (IOException ex) {
			// System.out.println("Capturada IOException");
		}
		catch (Exception ex) {
			System.out.println("Exception caught in thread: " + ex);
		} // fin catch
	} //fin run

} //fin class 
