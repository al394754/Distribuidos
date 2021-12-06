package subastas;

import java.io.EOFException;
import java.io.File;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.Vector;
import java.util.concurrent.atomic.AtomicInteger;


public class GestorSubastas {

	private RandomAccessFile stream;
	private AtomicInteger contador = new AtomicInteger(); // Contador del codigo de articulo


	/**
	 * Constructor del gestor de consultas de la casa de subastas.
	 * Crea un fichero con datos de prueba
	 */
	public GestorSubastas() {
		creaFichero("subasta.dat");
		
		Articulo articulo = new Articulo();
		// Obtiene el contador del Ãºltimo artÃ­culo.
		try {
			// Recorre el fichero hasta el final
			stream.seek(0);
			while(true) {
				articulo.leeDeFichero(stream);
			}
		}
		catch (EOFException e) { // Hemos acabado de leer el fichero
			// El contador inicial sera el del ultimo articulo + 1
			String codart = articulo.getCodart();
			int ultimo = Integer.parseInt(codart.substring(2,codart.length()));
			this.contador = new AtomicInteger(ultimo+1);
		}
		catch (IOException e) {
			System.out.println("Excepcion en GestorSubastas");
		}
		
	}

	/**
	 * Cierra el flujo/stream asociado al fichero de articulos.
	 * @throws IOException 
	 */
	public void cierraGestor() throws IOException{

		stream.close();
	
	}
	

	/**
	 * Si no existe, crea el fichero y lo rellena con unos articulos iniciales
	 * 
	 * @param nombreFichero
	 */
	public void creaFichero(String nombreFichero) {
		File f = new File(nombreFichero);
		stream = null;
		try {
			if (!f.exists()) {
				stream = new RandomAccessFile(f, "rw"); // si no existe, lo crea y lo rellena con datos iniciales
								
				Articulo articulo = new Articulo("LI1", false, "Dune", "herbert01", "", 10);
				articulo.escribeEnFichero(stream);

				articulo = new Articulo("RO2", false, "Zapatillas Lidl", "hipster ", "", 27);
				articulo.escribeEnFichero(stream);

				articulo = new Articulo("EL3", false, "iPhone 5 como nuevo", "vintage9", "", 250);
				articulo.escribeEnFichero(stream);

				articulo = new Articulo("OT4", false, "Piedra lunar", "amstrong", "", 15700);
				articulo.escribeEnFichero(stream);

				articulo = new Articulo("LI5", false, "La Illiada", "homersi ", "", 17);
				articulo.escribeEnFichero(stream);

				articulo = new Articulo("OT6", false, "Cards Bola de drac", "songoku ", "", 35);
				articulo.escribeEnFichero(stream);

			}	
			else {
				stream = new RandomAccessFile(f, "rw"); // si existia, solo lo habre
			}	
		}
		catch (IOException e) {
			System.out.println("Excepcion al crear el fichero");
			System.exit(0);
		};
	}





	/**
	 * Devuelve los articulos por los que ha pujado el cliente
	 * 
	 * @param codcli
	 * @return vector de articulos pujados. vector vacio si no tiene ninguna puja
	 * @throws IOException 
	 * @throws EOFException 
	 */
	public Vector<String> consultaPujas(String codcli) throws EOFException, IOException {	
		
		// POR IMPLEMENTAR
		
		Vector<String> articulosPujados = new Vector<String>();
		stream.seek(0);																						//Colocamos el puntero a principio de fichero
		int cantidadTotal = contador.get(); 																//Con el get obtenemos la cantidad total de enteros en formato int
		for(int contadorArticulos = 0; contadorArticulos < cantidadTotal - 1; contadorArticulos ++) {
			Articulo articulo = new Articulo();					
			articulo.leeDeFichero(stream);																	//Obtenemos cada articulo del fichero
			if(codcli.compareTo(articulo.getCodcli().substring(0, codcli.length())) == 0) {					//Comprobamos en cada artículo si coincide el código del cliente con el buscado
				if(!articulo.getVendido())
					articulosPujados.add(articulo.toString());												//Añadimos al vector el código del artículo
			}
		}return articulosPujados;
		
		// DEVOLVER LA INFORMACION ADECUADA EN CADA CASO

	}



	/**
	 * Devuelve los articulos de un determinado tipo
	 * 
	 * @param codcli
	 * @return vector de articulos de un tipo. vector vacio si no existe ninguno
	 * @throws IOException 
	 */
	public Vector<String> consultaTipo(String tipo) throws IOException {	

		// POR IMPLEMENTAR
		
		Vector<String> articulosDeUnTipo = new Vector<String>();
		stream.seek(0);
		int cantidadTotal = contador.get();
		for(int contadorArticulos = 0; contadorArticulos < cantidadTotal - 1; contadorArticulos ++) {
			Articulo articulo = new Articulo();
			articulo.leeDeFichero(stream);
			if(tipo.compareTo(articulo.rightPad(articulo.getCodart(), 2)) == 0){						//Comprobamos si las dos primeras letras de cada artículo coinciden con el tipo buscado
				articulosDeUnTipo.add(articulo.toString());
			}
		}return articulosDeUnTipo;
		
		// DEVOLVER LA INFORMACION ADECUADA EN CADA CASO

	}
	
	
	/**
	 * El cliente codcli pone en venta un articulo
	 * 
	 * @param codcli	codigo del cliente que pone el articulo en venta
	 * @param tipo		tipo del articulo
	 * @param descr		descripcion del articulo
	 * @param precio	precio de salida
	 * @return	articulo puesto en venta. null si no se ha podido poner en venta
	 * @throws IOException 
	 */
	public String poneEnVenta(String codcli, String tipo, String descr, int precio) throws IOException {

		// POR IMPLEMENTAR
		// Supongo que el puntero del fichero(stream) se encuentra al final de dicho fichero
		
		Articulo articulo = new Articulo();
		String codart = tipo + Integer.toString(contador.get()); //Añadimos el número del elemento junto al tipo
		articulo.setCodart(codart);
		articulo.setVendido(false);
		articulo.setDescr(descr);
		articulo.setCodcli("");
		articulo.setCodven(codcli);
		articulo.setPuja(precio);
		articulo.escribeEnFichero(stream);	//crea el artículo con todos los datos y se introduce en el fichero con el método de escribir en fichero
		this.contador = new AtomicInteger(contador.get() + 1); //Actualizamos contador de artículos
		return articulo.toString();
		
		// DEVOLVER LA INFORMACION ADECUADA EN CADA CASO

	}

