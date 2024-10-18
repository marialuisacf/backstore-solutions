package backsolutions.modelo;

import java.time.LocalDate;
/**
 * Se crea la clase backsolutions.modelo.Inscripcion
 */

public class Inscripcion {

    /**
     * Atributos de la clase backsolutions.modelo.Inscripcion
     */
    private String numInscripcion;
    private Socio socio;
    private Excursion excursion;
    private LocalDate fechaInscripcion;

    //Constructor de la clase Inscripción con los parámetros necesarios para inicializar una inscripción

    /**
     * Constructor de la clase Inscripcion
     * @param numInscripcion parámetro identificativo del número de la inscricion
     * @param socio parámetro que identifica al socio que realiza la inscripcion
     * @param excursion parámetro que identifica la excursion a la que se inscribe el socio
     * @param fechaInscripcion identifica la fecha en la que se realizó la inscripción
     */
    public Inscripcion(String numInscripcion, Socio socio, Excursion excursion, LocalDate fechaInscripcion) {
        this.numInscripcion = numInscripcion;
        this.socio = socio;
        this.excursion = excursion;
        this.fechaInscripcion = fechaInscripcion;
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
     * @param socio parámetro identificativo del socio que realizo la inscripcion
     */
    public void setSocio(Socio socio) {
        this.socio = socio;
    }
    /**
     * Getter de excursion
     * @return devuelve la excursion relacionada con la inscripcion
     */
    public Excursion getExcursion() {
        return excursion;
    }
    /**
     * Setter de excursion
     * @param excursion parámetro identificativo de la excursion relacionada con la inscripcion
     */
    public void setExcursion(Excursion excursion) {
        this.excursion = excursion;
    }
    /**
     * Getter de fechaInscripcion
     * @return devuelve la fecha en que se realizo la inscripcion
     */
    public LocalDate getFechaInscripcion() {
        return fechaInscripcion;
    }

    /**
     * Setter de fechaInscripcion
     * @param fechaInscripcion parámetro de la fecha de inscripcion
     */
    public void setFechaInscripcion(LocalDate fechaInscripcion) {
        this.fechaInscripcion = fechaInscripcion;
    }


    //Métodos de la clase backsolutions.modelo.Inscripcion

    /**
     * Método para calcular el precio total de la inscripción.
     * Este es el resultado de sumar el precio de la excursión y la cuota mensual del socio.
     * @return double que representa el precio total
     */
    public double calculoPrecioTotal() {
        // Sumar el precio de la excursión y la cuota mensual del socio
        return excursion.calculoPrecioExcursion(socio) + socio.calculoCuotaMensual();
    }

    /**
     * Método para verificar si la inscripción puede ser cancelada.
     * La inscripción puede cancelarse hasta el día anterior a la excursión.
     * @return boolean true si se puede cancelar, false si ya ha pasado la fecha límite.
     */
    public boolean verificarCancelacion() {
        LocalDate fechaExcursion = excursion.getFecha();
        // Verificar si la fecha de la inscripción es antes de la fecha de la excursión
        return LocalDate.now().isBefore(fechaExcursion);
    }

    /**
     * Representación de la información de la clase backsolutions.modelo.Inscripcion con toString
     * @return devuelve el método toString de la clase backsolutions.modelo.Inscripcion
     */
    @Override
    public String toString() {
        return "Inscripcion{" +
                "numInscripcion='" + numInscripcion + '\'' +
                ", socio=" + socio.getNombre() +
                ", excursion=" + excursion.getDescripcion() +
                ", fechaInscripcion=" + fechaInscripcion +
                '}';
    }
}
