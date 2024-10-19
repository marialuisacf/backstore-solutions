package backsolutions.modelo;

import java.util.List;

/**
 * Se crea la clase backsolutions.modelo.Estandar
 */

public class Estandar extends Socio {

    /**
     * Atributos de la clase backsolutions.modelo.Estandar
     */
    private String nif;
    private Seguro seguro;

    //Constructor de la clase backsolutions.modelo.Estandar con los parámetros necesarios para inicializar un socio estandar

    /**
     * Atributos de la clase backsolutions.modelo.Estandar añadidos al constructor
     * @param numSocio parámetro del número del socio
     * @param nombre parámetro identificativo del nombre del socio
     * @param nif parámetro del nif del socio
     * @param seguro parámetro del seguro del socio
     */
    public Estandar(int numSocio, String nombre, String nif, Seguro seguro) {
        super(numSocio, nombre);
        this.nif = nif;
        this.seguro = seguro;
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
     * Getter de seguro
     * @return devuelve el seguro del socio
     */
    public Seguro getSeguro() {
        return seguro;
    }
    /**
     * Setter de seguro
     * @param seguro parámetro identificativo del seguro
     */
    public void setSeguro(Seguro seguro) {
        this.seguro = seguro;
    }


    //Métodos de la clase backsolutions.modelo.Estandar
    @Override
    public double calculoCuotaMensual() {
        return 10.0; // Ejemplo de cuota mensual para el socio estándar
    }

    @Override
    public Seguro obtenerSeguro() {
        return this.seguro; // Devuelve el seguro del socio
    }

    public void modificarSeguro(String nuevoTipo, double nuevoPrecio) {
        Seguro nuevoSeguro = new Seguro(nuevoTipo, nuevoPrecio);
        this.setSeguro(nuevoSeguro);
    }

    @Override
    public String detallesSocio() {
        return "Socio Estándar: " + getNombre() + ", NIF: " + nif + ", Seguro: " + seguro;
    }

    @Override
    public String toString() {
        return "Estandar{" +
                "nif='" + nif + '\'' +
                ", seguro=" + seguro +
                '}';
    }

    /**
     * override
     * @return devuelve el tipo "backsolutions.modelo.Estandar"
     */
    @Override
    public java.lang.String getTipo(){return "Estándar";}
}
