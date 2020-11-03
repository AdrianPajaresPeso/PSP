package ejercicio3;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;


public class Cliente {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			Socket cliente = new Socket("localhost",6000);
			BufferedReader br = new BufferedReader(new InputStreamReader(cliente.getInputStream()));
			String fechaStr = br.readLine();
			Date fecha =  new SimpleDateFormat("HH:mm:ss").parse(fechaStr);
			System.out.println(fechaStr);
			
		} catch (IOException | ParseException e) {
			e.printStackTrace();
		}
	}

}
