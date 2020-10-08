import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

public class Ejercicio7 {
	public void menu7() {
		Scanner sc = new Scanner(System.in);
		while (true) {
			printMenu();

			int input = sc.nextInt();
			if (input == 1) {
				option1(sc);

			} else if (input == 2) {
				option2();
			} else if (input == 3) {
				option3();
			} else {
				break;
			}

		}
	}

	/**
	 * Lista el contenido de un fichero zip
	 * */
	private void option1(Scanner sc) {
		sc.nextLine();// limpiamos el buffer
		try {
			File f = new File(sc.nextLine());
			ZipInputStream zis = new ZipInputStream(new FileInputStream(f));
			ZipEntry ze = null;

			while ((ze = zis.getNextEntry()) != null) {
				System.out.println(ze.getName());
			}

			zis.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void option3() {

	}

	private void option2() {

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