	/**
	 * Devuelve la posicion en el fichero del articulo con un codigo dado
	 * 
	 * @param codart	codigo del articulo
	 * @return	indice en el fichero. -1 si no se encuentra el articulo
	 * @throws IOException 
	 */
	private long buscaArticulo(String codart) throws IOException {

		// POR IMPLEMENTAR
		
		stream.seek(0);
		long puntero;
		int cantidadTotal = contador.get();
		for(int contadorArticulos = 1; contadorArticulos < cantidadTotal; contadorArticulos ++) {
			puntero = stream.getFilePointer();
			Articulo articulo = new Articulo();					
			articulo.leeDeFichero(stream);
			if (articulo.getCodart().equals(codart))
				return puntero;			//Devolvemos el puntero donde se encuentra el artículo buscado en fichero
		}
		return -1; // DEVOLVER LA INFORMACION ADECUADA EN CADA CASO

	}



	/**
	 * El cliente codcli puja por el articulo codart
	 * 
	 * @param codcli	codigo del cliente que puja
	 * @param codart	codigo del articulo por el que puja
	 * @return	articulo por el que se ha pujado. null si no ha podido pujar
	 * @throws IOException 
	 */
	public String puja(String codcli, String codart, int puja) throws IOException {

		long puntero = this.buscaArticulo(codart);	//Devuelve -1 si no encuentra el artículo
		if(puntero == -1)			
			return "El articulo no existe";										
		stream.seek(puntero);
		Articulo articulo = new Articulo();
		articulo.leeDeFichero(stream);
		if(codcli.compareTo(articulo.rightPad(articulo.getCodven(), codcli.length())) == 0) //Comprobamos si el cliente y el cliente son el mismo. Codcli tiene la longitud que se le pasa por teclado(máx. 8) pero codven siempre será de longitud 8, por lo que acortamos codven a la longitud del código que pasamos por teclado
			return "El vendedor no puede pujar por el artículo que ha puesto a la venta";
		if(articulo.getVendido())
			return "El artículo " + codart + " ya ha sido vendido";
		if(puja > articulo.getPuja()) {		//Comprobamos si la puja supera el mínimo
			stream.seek(puntero);
			articulo.setPuja(puja);
			articulo.setCodcli(articulo.rightPad(codcli, 8));
			articulo.escribeEnFichero(stream);
			return "El artículo " + codart + " se ha pujado por valor de " + puja + " euros, por el cliente " + codcli;
		}
		
		return "Es necesario una puja mayor a " + articulo.getPuja() + ". Puja no realizada";
		
		// DEVOLVER LA INFORMACION ADECUADA EN CADA CASO
	}



	/**
	 * Vende un articulo con un codigo dado al cliente con la mayor puja
	 * 
	 * @param codcli	codigo del cliente vendedor
	 * @param codart	codigo del articulo
	 * @return	articulo vendido. null si no la ha podido vender
	 * @throws IOException 
	 * @throws EOFException 
	 */
	public String vende(String vendedor, String codart) throws EOFException, IOException {

		// POR IMPLEMENTAR
			
		long puntero = this.buscaArticulo(codart);
		if(puntero == -1)
			return "El articulo no existe";
		stream.seek(puntero);
		Articulo articulo = new Articulo();
		articulo.leeDeFichero(stream);
		if (articulo.getVendido())
			return "El articulo ya ha sido vendido";
		if(articulo.rightPad(articulo.getCodcli(), 1).equals(" ")) 	//Con esta línea comprobamos que nadie ha pujado aún, si no hay nadie se almacena una cadena vacía(espacio)
			return "No se puede vender debido a que nadie ha pujado por el articulo";
		if(vendedor.compareTo(articulo.rightPad(articulo.getCodven(), vendedor.length())) == 0) {  	//Comprobamos que el cliente que desea vender es el propietario		
			articulo.setVendido(true);
			stream.seek(puntero);
			articulo.escribeEnFichero(stream);	//Modificamos el campo de vendido de false a true
			return "El articulo " + codart + " ha sido vendido al cliente " + articulo.getCodcli(); 
		}return "No eres el vendedor del articulo " + codart;
		
		
		// DEVOLVER LA INFORMACION ADECUADA EN CADA CASO

	}




}
