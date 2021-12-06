package cliente;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface InterfazClienteSubastasRMICallback extends Remote{
	public void notificar(String mensaje) throws RemoteException;
}
