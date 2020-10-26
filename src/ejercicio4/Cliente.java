package ejercicio4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.Socket;

public class Cliente {

	public static void main(String[] args){
		try {
			Socket cliente = new Socket("localhost", 6000);
			PrintStream ps = new PrintStream(cliente.getOutputStream());
			ps.println("GET PEPE\n");
			
			BufferedReader br = new BufferedReader(new InputStreamReader(cliente.getInputStream()));
			String line = br.readLine();
			System.out.println(line);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		

	}
}
