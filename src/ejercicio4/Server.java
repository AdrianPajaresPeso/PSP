package ejercicio4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {

	private static String commandGET(String comand) {

		AgendaTelefonos at = new AgendaTelefonos();
		String name = comand.replace("GET", "");
		String cadenaRetorno = "No hay telefono asociado al nombre ";
		if (name.charAt(0) == ' ') {
			if (at.getTfno(name) == null) {
				cadenaRetorno += name;
			} else {
				name = name.substring(1);
				cadenaRetorno = at.getTfno(name);
			}

		} else {
			if (at.getTfno(name) == null) {
				cadenaRetorno += name;
			} else {
				cadenaRetorno = at.getTfno(name);
			}
		}
		return cadenaRetorno;
	}

	private static void commandPOST(String command) {

	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			ServerSocket servidor = new ServerSocket(6000);

			while (true) {
				System.out.println("Esperando conexion...");
				Socket cliente = servidor.accept();

				System.out.println(cliente.getInetAddress().getHostAddress() + " se ha conectado");

				BufferedReader br = new BufferedReader(new InputStreamReader(cliente.getInputStream()));
				String command = br.readLine();
				System.out.println("datos recividos " + command);
				
				PrintStream ps = new PrintStream(cliente.getOutputStream());
				if (command.contains("GET")) {

					ps.println(commandGET(command));
				} else if (command.contains("POST")) {
					commandPOST(command);
					ps.println("se ha añadido correctamente");
				} else {
					ps.println("El comando no se ha podido interpretar\n");
				}

			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
