package backsolutions.modelo;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "socios") //Nombre de la tabla en la BD
@Inheritance(strategy = InheritanceType.JOINED) //para manejar la herencia en la BD
@DiscriminatorColumn(name = "tipo", discriminatorType = DiscriminatorType.STRING) //decidimos mantener tipo, por ello mapeamos el discriminador

/**
 * Se crea la clase abstracta backsolutions.modelo.Socio
 */

public abstract class Socio {

    /**
     * Atributos de la clase asbtracta backsolutions.modelo.Socio
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //Autonumerico
    @Column(name = "id")
    private Long id;

    @Column(name = "numSocio", nullable = false, unique = true)
    private int numSocio;

    @Column(name = "nombre", nullable = false)
    private String nombre;

    @Column(name = "tipo", nullable = false)
    private String tipo;

    @OneToMany(mappedBy = "socio", cascade = CascadeType.ALL, orphanRemoval = true) // Relación con Inscripcion de uno a muchos
    private List<Inscripcion> inscripciones = new ArrayList<>();

    //Constructor vacio obligatorio para JPA
    public Socio() {}

    //Constructor de la clase backsolutions.modelo.Socio con los parámetros necesarios para inicializar un backsolutions.modelo.Socio
    /**
     * Atributos de la clase backsolutions.modelo.Socio añadidos en el constructor
     * @param numSocio parámetro distintivo del número de socio
     * @param nombre parámetro del nombre del socio
     */
    public Socio(int numSocio, String nombre) {
        this.numSocio = numSocio;
        this.nombre = nombre;
        this.inscripciones = new ArrayList<>();
    }

    //Getter y Setter

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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


    public void setTipo(String tipo) {
        this.tipo = tipo;
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

    // Metodos equals y hashCode basados en numSocio, para que se basen en un identificador unico y no en atributos cambiantes
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (!(obj instanceof Socio)) return false;
        Socio other = (Socio) obj;
        return numSocio == other.numSocio;
    }
    @Override
    public int hashCode() {
        return Integer.hashCode(numSocio);
    }

    //Métodos de la clase backsolutions.modelo.Socio

    /**
     * Metodo para calcular la cuota mensual, que debe ser implementado en las subclases.
     * @return devuelve la cuota mensual correspondiente a cada tipo de socio.
     */
    public abstract double calculoCuotaMensual();

    /**
     * Metodo opcional para obtener el seguro
     * @return
     */
    public Seguro obtenerSeguro() {
        return null; // Implementación por defecto que retorna null
    }

    /**
     * Metodo para obtener los detalles del socio
     * @return devuelve una cadena con los detalles del socio.
     */
    public abstract String detallesSocio();

    @Override
    public String toString() {
        return "Número de Socio: " + numSocio + ", Nombre: " + nombre + ", Tipo: " + this.getClass().getSimpleName();
    }

    /**
     * Metodo getTipo
     * @return devuelve el tipo de socio, que es específico para cada subclase del socio
     * (backsolutions.modelo.Estandar, backsolutions.modelo.Federado, backsolutions.modelo.Infantil)
     */
    public abstract String getTipo();
}
