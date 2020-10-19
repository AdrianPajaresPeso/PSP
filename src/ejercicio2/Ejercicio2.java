package ejercicio2;

import java.io.IOException;
import java.net.InetAddress;
import java.net.URL;

public class Ejercicio2 {

	public void splitUrl(String url0) {
		InetAddress ia;
		URL url;
		try {

			url = new URL(url0);
			ia = InetAddress.getByName(url.getHost());
			System.out.println("----------------------------------------");
			System.out.println("Protocolo: <" + url.getProtocol() + ">");
			System.out.println("Hostname: <" + url.getHost() + ">");
			
			if (url.getPort() == -1)
				System.out.println("Puerto: <" + url.getDefaultPort() + ">");
			else
				System.out.println("Puerto: <" + url.getPort() + ">");
			
			System.out.println("Path: <" + url.getPath() + ">");
			System.out.println("Recurso: <" + url.getFile() + ">");
			System.out.println("IP host: <" + ia.getHostAddress() + ">");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

}
