package ejercicio7;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.zip.Deflater;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

import ejercicio6.Ejercicio6;

public class Ejercicio7 {

	//almacen de arbol de directorios
	private HashSet<File> hsFile;

	public void menu7() {
		Scanner sc = new Scanner(System.in);
		while (true) {
			printMenu();

			int input = sc.nextInt();
			if (input == 1) {
				// lista todo el contenido del zip seleccionado
				listarZip(sc);

			} else if (input == 2) {

				// busca todos los ficheros del directorio y subdirectorios
				// los comprime, ojo con las rutas, se soluciona error en la descompresion
				comprimirDirectorio(sc);

			} else if (input == 3) {
				// descomprime un fichero zip y corrige error de compresion
				opcionDescomprimir(sc);

			} else {
				break;
			}

		}
	}

	/**
	 * Contiene la UI de consola
	 * 
	 */
	private void printMenu() {
		System.out.println("Seleccione una opción");
		System.out.println("\t1. Mostrar contenido zip.");
		System.out.println("\t2. Comprimir directorio.");
		System.out.println("\t3. Descomprimir un fichero .zip");
		System.out.println("\t4. Salir.");

	}

	/**
	 * Lista el contenido de un fichero zip
	 */
	private void listarZip(Scanner sc) {
		sc.nextLine();// limpiamos el buffer
		System.out.println("Introduce la ruta del archivo zip que quieres mostrar");
		try {

			// input de fichero, si falta extension zip, la añadimos
			String path = sc.nextLine();
			if (!path.contains(".zip")) {
				path = path + ".zip";
			}

			// comprobacion de existencia del fichero
			File f = new File(path);
			if (!f.exists()) {
				System.out.println("No existe el archivo que has seleccionado");
			}

			// Lee el fichero zip
			ZipInputStream zis = new ZipInputStream(new FileInputStream(f));
			ZipEntry ze = null;

			// printa el nombre (path) de cada entrada del fichero
			while ((ze = zis.getNextEntry()) != null) {
				System.out.println(ze.getName());
			}
			zis.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println(e.getMessage());
		}
	}

	/**
	 * metodo de compresion de archivos
	 */
	private void comprimirDirectorio(Scanner sc) {
		sc.nextLine();// limpiamos el buffer de entrada

		// pedimos la ruta que queremos comprimir
		System.out.println("Introduce la ruta del directorio a comprimir");
		String dir = sc.nextLine();

		// pedimos el nombre del fichero zip
		System.out.println("Introduce el nombre del fichero zip");
		String nombre = sc.nextLine();

		// si el nomre no contiene la extension zip, lo añadimos
		if (!nombre.contains(".zip")) {
			nombre = nombre + ".zip";
		}

		File f = new File(dir);
		// si el fichero no existe volvemos al menu
		if (!f.exists()) {
			System.out.println("No se encuentra el directorio");
		} else {

			ZipOutputStream zos = null;
			FileInputStream fis = null;
			try {
				// se crea el archivo zip
				File fileDir = new File(dir);
				zos = new ZipOutputStream(new FileOutputStream(nombre));

				// establecemos el nivel de compresion
				zos.setLevel(Deflater.DEFAULT_COMPRESSION);
				zos.setMethod(Deflater.DEFLATED);

				/**
				 * ¡¡¡CUIDADO!!! En este sitio se almacena un listado de TODOS LOS ARCHIVOS PERO
				 * NO DE LOS DIRECTORIOS Esto es un error que se solucionará en la descompresión
				 * 
				 **/
				List<File> array = Ejercicio6.listarDirectorios(fileDir);
				for (File archivo : array) {
//					System.out.println(archivo.getPath());

					/** Estructura que se repetira en caso de añadir varios ficheros **/

					// creamos entradas para el zip (lo trataremos como un array)
					// cada indice será una entrada y cada entrada un fichero
					ZipEntry entrada = new ZipEntry(archivo.getPath());
					zos.putNextEntry(entrada);

					fis = new FileInputStream(archivo.getPath());
					byte[] buffer = new byte[1024];

					// metemos la inforación en el zip
					int leido = 0;
					while (0 < (leido = fis.read(buffer))) {
						zos.write(buffer, 0, leido);
					}

				}
				zos.closeEntry();
				fis.close();
				zos.close();

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();

			}
		}
	}

