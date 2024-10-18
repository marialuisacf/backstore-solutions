package backsolutions.modelo;

import java.util.List;

/**
 * Se crea la clase Factura
 */

public class DetallesFactura {
    /**
     * Atributos de la clase backsolutions.modelo.DetallesFactura
     */
    private List<Inscripcion> listaInscripcion;

    //Constructor de la clase backsolutions.modelo.DetallesFactura con los parámetros necesarios para inicializar un DetalleFactura
    /**
     * Atributos de la clase backsolutions.modelo.DetallesFactura añadidos al constructor
     * @param listaInscripcion parámetro identificativo de la lista de inscripción
     */
    public DetallesFactura(List<Inscripcion> listaInscripcion) {
        this.listaInscripcion = listaInscripcion;
    }

    //Getter y Setter

    /**
     * Getter de listaInscripcion
     * @return devuelve la lista de inscripciones
     */
    public List<Inscripcion> getListaInscripcion() {
        return listaInscripcion;
    }
    /**
     * Setter de listaInscripcion
     * @param listaInscripcion parámetro identificativo de la lista de inscripciones
     */
    public void setListaInscripcion(List<Inscripcion> listaInscripcion) {
        this.listaInscripcion = listaInscripcion;
    }


    //Métodos de la clase DetallesFactura
    //Metodo para generar el detalle de la factura

    public String generarDetalleFactura() {
        StringBuilder detalle = new StringBuilder();
        detalle.append("Detalles de la Factura:\n");

        // Añadir detalles de cada inscripción
        for (Inscripcion inscripcion : listaInscripcion) {
            detalle.append("Inscripción número: ").append(inscripcion.getNumInscripcion()).append("\n");
            detalle.append("Excursión: ").append(inscripcion.getExcursion().getDescripcion()).append("\n");
            detalle.append("Fecha de inscripción: ").append(inscripcion.getFechaInscripcion()).append("\n");
            detalle.append("Precio total: ").append(inscripcion.calculoPrecioTotal()).append("€\n\n");
        }

        return detalle.toString();
    }
}
