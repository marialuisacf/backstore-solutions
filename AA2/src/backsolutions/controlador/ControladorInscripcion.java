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

    public void addInscripcion(Inscripcion inscripcion) throws ControladorExcepcion {
        if (inscripciones.stream().anyMatch(i -> i.getNumInscripcion().equals(inscripcion.getNumInscripcion()))) {
            throw new ControladorExcepcion("La inscripción con número " + inscripcion.getNumInscripcion() + " ya existe.");
        }
        inscripciones.add(inscripcion);
        System.out.println("Inscripción añadida con éxito.");
    }

    public void cancelarInscripcion(Socio socio, Excursion excursion) throws ControladorExcepcion {
        Inscripcion inscripcion = inscripciones.stream()
                .filter(i -> i.getSocio().equals(socio) && i.getExcursion().equals(excursion))
                .findFirst()
                .orElse(null);

        if (inscripcion == null) {
            throw new ControladorExcepcion("No se encontró una inscripción para el socio en la excursión proporcionada.");
        }

        inscripciones.remove(inscripcion);
        System.out.println("Inscripción cancelada con éxito.");
    }
}
