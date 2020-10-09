package ejercicio3;
import java.io.File;
import java.text.SimpleDateFormat;

public class Ejercicio3 {
	public void listDirectory(String ruta) {

		File directory = new File(ruta);
		
		
		
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy HH:mm:ss");
		System.out.println("Nombre\t\t\t\tTamaño\t\tUltima Modificacion\t\tRuta\t\t\t\tTipo");
		System.out.println(
				"-----------------------------------------------------------------------------------------------------------------------------------------");
		
		
		
		for (File elementos : directory.listFiles()) {

			if (elementos.isDirectory()) {

				System.out.format("%-25s%8d%5s%30s%30s%20s", elementos.getName(), elementos.length() / 1024, "KB",
						sdf.format(elementos.lastModified()), elementos.getParent(), "DIRECTORIO");
				System.out.println();
			}
			if (elementos.isFile()) {

				System.out.format("%-25s%8d%5s%30s%30s%20s", elementos.getName(), elementos.length() / 1024, "KB",
						sdf.format(elementos.lastModified()), elementos.getParent(), "Archivo");
				System.out.println();
			}

		}

	}
	
}
