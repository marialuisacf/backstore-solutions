package modeloTest;

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
    private Seguro seguro; //Atributo para el seguro contratado (opcional para algunos socios como Federado o Infantil)

    //Constructor de la clase Inscripción con los parámetros necesarios para inicializar una inscripción

    /**
     * Constructor de la clase Inscripcion
     * @param numInscripcion parámetro identificativo del número de la inscricion
     * @param socio parámetro que identifica al socio que realiza la inscripcion
     * @param excursion parámetro que identifica la excursion a la que se inscribe el socio
     * @param fechaInscripcion identifica la fecha en la que se realizó la inscripción
     * @param seguro identifica el seguro del socio Estandar.
     */
    public Inscripcion(String numInscripcion, Socio socio, Excursion excursion, LocalDate fechaInscripcion, Seguro seguro) {
        this.numInscripcion = numInscripcion;
        this.socio = socio;
        this.excursion = excursion;
        this.fechaInscripcion = fechaInscripcion;
        this.seguro = seguro;
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

    /**
     * Getter de seguro
     * @return devuelve el precio del seguro contratado por el socio Estandar
     */
    public Seguro getSeguro() {
        return seguro;
    }

    /**
     * Setter seguro
     * @param seguro parámetro del seguro contratado
     */
    public void setSeguro(Seguro seguro) {
        this.seguro = seguro;
    }

    //Métodos de la clase Inscripcion

    /**
     * Metodo para calcular el precio total de la inscripción.
     * @return El precio total de la inscripción basado en el tipo de socio y si requiere seguro.
     */
    public double calculoPrecioTotal() {
        double precioExcursion = excursion.calculoPrecioExcursion(socio);

        if (socio instanceof Estandar) {
            //Los socios estándar deben contratar un seguro, se suma al precio de la excursión
            return precioExcursion + seguro.getPrecio();
        } else if (socio instanceof Federado || socio instanceof Infantil) {
            //Los socios federados e infantiles no pagan seguro, solo el precio de la excursión
            return precioExcursion;
        } else {
            //Caso por defecto para cualquier otro tipo de socio
            return precioExcursion;
        }
    }

    // Metodo para verificar si se puede cancelar la inscripción
    public boolean verificarCancelacion() {
        LocalDate fechaExcursion = excursion.getFecha();
        //Se puede cancelar solo hasta el día anterior a la excursión
        LocalDate fechaLimiteCancelacion = fechaExcursion.minusDays(1);
        //se calcula la fecha límite para cancelar
        return LocalDate.now().isBefore(fechaLimiteCancelacion);
        //verifica si la fecha actual es anterior a esa fecha límite.
    }

    /**
     * Representación de la información de la clase backsolutions.modelo.Inscripcion con toString
     * @return devuelve el metodo toString de la clase backsolutions.modelo.Inscripcion
     */
    @Override
    public String toString() {
        return "Inscripcion{" +
                "Número de inscripción='" + numInscripcion + '\'' +
                ", Socio=" + socio +
                ", excursion=" + excursion +
                ", fechaInscripcion=" + fechaInscripcion +
                ", seguro=" + seguro +
                '}';
    }
}
