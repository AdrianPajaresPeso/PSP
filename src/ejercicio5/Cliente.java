package ejercicio5;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

/**
 * @author Adrian Pajares peso
 *
 */
public class Cliente {
	// parametros de configuracion para que el cliente pueda conectarse
	private static String ip = "";
	private static String puerto = "";

	/**
	 * Metodo principal en el que se llamarán todos los metodos que queramos
	 * ejecutar. Esta funcion es la que se debe de poner el un Main para pode
	 * ejecutar bien el cliente.
	 */
	public static void menu() {
		Scanner sc = new Scanner(System.in);
		String input;
		// bucle para que el usuario no termine su ejecucion, a menos que quiera
		while (true) {

			System.out.println("Escribe la ruta del fichero a enviar");
			input = sc.nextLine();
			// si el fichero existe obtenemos los datos de la conexión con el servidor
			if (buscaFichero(input)) {
				getConnection(sc, input);
			} else {
				System.out.println("El fichero seleccionado no se encuentra");

			}
			// controla si queremos salir del programa o no
			System.out.println("Desea realizar otra operación? (y/n)");
			input = sc.nextLine();
			if (input.equals("n") || input.equals("No") || input.equals("no")) {
				break;
			}

		}
	}

	/**
	 * Metodo que obtiene los datos para conectarse al servidor y poder enviar la
	 * informacion que queremos
	 * 
	 * @param sc    Variable de tipo scanner para poder obtener mas informacion del
	 *              usuario en cuestion a acciones a realizar por consola
	 * @param input Nombre del fichero que queremos pasar al servidor. Se realizarán
	 *              comprobaciones con el
	 */
	private static void getConnection(Scanner sc, String input) {

		// comprobamos datos de conexion
		getDataConnection(sc);

		try {
			// obtiene el valor de los parametros
			Socket cliente = new Socket(ip, Integer.parseInt(puerto));
			File f = new File(input);
			BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(f)));


			//obtiene el outputStream del socket y manda linea a linea al servidor
			PrintStream ps = new PrintStream(cliente.getOutputStream());
			String line;
			while ((line = br.readLine()) != null) {
				ps.println(line);
			}
			ps.close();
			br.close();		
			
			cliente.close();
			
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Metodo que se encarga de comprobar que los parametros de conexión están
	 * establecidos, si uno de ellos está vacío se mostrará el mensaje de que no hay
	 * parametos establecidos Si hay parametros establecidos de una anterior
	 * conexión, el usuario puede decidir si cambiarlos o no
	 * 
	 * @param sc variable de tipo scanner para poder hacer que el usuario introduzca
	 *           datos por consola
	 */
	private static void getDataConnection(Scanner sc) {
		if (ip.equals("") && puerto.equals("")) {
			System.out.println("No hay datos de conexion");

			newDataConnection(sc);
		} else {
			System.out.println("Ya hay datos de conexión anteriores, deseas utilizarlos (y/n)");
			if (sc.nextLine().equals("n")) {
				newDataConnection(sc);
			}

		}
	}

	/**
	 * Metodo en el cual se introducen los parametros de conexion
	 * 
	 * @param sc variable de tipo scanner para poder introducir los datos por
	 *           consola.
	 */
	private static void newDataConnection(Scanner sc) {
		System.out.println("Introduce una dirección IP:");
		ip = sc.nextLine();
		System.out.println("Introduce el puerto al que dirigirse:");
		puerto = sc.nextLine();
	}

	/**
	 * Este método se encarga de comprobar si podemos mandar el fichero por su
	 * existencia y por su tipo, no se podrá enviar un directorio, solo un fichero.
	 * 
	 * @param path
	 * @return true si el fichero que queremos mandar existe y es un fichero. false
	 *         si el fichero que queremos mandar no existe
	 */
	private static Boolean buscaFichero(String path) {

		boolean flag = false;
		File f = new File(path);
		if (f.exists() && f.isFile()) {
			flag = true;
		}
		return flag;
	}

	
	private static void recibirMensaje(Socket cliente) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(cliente.getInputStream()));
		String msg = br.readLine();
		System.out.println(msg);
		br.close();
	}
	public static void main(String[] args) {
		menu();

	}

}
