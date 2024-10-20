package controladorTest;

//Excepcion personalizada

public class SocioNoEncontradoExcepcion extends RuntimeException {
    public SocioNoEncontradoExcepcion(String message) {
        super(message);
    }
    //Se lanzara cuando un socio no se encuentre en la lista de socios durante la inscripcion
}
