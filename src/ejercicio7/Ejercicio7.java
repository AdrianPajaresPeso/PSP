package ejercicio7;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Scanner;
import java.util.zip.Deflater;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

import ejercicio6.Ejercicio6;

public class Ejercicio7 {
	public void menu7() {
		Scanner sc = new Scanner(System.in);
		while (true) {
			printMenu();

			int input = sc.nextInt();
			if (input == 1) {
				option1(sc);

			} else if (input == 2) {
				option2(sc);

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
	private void option1(Scanner sc) {
		sc.nextLine();// limpiamos el buffer
		System.out.println("Introduce la ruta del archivo zip que quieres mostrar");
		try {

			File f = new File(sc.nextLine());
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

	private void option3(Scanner sc) {
		sc.nextLine();
		String path = sc.nextLine();
		try {

			ZipInputStream zis = new ZipInputStream(new FileInputStream(path));

			ZipEntry entrada;
			while (null != (entrada = zis.getNextEntry())) {
				System.out.println(entrada.getName());
				FileOutputStream fos = new FileOutputStream(entrada.getName());
				int leido;
				byte[] buffer = new byte[1024];
				while (0 < (leido = zis.read(buffer))) {
					fos.write(buffer, 0, leido);
				}
				fos.close();
				zis.closeEntry();

			}
			zis.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void option2(Scanner sc) {
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
		ZipOutputStream zos = null;
		FileInputStream fis = null;
		try {
			File fileDir = new File(dir);
			zos = new ZipOutputStream(new FileOutputStream(nombre));

			zos.setLevel(Deflater.DEFAULT_COMPRESSION);
			zos.setMethod(Deflater.DEFLATED);

			List<File> array = Ejercicio6.listarDirectorios(fileDir);

			for (File archivo : array) {

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

		} finally {
			try {

				zos.closeEntry();
				fis.close();
				zos.close();

			} catch (Exception e2) {
				// TODO: handle exception
			}
		}
	}

	private Boolean existeZip(File dir, String nombre) {
		Boolean flag = false;
		List<File> listaArchivos = Ejercicio6.listarDirectorios(dir);

		for (File file : listaArchivos) {
			if (file.getName().equals(nombre)) {
				flag = true;
			}
		}

		return flag;
	}

	private void printMenu() {
		System.out.println("Seleccione una opción");
		System.out.println("\t1. Mostrar contenido zip.");
		System.out.println("\t2. Comprimir directorio.");
		System.out.println("\t3. Descomprimir un fichero .zip");
		System.out.println("\t4. Salir.");

	}

}
