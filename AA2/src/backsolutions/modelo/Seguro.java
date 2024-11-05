package backsolutions.modelo;

/**
 * Se crea la clase backsolutions.modelo.Seguro
 */
public class Seguro {

    /**
     * Atributos de la clase backsolutions.modelo.Seguro
     */
    private String tipo;
    private double precio;

    //Constructor de la clase backsolutions.modelo.Seguro con los parámetros necesarios para inicializar un seguro

    /**
     * Atributos de la clase backsolutions.modelo.Seguro añadidos al constructor
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


    //Métodos de la clase backsolutions.modelo.Seguro
    /**
     * Metodo detallesSeguro
     * @return devuelve una descripcion del seguro
     */
    public String detallesSeguro() {
        return "Tipo de Seguro: " + tipo + ", Precio: " + precio;
    }

    /**
     * Representación de la información de la clase backsolutions.modelo.Seguro con toString
     * @return devuelve el método toString de la clase backsolutions.modelo.Seguro
     */
    @Override
    public String toString() {
        return "especificaciones del seguro -> " +
                "tipo='" + tipo + '\'' +
                ", precio=" + precio +
                '.';
    }
}
