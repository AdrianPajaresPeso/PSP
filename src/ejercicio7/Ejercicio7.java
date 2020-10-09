package ejercicio7;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.zip.Deflater;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import java.util.zip.ZipOutputStream;

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
				option3();

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

	private void option3() {

	}

	private void option2(Scanner sc) {
		sc.nextLine();
		System.out.println("Introduce la ruta del directorio a comprimir");
		String dir = sc.nextLine();
		System.out.println("Introduce el nombre del fichero zip");
		String nombre = sc.nextLine();
		if (!nombre.contains(".zip")) {
			nombre = nombre + ".zip";
		}

		try {
			ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(nombre));

			zos.setLevel(Deflater.DEFAULT_COMPRESSION);
			zos.setMethod(Deflater.DEFLATED);

			
			
			/** Estructura que se repetira en caso de añadir varios ficheros **/
			
			ZipEntry entrada = new ZipEntry("fich2.txt");
			zos.putNextEntry(entrada);

			FileInputStream fis = new FileInputStream("fich2.txt");
			byte[] buffer = new byte[1024];
			int leido = 0;
			while (0 < (leido = fis.read(buffer))) {
				zos.write(buffer, 0, leido);
			}

			zos.closeEntry();
			
			fis.close();
			zos.close();
//			ZipOutputStream zos = new ZipOutputStream(new FileOutputStream(dir+"/"+nombre));
//			zos.setLevel(Deflater.DEFAULT_COMPRESSION);
//			zos.setMethod(Deflater.DEFLATED);
//
//			ZipEntry entrada = new ZipEntry(nombre);
//			zos.putNextEntry(entrada);
//			File f = new File(dir);
//			List<File> listaFicheros = Ejercicio6.listarDirectorios(f);
//			FileInputStream fis = null;
//			for (File archivo : listaFicheros) {
//				fis = new FileInputStream(archivo.getAbsolutePath());
//				byte buffer[]= new byte[1024];
//				int leido = 0;
//				while(0<(leido = fis.read(buffer))) {
//					zos.write(buffer, 0, leido);
//				}
//			}
//			
//			zos.closeEntry();
//			fis.close();
//			zos.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void printMenu() {
		System.out.println("Seleccione una opción");
		System.out.println("\t1. Mostrar contenido zip.");
		System.out.println("\t2. Comprimir directorio.");
		System.out.println("\t3. Descomprimir un fichero .zip");
		System.out.println("\t4. Salir.");

		System.out.println("~$:");
	}

}
