package ejercicio3;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Ejercicio3 {

	public void infoQuery(String url) {

		try {
			URL link = new URL(url);
			try {
				URLConnection uc = link.openConnection();

				Map<String, List<String>> mapa = uc.getHeaderFields();
				Set<String> claves = mapa.keySet();
				
				System.out.println("----------------------Cabeceras de la respuesta-----------------------");
				for (String clave : claves ) {
					String valor = uc.getHeaderField(clave);
					System.out.println("<"+clave+">\t"+valor);
				}

				System.out.println("----------------------Contenido de la respuesta-----------------------");
				System.out.println(link.getContent());

			} catch (IOException e) {
				// TODO: handle exception
			}

		} catch (MalformedURLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	}
}
