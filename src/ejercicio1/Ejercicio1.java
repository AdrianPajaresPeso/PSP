package ejercicio1;
import java.io.BufferedReader;
import java.io.FileReader;

public class Ejercicio1 {
	
	public int obtenerNumeroRepeticiones(String ruta, String iniciales) {
		int repeticiones = 0;
		try {
			BufferedReader br = new BufferedReader(new FileReader(ruta));
			String linea = br.readLine();
			
			while(linea != null) {
				if(linea.equals(iniciales)) {
					repeticiones++;
				}
				linea = br.readLine();
			}
			br.close();
		} catch (Exception e) {
			// TODO: handle exception
		}
		return repeticiones;
	}
}
