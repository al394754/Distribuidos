package servidor;

import java.io.IOException;
import java.net.ServerSocket;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import comun.MyStreamSocket;
import gestor.GestorSubastas;


/**
 * Este modulo contiene la logica de aplicacion del servidor de la casa de subastas.
 * Utiliza sockets en modo stream para llevar a cabo la comunicacion entre procesos.
 * Puede servir a varios clientes de modo concurrente lanzando una hebra para atender a cada uno de ellos.
 * Se le puede indicar el puerto del servidor en linea de ordenes.
 */

/**
 * En esta version del servidor, todos los hilos que atienden concurrentemente las sesiones de
 * distintos clientes comparten un mismo gestor de subastas.
 * Este gestor se pasa como argumento a cada hilo, junto con el socket.
 */


public class ServidorSubastasRMICallback {

	public static void main(String[] args) throws IOException {
			startRegistry(12345);
			System.setProperty("java.security.policy", "src\\servidor\\java.policy");
			if(System.getSecurityManager() == null) {
				System.setSecurityManager(new SecurityManager());
			}
			try {
				ImplServidorSubastasRMICallback obj = new ImplServidorSubastasRMICallback();
				String url = "rmi://localhost:12345/callback";
				Naming.rebind(url, obj);
			}catch (Exception e){ //Añadir policy ficheros
				e.printStackTrace();
				System.err.println("Excepción en Servidor: ");
				System.exit(1);
			}
			System.out.println("Remote object server working...");
	}
		
	private static void startRegistry(int RMIPortNum) throws RemoteException{
		try {
			Registry registro = LocateRegistry.getRegistry(RMIPortNum);
			registro.list();
		}catch (RemoteException e) {
			System.out.println("El registro no se pudo localizar en el puerto indicado");
			LocateRegistry.createRegistry(RMIPortNum);
			System.out.println("Registro creado en el puerto indicado");
		}
		
	}
} // fin class
