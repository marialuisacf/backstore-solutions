package backsolutions.modelo;

import java.util.ArrayList;
import java.util.List;

/**
 * Se crea la clase abstracta backsolutions.modelo.Socio
 */

public abstract class Socio {

    /**
     * Atributos de la clase asbtracta backsolutions.modelo.Socio
     */
    private int numSocio;
    private String nombre;
    private List<Inscripcion> inscripciones;

    //Constructor de la clase backsolutions.modelo.Socio con los parámetros necesarios para inicializar un backsolutions.modelo.Socio

    /**
     * Atributos de la clase backsolutions.modelo.Socio añadidos en el constructor
     * @param numSocio parámetro distintivo del número de socio
     * @param nombre parámetro del nombre del socio
     */
    public Socio(int numSocio, String nombre) {
        this.numSocio = numSocio;
        this.nombre = nombre;
        this.inscripciones = new ArrayList<Inscripcion>(); //inicializa la lista de inscripciones
    }

    //Getter y Setter

    /**
     * Getter de numSocio
     * @return devuelve el número de socio relacionado si ha sido encontrado
     */
    public int getNumSocio() {
        return numSocio;
    }
    /**
     * Setter de numSocio
     * @param numSocio parámetro identificador del número del socio
     */
    public void setNumSocio(int numSocio) {
        this.numSocio = numSocio;
    }
    /**
     * Getter de nombre
     * @return devuelve el nombre del socio
     */
    public String getNombre() {
        return nombre;
    }

    /**
     * Setter de nombre
     * @param nombre parámetro identificativo del nombre del socio
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    /**
     * Getter de inscripciones
     * @return devuelve las inscripciones si han sido encontradas
     */
    public List<Inscripcion> getInscripciones() {
        return inscripciones;
    }
    /**
     * Setter de inscripciones
     * @param inscripciones parámetro de inscripciones
     */
    public void setInscripciones(List<Inscripcion> inscripciones) {
        this.inscripciones = inscripciones;
    }

    //Métodos de la clase backsolutions.modelo.Socio

    /**
     * Metodo para calcular la cuota mensual, que debe ser implementado en las subclases.
     * @return devuelve la cuota mensual correspondiente a cada tipo de socio.
     */
    public abstract double calculoCuotaMensual();

    /**
     * Metodo para obtener los detalles del socio
     * @return devuelve una cadena con los detalles del socio.
     */
    public abstract String detallesSocio();

    @Override
    public String toString() {
        return "Socio{" +
                "numSocio=" + numSocio +
                ", nombre='" + nombre + '\'' +
                '}';
    }

    /**
     * Método getTipo
     * @return devuelve el tipo de socio, que es específico para cada subclase del socio
     * (backsolutions.modelo.Estandar, backsolutions.modelo.Federado, backsolutions.modelo.Infantil)
     */
    public abstract String getTipo();
}
