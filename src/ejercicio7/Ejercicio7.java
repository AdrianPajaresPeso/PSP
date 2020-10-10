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

	HashSet<File> hsFile;

	public void menu7() {
		Scanner sc = new Scanner(System.in);
		while (true) {
			printMenu();

			int input = sc.nextInt();
			if (input == 1) {
				listarZip(sc);

			} else if (input == 2) {
				comprimirDirectorio(sc);

			} else if (input == 3) {
				option3(sc);

			} else {
				break;
			}

		}
	}

	/**
	 * Lista el contenido de un fichero zip
	 */
	private void listarZip(Scanner sc) {
		sc.nextLine();// limpiamos el buffer
		System.out.println("Introduce la ruta del archivo zip que quieres mostrar");
		try {
			String path = sc.nextLine();
			if (!path.contains(".zip")) {
				path = path + ".zip";
			}
			File f = new File(path);
			if (!f.exists()) {
				System.out.println("No existe el archivo que has seleccionado");
			}
			ZipInputStream zis = new ZipInputStream(new FileInputStream(f));
			ZipEntry ze = null;

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
	 * Descomprime el fichero zip
	 */
	private void option3(Scanner sc) {
		sc.nextLine();
		System.out.println("Escribe el nombre del fichero zip a descomprimir:");
		String path = sc.nextLine();
		String rutaPadre = "";
		// si el archivo introducido no tiene zip, lo guardamos como ruta
		// y le ponemos .zip para poder extraerlo
		if (!path.contains(".zip")) {
			rutaPadre = path + "\\";
			path = path + ".zip";
		}

		try {

			ZipInputStream zis = new ZipInputStream(new FileInputStream(path));

			ZipEntry entrada;

			while (null != (entrada = zis.getNextEntry())) {

				System.out.println(entrada);
				System.out.println("Descomprimiendo...");
				//crea el primer directorio para ir extrayendo los archivos
				if (entrada.isDirectory()) {
					File f = new File(rutaPadre + entrada);
					f.mkdirs();
				} else {

					File dir = new File(rutaPadre + entrada.getName());

					hsFile = comprobarDirectorios(dir, rutaPadre.substring(0, rutaPadre.indexOf("\\")));
					ArrayList<File> alistFile = new ArrayList<File>();
					alistFile.addAll(hsFile);

					Collections.sort(alistFile);
					for (File file : alistFile) {
						if (!file.exists()) {
							file.mkdirs();
						}
					}
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
			removeHashSet();
		} catch (IOException e) {
			System.out.println(e.getMessage());
		}
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

	/**
	 * metodo recursivo que saca las rutas de los directorios padres este metodo
	 * arregla un fallo con el listado de archivos a la hora de comprimir
	 */
	private HashSet<File> comprobarDirectorios(File rutas, String parent) {
		HashSet<File> setFiles = new HashSet<>();
		// si rutas es diferente de null pasa
		if (rutas != null) {

			// si rutas es igual al directorio padre pasado por teclado
			if (!rutas.getPath().equals(parent)) {
				File f = new File(rutas.getParent());
				setFiles.add(f);
				comprobarDirectorios(f, parent);
			}

		}

		return setFiles;
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
					System.out.println(archivo.getPath());
					/** Estructura que se repetira en caso de añadir varios ficheros **/

					ZipEntry entrada = new ZipEntry(archivo.getPath());
					zos.putNextEntry(entrada);

					fis = new FileInputStream(archivo.getPath());
					byte[] buffer = new byte[1024];
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

}
