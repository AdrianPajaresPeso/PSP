package ejercicio4;

import java.net.InetAddress;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;

public class Ejercicio4 {
	
	public void generateQR() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Mensaje que quieras usar para crear el codigo QR");
		String msg = sc.nextLine();
		try {
			URL link = new URL("https://api.qrserver.com/v1/create-qr-code/?data="+msg);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		
	}
}
