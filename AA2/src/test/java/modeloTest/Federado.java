package modeloTest;

/**
 * Se crea la clase backsolutions.modelo.Federado
 */

public class Federado extends Socio {

    /**
     * Atributos de la clase backsolutions.modelo.Federado
     */
    private String nif;
    private Federacion federacion;

    //Constructor de la clase backsolutions.modelo.Federado con los parámetros necesarios para inicializar un socio federado

    /**
     * Atributos de la clase backsolutions.modelo.Federado añadidos al constructor
     * @param numSocio parámetro del número del socio
     * @param nombre parámetro identificativo del nombre del socio
     * @param nif parámetro del nif del socio
     * @param federacion parámetro de la federación del socio
     */
    public Federado(int numSocio, String nombre, String nif, Federacion federacion) {
        super(numSocio, nombre);
        this.nif = nif;
        this.federacion = federacion;
    }
    //Getter y Setter

    /**
     * Getter de nif
     * @return devuelve el nif del socio
     */
    public String getNif() {
        return nif;
    }
    /**
     * Setter de nif
     * @param nif parámetro identificativo del nif del socio
     */
    public void setNif(String nif) {
        this.nif = nif;
    }
    /**
     * Getter de federacion
     * @return devuelve la federacion del socio
     */
    public Federacion getFederacion() {
        return federacion;
    }
    /**
     * Setter de federacion
     * @param federacion parámetro identificativo de la federación
     */
    public void setFederacion(Federacion federacion) {
        this.federacion = federacion;
    }

    //Métodos de la clase backsolutions.modelo.Federado
    @Override
    public double calculoCuotaMensual() {
        return 10.0 * 0.95; // Debido al 5% de descuento para socios federados
    }

    public Federacion obtenerFederacion() {
        return federacion;
    }

    @Override
    public String detallesSocio() {
        return "Socio Federado: " + getNombre() + ", NIF: " + nif + ", Federación: " + federacion;
    }

    @Override
    public String toString() {
        return super.toString() + ", NIF: " + nif + ", Federación: " + federacion.getNombre();
    }

    /**
     * override
     * @return devuelve el tipo "backsolutions.modelo.Federado"
     */
    @Override
    public String getTipo(){return "Federado";}
}
