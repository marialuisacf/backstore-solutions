import java.util.List;

/**
 * Se crea la clase Infantil
 */

public class Infantil extends Socio {

    /**
     * Atributos de la clase Infantil
     */
    private String numSocioTutor;

    //Constructor de la clase Infantil con los parámetros necesarios para inicializar un socio infantil

    /**
     * Atributos de la clase Infantil añadidos al constructor
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

    //Métodos de la clase Infantil

    @Override
    public String toString() {
        return "Infantil{" +
                "numSocioTutor='" + numSocioTutor + '\'' +
                '}';
    }

    /**
     * override
     * @return devuelve el tipo "Infantil"
     */
    @Override
    public java.lang.String getTipo(){return "Infantil";}
}
