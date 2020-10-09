package ejercicio4;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class Ejercicio4 {
	
	public String obtenerValorParametroStreams(String nombreParametro, String ruta) {
		String retornaValor = "";
		try {
			DataInputStream dis = new DataInputStream(new FileInputStream(ruta)) ;
			String linea;
			while((linea = dis.readLine()) != null) {
				
				int position = linea.indexOf("=");
				if(linea.substring(0, position).contains(nombreParametro)) {
					retornaValor = linea.substring(position+1);
				}
				
			}
			dis.close();
		} catch ( IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return retornaValor;
	}

	public String obtenerValorParametroProperties(String nombreParametro, String ruta) {
		String palabra="";
		try {
			FileInputStream fis = new FileInputStream(ruta);
			Properties ppt = new Properties();
			ppt.load(fis);
			
			palabra = ppt.getProperty(nombreParametro);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return palabra;
	}
}
