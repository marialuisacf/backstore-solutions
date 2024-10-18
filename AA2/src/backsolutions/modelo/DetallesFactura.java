package backsolutions.modelo;

import java.util.List;

/**
 * Se crea la clase backsolutions.modelo.DetallesFactura
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

    //Métodos de la clase backsolutions.modelo.DetallesFactura

    /**
     * Representación de la información de la clase backsolutions.modelo.DetallesFactura con toString
     * @return devuelve el método toString de la clase backsolutions.modelo.DetallesFactura
     */
    @Override
    public String toString() {
        return "backsolutions.modelo.DetallesFactura{" +
                "listaInscripcion=" + listaInscripcion +
                '}';
    }
}
