package servidor;

import java.io.IOException;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Vector;

public interface IntServidorSubastasRMI extends Remote{
	public Vector<String> consultaPujas(String codcli) throws RemoteException, IOException;
	public Vector<String> consultaTipo(String tipo) throws RemoteException, IOException;
	public String poneEnVenta(String codcli, String tipo, String descr, int precio) throws RemoteException, IOException;
	public String puja(String codcli, String codart, int puja) throws RemoteException, IOException;
	public String vende(String vendedor, String codart) throws RemoteException, IOException;
	public void cierraGestor() throws RemoteException, IOException;
}
