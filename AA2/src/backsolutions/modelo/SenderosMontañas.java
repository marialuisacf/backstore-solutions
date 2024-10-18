package backsolutions.modelo;

import java.util.List;

/**
 * Se crea la clase backsolutions.modelo.SenderosMontañas
 */

public class SenderosMontañas {

    /**
     * Atributos de la clase senderosMontañas
     */
    private String nif;
    private double cuotaBaseMensual;
    private List<Socio> socios;
    private List<Excursion> excursiones;
    private List<Inscripcion> inscripciones;

    //Constructor de la clase backsolutions.modelo.SenderosMontañas con los parámetros necesarios para inicializar la aplicación

    /**
     * Atributos de la clase backsolutions.modelo.SenderosMontañas añadidos en el constructor
     * @param nif parámetro distintivo del nif
     * @param cuotaBaseMensual parámetro de la cuota base mensual
     * @param socios parámetro del listado de socios
     * @param excursiones parámetro del listado de excursiones
     * @param inscripciones parámetro del listado de incripciones
     */

    public SenderosMontañas(String nif, double cuotaBaseMensual, List<Socio> socios, List<Excursion> excursiones, List<Inscripcion> inscripciones) {
        this.nif = nif;
        this.cuotaBaseMensual = cuotaBaseMensual;
        this.socios = socios;
        this.excursiones = excursiones;
        this.inscripciones = inscripciones;
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
     * @param nif parámetro del nif del socio
     */
    public void setNif(String nif) {
        this.nif = nif;
    }
    /**
     * Getter de cuotaBaseMensual
     * @return devuelve la cuota base mensual
     */
    public double getCuotaBaseMensual() {
        return cuotaBaseMensual;
    }
    /**
     * Setter de cuotaBaseMensual
     * @param cuotaBaseMensual parámetro de la cuota base mensual
     */
    public void setCuotaBaseMensual(double cuotaBaseMensual) {
        this.cuotaBaseMensual = cuotaBaseMensual;
    }
    /**
     * Getter de socios
     * @return devuelve el listado de socios
     */
    public List<Socio> getSocios() {
        return socios;
    }
    /**
     * Setter de socios
     * @param socios parámetro del listado de socios
     */
    public void setSocios(List<Socio> socios) {
        this.socios = socios;
    }
    /**
     * Getter de excursiones
     * @return devuelve el listado de excursiones
     */
    public List<Excursion> getExcursiones() {
        return excursiones;
    }
    /**
     * Setter de excursiones
     * @param excursiones parámetro del listado de excursiones
     */
    public void setExcursiones(List<Excursion> excursiones) {
        this.excursiones = excursiones;
    }
    /**
     * Getter de inscripciones
     * @return devuelve el listado de inscripciones
     */
    public List<Inscripcion> getInscripciones() {
        return inscripciones;
    }
    /**
     * Setter de inscripciones
     * @param inscripciones parámetro del listado de inscripciones
     */
    public void setInscripciones(List<Inscripcion> inscripciones) {
        this.inscripciones = inscripciones;
    }

    //Métodos de la clase backsolutions.modelo.SenderosMontañas

    /**
     * Representación de la información de la clase senderosMontañas con toString
     * @return devuelve el método toString de la clase senderosMontañas
     */
    @Override
    public String toString() {
        return "backsolutions.modelo.SenderosMontañas{" +
                "nif='" + nif + '\'' +
                ", cuotaBaseMensual=" + cuotaBaseMensual +
                ", socios=" + socios +
                ", excursiones=" + excursiones +
                ", inscripciones=" + inscripciones +
                '}';
    }
}
