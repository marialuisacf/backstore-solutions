package backsolutions.vista;

import backsolutions.controlador.*;
import java.util.Scanner;

public class VistaInscripciones {
    private ControladorInscripcion controladorInscripcion;
    private Scanner scanner;

    public VistaInscripciones(ControladorInscripcion controladorInscripcion) {
        this.controladorInscripcion = controladorInscripcion;
        this.scanner = new Scanner(System.in);
    }

    public void mostrarMenu() {
        int opcion = 0;
        while (opcion != 4) {
            System.out.println("---- Menú Inscripciones ----");
            System.out.println("1. Añadir inscripción");
            System.out.println("2. Cancelar inscripción");
            System.out.println("3. Mostrar inscripciones");
            System.out.println("4. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar buffer

            switch (opcion) {
                case 1 -> addInscripcion();
                case 2 -> cancelarInscripcion();
                case 3 -> mostrarInscripciones();
                case 4 -> System.out.println("Saliendo del menú inscripciones.");
                default -> System.out.println("Opción no válida.");
            }
        }
    }

    private void addInscripcion() {
        System.out.print("Ingrese el número de socio para la inscripción: ");
        int numSocio = scanner.nextInt();
        scanner.nextLine(); // Limpiar el buffer

        System.out.print("Ingrese el código de la excursión a inscribir: ");
        String codigoExcursion = scanner.nextLine();

        System.out.print("Ingrese el tipo de seguro (opcional): ");
        String tipoSeguro = scanner.nextLine();
        double precioSeguro = 0.0;

        if (!tipoSeguro.isEmpty()) {
            System.out.print("Ingrese el precio del seguro: ");
            precioSeguro = scanner.nextDouble();
        }

        try {
            controladorInscripcion.addInscripcion(codigoExcursion, numSocio, tipoSeguro, precioSeguro);
            System.out.println("Inscripción añadida con éxito.");
        } catch (ControladorExcepcion e) {
            System.out.println("Error al añadir la inscripción: " + e.getMessage());
        }
    }

    private void cancelarInscripcion() {
        System.out.print("Ingrese el número de socio para cancelar la inscripción: ");
        int numSocio = scanner.nextInt();
        scanner.nextLine(); // Limpiar el buffer

        System.out.print("Ingrese el código de la excursión para cancelar la inscripción: ");
        String codigoExcursion = scanner.nextLine();

        try {
            // Llamar al método cancelarInscripcion en el controlador con los parámetros adecuados
            controladorInscripcion.cancelarInscripcion(numSocio, codigoExcursion);
            System.out.println("Inscripción cancelada con éxito.");
        } catch (ControladorExcepcion e) {
            System.out.println("Error al cancelar la inscripción: " + e.getMessage());
        }
    }


    private void mostrarInscripciones() {
        try {
            var inscripciones = controladorInscripcion.mostrarInscripciones();

            if (inscripciones.isEmpty()) {
                System.out.println("No hay inscripciones disponibles.");
            } else {
                System.out.println("---- Inscripciones ----");
                for (var inscripcion : inscripciones) {
                    String socioNombre = inscripcion.getSocio().getNombre();
                    String excursionDescripcion = inscripcion.getExcursion().getDescripcion();
                    String fechaExcursion = inscripcion.getExcursion().getFecha().toString();
                    double importe = controladorInscripcion.calcularImporte(inscripcion);

                    System.out.printf("Número de Inscripción: %s, Nombre del Socio: %s, Fecha de Excursión: %s, Descripción: %s, Importe: %.2f%n",
                            inscripcion.getNumInscripcion(), socioNombre, fechaExcursion, excursionDescripcion, importe);
                }
            }
        } catch (ControladorExcepcion e) {
            System.out.println("Error al mostrar las inscripciones: " + e.getMessage());
        }
    }

}
