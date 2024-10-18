import java.util.List;

/**
 * Se crea la clase abstracta Socio
 */

public abstract class Socio {

    /**
     * Atributos de la clase asbtracta Socio
     */
    private int numSocio;
    private String nombre;
    private List<Inscripcion> inscripciones;

    //Constructor de la clase Socio con los parámetros necesarios para inicializar un Socio

    /**
     * Atributos de la clase Socio añadidos en el constructor
     * @param numSocio parámetro distintivo del número de socio
     * @param nombre parámetro del nombre del socio
     * @param inscripciones parámetro de las inscripciones del socio
     */
    public Socio(int numSocio, String nombre, List<Inscripcion> inscripciones) {
        this.numSocio = numSocio;
        this.nombre = nombre;
        this.inscripciones = inscripciones;
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

    //Métodos de la clase Socio

    @Override
    public String toString() {
        return "Socio{" +
                "numSocio=" + numSocio +
                ", nombre='" + nombre + '\'' +
                ", inscripciones=" + inscripciones +
                '}';
    }

    /**
     * Método getTipo
     * @return devuelve el tipo de socio, que es específico para cada subclase del socio
     * (Estandar, Federado, Infantil)
     */
    public abstract String getTipo();
}
