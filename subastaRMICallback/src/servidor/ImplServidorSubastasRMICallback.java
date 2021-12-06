package servidor;


import java.io.IOException;
import java.rmi.ConnectException;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.HashMap;
import java.util.Vector;

import cliente.InterfazClienteSubastasRMICallback;
import gestor.GestorSubastas;

class ImplServidorSubastasRMICallback extends UnicastRemoteObject implements IntServidorSubastasRMICallback {

		private GestorSubastas gestor;
		private HashMap<String, Vector<InterfazClienteSubastasRMICallback>> mapaClientes;

		/**
		 * Construye el objeto a ejecutar por la hebra para servir a un cliente
		 * @param	myDataSocket	socket stream para comunicarse con el cliente
		 * @param	ioStream		stream asociado al fichero con los datos de los cï¿½mics
		 */
		public ImplServidorSubastasRMICallback() throws RemoteException{
	        // POR IMPLEMENTAR
			super();
			this.gestor = new GestorSubastas();
			this.mapaClientes = new HashMap<>();
		}

		@Override
		public Vector<String> consultaPujas(String codcli) throws RemoteException, IOException{
			return gestor.consultaPujas(codcli);
		}

		@Override
		public Vector<String> consultaTipo(String tipo) throws RemoteException, IOException {
			return gestor.consultaTipo(tipo);
		}

		@Override
		public String poneEnVenta(String codcli, String tipo, String descr, int precio)
				throws RemoteException, IOException {
			String mensaje = gestor.poneEnVenta(codcli, tipo, descr, precio);
			hacerCallback(tipo, "Nuevo articulo: \n" + mensaje);
			return mensaje;
		}

		@Override
		public String puja(String codcli, String codart, int puja) throws RemoteException, IOException {
			return gestor.puja(codcli, codart, puja);
		}

		@Override
		public String vende(String vendedor, String codart) throws RemoteException, IOException {
			return gestor.vende(vendedor, codart);
		}
		
		public void cierraGestor() throws RemoteException, IOException{
			gestor.cierraGestor();
		}

		@Override
		public void registrarCallback(InterfazClienteSubastasRMICallback objCallbackCliente, String tipoArticulo)
				throws RemoteException, IOException {
			Vector<InterfazClienteSubastasRMICallback> listaClientes = mapaClientes.get(tipoArticulo);
			if(listaClientes == null) {
				Vector<InterfazClienteSubastasRMICallback> nuevaListaClientes = new Vector<InterfazClienteSubastasRMICallback>();
				nuevaListaClientes.addElement(objCallbackCliente);
				mapaClientes.put(tipoArticulo, nuevaListaClientes);
				objCallbackCliente.notificar("Se le notificará cuando un nuevo artículo de este tipo sea puesto a la venta");
			}
			else {
				if(!(listaClientes.contains(objCallbackCliente))) {
					listaClientes.addElement(objCallbackCliente);
				}
			}
			
		}

		@Override
		public void eliminarCallback(InterfazClienteSubastasRMICallback objCallbackCliente, String tipoArticulo)
				throws RemoteException, IOException {
			Vector<InterfazClienteSubastasRMICallback> listaClientes = mapaClientes.get(tipoArticulo);
			if(listaClientes != null && listaClientes.contains(objCallbackCliente)) {
				listaClientes.removeElement(objCallbackCliente);
				objCallbackCliente.notificar("No le notificará más cuando un nuevo artículo de este tipo sea puesto a la venta");
			}else {
				objCallbackCliente.notificar("No está registrado para recibir notificaciones de este tipo de objeto");
			}
		}
		
		private synchronized void hacerCallback(String tipoArticulo, String mensaje) throws RemoteException{
			Vector<InterfazClienteSubastasRMICallback> listaClientes = mapaClientes.get(tipoArticulo);
			if(listaClientes != null) {
				for(int i = listaClientes.size() - 1; i >= 0; i--) {
					InterfazClienteSubastasRMICallback cliente = (InterfazClienteSubastasRMICallback) listaClientes.elementAt(i);
					try {
						cliente.notificar(mensaje);
					}catch(Exception e) {
						listaClientes.remove(i);
					}
				}
			}
		}
}