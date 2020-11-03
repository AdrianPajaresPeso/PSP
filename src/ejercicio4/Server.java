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
	 * 
	 * Metodo que tiene la funcionalidad del comando GET, el cual obtiene y devuelve
	 * como un string el numero de telefono que se ha almacenado en la agenda
	 * telefonica
	 * 
	 * @param comand variable de tipo String, que pasa el comando a realizar, este
	 *               ser� tratado para obtener la informacion
	 * @return cadenaRetorno, variable de tipo string que almacena el mensaje del
	 *         resultado del comando si el comando consigue un dato, mandar� un
	 *         nombre
	 */
	private static String commandGET(String comand) {

		String name = comand.replace("GET ", "");
		String name2 = name.replace("\n", "");
		String cadenaRetorno = "No hay telefono asociado al nombre ";
		if (at.getTfno(name2) == null) {
			cadenaRetorno += name2;
		} else {
			cadenaRetorno = at.getTfno(name);

		}

		return cadenaRetorno;
	}

	/**
	 * Lleva a cabo la funcionalidad del comando POST
	 * 
	 * @return true, si se ha a�adido correctamente el numero de telefono</br>
	 *         false, si el numero no se ha podido a�adir o ya exist�a
	 */
	private static Boolean commandPOST(String command) {
		boolean flag = false;

		// numero de telefonos almacenados en la agenda
		int antes = at.size();

		String aux = command.replace("POST ", "");
		if (aux.contains(" ")) {
			String data[] = aux.split(" ");
			String name = data[0];
			String numero = data[1];
			at.anadeTfno(name, numero);

			// esta funci�n ha sido a�adida a mano en la clase para poder llevar a cabo la
			// comprobaci�n
			int despues = at.size();
			if (antes < despues) {
				flag = true;
			}
		}

		return flag;
	}

	/**
	 * M�todo del cual cuelgan todos los dem�s, aqui se interpreta el comando y se
	 * pone en marcha el servidor en funcion del comando se ejecutar�n unos m�todos
	 * u otros.
	 */
	public static void startServer() {
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
				// si el comando contiene la palabra GET
				if (command.contains("GET")) {
					// ejecutaremos el metodo que tiene la funcionalidad del GET
					ps.println(commandGET(command));

					// si el comando contiene la palabra POST
				} else if (command.contains("POST")) {

					// si el comando se ejecuta correctamente se pasar� al cliente la cadena de
					// confirmaci�n de que se ha a�adido correctamente el numero a la agenda
					if (commandPOST(command)) {
						ps.println("Se ha a�adido correctamente");
					} else {
						ps.println(
								"No se ha a�adido correctamente, los datos son no son v�lidos, o este numero ya exist�a");
					}

				} else {
					// si el comando no tiene ninguna palabra clave se le envia el mensaje al
					// cliente
					ps.println("El comando no se ha podido interpretar\n");
				}

			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public static void main(String[] args) {
		startServer();
	}

}
