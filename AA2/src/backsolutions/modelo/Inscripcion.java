package backsolutions.modelo;

import jakarta.persistence.*;

import java.time.LocalDate;

@Entity
@Table (name = "inscripciones") //nombre de la tabla en la BD
/**
 * Se crea la clase backsolutions.modelo.Inscripcion
 */

public class Inscripcion {

    /**
     * Atributos de la clase backsolutions.modelo.Inscripcion
     */

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)//Autonumerico
    @Column(name = "id")
    private Long id;

    @Column(name = "numInscripcion", nullable = false, unique = true)
    private String numInscripcion;

    @ManyToOne
    @JoinColumn (name = "numSocio", nullable = false) //clave FORANEA hacia SOCIO
    private Socio socio;

    @ManyToOne
    @JoinColumn(name = "codigoExcursion", nullable = false)//clave Foranea hacia Excursion
    private Excursion excursion;

    @Column(name = "fechaInscripcion", nullable = false)
    private LocalDate fechaInscripcion;

    @Column(name = "tipoSeguro")
    private String tipoSeguro;

    @Column(name = "seguroPrecio")
    private double seguroPrecio;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idFactura")
    private Factura factura;


    // Constructor vacío obligatorio para JPA
    public Inscripcion() {}

    //Constructor de la clase Inscripción con los parámetros necesarios para inicializar una inscripción
    /**
     * Constructor de la clase Inscripcion
     * @param numInscripcion parámetro identificativo del número de la inscricion
     * @param socio parámetro que identifica al socio que realiza la inscripcion
     * @param excursion parámetro que identifica la excursion a la que se inscribe el socio
     * @param fechaInscripcion identifica la fecha en la que se realizó la inscripción
     * @param tipoSeguro
     * @param seguroPrecio
     */
    public Inscripcion(String numInscripcion, Socio socio, Excursion excursion, LocalDate fechaInscripcion, String tipoSeguro, double seguroPrecio) {
        this.numInscripcion = numInscripcion;
        this.socio = socio;
        this.excursion = excursion;
        this.fechaInscripcion = fechaInscripcion;
        this.tipoSeguro = tipoSeguro;
        this.seguroPrecio = seguroPrecio;
    }

    //Getter y Setter

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTipoSeguro() {
        return tipoSeguro;
    }

    public void setTipoSeguro(String tipoSeguro) {
        this.tipoSeguro = tipoSeguro;
    }

    public double getSeguroPrecio() {
        return seguroPrecio;
    }

    public void setSeguroPrecio(double seguroPrecio) {
        this.seguroPrecio = seguroPrecio;
    }

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

    public Factura getFactura() {
        return factura;
    }

    public void setFactura(Factura factura) {
        this.factura = factura;
    }

    //Métodos de la clase Inscripcion

    /**
     * Metodo para calcular el precio total de la inscripción.
     * @return El precio total de la inscripción basado en el tipo de socio y si requiere seguro.
     */
    public double calculoPrecioTotal() {
        double precioExcursion = excursion.calculoPrecioExcursion(socio);

        if (socio instanceof Estandar) {
            return precioExcursion + seguroPrecio;
        } else if (socio instanceof Federado || socio instanceof Infantil) {
            return precioExcursion;
        } else {
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
        return "Inscripcion:" +
                "Numero de inscripcion='" + numInscripcion + '\'' +
                ", Socio=" + socio.getNombre() +
                ", Excursion=" + excursion.getDescripcion() +
                ", Fecha de Inscripcion=" + fechaInscripcion +
                ", Tipo de Seguro='" + tipoSeguro + '\'' +
                ", Precio del seguro=" + seguroPrecio +
                '}';
    }
}
