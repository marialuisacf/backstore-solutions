package controladorTest;

//Excepcion personalizada

public class InscripcionInvalidaExcepcion extends RuntimeException {
    public InscripcionInvalidaExcepcion(String message) {
        super(message);
    }

    //Esta excepcion se lanzara cuando la inscripcion no cumpla con los requisitos como que la excursion ya haya comenzado
}
