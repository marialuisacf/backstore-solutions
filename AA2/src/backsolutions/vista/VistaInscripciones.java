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
        // Aquí se piden los datos de la inscripción y el socio, y se crea el objeto Inscripcion
        System.out.print("Ingrese el número de inscripción: ");
        String numInscripcion = scanner.nextLine();
    }

    private void cancelarInscripcion() {
        // Aquí se piden los datos del socio y excursión, y se llama a controladorInscripcion.cancelarInscripcion
        System.out.print("Ingrese el número del socio: ");
        int numSocio = scanner.nextInt();
    }

    private void mostrarInscripciones() {
        // Llama al metodo del controlador para obtener la lista de inscripciones y mostrarlas
        var inscripciones = controladorInscripcion.getInscripciones();
        if (inscripciones.isEmpty()) {
            System.out.println("No hay inscripciones disponibles.");
        } else {
            System.out.println("---- Inscripciones ----");
            for (var inscripcion : inscripciones) {
                System.out.println(inscripcion);
            }
        }
    }

}
