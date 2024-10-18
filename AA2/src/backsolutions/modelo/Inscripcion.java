import java.time.LocalDate;
/**
 * Se crea la clase Inscripcion
 */

public class Inscripcion {

    /**
     * Atributos de la clase Inscripcion
     */
    private String numInscripcion;
    private Socio socio;
    private Excursion excursion;
    private LocalDate fechaInscripcion;

    //Constructor de la clase Inscripción con los parámetros necesarios para inicializar una inscripción

    /**
     * Atributos de la clase Inscripcion añadidos al constructor
     * @param socio parámetro identificativo del socio inscrito
     * @param excursion parámetro identifiactivo de la excursión
     * @param fechaInscripcion parámetro identificativo de la inscripción
     * @param numInscripcion parámetro identificativo del número de la inscripción
     */
    public Inscripcion(Socio socio, Excursion excursion, LocalDate fechaInscripcion, String numInscripcion) {
        this.socio = socio;
        this.excursion = excursion;
        this.fechaInscripcion = fechaInscripcion;
        this.numInscripcion = numInscripcion;

    }

    //Getter y Setter

    /**
     * Getter de numInscripcion
     * @return devuelve el número de la inscripción si se ha encontrado
     */
    public String getNumInscripcion() {
        return numInscripcion;
    }
    /**
     * Setter de numInscripcion
     * @param numInscripcion parámetro del número de la inscripción
     */
    public void setNumInscripcion(String numInscripcion) {
        this.numInscripcion = numInscripcion;
    }
    /**
     * Getter de socio
     * @return devuelve el socio inscrito
     */
    public Socio getSocio() {
        return socio;
    }
    /**
     * Setter de socio
     * @param socio parámetro identificativo del socio inscrito
     */
    public void setSocio(Socio socio) {
        this.socio = socio;
    }
    /**
     * Getter de excursion
     * @return devuelve la excursion
     */
    public Excursion getExcursion() {
        return excursion;
    }
    /**
     * Setter de excursion
     * @param excursion parámetro identificativo de la excursion
     */
    public void setExcursion(Excursion excursion) {
        this.excursion = excursion;
    }
    /**
     * Getter de fechaInscripcion
     * @return devuelve la fecha de la inscripción
     */
    public LocalDate getFechaInscripcion() {
        return fechaInscripcion;
    }

    /**
     * Setter de fecha Inscripcion
     * @param fechaInscripcion parámetro de la fecha de inscripcion
     */
    public void setFechaInscripcion(LocalDate fechaInscripcion) {
        this.fechaInscripcion = fechaInscripcion;
    }

    //Métodos de la clase Inscripcion

    /**
     * Representación de la información de la clase Inscripcion con toString
     * @return devuelve el método toString de la clase Inscripcion
     */
    @Override
    public String toString() {
        return "Inscripcion{" +
                "numInscripcion='" + numInscripcion + '\'' +
                ", socio=" + socio +
                ", excursion=" + excursion +
                ", fechaInscripcion=" + fechaInscripcion +
                '}';
    }
}
