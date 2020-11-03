package ejercicio4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;

public class Cliente {

	/**
	 * Metodo para que el cliente elija las opciones que quiera hacer
	 * 
	 */
	public static void menu() {
		Scanner sc = new Scanner(System.in);
		String input;
		while (true) {
			menuText();
			input = sc.nextLine();
			bakeConnection(input);

			// si el usuario introduce exit, el programa cliente se para
			if (input.equals("exit")) {
				break;
			}
		}
	}

	/**
	 * muestra las opciones del usuario
	 */
	public static void menuText() {
		System.out.println("Escribe GET para optener la informacion de un contacto");
		System.out.println("Escribe POST para a�adir un nuevo contacto");
	}

	/**
	 * M�todo que "cocina la conexi�n" recibiendo un input para poder mandarle los
	 * comandos al servidor
	 * 
	 * @param input variable de tipo string que pasa el comando al print stream para
	 *              que el servidor lo entienda
	 */
	private static void bakeConnection(String input) {
		try {
			Socket cliente = new Socket("localhost", 6000);
			PrintStream ps = new PrintStream(cliente.getOutputStream());
			ps.println(input);

			BufferedReader br = new BufferedReader(new InputStreamReader(cliente.getInputStream()));
			String line = br.readLine();
			System.out.println(line);

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		menu();

	}

}
