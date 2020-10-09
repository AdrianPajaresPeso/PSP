package ejercicio2;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class Ejercicio2 {

	public void copiarFichero(String ruta) {
		try {
			DataInputStream dis = new DataInputStream(new FileInputStream(ruta));

			Integer posExtension = ruta.indexOf(".");// obtenemos la posicion de la extension
			String extension = ruta.substring(posExtension); // guardamos la extension
			String fileName = ruta.substring(0, posExtension); // guardamos el nombre

			DataOutputStream dos = new DataOutputStream(new FileOutputStream(fileName + "_copy" + extension));

			byte cuchara[] = new byte[1024];
			int codigo = dis.read(cuchara);

			while (codigo != -1) {
				dos.write(cuchara);
				codigo = dis.read(cuchara);
			}
			dis.read(cuchara);

			dis.close();
			dos.close();

		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
