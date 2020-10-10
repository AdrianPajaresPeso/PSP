package ejercicio6;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Ejercicio6 {

	
	public void menu6(String path) {
		File f = new File(path);
		List<File> lista = listarDirectorios(f);

		for (File file : lista) {
			System.out.println(file);
		}
	}

	
	public static List<File> listarDirectorios(File directorio) {

		ArrayList<File> listaFicheros = new ArrayList<>();

        File[] contenido = directorio.listFiles();

        for (File file : contenido) {
            if (file.isDirectory()) {
                listaFicheros.addAll(listarDirectorios(file));
            } else {
                listaFicheros.add(file);
            }
        }

        return listaFicheros;
	}
}
