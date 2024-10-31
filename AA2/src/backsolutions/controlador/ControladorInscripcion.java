package backsolutions.controlador;

import backsolutions.modelo.*;
import java.util.ArrayList;
import java.util.List;

public class ControladorInscripcion {
    private List<Inscripcion> inscripciones;
    private List<Excursion> excursiones;

    //Constructor
    public ControladorInscripcion(List<Inscripcion> inscripciones, List<Excursion> excursiones) {
        this.inscripciones = inscripciones;
        this.excursiones = excursiones; // Inicializa la lista de excursiones
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

    }

    // Método para mostrar todas las inscripciones
    public List<Inscripcion> mostrarInscripciones() {
        return inscripciones; // Devuelve la lista de inscripciones
    }

    // Método para calcular el importe
    public double calcularImporte(Inscripcion inscripcion) {
        double precioBase = inscripcion.getExcursion().getPrecioInscripcion();

        // Aplica descuentos según el tipo de socio
        if (inscripcion.getSocio() instanceof Estandar) {
            return precioBase + inscripcion.getSeguro().getPrecio();
        } else if (inscripcion.getSocio() instanceof Federado) {
            return precioBase * 0.9;
        } else if (inscripcion.getSocio() instanceof Infantil) {
            return precioBase * 0.5;
        }

        return precioBase;
    }

    public List<Inscripcion> getInscripciones() {
        return inscripciones; // Devuelve la lista de inscripciones
    }

    public List<Inscripcion> mostrarInscripcionesPorSocio(int numSocio) throws ControladorExcepcion {
        List<Inscripcion> inscripcionesDelSocio = new ArrayList<>();

        // Filtrar las inscripciones para que solo queden las que corresponden al socio dado
        for (Inscripcion inscripcion : inscripciones) {
            if (inscripcion.getSocio().getNumSocio() == numSocio) {
                inscripcionesDelSocio.add(inscripcion);
            }
        }

        return inscripcionesDelSocio;
    }

    public double generarFacturaMensual(Socio socio) throws ControladorExcepcion {
        double cuotaMensual = socio.calculoCuotaMensual(); // Calcula la cuota mensual
        double totalInscripciones = 0.0;

        // Obtener las inscripciones del socio
        List<Inscripcion> inscripciones = mostrarInscripcionesPorSocio(socio.getNumSocio());
        for (Inscripcion inscripcion : inscripciones) {
            totalInscripciones += calcularImporte(inscripcion); // Calcula el importe de cada inscripción
        }

        return totalInscripciones + cuotaMensual; // Devuelve el total de la factura mensual
    }

}
