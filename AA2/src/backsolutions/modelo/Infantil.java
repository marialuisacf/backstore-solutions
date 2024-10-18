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
     * @param inscripciones parámetro identificativo de inscirpciones
     * @param numSocioTutor parámetro identificativo del número de socio del tutor del menor
     */
    public Infantil(int numSocio, String nombre, List<Inscripcion> inscripciones, String numSocioTutor) {
        super(numSocio, nombre, inscripciones);
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
    public String toString() {
        return "backsolutions.modelo.Infantil{" +
                "numSocioTutor='" + numSocioTutor + '\'' +
                '}';
    }

    /**
     * override
     * @return devuelve el tipo "backsolutions.modelo.Infantil"
     */
    @Override
    public java.lang.String getTipo(){return "backsolutions.modelo.Infantil";}
}
