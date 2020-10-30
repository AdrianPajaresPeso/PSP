package ejercicio4;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
	private static AgendaTelefonos at = new AgendaTelefonos();
	/**
	 * Lleva a cabo la funcionalidad del comando GET
	 * */
	private static String commandGET(String comand) {

		
		String name = comand.replace("GET ", "");
		String name2 = name.replace("\n", "");
		String cadenaRetorno = "No hay telefono asociado al nombre ";
		if (at.getTfno(name2) == null) {
			cadenaRetorno += name2;
		} else {
			cadenaRetorno = at.getTfno(name2);
		}

		return cadenaRetorno;
	}

	/**
	 * Lleva a cabo la funcionalidad del comando POST
	 * @return true, si se ha añadido correctamente el numero de telefono</br>
	 * false, si el numero no se ha podido añadir o ya existía
	 * */
	private static Boolean commandPOST(String command) {
		boolean flag = false;
		
		
		//numero de telefonos almacenados en la agenda
		int antes = at.size();
		
		String aux = command.replace("POST ", "");
		if(aux.contains(" ")) {
			String data[] = aux.split(" ");
			String name = data[0];
			String numero = data[1];
			at.anadeTfno(name, numero);
			int despues = at.size();
			if (antes < despues) {
				flag = true;
			}
		}
		
		

		return flag;
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
				System.out.println("datos recibidos " + command);

				PrintStream ps = new PrintStream(cliente.getOutputStream());
				if (command.contains("GET")) {

					ps.println(commandGET(command));
				} else if (command.contains("POST")) {
					if (commandPOST(command)) {
						ps.println("Se ha añadido correctamente");
					} else {
						ps.println("No se ha añadido correctamente, los datos son no son válidos, o este numero ya existía");
					}

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
