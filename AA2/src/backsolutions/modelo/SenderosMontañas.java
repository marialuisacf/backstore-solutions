package backsolutions.modelo;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.time.LocalDate;
import backsolutions.controlador.*;

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
    private ControladorSocio controladorSocio;
    private ControladorInscripcion controladorInscripcion;

    //Constructor de la clase backsolutions.modelo.SenderosMontañas con los parámetros necesarios para inicializar la aplicación

    /**
     * Atributos de la clase backsolutions.modelo.SenderosMontañas añadidos en el constructor
     * @param nif parámetro distintivo del nif
     * @param cuotaBaseMensual parámetro de la cuota base mensual
     */

    public SenderosMontañas(String nif, double cuotaBaseMensual) {
        this.nif = nif;
        this.cuotaBaseMensual = cuotaBaseMensual;
        this.socios = new ArrayList<>();
        this.excursiones = new ArrayList<>();
        this.inscripciones = new ArrayList<>();
        this.controladorSocio = controladorSocio; //para inicializar controlador
        this.controladorInscripcion = controladorInscripcion;
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


    //Métodos de la clase SenderosMontañas
    public void addExcursion(Excursion excursion) {
        excursiones.add(excursion);
    }

    public void deleteExcursion(String codigoExcursion) {
        excursiones.removeIf(excursion -> excursion.getCodigo().equals(codigoExcursion));
    }

    // Métodos para gestionar socios
    public void addSocio(int numSocio, String nombre, String tipo, String nif, Seguro seguro, Federacion federacion) {
        Socio socio = null;

        switch (tipo.toLowerCase()) {
            case "estandar":
                socio = new Estandar(numSocio, nombre, nif, seguro);
                break;
            case "federado":
                socio = new Federado(numSocio, nombre, nif, federacion);
                break;
            case "infantil":
                socio = new Infantil(numSocio, nombre, nif);
                break;
            default:
                throw new IllegalArgumentException("Tipo de socio no válido. Debe ser 'Estandar', 'Federado' o 'Infantil'.");
        }

        try {
            controladorSocio.addSocio(socio); // Usar la instancia del controlador
        } catch (ControladorExcepcion e) {
            // Manejo de excepciones, puedes almacenar el mensaje en una lista o lanzar otra excepción
        }
    }

    // Método para eliminar socio y verificar su baja
    public void deleteSocio(Socio socio) {
        boolean tieneInscripciones = inscripciones.stream()
                .anyMatch(inscripcion -> inscripcion.getSocio().equals(socio));

        if (tieneInscripciones) {
            // Manejo de la baja del socio que tiene inscripciones (puedes lanzar una excepción o similar)
        } else {
            socios.remove(socio);
            // Mensaje de éxito (puedes manejarlo de otra manera)
        }
    }

    // Método para añadir inscripciones y verificar la inscripción
    public void addInscripcion(String codigoExcursion, boolean esSocio, int numSocio, Seguro seguro) {
        Excursion excursion = excursiones.stream()
                .filter(exc -> exc.getCodigo().equals(codigoExcursion))
                .findFirst()
                .orElse(null);

        if (excursion == null) {
            throw new IllegalArgumentException("La excursión con el código " + codigoExcursion + " no existe.");
        }

        if (!esSocio) {
            throw new IllegalStateException("El usuario debe ser socio para inscribirse.");
        }

        Socio socio = socios.stream()
                .filter(s -> s.getNumSocio() == numSocio)
                .findFirst()
                .orElse(null);

        if (socio == null) {
            throw new IllegalArgumentException("No se encontró un socio con el número proporcionado.");
        }

        String numInscripcion = generarNumeroInscripcion();
        Inscripcion inscripcion = new Inscripcion(numInscripcion, socio, excursion, LocalDate.now(), seguro);

        try {
            controladorInscripcion.addInscripcion(inscripcion);
            // Mensaje de éxito (puedes manejarlo de otra manera)
        } catch (InscripcionInvalidaExcepcion e) {
            // Manejo de la excepción
        }
    }

    // Metodo para generar un número de inscripción único
    private String generarNumeroInscripcion() {
        // Lógica para generar un número único
        return "INS" + (inscripciones.size() + 1);
    }

    // Metodo para crear un nuevo seguro (placeholder)
    private Seguro crearNuevoSeguro() {
        // Lógica para crear un nuevo seguro
        return new Seguro(crearNuevoSeguro().getTipo(), crearNuevoSeguro().getPrecio());
    }

    /**
     * Representación de la información de la clase senderosMontañas con toString
     * @return devuelve el metodo toString de la clase senderosMontañas
     */
    @Override
    public String toString() {
        return "SenderosMontañas{" +
                "nif='" + nif + '\'' +
                ", cuotaBaseMensual=" + cuotaBaseMensual +
                ", socios=" + socios +
                ", excursiones=" + excursiones +
                ", inscripciones=" + inscripciones +
                '}';
    }
}
