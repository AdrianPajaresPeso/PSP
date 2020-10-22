package ejercicio5;

import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Map;
import java.util.Scanner;

import ejercicio4.Ejercicio4;

public class Ejercicio5 {
	
	
	/**
	 * 
	 * 
	 * */
	public void readCSV() {
		
		
		Scanner sc = new Scanner(System.in);
		System.out.println("Introduzca la ruta del archivo a tratar");
		String file = sc.nextLine();
		printProcess();
		
		try {

			
			// cargamos el documento para gestionarlo
			DataInputStream dis = new DataInputStream(new FileInputStream(file));
			
			//procesamos el documento linea a linea
			String line;
			while ((line = dis.readLine()) != null) {
				String aux[] = line.split(",");

				System.out.println("Generando Qr <" + aux[1] + ">...");
				Ejercicio4.generatedQR(aux[0], aux[1]);//se genera el fichero qr
				Thread.sleep(500);
				System.out.println("Fichero generado!\n");
				Thread.sleep(500);
			}
			
			System.out.println("Todos los QR Generados con éxito");
			dis.close();
//src/ejercicio5/datosEntrada.csv
		} catch (IOException | InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void printProcess(){
		
		try {
			System.out.print("Procesando Fichero");
			Thread.sleep(500);
			System.out.print(".");
			Thread.sleep(500);
			System.out.print(".");
			Thread.sleep(500);
			System.out.print(".\n");
			Thread.sleep(500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
