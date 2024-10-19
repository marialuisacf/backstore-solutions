package backsolutions.modelo;

import java.util.List;

/**
 * Se crea la clase backsolutions.modelo.Infantil
 */

public class Infantil extends Socio {

    /**
     * Atributos de la clase backsolutions.modelo.Infantil
     */
    private String numSocioTutor;

    //Constructor de la clase backsolutions.modelo.Infantil con los parámetros necesarios para inicializar un socio infantil

    /**
     * Atributos de la clase backsolutions.modelo.Infantil añadidos al constructor
     * @param numSocio parámetro del número del socio
     * @param nombre parámetro identificativo del nombre del socio
     * @param numSocioTutor parámetro identificativo del número de socio del tutor del menor
     */
    public Infantil(int numSocio, String nombre, String numSocioTutor) {
        super(numSocio, nombre);
        this.numSocioTutor = numSocioTutor;
    }
    //Getter y Setter

    /**
     * Getter de numSocioTutor
     * @return devuelve el número de socio del tutor del menor
     */
    public String getNumSocioTutor() {
        return numSocioTutor;
    }
    /**
     * Setter de numSocioTutor
     * @param numSocioTutor parámetro identificativo del número de socio del tutor del menor
     */
    public void setNumSocioTutor(String numSocioTutor) {
        this.numSocioTutor = numSocioTutor;
    }

    //Métodos de la clase backsolutions.modelo.Infantil

    @Override
    public double calculoCuotaMensual() {
        return 10.0 * 0.50; // Es el 50% de descuento para socios infantiles
    }

    @Override
    public String detallesSocio() {
        return "Socio Infantil: " + getNombre() + ", N° Socio del Tutor del menor: " + numSocioTutor;
    }

    @Override
    public String toString() {
        return super.toString() + ", Número de Socio Tutor: " + numSocioTutor;
    }

    /**
     * override
     * @return devuelve el tipo "backsolutions.modelo.Infantil"
     */
    @Override
    public java.lang.String getTipo(){return "Infantil";}
}
