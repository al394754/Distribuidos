package servidor;


import java.io.IOException;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Vector;
import gestor.GestorSubastas;

class ImplServidorSubastasRMI extends UnicastRemoteObject implements IntServidorSubastasRMI {

		private GestorSubastas gestor;

		/**
		 * Construye el objeto a ejecutar por la hebra para servir a un cliente
		 * @param	myDataSocket	socket stream para comunicarse con el cliente
		 * @param	ioStream		stream asociado al fichero con los datos de los c�mics
		 */
		public ImplServidorSubastasRMI() throws RemoteException{
	        // POR IMPLEMENTAR
			super();
			this.gestor = new GestorSubastas();
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
			return gestor.poneEnVenta(codcli, tipo, descr, precio);
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
}