	/**
	 * Descomprime el fichero zip
	 */
	private void opcionDescomprimir(Scanner sc) {
		sc.nextLine();
		System.out.println("Escribe el nombre del fichero zip a descomprimir:");
		String path = sc.nextLine();
		String rutaPadre = "";

		// si el nombre no tiene .zip se lo añadimos
		// y guardamos el nombre para usarlo como directorio para crear
		// la carpeta que contendrá toda la descompresion
		if (!path.contains(".zip")) {
			rutaPadre = path + "\\";
			path = path + ".zip";
		}

		try {

			// leemos el archivo zip
			ZipInputStream zis = new ZipInputStream(new FileInputStream(path));

			// preparamos las entradas del zip para leerlas
			ZipEntry entrada;
			System.out.println("Descomprimiendo " + path + "...");
			while (null != (entrada = zis.getNextEntry())) {

				System.out.println(entrada);

				/**
				 * Si el zip contiene las rutas de los directorios comprimidos crea toda la
				 * estructura de directorios
				 */
				if (entrada.isDirectory()) {
					File f = new File(rutaPadre + entrada);
					f.mkdirs();
				} else {

					File dir = new File(rutaPadre + entrada.getName());

					/**
					 * ¡¡¡CUIDADO!!! Aqui se crea un HashSet que almacena el arbol de directorios
					 * del zip para poder crear el mismo arbol de directorios y extraer los
					 * ficheros. Corrige el fallo de los paths de compresion El metodo que guarda
					 * toda esa informacion es recursivo
					 * 
					 **/
					hsFile = comprobarDirectorios(dir, rutaPadre.substring(0, rutaPadre.indexOf("\\")));

					// pasamos toda la informacion a un arrayList para ordenarla
					ArrayList<File> listaDirectorios = new ArrayList<File>();
					listaDirectorios.addAll(hsFile);
					Collections.sort(listaDirectorios);

					/**
					 * Este bucle comprueba que los directorios que hay almacenados estén creados si
					 * no lo están los crean para preparar la estructura de descompresion
					 */
					for (File directorio : listaDirectorios) {
						if (!directorio.exists()) {
							directorio.mkdirs();
						}
					}

					/***/
					FileOutputStream fos = new FileOutputStream(rutaPadre + entrada.getName());
					int leido;
					byte[] buffer = new byte[1024];
					while (0 < (leido = zis.read(buffer))) {
						fos.write(buffer, 0, leido);
					}
					fos.close();
					zis.closeEntry();
				}
				zis.closeEntry();

			}
			zis.close();

			// una vez finalizado eliminamos las rutas de la variable.
			removeHashSet();
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
	}

	/**
	 * metodo recursivo que saca las rutas de los directorios padres este metodo
	 * arregla un fallo con el listado de archivos a la hora de comprimir
	 * 
	 * Utilizamos hashSet ya que pasarán muchas rutas por aqui, y pueden repetirse.
	 * Asi lo que hacemos es almacenar todos los directorios sin que se repita
	 * ninguno
	 * 
	 * Por parámetro se le pasa siempre el directorio padre de la descompresion
	 * para poder parar la recursividad diciendolo que si la ruta del parametro 
	 * de tipo file llega a ser igual, que se detenga
	 */
	private HashSet<File> comprobarDirectorios(File rutas, String parent) {
		
		//este es el principal almacen de rutas que se retornará
		HashSet<File> setFiles = new HashSet<>();
		// si rutas es diferente de null pasa
		if (rutas != null) {

			// si rutas es igual al directorio padre pasado por teclado
			if (!rutas.getPath().equals(parent)) {
				
				// quitamos hasta encontrar el padre
				File f = new File(rutas.getParent());
				setFiles.add(f); // añadimos al hashset
				comprobarDirectorios(f, parent);
			}

		}

		return setFiles;
	}

	/**
	 * elimina todas las rutas para la siguiente descompresión
	 * 
	 */
	private void removeHashSet() {
		for (File file : hsFile) {
			hsFile.remove(file);
		}
	}
}
