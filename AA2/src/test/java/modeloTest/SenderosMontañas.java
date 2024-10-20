package modeloTest;

import controladorTest.ControladorExcepcion;
import controladorTest.ControladorInscripcion;
import controladorTest.ControladorSocio;
import controladorTest.InscripcionInvalidaExcepcion;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

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

    private void addSocio() {
        System.out.print("Ingrese el número de socio: ");
        int numSocio = scanner.nextInt();
        scanner.nextLine(); // Limpiar buffer

        System.out.print("Ingrese el nombre del socio: ");
        String nombre = scanner.nextLine();

        System.out.print("Seleccione el tipo de socio (Estandar/Federado/Infantil): ");
        String tipo = scanner.nextLine().toLowerCase(); // Convertir a minúsculas para facilitar la comparación

        Socio socio = null;

        switch (tipo) {
            case "estandar":
                System.out.print("Ingrese el NIF: ");
                String nifEstandar = scanner.nextLine();
                System.out.print("Ingrese el tipo de Seguro: ");
                String tipoSeguro = scanner.nextLine();
                System.out.print("Ingrese el precio del Seguro: ");
                double precioSeguro = scanner.nextDouble();
                scanner.nextLine(); // Limpiar buffer
                Seguro seguro = new Seguro(tipoSeguro, precioSeguro);
                socio = new Estandar(numSocio, nombre, nifEstandar, seguro);
                break;
            case "federado":
                System.out.print("Ingrese el NIF: ");
                String nifFederado = scanner.nextLine();
                System.out.print("Ingrese el código de la Federación: ");
                String codigoFederacion = scanner.nextLine();
                System.out.print("Ingrese el nombre de la Federación: ");
                String nombreFederacion = scanner.nextLine();
                Federacion federacion = new Federacion(codigoFederacion, nombreFederacion);
                socio = new Federado(numSocio, nombre, nifFederado, federacion);
                break;
            case "infantil":
                System.out.print("Ingrese el número de socio del tutor: ");
                String numSocioTutor = scanner.nextLine();
                socio = new Infantil(numSocio, nombre, numSocioTutor);
                break;
            default:
                System.out.println("Tipo de socio no válido. Debe ser 'Estandar', 'Federado' o 'Infantil'.");
                return; // Salir del método si el tipo es inválido
        }

        try {
            controladorSocio.addSocio(socio); // Usar la instancia del controlador
        } catch (ControladorExcepcion e) {
            System.out.println(e.getMessage());
        }
    }

    // metodo para eliminar socio y verificar su baja
    public void deleteSocio(Socio socio) {
        // Verificar si el socio tiene inscripciones
        boolean tieneInscripciones = inscripciones.stream()
                .anyMatch(inscripcion -> inscripcion.getSocio().equals(socio));
        //anyMatch es el metodo que sigue buscando en la lista de inscripciones si hay alguna inscripcion donde el socio coincida con el socio a eliminar.
        if (tieneInscripciones) {
            System.out.println("No se puede dar de baja al socio " + socio.getNombre() + " porque está inscrito en una o más excursiones.");
        } else {
            socios.remove(socio);
            System.out.println("Socio " + socio.getNombre() + " eliminado con éxito.");
            //si el socio no tiene inscripciones se procede a eliminarlo de la lista de socios.
        }
    }
    private Scanner scanner = new Scanner(System.in);

    // Metodo para añadir inscripciones y verificar la inscripción (verificar existencia excursión, verificación existencia socio)
    public void addInscripcion(String codigoExcursion) {
        // Verificar si la excursión existe
        Excursion excursion = excursiones.stream()
                .filter(exc -> exc.getCodigo().equals(codigoExcursion))
                .findFirst()
                .orElse(null);

        if (excursion == null) {
            System.out.println("La excursión con el código " + codigoExcursion + " no existe.");
            return;
        }

        // Preguntar al usuario si ya es cliente
        System.out.print("¿Es usted socio? (si/no): ");
        String respuesta = scanner.nextLine(); // Suponiendo que tienes un scanner para entrada

        if (respuesta.equalsIgnoreCase("no")) {
            System.out.println("Por favor, primero debe darse de alta como socio.");
            // Aquí se podría llamar a un método para gestionar el alta de socio
            return;
        }

        // Si es socio, se le pide su número de socio
        System.out.print("Introduce tu número de socio: ");
        int numSocio = scanner.nextInt(); // Lee como un int
        scanner.nextLine(); // Limpiar el buffer después de leer el número

        // Verificar si el socio ya existe
        Socio socio = socios.stream()
                .filter(s -> s.getNumSocio() == numSocio) // Compara por numSocio
                .findFirst()
                .orElse(null);

        if (socio == null) {
            System.out.println("No se encontró un socio con el número proporcionado.");
            return;
        }

        // Generar un número de inscripción único
        String numInscripcion = generarNumeroInscripcion();

        // Crear el objeto Seguro si es necesario
        Seguro seguro = null;
        if (socio instanceof Estandar) {
            seguro = crearNuevoSeguro(); // Método para solicitar datos del seguro
        }

        // Crear una nueva inscripción
        Inscripcion inscripcion = new Inscripcion(numInscripcion, socio, excursion, LocalDate.now(), seguro);

        // Intentar agregar la inscripción a través del controlador
        try {
            controladorInscripcion.addInscripcion(inscripcion);
            System.out.println("Inscripción realizada con éxito para el socio " + socio.getNombre());
        } catch (InscripcionInvalidaExcepcion e) {
            System.out.println("Error al añadir la inscripción: " + e.getMessage());
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
