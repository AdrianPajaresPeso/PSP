package ejercicio3;

import java.io.IOException;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Date;

public class Server {

	public static void main(String[] args) {
		try {
			ServerSocket server = new ServerSocket(6000);

			while (true) {
				System.out.println("Esperando conexión...");
				Socket cliente = server.accept();

				System.out.println(cliente.getInetAddress().getHostAddress() + " se ha conectado");
				
				PrintStream ps = new PrintStream(cliente.getOutputStream());
				Date today  = new Date();
				String date = today.getHours()+":"+today.getMinutes()+":"+today.getSeconds();
				ps.println(date);
			}
		} catch (IOException e) {
			// TODO: handle exception
		}

	}

}
