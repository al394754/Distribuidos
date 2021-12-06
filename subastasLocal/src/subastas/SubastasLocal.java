package subastas;

import java.io.IOException;
import java.util.Scanner;
import java.util.Vector;


public class SubastasLocal {


	/**
	 * Muestra el menu de opciones y lee repetidamente de teclado hasta obtener una opcion valida
	 * @param teclado
	 * @return
	 */
	public static int menu(Scanner teclado) {
		int opcion;
		System.out.println("\n\n");
		System.out.println("=====================================================");
		System.out.println("============            MENU        =================");
		System.out.println("=====================================================");
		System.out.println("0. Salir");
		System.out.println("1. Consultar articulos de un tipo");
		System.out.println("2. Consultar mis pujas");
		System.out.println("3. Poner a la venta");
		System.out.println("4. Pujar");
		System.out.println("5. Vender");
		do {
			System.out.print("\nElige una opcion (0..5): ");
			opcion = teclado.nextInt();
		} while ( (opcion<0) || (opcion>5) );
		teclado.nextLine(); // Elimina retorno de carro del buffer de entrada
		return opcion;
	}

	/* TIPOS de artículo actuales
	 *  LI: Libros y cómics
	 *  EL: Electrónica e Informática
	 *  RO: Ropa y complementos
	 *  OT: Otros
	 */

	static String leeTipo(Scanner teclado) {
		boolean tipoCorrecto = false;
		String tipo;
		do {
			System.out.print("Introduce un tipo (LI, EL, RO, OT): ");
			tipo = teclado.nextLine();
			tipoCorrecto = ( tipo.equals("LI") || tipo.equals("EL") || tipo.equals("RO") || tipo.equals("OT") );				
		} while (!tipoCorrecto);
		return tipo;
	}


	/**
	 * Programa principal. Muestra el menú repetidamente y atiende las peticiones del cliente.
	 * 
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException  {

		Scanner teclado = new Scanner(System.in);

		// Crea un gestor de alquiler de bicis
		GestorSubastas gestor = new GestorSubastas();

		System.out.print("Introduce tu codigo de cliente (max. 8 caracteres): ");
		String codcli = teclado.nextLine();
		if(codcli.length() > 8)
			codcli = codcli.substring(0, 8);

		int opcion; 
		do {
			opcion = menu(teclado);
			switch (opcion) {
			case 0: // Cierra el gestor y sale del programa

				// POR IMPLEMENTAR

				gestor.cierraGestor();
				
				break;

			case 1: { // Consultar articulos de un tipo

				// POR IMPLEMENTAR
				
				String tipo = leeTipo(teclado);
				Vector<String> articulosDeUnTipo = gestor.consultaTipo(tipo);
				if(articulosDeUnTipo.size() == 0) {
					System.out.print("[]");
					break;
				}
				for(String articulo : articulosDeUnTipo) {
					System.out.print(articulo + "\n");
				}

				break;
			}

			case 2: { // Consultar mis pujas

				// POR IMPLEMENTAR
				
				Vector<String> todasLasPujas = gestor.consultaPujas(codcli);
				if(todasLasPujas.size() == 0) {
					System.out.print("[]");
					break;
				}
				for(String articulo : todasLasPujas)
					System.out.print(articulo + "\n");

				break;
			}

			case 3: { // Poner en venta

				// POR IMPLEMENTAR
				
				String tipo = leeTipo(teclado);
				System.out.print("Introduce una descripci�n breve del art�culo: ");
				String descrip = teclado.nextLine();
				System.out.print("Introduce un precio de venta inicial: ");
				int precio = teclado.nextInt();
				String articulo = gestor.poneEnVenta(codcli, tipo, descrip, precio);
				System.out.println(articulo);
				System.out.println("Campos: C�digo # Vendido(True/False) # Descripci�n # Usuario que pone a la venta # Usuario que puja # Valor de puja");

				
				break;
			}

			case 4: { // Pujar

				System.out.print("Introduce el art�culo por el que desea pujar(Debe estar en may�sculas): ");
				String articulo = teclado.next();
				System.out.print("Introduce la cantidad por la que se va a pujar: ");
				int puja = teclado.nextInt();
				String resultado = gestor.puja(codcli, articulo, puja);
				System.out.println(resultado);
				

				break;
			}

			case 5: { // Vender

				// POR IMPLEMENTAR
				
				System.out.print("Introduce el art�culo que desea vender: ");
				String articulo = teclado.next();
				String resultado = gestor.vende(codcli, articulo);
				System.out.println(resultado);
				break;
			}
			
			} // fin switch

		} while (opcion != 0);

		teclado.close();

	} // fin de main

} // fin class
