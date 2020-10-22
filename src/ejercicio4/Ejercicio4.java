package ejercicio4;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;

public class Ejercicio4 {
	
	
	
	public void generateQR() {
		String inputs[] = input();
		
		try {
			URL link = new URL("https://api.qrserver.com/v1/create-qr-code/?data="+inputs[0]+"&format=jpg&color=255-0-0&size=1000x1000");
			DataInputStream dis = new DataInputStream(link.openStream());
			DataOutputStream dos = new DataOutputStream(new FileOutputStream(inputs[1]));
			
			
			//generamos el fichero
			byte[] buffer = new byte[1024];
			if ((buffer = dis.readAllBytes())!=null) {
					dos.write(buffer);
					
			}
			//cerramos streams
			dos.close();
			dis.close();
			
			
		
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}

	private String[] input() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Mensaje que quieras usar para crear el codigo QR");
		String msg = sc.nextLine();
		
		//controlar espacios de la url
		if(msg.contains(" ")) {
			msg = msg.replace(" ", "+");
		}
		
		System.out.println("Nombre del fichero QR: ");
		String nameQR = sc.nextLine();
		
		//controlar tipo de imagen que tenemos
		if(!nameQR.contains(".jpg")) {
			nameQR += ".jpg";
		}
		String returned[] = {msg, nameQR};
		return returned;
	}
}
