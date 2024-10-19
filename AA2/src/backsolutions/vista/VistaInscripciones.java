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
        while (opcion != 3) {
            System.out.println("---- Menú Inscripciones ----");
            System.out.println("1. Añadir inscripción");
            System.out.println("2. Cancelar inscripción");
            System.out.println("3. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar buffer

            switch (opcion) {
                case 1 -> addInscripcion();
                case 2 -> cancelarInscripcion();
                case 3 -> System.out.println("Saliendo del menú inscripciones.");
                default -> System.out.println("Opción no válida.");
            }
        }
    }

    private void addInscripcion() {
        // Aquí se piden los datos de la inscripción y el socio, y se crea el objeto Inscripcion
        // Ejemplo:
        System.out.print("Ingrese el número de inscripción: ");
        String numInscripcion = scanner.nextLine();
        // Recoger más datos y luego añadir
        // controladorInscripcion.addInscripcion(inscripcion);
    }

    private void cancelarInscripcion() {
        // Aquí se piden los datos del socio y excursión, y se llama a controladorInscripcion.cancelarInscripcion
        // Ejemplo:
        System.out.print("Ingrese el número del socio: ");
        int numSocio = scanner.nextInt();
        // controladorInscripcion.cancelarInscripcion(socio, excursion);
    }

}
