package backsolutions.controlador;

import backsolutions.modelo.*;

import java.util.List;

public class ControladorInscripcion {
    private List<Inscripcion> inscripciones;
    private List<Excursion> excursiones;

    //Constructor
    public ControladorInscripcion(List<Inscripcion> inscripciones, List<Excursion> excursiones) {
        this.inscripciones = inscripciones;
        this.excursiones = excursiones; // Inicializa la lista de excursiones
    }

    //metodo para mostrar todas las inscripciones
    public List<Inscripcion> mostrarInscripciones() {
        return inscripciones; // Devuelve la lista de inscripciones
    }

    //metodo para añadir inscripciones con excepciones personalizadas
    public void addInscripcion(Inscripcion inscripcion) throws InscripcionInvalidaExcepcion {
        // Verificar si la excursión existe
        boolean excursionExiste = excursiones.stream().anyMatch(e -> e.equals(inscripcion.getExcursion()));
        if (!excursionExiste) {
            throw new InscripcionInvalidaExcepcion("La excursión " + inscripcion.getExcursion().getCodigo() + " no existe.");
        }

        // Verificar si el socio ya está inscrito en la misma excursión
        if (inscripciones.stream().anyMatch(i -> i.getSocio().equals(inscripcion.getSocio()) && i.getExcursion().equals(inscripcion.getExcursion()))) {
            throw new InscripcionInvalidaExcepcion("El socio ya está inscrito en esta excursión.");
        }

        // Si pasa todas las verificaciones, añade la inscripción
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
