package ejercicio6;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Ejercicio6 {

	
	public void menu6(String path) {
		File f = new File(path);
		List<File> lista = listarDirectorios(f);

		for (File file : lista) {
			System.out.println(file.getName());
		}
	}

	
	public static List<File> listarDirectorios(File directorio) {

		List<File> listFicheros = new ArrayList<File>();
		// si el directorio que hemos marcado no existe o no tiene contenido retornamos
		// directamente la lista de ficheros
		if (directorio == null || directorio.listFiles() == null) {
			return listFicheros;
		}

		// recorremos el listado de ficheros del directorio pasado por parametro
		for (File fichero : directorio.listFiles()) {

			//Si el elemento es un fichero lo añado a la lista de ficheros
			if (fichero.isFile()) {
				listFicheros.add(fichero);
			} else {
				//si no es fichero
				listFicheros.addAll(listarDirectorios(fichero));
			}

		}
		return listFicheros;
	}
}
