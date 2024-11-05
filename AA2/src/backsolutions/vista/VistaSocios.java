package backsolutions.vista;

import backsolutions.controlador.*;
import backsolutions.modelo.*;

import java.util.List;
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
        while (opcion != 5) { // Cambia a 5 para incluir la nueva opción
            System.out.println("---- Menú Socios ----");
            System.out.println("1. Añadir socio");
            System.out.println("2. Eliminar socio");
            System.out.println("3. Modificar tipo de seguro de un socio estándar");
            System.out.println("4. Mostrar socios"); // Nueva opción
            System.out.println("5. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();
            scanner.nextLine(); // Limpiar buffer

            switch (opcion) {
                case 1 -> addSocio();
                case 2 -> deleteSocio();
                case 3 -> modificarTipoSeguro();
                case 4 -> mostrarSocios(); // Llamar al método aquí
                case 5 -> System.out.println("Saliendo del menú socios.");
                default -> System.out.println("Opción no válida.");
            }
        }
    }

    private void mostrarSocios() {
        System.out.print("Ingrese el filtro para mostrar socios (todos/estandar/federado/infantil): ");
        String filtro = scanner.nextLine().toLowerCase(); // Obtener el filtro del usuario y convertir a minúsculas

        try {
            List<Socio> socios = controladorSocio.mostrarSociosFiltrados(filtro); // Llamar al método mostrarSocios() del controlador

            if (socios.isEmpty()) {
                System.out.println("No hay socios registrados con el filtro: " + filtro);
            } else {
                System.out.println("---- Lista de Socios ----");
                for (Socio socio : socios) {
                    System.out.println(socio.toString()); // Asegúrate de que Socio tenga un método toString() definido
                }
            }
        } catch (ControladorExcepcion e) {
            System.out.println(e.getMessage()); // Manejo de excepciones si el filtro no es válido
        }
    }

    private void modificarTipoSeguro() {
        System.out.print("Ingrese el número de socio estándar: ");
        int numSocio = scanner.nextInt();
        scanner.nextLine(); // Limpiar buffer

        System.out.print("Ingrese el nuevo tipo de seguro: ");
        String nuevoTipoSeguro = scanner.nextLine();

        System.out.print("Ingrese el nuevo precio del seguro: ");
        double nuevoPrecioSeguro = scanner.nextDouble();

        try {
            controladorSocio.modificarSeguro(numSocio, nuevoTipoSeguro, nuevoPrecioSeguro);
            System.out.println("Tipo de seguro modificado con éxito.");
        } catch (backsolutions.controlador.ControladorExcepcion e) {
            System.out.println(e.getMessage());
        }
    }

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
                backsolutions.modelo.Seguro seguro = new backsolutions.modelo.Seguro(tipoSeguro, precioSeguro);
                socio = new backsolutions.modelo.Estandar(numSocio, nombre, nifEstandar, seguro);
                break;
            case "federado":
                System.out.print("Ingrese el NIF: ");
                String nifFederado = scanner.nextLine();
                System.out.print("Ingrese el código de la Federación: ");
                String codigoFederacion = scanner.nextLine();
                System.out.print("Ingrese el nombre de la Federación: ");
                String nombreFederacion = scanner.nextLine();
                backsolutions.modelo.Federacion federacion = new backsolutions.modelo.Federacion(codigoFederacion, nombreFederacion);
                socio = new backsolutions.modelo.Federado(numSocio, nombre, nifFederado, federacion);
                break;
            case "infantil":
                System.out.print("Ingrese el número de socio del tutor: ");
                String numSocioTutor = scanner.nextLine();
                socio = new backsolutions.modelo.Infantil(numSocio, nombre, numSocioTutor);
                break;
            default:
                System.out.println("Tipo de socio no válido. Debe ser 'Estandar', 'Federado' o 'Infantil'.");
                return; // Salir del método si el tipo es inválido
        }

        try {
            controladorSocio.addSocio(socio); // Llamar al método correcto en el controlador
            System.out.println("Socio añadido con éxito.");
        } catch (backsolutions.controlador.ControladorExcepcion e) {
            System.out.println(e.getMessage());
        }
    }

    private void deleteSocio() {
        System.out.print("Ingrese el número de socio: ");
        int numSocio = scanner.nextInt();
        controladorSocio.deleteSocio(numSocio);
        System.out.println("Socio eliminado con éxito.");
    }

    public void mostrarFacturaMensual() {
        System.out.print("Ingrese el número de socio: ");
        int numSocio = scanner.nextInt();
        scanner.nextLine(); // Limpiar buffer

        Socio socio = controladorSocio.buscarSocio(numSocio);
        if (socio != null) {
            String mensajeFactura = controladorSocio.mostrarFacturaMensual(socio);
            System.out.println(mensajeFactura); // Muestra el mensaje de ControladorSocio
        } else {
            System.out.println("Socio no encontrado.");
        }
    }

}
