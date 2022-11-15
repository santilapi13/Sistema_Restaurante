package persistencia;

import java.io.IOException;

public interface IPersistencia<Serializable> {
    void abrirInput(String nombre) throws IOException;

    void abrirOutput(String nombre) throws IOException;

    void cerrarOutput() throws IOException;

    void cerrarInput() throws IOException;

    void escribir(Serializable objecto) throws IOException;

    Serializable leer() throws IOException, ClassNotFoundException;
}
