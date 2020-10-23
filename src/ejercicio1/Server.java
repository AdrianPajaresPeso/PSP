package ejercicio1;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			ServerSocket server = new ServerSocket(6000);
			while (true) {
				System.out.println("Esperando Conexion...");
				Socket cliente = server.accept();
				
				System.out.println(cliente.getInetAddress().getHostAddress()+" se ha conectado");
				BufferedReader br = new BufferedReader(new InputStreamReader(cliente.getInputStream()));
				
				String msg = br.readLine();
				System.out.println("Mensaje recibido: <"+msg+">");
				PrintStream ps = new PrintStream(cliente.getOutputStream());
				System.out.println("Devolviendo mensaje");
				ps.println(msg);
				
				ps.close();
				br.close();
				cliente.close();
			}
			
		} catch (IOException e) {
			// TODO: handle exception
		}
	}

}
