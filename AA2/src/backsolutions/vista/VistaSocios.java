package backsolutions.vista;

import backsolutions.controlador.ControladorSocio;
import backsolutions.modelo.Socio;

import java.util.Scanner;

public class VistaSocios {
    private ControladorSocio controladorSocio;
    private Scanner scanner;

    public VistaSocios(ControladorSocio controladorSocio) {
        this.controladorSocio = controladorSocio;
        this.scanner = new Scanner(System.in);
    }

    public void mostrarMenu() {
        int opcion = 0;
        while (opcion != 3) {
            System.out.println("---- Menú Socios ----");
            System.out.println("1. Añadir socio");
            System.out.println("2. Eliminar socio");
            System.out.println("3. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar buffer

            switch (opcion) {
                case 1 -> addSocio();
                case 2 -> deleteSocio();
                case 3 -> System.out.println("Saliendo del menú socios.");
                default -> System.out.println("Opción no válida.");
            }
        }
    }

    private void addSocio() {
        // Aquí pedirías los datos del socio y lo añadirías
        // Ejemplo:
        System.out.print("Ingrese el número de socio: ");
        int numSocio = scanner.nextInt();
        // controladorSocio.addSocio(socio);
    }

    private void deleteSocio() {
        System.out.print("Ingrese el número de socio: ");
        int numSocio = scanner.nextInt();
        controladorSocio.deleteSocio(numSocio);
    }

}
