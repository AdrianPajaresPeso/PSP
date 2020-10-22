package ejercicio6;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;

public class Ejercicio6 {

	public void searchPoketMonster() {
		Scanner sc = new Scanner(System.in);
		System.out.println("¿Que Pokemon quieres capturar? Dinos su nombre:");
		String poketMonster = sc.nextLine();
		
		try {
			
			//Creas la url y la conexion
			URL link = new URL("https://pokeapi.co/api/v2/pokemon/"+poketMonster);
			
			URLConnection conn = link.openConnection();
			conn.addRequestProperty("User-Agent", "Java Aplication");
			
			//obtienes la informacion con el openStream
			DataInputStream dis = new DataInputStream(link.openStream());
			DataOutputStream dos = new DataOutputStream(new FileOutputStream("src/ejercicio6/"+poketMonster+".json"));
			printStory();
			
			int cont;
			while ((cont = dis.read())!=-1) {
				dos.writeByte(cont);
			}
			dos.close();
			dis.close();
			System.out.println("¡Pokémon capturado!");
		} catch ( IOException e) {
			// TODO Auto-generated catch block
			System.out.println("Lo sentimos, el pokemon solicitado no se encuentra o no existe");
			searchPoketMonster();
		}
	}

	private void printStory() {
		// TODO Auto-generated method stub
		try {

			System.out.print("Buscando Pokémon solicitado");
			Thread.sleep(500);
			System.out.print(".");
			Thread.sleep(500);
			System.out.print(".");
			Thread.sleep(500);
			System.out.print(".\n");
			Thread.sleep(5000);
			
			
			System.out.println("¡Pokémon encontrado, vamos a intentar capturarlo!");
			Thread.sleep(5000);
			System.out.print("Capturando Pokémon");
			Thread.sleep(500);
			System.out.print(".");
			Thread.sleep(500);
			System.out.print(".");
			Thread.sleep(500);
			System.out.print(".\n");
			Thread.sleep(500);
			
		} catch (Exception e) {
			// TODO: handle exception
		}
	}
}
