package ejercicio4;

import java.util.HashMap;
import java.util.Map;

public class AgendaTelefonos {

	private Map<Object, Object> agenda = new HashMap<>();

	public void anadeTfno(String nombre, String tfno) {
		agenda.put(nombre, tfno);
	}

	public String getTfno(String nombre) {
		return (String) agenda.get(nombre);
	}
	public Integer size () {
		return agenda.size();
	}
}
