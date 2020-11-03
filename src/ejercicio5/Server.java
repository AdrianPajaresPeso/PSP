package ejercicio5;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

/**
 * @author Adrian
 *
 */
public class Server {

	
	/**
	 * Función principal del servidor, en ella se llama a la función de extracción
	 * de datos que manda el usuario para poder llevar a cabo las tareas asignadas.
	 * Antes de entrar en funcionamiento total, le pregunta al usuario el puerto que
	 * quiere abrir para recibir la informacion
	 */
	public static void startServer() {
		try {

			// se pide al usuario el puerto por el que se va a recibir la información.
			Scanner sc = new Scanner(System.in);
			System.out.println("Establece el numero del puerto que quieres abrir para esta tarea");

			Integer input = sc.nextInt();
			sc.nextLine(); // limpieza del buffer de entrada del servidor

			// definicion del puerto para el servidor
			ServerSocket server = new ServerSocket(input);

			while (true) {
				System.out.println("Esperando conexion...");
				Socket cliente = server.accept();
				System.out.println(cliente.getInetAddress().getHostAddress() + " se ha conectado");

				extractData(cliente);// extrae todos los datos del fichero que se ha recibido
				
				
				
				cliente.close();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * Metodo que extrae los datos que le manda el cliente al servidor estos datos
	 * se guardarán en un fichero con el nombre de "Recibido_ip del cliente", si el
	 * fichero existe ya, generará otro fichero con el numero siguiente al que le
	 * toca
	 * 
	 * @param cliente es el socket del cliente por el cual está entrando la
	 *                información al servidor. No se debe cerrar la conexion dentro
	 *                ya que se cierra despues de la llamada a esta funcion en su
	 *                funcion padre
	 * @throws IOException
	 */
	private static void extractData(Socket cliente) throws IOException {

		// se crea un buffered Reader para poder recoger toda la info
		BufferedReader br = new BufferedReader(new InputStreamReader(cliente.getInputStream()));
		File f = new File("Recibido_" + cliente.getInetAddress().getHostAddress());

		String fileName = "";

		// si el fichero existe se llama a la funcion nuevo nombre para comprobar el
		// numero de copia que es y asignarle un nuevo nombre
		if (f.exists()) {
			fileName = nuevoNombre("Recibido_" + cliente.getInetAddress().getHostAddress());
		} else {
			fileName = "Recibido_" + cliente.getInetAddress().getHostAddress();
			
		}
		

		// se crea el fichero y se escribe su contenido
		FileOutputStream dos = new FileOutputStream(fileName);
		
		byte buffer[] = new byte[1024];
		int dato;
		while ((dato = br.read()) != -1) {
			dos.write(dato);
			if(String.valueOf(dato).equals("")) {
				break;
			}
		}
		
		System.out.println("El servidor ha recibido "+f.getName() + "   < " + f.length()  + " Bytes >");
		
		
		dos.close();
		
		
	}

	/**
	 * Controla el nombre del fichero que se generará en la transmision del cliente
	 * al servidor
	 * 
	 * @param name nombre del fichero en caso de que ya exista
	 * @return nombre del nuevo fichero que se generará
	 */
	private static String nuevoNombre(String name) {

		// fichero para comprobaciones
		File f = new File(name);

		// cadena de texto a retornar
		String nuevoNombre = "";
		// si el fichero existe se ejecutará el bucle, recogiendo siempre el nombre del
		// fichero para devolverlo en caso de que no exista
		for (int i = 1; f.exists(); i++) {
			// al fichero se le añade un nuevo path
			f = new File(name + "_" + i);
			nuevoNombre = name + "_" + i;
		}
		return nuevoNombre;
	}

	public static void main(String[] args) {
		startServer();

	}

}
