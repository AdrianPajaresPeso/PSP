import java.awt.List;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

public class Ejercicio2 {

//	public void copiarFichero(String ruta) {
//		try {
//			DataInputStream dis = new DataInputStream(new FileInputStream(ruta));
//			DataOutputStream dos = new DataOutputStream(new FileOutputStream("2"+ruta));
//			
//			byte array[] = new byte[1024];
//
//			byte b =dis.readByte();
//			while (b != -1) {
//				
//				a
//				b = dis.readByte();
//			}
//			dis.close();
//			
//			for (Byte b2 : lista) {
//				dos.write(b2);
//			}
//			
//			dos.close();
//		} catch (IOException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	
//	}
	public void copiarFichero(String ruta) {
        try {
            DataInputStream dis = new DataInputStream(new FileInputStream(ruta));
            DataOutputStream dos = new DataOutputStream(new FileOutputStream(ruta.substring(0 , ruta.lastIndexOf('.')) + " - copia" + ruta.substring(ruta.lastIndexOf('.'))));
            
            byte[] buffer = new byte[1024];
            
            int codigo = dis.read(buffer);
            while (codigo != -1) {
                dos.write(buffer);
                codigo = dis.read(buffer);
                
            }
            
            dis.read(buffer);
            
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
}	
