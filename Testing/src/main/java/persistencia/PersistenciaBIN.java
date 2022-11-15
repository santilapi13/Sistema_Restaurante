package persistencia;

import java.io.*;

public class PersistenciaBIN implements IPersistencia<Serializable> {

	private FileOutputStream fileoutput;
	private FileInputStream fileinput;
	private ObjectOutputStream objectoutput;
	private ObjectInputStream objectinput;

	public void abrirInput(String nombre) throws IOException {
		fileinput = new FileInputStream(nombre);
		objectinput = new ObjectInputStream(fileinput);

	}

	public void abrirOutput(String nombre) throws IOException {
		fileoutput = new FileOutputStream(nombre);
		objectoutput = new ObjectOutputStream(fileoutput);

	}

	public void cerrarOutput() throws IOException {
		if (objectoutput != null)
			objectoutput.close();

	}

	public void cerrarInput() throws IOException {
		if (objectinput != null)
			objectinput.close();

	}

	public void escribir(Serializable serializable) throws IOException {
		if (objectoutput != null)
			objectoutput.writeObject(serializable);
	}

	public Serializable leer() throws IOException, ClassNotFoundException {
		Serializable serializable = null;
		if (objectinput != null)
			serializable = (Serializable) objectinput.readObject();
		return serializable;
	}

}