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

    // Metodo para mostrar todas las inscripciones
    public List<Inscripcion> mostrarInscripciones() {
        // Comprueba si hay inscripciones
        if (inscripciones.isEmpty()) {
            System.out.println("No hay inscripciones registradas.");
            return inscripciones; // Devuelve la lista vacía
        }

        System.out.println("Lista de Inscripciones:");
        for (Inscripcion inscripcion : inscripciones) {
            // Obtiene los detalles necesarios
            int numSocio = inscripcion.getSocio().getNumSocio(); // Suponiendo que 'nif' es el número de socio
            String nombreSocio = ""; // Añade lógica para obtener el nombre según el tipo de socio

            // Obtener el nombre según el tipo de socio
            if (inscripcion.getSocio() instanceof Estandar) {
                nombreSocio = ((Estandar) inscripcion.getSocio()).getNombre(); // Asegúrate de tener un método getNombre()
            } else if (inscripcion.getSocio() instanceof Federado) {
                nombreSocio = ((Federado) inscripcion.getSocio()).getNombre(); // Asegúrate de tener un método getNombre()
            }

            // Obtener detalles de la excursión
            String fechaExcursion = inscripcion.getExcursion().getFecha().toString(); // Asegúrate de que esto devuelve la fecha correctamente
            String descripcionExcursion = inscripcion.getExcursion().getDescripcion();
            double importe = calcularImporte(inscripcion); // Asegúrate de tener un método para calcular el importe

            // Imprime los detalles de la inscripción
            System.out.printf("Número de Socio: %s, Nombre: %s, Fecha de Excursión: %s, Descripción: %s, Importe: %.2f%n",
                    numSocio, nombreSocio, fechaExcursion, descripcionExcursion, importe);
        }

        return inscripciones; // Devuelve la lista de inscripciones
    }

    // Método para calcular el importe
    private double calcularImporte(Inscripcion inscripcion) {
        double precioBase = inscripcion.getExcursion().getPrecioInscripcion();

        // Aplica descuentos según el tipo de socio
        if (inscripcion.getSocio() instanceof Estandar) {
            return precioBase + inscripcion.getSeguro().getPrecio(); // Asumiendo que tienes un método para obtener el precio del seguro
        } else if (inscripcion.getSocio() instanceof Federado) {
            return precioBase * 0.9; // Descuento del 10% para federados
        } else if (inscripcion.getSocio() instanceof Infantil) {
            return precioBase * 0.5; // Descuento del 50% para infantiles
        }

        return precioBase; // Si no se aplica ningún descuento
    }


}
