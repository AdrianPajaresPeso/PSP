import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Scanner;

public class Ejercicio5 {
	private HashMap<String, Integer> mapProcesado = new HashMap<>();

	/**
	 * metodo en el que se elige la opción para poder procesar el fichero que se
	 * desea y mostrar su información
	 * 
	 * 
	 */
	public void menu() {
		Scanner sc = new Scanner(System.in);

		int input;
		String ruta;

		while (true) {
			printMenu();// se muestra el menu a cada iteracion que se realiza
			System.out.print("--->");

			input = sc.nextInt();// entrada de la opción
			sc.nextLine();// limpiamos el buffer

			if (input == 1) {// si la entrada es 1
				// si ya se habia procesado un fichero antes
				// se borra el contenido del hashmap
				if (!mapProcesado.isEmpty()) {
					mapProcesado.clear();
				}
				ruta = sc.nextLine();// pedimos la ruta
				procesarFichero(ruta);// se procesa el fichero

			} else if (input == 2) {// si la entrada es 2

				// si el hashmap tiene contenido muestra su contenido
				if (!mapProcesado.isEmpty()) {
					printAllWords();
				} else {
					// si está vacío vuelve a iterar en el menú y muestra mensaje
					System.out.println("No se ha procesado ningun archivo");
				}

			} else {
				// si la opción es 3, el programa finaliza.
				break;
			}
		}

	}

	/**
	 * Muestra por pantalla el menu de la aplicación
	 * 
	 */
	private void printMenu() {
		System.out.println("Seleccione una opción: ");
		System.out.println("\t1. Procesar un nuevo fichero de texto.");
		System.out.println("\t2. Ver número de repeticiones de una palabra");
		System.out.println("\t3. Salir");
	}

	/**
	 * Muestra por pantalla todas las palabras y las veces que se repite en el
	 * archivo que están almacenadas en el hashMap
	 * 
	 */
	private void printAllWords() {
		// muestra las claves con sus valores
		for (Entry<String, Integer> entry : mapProcesado.entrySet()) {
			String key = entry.getKey();
			Object value = entry.getValue();
			System.out.println(key + "---" + value);
		}
	}

	/**
	 * Metodo encargado de procesar el fichero que se le ha pasado por la ruta
	 * establecida
	 * 
	 */
	private void procesarFichero(String ruta) {
		try {
			DataInputStream dis = new DataInputStream(new FileInputStream(ruta));
			String linea;

			// Se lee el fichero linea por linea, buscando espacios
			while ((linea = dis.readLine()) != null) {
				String array[] = linea.split(" ");

				for (int i = 0; i < array.length; i++) {

					// seguimos quitando caracteres que puedan interrumpir el conteo
					String palabra = array[i];
					if (array[i].contains(".")) {
						palabra = array[i].substring(0, array[i].indexOf("."));
					}
					if (array[i].contains(",")) {
						palabra = array[i].substring(0, array[i].indexOf(","));
					}

					// si el hashmap está vacio la primera palabra entra directamente
					if (mapProcesado.isEmpty()) {
						mapProcesado.put(palabra, 1);
					} else if (!mapProcesado.containsKey(palabra)) {
						// si el hashmap no tiene la palabra como key, la añadimos con valor 1
						mapProcesado.put(palabra, 1);
					} else {
						// si el hashmap tiene la palabra como key, suma 1 al valor asignado
						int contador = mapProcesado.get(palabra) + 1;
						mapProcesado.put(palabra, contador);
					}
				}
			}
			dis.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
