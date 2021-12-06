package servidor;

import java.io.IOException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Vector;

import cliente.InterfazClienteSubastasRMICallback;

public interface IntServidorSubastasRMICallback extends Remote{
	public Vector<String> consultaPujas(String codcli) throws RemoteException, IOException;
	public Vector<String> consultaTipo(String tipo) throws RemoteException, IOException;
	public String poneEnVenta(String codcli, String tipo, String descr, int precio) throws RemoteException, IOException;
	public String puja(String codcli, String codart, int puja) throws RemoteException, IOException;
	public String vende(String vendedor, String codart) throws RemoteException, IOException;
	public void cierraGestor() throws RemoteException, IOException;
	public void registrarCallback(InterfazClienteSubastasRMICallback objCallbackCliente, String tipoArticulo) throws RemoteException, IOException;
	public void eliminarCallback(InterfazClienteSubastasRMICallback objCallbackCliente, String tipoArticulo) throws RemoteException, IOException;

}
