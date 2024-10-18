package backsolutions.modelo;

import java.time.LocalDate;

/**
 * Se crea la clase backsolutions.modelo.Excursion
 */

public class Excursion {
    /**
     * Atributos de la clase backsolutions.modelo.Excursion
     */
    private String codigo;
    private String descripcion;
    private LocalDate fecha;
    private int numDias;
    private double precioInscripcion;

    //Constructor de la clase backsolutions.modelo.Excursion con los parámetros necesarios para inicializar una excursion

    /**
     * Atributos de la clase backsolutions.modelo.Excursion añadidos al constructor
     * @param codigo parámetro identificativo del codigo de la excursion
     * @param descripcion parámetro identificativo de la descripción de la excursión
     * @param fecha parámetro identificativo de la fecha de la excursión
     * @param numDias parámetro identificativo del número de días de la excursión
     * @param precioInscripcion parámetro identificativo del precio de la inscripción
     */
    public Excursion(String codigo, String descripcion, LocalDate fecha, int numDias, double precioInscripcion) {
        this.codigo = codigo;
        this.descripcion = descripcion;
        this.fecha = fecha;
        this.numDias = numDias;
        this.precioInscripcion = precioInscripcion;
    }

    //Getter y Setter

    /**
     * Getter de codigo
     * @return devuelve el código de la excursión
     */
    public String getCodigo() {
        return codigo;
    }
    /**
     * Setter de codigo
     * @param codigo parámetro identificativo del código
     */
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
    /**
     * Getter de descripción
     * @return devuelve la descripción de la excursión
     */
    public String getDescripcion() {
        return descripcion;
    }
    /**
     * Setter de descripción
     * @param descripcion parámetro de la descripción de la excursión
     */
    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }
    /**
     * Getter de fecha
     * @return devuelve la fecha de la excursión
     */
    public LocalDate getFecha() {
        return fecha;
    }
    /**
     * Setter de fecha
     * @param fecha parámetro de la fecha de la excursión
     */
    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }
    /**
     * Getter numDias
     * @return devuelve el número de días de la excursión
     */
    public int getNumDias() {
        return numDias;
    }
    /**
     * Setter numDias
     * @param numDias parámetro del número de días de la excursión
     */
    public void setNumDias(int numDias) {
        this.numDias = numDias;
    }
    /**
     * Getter precioInscripcion
     * @return devuelve el precio de la inscripción
     */
    public double getPrecioInscripcion() {
        return precioInscripcion;
    }
    /**
     * Setter de precioInscripcion
     * @param precioInscripcion parámetro del precio de la inscripción
     */
    public void setPrecioInscripcion(double precioInscripcion) {
        this.precioInscripcion = precioInscripcion;
    }

    //Métodos de la clase Excursión

    /**
     * Metodo para calcular el precio de inscripción
     * @return sólo devuelve el precio de la inscripción
     */
    public double calculoPrecioInscripcion() {
        return this.precioInscripcion;
    }

    /**
     * Metodo para mostrar detalles de la excursión
     * @return
     */
    public String detallesExcursion() {
        return "Excursión: " + this.codigo + ", Descripción: " + this.descripcion + ", Fecha: " + this.fecha + ", Días: " + this.numDias + ", Precio: " + this.precioInscripcion;
    }
    /**
     * Representación de la información de la clase Excursión con toString
     * @return devuelve el método toString de la clase Excursión
     */
    @Override
    public String toString() {
        return "backsolutions.modelo.Excursion{" +
                "codigo='" + codigo + '\'' +
                ", descripcion='" + descripcion + '\'' +
                ", fecha=" + fecha +
                ", numDias=" + numDias +
                ", precioInscripcion=" + precioInscripcion +
                '}';
    }
}
