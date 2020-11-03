package ejercicio1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;
import java.util.Scanner;


public class Cliente {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			Scanner sc = new Scanner (System.in);
			Socket cliente = new Socket("localhost", 6000);
			PrintStream ps = new PrintStream(cliente.getOutputStream());
			System.out.println("Escribe el mensaje que quieras enviar:");
			ps.println(sc.nextLine());
			
			BufferedReader br = new BufferedReader(new InputStreamReader(cliente.getInputStream()));
			String msg = br.readLine();
			System.out.println(msg);
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

}
