package vistaTest;

import controladorTest.ControladorExcursion;
import modeloTest.Excursion;

import java.time.LocalDate;
import java.util.Scanner;

public class VistaExcursiones {

    private ControladorExcursion controladorExcursion;
    private Scanner scanner;

    public VistaExcursiones(ControladorExcursion controladorExcursion) {
        this.controladorExcursion = controladorExcursion;
        this.scanner = new Scanner(System.in);
    }

    public void mostrarMenu() {
        int opcion = 0;
        while (opcion != 4) {
            System.out.println("---- Menú Excursiones ----");
            System.out.println("1. Añadir excursión");
            System.out.println("2. Mostrar todas las excursiones");
            System.out.println("3. Filtrar excursiones por fecha");
            System.out.println("4. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar buffer

            switch (opcion) {
                case 1 -> addExcursion();
                case 2 -> mostrarExcursiones();
                case 3 -> filtrarExcursiones();
                case 4 -> System.out.println("Saliendo del menú excursiones.");
                default -> System.out.println("Opción no válida.");
            }
        }
    }

    private void addExcursion() {
        System.out.print("Ingrese el código de la excursión: ");
        String codigo = scanner.nextLine();

        System.out.print("Ingrese la descripción de la excursión: ");
        String descripcion = scanner.nextLine();

        System.out.print("Ingrese la fecha de la excursión (YYYY-MM-DD): ");
        LocalDate fecha = LocalDate.parse(scanner.nextLine());

        System.out.print("Ingrese el número de días de la excursión: ");
        int numDias = scanner.nextInt();

        System.out.print("Ingrese el precio de inscripción de la excursión: ");
        double precioInscripcion = scanner.nextDouble();
        scanner.nextLine(); // Limpiar buffer

        // Crear el objeto Excursion
        Excursion excursion = new Excursion(codigo, descripcion, fecha, numDias, precioInscripcion);

        // Añadir la excursión a través del controlador
        controladorExcursion.addExcursion(excursion);
    }

    private void mostrarExcursiones() {
        controladorExcursion.mostrarExcursiones();
    }

    private void filtrarExcursiones() {
        System.out.print("Ingrese la fecha de inicio (YYYY-MM-DD): ");
        LocalDate inicio = LocalDate.parse(scanner.nextLine());
        System.out.print("Ingrese la fecha de fin (YYYY-MM-DD): ");
        LocalDate fin = LocalDate.parse(scanner.nextLine());
        controladorExcursion.filtrarExcursiones(inicio, fin);
    }
}
