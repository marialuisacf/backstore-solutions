package backsolutions.controlador;

import backsolutions.modelo.Inscripcion;
import backsolutions.modelo.Socio;
import backsolutions.modelo.Excursion;
import java.util.List;

public class ControladorInscripcion {
    private List<Inscripcion> inscripciones;

    public ControladorInscripcion(List<Inscripcion> inscripciones) {
        this.inscripciones = inscripciones;
    }

    //metodo para añadir inscripciones con excepciones personalizadas
    public void addInscripcion(Inscripcion inscripcion) throws InscripcionInvalidaExcepcion {
        // Verificar si la inscripción ya existe
        if (inscripciones.stream().anyMatch(i -> i.getNumInscripcion().equals(inscripcion.getNumInscripcion()))) {
            throw new InscripcionInvalidaExcepcion("La inscripción con número " + inscripcion.getNumInscripcion() + " ya existe.");
        }
        inscripciones.add(inscripcion);
        System.out.println("Inscripción añadida con éxito.");
    }

    //metodo para cancelar inscripciones con excepciones personalizadas
    public void cancelarInscripcion(Socio socio, Excursion excursion) throws InscripcionInvalidaExcepcion, SocioNoEncontradoExcepcion {
        // Verificar si el socio existe
        if (socio == null) {
            throw new SocioNoEncontradoExcepcion("El socio no existe.");
        }

        // Buscar la inscripción
        Inscripcion inscripcion = inscripciones.stream()
                .filter(i -> i.getSocio().equals(socio) && i.getExcursion().equals(excursion))
                .findFirst()
                .orElse(null);

        if (inscripcion == null) {
            throw new InscripcionInvalidaExcepcion("No se encontró una inscripción para el socio en la excursión proporcionada.");
        }

        inscripciones.remove(inscripcion);
        System.out.println("Inscripción cancelada con éxito.");
    }


}
