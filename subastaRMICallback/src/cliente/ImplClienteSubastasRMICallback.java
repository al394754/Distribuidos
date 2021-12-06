package cliente;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class ImplClienteSubastasRMICallback extends UnicastRemoteObject implements InterfazClienteSubastasRMICallback{

	
	protected ImplClienteSubastasRMICallback() throws RemoteException {
		super();
	}

	@Override
	public void notificar(String mensaje) {
		System.out.println("\n");
		System.out.println("--------------------------------------------------------------------------------------");
		System.out.println(mensaje);
		System.out.println("--------------------------------------------------------------------------------------");
		System.out.println("\n");
	}
	
}
