package backsolutions.controlador;
/**
 * Clase personalizada para gestionar excepciones específicas de la aplicación.
 */
public class ControladorExcepcion extends RuntimeException {
    public ControladorExcepcion(String message) {
        super(message);
    }
}