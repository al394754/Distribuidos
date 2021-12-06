package subastas;

import java.io.EOFException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.io.Serializable;

public class Articulo implements Serializable {


	private static final long serialVersionUID = 1L;
	
	/**
	 * Clase Articulo subastado
	 */
	
	private String codart;     // Las dos primeras letras nos dicen el tipo LI:Libros; EL:Electronica; RO:Ropa; OT:Otros
	private boolean vendido;   // true si se ha vendido
    private String descr;      // breve descripcion del articulo
    private String codven;     // codigo del vendedor del articulo
    private String codcli;     // codigo del cliente que ha hecho la ultima puja. cadena vacia si no hay pujas
    private int puja;          // precio de salida o puja mas reciente
    
	// codprop y codcli tienen que medir 8 caracteres para poder ser modificados en el fichero

	public Articulo(String codart, boolean vendido, String descr, String codven, String codcli, int puja) {
		super();
		this.codart = codart;
		this.vendido = vendido;
		this.descr = descr;
		this.codven = codven;
		this.codcli = codcli;
		this.puja = puja;
	}
	
	@Override
	/**
	 * Devuelve los datos del articulo en una cadena
	 */
	public String toString() {
		return codart + "#" + vendido + "#" + descr +  "#" + codven+  "#" + codcli + "#" + puja;
	}
	
	/**
	 * Ajusta el tamaño de una cadena para que ocupe n caracteres.
	 * La recorta o la rellena con blancos por la derecha.
	 * 
	 * @param cadena	cadena que queremos ajustar
	 * @param n			numero de caracteres que queremos que acabe teniendo
	 * @return			cadena con el tamaño ajustado
	 */
	public String rightPad(String cadena, int n) {
		return (cadena.length()) < n ? cadena+"        ".substring(0,n-cadena.length()) : cadena.substring(0,n);
	}


	public String getCodart() {
		return codart;
	}


	public void setCodart(String codart) {
		this.codart = codart;
	}
	
	public boolean getVendido() {
		return vendido;
	}


	public void setVendido(boolean vendido) {
		this.vendido = vendido;
	}


	public String getDescr() {
		return descr;
	}


	public void setDescr(String descr) {
		this.descr = descr;
	}


	public String getCodven() {
		return codven;
	}


	public void setCodven(String codven) {
		this.codven = rightPad(codven, 8);
	}


	public String getCodcli() {
		return codcli;
	}


	public void setCodcli(String codcli) {
		this.codcli = rightPad(codcli, 8);
	}


	public int getPuja() {
		return puja;
	}


	public void setPuja(int puja) {
		this.puja = puja;
	}


	public Articulo() {
		super();
		// TODO Auto-generated constructor stub
	}


	/**
	 * Lee los datos de un articulo de la posicion actual de un fichero
	 * @param	stream	stream asociado al fichero
	 * @throws	EOFException, IOException
	 */	
  	public synchronized void leeDeFichero(RandomAccessFile stream) throws EOFException, IOException {
  		
		  // POR IMPLEMENTAR
  		
  		setCodart(stream.readUTF());
  		setVendido(stream.readBoolean());
  		setDescr(stream.readUTF());
  		setCodven(stream.readUTF());
  		setCodcli(stream.readUTF());
  		setPuja(stream.readInt());
    
  	} // fin leeDeFichero
  	

	/**
	 * Escribe los datos de un articulo en la posicion actual de un fichero en formato binario
	 * @param	stream	stream asociado al fichero
	 * @throws IOException 
	 */	
  	public synchronized void escribeEnFichero(RandomAccessFile stream) throws IOException {
  		
		stream.writeUTF(this.getCodart());
		stream.writeBoolean(this.getVendido());
		stream.writeUTF(this.getDescr());
		stream.writeUTF(this.rightPad(this.getCodven(), 8));
		stream.writeUTF(this.rightPad(this.getCodcli(), 8));
		stream.writeInt(this.getPuja());
  		
    } // fin escribeEnFichero
	

   
}
