/**
 * Se crea la clase Seguro
 */
public class Seguro {

    /**
     * Atributos de la clase Seguro
     */
    private String tipo;
    private double precio;

    //Constructor de la clase Seguro con los parámetros necesarios para inicializar un seguro

    /**
     * Atributos de la clase Seguro añadidos al constructor
     * @param tipo parámetro identificativo del tipo de seguro
     * @param precio parámetro identificativo dle precio del seguro
     */
    public Seguro(String tipo, double precio) {
        this.tipo = tipo;
        this.precio = precio;
    }

    //Getter y Setter

    /**
     * Getter de tipo
     * @return devuelve el tipo de seguro
     */
    public String getTipo() {
        return tipo;
    }
    /**
     * Setter de tipo
     * @param tipo parámetro identificativo del tipo de seguro
     */
    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
    /**
     * Getter de precio
     * @return devuelve el precio del seguro
     */
    public double getPrecio() {
        return precio;
    }
    /**
     * Setter de precio
     * @param precio parámetro identificativo del precio
     */
    public void setPrecio(double precio) {
        this.precio = precio;
    }

    //Métodos de la clase Seguro

    /**
     * Representación de la información de la clase Seguro con toString
     * @return devuelve el método toString de la clase Seguro
     */
    @Override
    public String toString() {
        return "Seguro{" +
                "tipo='" + tipo + '\'' +
                ", precio=" + precio +
                '}';
    }
}
