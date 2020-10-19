package ejercicio1;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

public class Ejercicio1 {

	public void nslookupCommand(String url) {

		// si es IPv4 pasa
		if (isIPv4(url)) {
			// si es válida pasa
			if (isValidIP(url)) {
				nslookupIp(url);
			}

		} else {
			// si no es IPv4 es nombre de dominio
			nslookupName(url);

		}

	}

	private void nslookupIp(String IP) {
		// TODO Auto-generated method stub
		InetAddress ia;
		try {
			ia = InetAddress.getByName(IP);
			System.out.println("Address: " + ia.getHostAddress());
			System.out.println("Server: " + ia.getHostName());

		} catch (Exception e) {
			// TODO: handle exception
		}
	}

	private void nslookupName(String url) {
		InetAddress ia;
		try {
			ia = InetAddress.getByName(url);
			System.out.println("Name: " + ia.getHostName());
			System.out.println("Address: " + ia.getHostAddress());
			System.out.println("Server: " + ia.getCanonicalHostName());
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private boolean isValidIP(String url) {
		boolean flag = true;
		// dividimos el string en partes
		String partes[] = url.split("\\.");

		for (String string2 : partes) {
			// si el numero supera el 255 no es ip válida
			if (Integer.parseInt(string2) > 255) {
				flag = false;
				System.out.println("La IP introducida no es válida");
				break;
			}
		}

		return flag;
	}

	private boolean isIPv4(String ip) {
		// variable para retornar
		Boolean flag = true;

		// si la cadena introducida está vacia o es null, retorna false
		if (ip == null || ip.isEmpty()) {
			flag = false;
		}

		// dividimos el string en partes
		String partes[] = ip.split("\\.");

		// si las partes son diferentes de IPv4
		if (partes.length != 4) {
			flag = false;
		} else {

			// si tiene 4 partes hay que comprobar que no contengan letras
			for (String string : partes) {

				// si el char es digito comprobamos que int es
				if (!isDigit(string)) {

					// si contiene letras no es IPv4
					flag = false;
					break;
				}

			}

		}

		return flag;
	}

	private Boolean isDigit(String string) {
		Boolean auxFlag = true;

		for (int i = 0; i < string.length(); i++) {

			// si el caracter es alfabético retorna false;
			if (Character.isAlphabetic(string.charAt(i))) {
				auxFlag = false;
			}
			if (!auxFlag) {
				break;
			}
		}

		return auxFlag;
	}
}
