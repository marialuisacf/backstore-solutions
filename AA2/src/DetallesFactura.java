import java.util.List;

/**
 * Se crea la clase DetallesFactura
 */

public class DetallesFactura {
    /**
     * Atributos de la clase DetallesFactura
     */
    private List<Inscripcion> listaInscripcion;

    //Constructor de la clase DetallesFactura con los parámetros necesarios para inicializar un DetalleFactura

    /**
     * Atributos de la clase DetallesFactura añadidos al constructor
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

    /**
     * Representación de la información de la clase DetallesFactura con toString
     * @return devuelve el método toString de la clase DetallesFactura
     */
    @Override
    public String toString() {
        return "DetallesFactura{" +
                "listaInscripcion=" + listaInscripcion +
                '}';
    }
}
