package backsolutions.vista;

import backsolutions.controlador.ControladorExcursion;
import backsolutions.controlador.ControladorExcepcion;
import backsolutions.dto.ExcursionDTO;

import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class VistaExcursiones {

    private ControladorExcursion controladorExcursion;
    private Scanner scanner;

    public VistaExcursiones(ControladorExcursion controladorExcursion) {
        this.controladorExcursion = controladorExcursion;
        this.scanner = new Scanner(System.in);
    }

    public void mostrarMenu() {
        int opcion = 0;
        while (opcion != 3) {
            System.out.println("---- Menú Excursiones ----");
            System.out.println("1. Añadir excursión");
            System.out.println("2. Filtrar o mostrar todas las excursiones");
            System.out.println("3. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar buffer

            switch (opcion) {
                case 1 -> addExcursion();
                case 2 -> filtrarExcursiones();
                case 3 -> System.out.println("Saliendo del menú excursiones.");
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

        // Crear el ExcursionDTO para pasar al controlador
        ExcursionDTO excursionDTO = new ExcursionDTO(codigo, descripcion, fecha, numDias, precioInscripcion);

        try {
            controladorExcursion.addExcursion(excursionDTO); // Usar ExcursionDTO
            System.out.println("Excursión añadida con éxito.");
        } catch (ControladorExcepcion e) {
            System.out.println(e.getMessage());
        }
    }

    private void filtrarExcursiones() {
        System.out.print("Ingrese la fecha de inicio (YYYY-MM-DD) o presione Enter para ver todas: ");
        String inicioString = scanner.nextLine().trim();
        LocalDate inicio = null;

        System.out.print("Ingrese la fecha de fin (YYYY-MM-DD) o presione Enter para ver todas: ");
        String finString = scanner.nextLine().trim();
        LocalDate fin = null;

        try {
            DateTimeFormatter formatoFecha = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            if (!inicioString.isEmpty()) {
                inicio = LocalDate.parse(inicioString, formatoFecha);
            }
            if (!finString.isEmpty()) {
                fin = LocalDate.parse(finString, formatoFecha);
            }

            List<ExcursionDTO> filtradas = controladorExcursion.filtrarExcursiones(inicio, fin);
            if (filtradas.isEmpty()) {
                System.out.println("No se encontraron excursiones en el rango de fechas proporcionado.");
            } else {
                filtradas.forEach(System.out::println);
            }

        } catch (DateTimeParseException e) {
            System.out.println("Formato de fecha inválido. Use 'YYYY-MM-DD'.");
        } catch (ControladorExcepcion e) {
            System.out.println("Error al filtrar las excursiones: " + e.getMessage());
        }
    }
}
