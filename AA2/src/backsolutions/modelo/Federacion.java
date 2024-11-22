package backsolutions.modelo;

import jakarta.persistence.*;

@Entity
@Table(name = "federaciones")

/**
 * Se crea la clase Federación
 */

public class Federacion {

    /**
     * Atributos de la clase Federación
     */
    @Id
    @Column(name = "codigo", nullable = false, unique = true)
    private String codigo;

    @Column(name = "nombre", nullable = false)
    private String nombre;

    //Constructor vacio para JPA
    public Federacion() {}

    //Constructor de la clase Federación con los parámetros necesarios para inicializar una federación

    /**
     * Atributos de la clase backsolutions.modelo.Federacion añadidos al constructor
     * @param codigo parámetro identificativo del código de la federación
     * @param nombre parámetro identificativo del nombre de la federación
     */
    public Federacion(String codigo, String nombre) {
        this.codigo = codigo;
        this.nombre = nombre;
    }

    //Getter y Setter

    /**
     * Getter de codigo
     * @return devuelve el código de la federación
     */
    public String getCodigo() {
        return codigo;
    }
    /**
     * Setter de codigo
     * @param codigo parámetro identificativo del código de la federación
     */
    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }
    /**
     * Getter de nombre
     * @return devuelve el nombre de la federación
     */
    public String getNombre() {
        return nombre;
    }
    /**
     * Setter de nombre
     * @param nombre parámetro identificativo del nombre de la federación
     */
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    //Métodos de la clase backsolutions.modelo.Federacion

    // Metodo para obtener los detalles de la federación
    public String detallesFederacion() {
        return "Federación: " + nombre + ", Código: " + codigo;
    }

    /**
     * Representación de la información de la clase backsolutions.modelo.Federacion con toString
     * @return devuelve el método toString de la clase backsolutions.modelo.Federacion
     */
    @Override
    public String toString() {
        return "Federacion: " +
                "Codigo='" + codigo + '\'' +
                ", Nombre de la federacion='" + nombre + '\'' +
                '.';
    }
}
