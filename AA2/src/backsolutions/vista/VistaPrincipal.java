package backsolutions.vista;

import backsolutions.controlador.*;
import backsolutions.modelo.*;
import backsolutions.dto.*;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;
import java.util.List;


public class VistaPrincipal {
    private VistaSocios vistaSocios;
    private Scanner scanner;

    private ControladorExcursion controladorExcursion;
    private ControladorSocio controladorSocio;
    private ControladorInscripcion controladorInscripcion;

    public VistaPrincipal(ControladorExcursion controladorExcursion, ControladorSocio controladorSocio, ControladorInscripcion controladorInscripcion) {
        this.controladorExcursion = controladorExcursion;
        this.controladorSocio = controladorSocio;
        this.controladorInscripcion = controladorInscripcion;
        this.vistaSocios = new VistaSocios(controladorSocio); // Inicializar VistaSocios
        this.scanner = new Scanner(System.in); // Inicializar el scanner aquí
    }

    public void mostrarMenu() {
        Scanner scanner = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("-----------------------------------------------------");
            System.out.println("¡Bienvenido a Senderos y Montañas! - Menú de opciones:");
            System.out.println("-----------------------------------------------------");
            System.out.println("Gestión de excursiones:");
            System.out.println("1. Añadir Excursión");
            System.out.println("2. Mostrar Excursiones con filtro entre fechas");
            System.out.println();
            System.out.println("Gestión de socios:");
            System.out.println("3. Añadir Socio");
            System.out.println("4. Modificar tipo de seguro de un socio Estándar");
            System.out.println("5. Eliminar Socio");
            System.out.println("6. Mostrar Socios (Todos o por tipo de socio)");
            System.out.println("7. Mostrar Factura mensual por socios");
            System.out.println();
            System.out.println("Gestión de inscripciones:");
            System.out.println("8. Añadir Inscripción");
            System.out.println("9. Cancelar Inscripción");
            System.out.println("10. Mostrar Inscripciones");
            System.out.println("11. Salir");
            System.out.println("-----------------------------------------------------");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    addExcursion(scanner);
                    break;
                case 2:
                    filtrarExcursiones();
                    break;
                case 3:
                    addSocio(scanner);
                    break;
                case 4:
                    modificarSeguro(scanner);
                    break;
                case 5:
                    deleteSocio(scanner);
                    break;
                case 6:
                    mostrarSocios();
                    break;
                case 7:
                    mostrarFacturaMensual();
                    break;
                case 8:
                    addInscripcion(scanner);
                    break;
                case 9:
                    cancelarInscripcion(scanner);
                    break;
                case 10:
                    mostrarInscripciones();
                    break;
                case 11:
                    System.out.println("Saliendo del programa.");
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        } while (opcion != 11);

        scanner.close();
    }

    //CASO 1: Agregar Excursión
    private void addExcursion(Scanner scanner) {
        System.out.print("Ingrese el código de la excursión: ");
        String codigo = scanner.next();

        System.out.print("Ingrese la descripción de la excursión: ");
        scanner.nextLine(); // Limpiar el buffer después de scanner.next()
        String descripcion = scanner.nextLine(); // Captura toda la línea para la descripción

        LocalDate fecha = null;
        while (fecha == null) {
            System.out.print("Ingrese la fecha de la excursión (dd-MM-yyyy): ");
            String fechaString = scanner.next();
            try {
                DateTimeFormatter formatoFecha = DateTimeFormatter.ofPattern("dd-MM-yyyy");
                fecha = LocalDate.parse(fechaString, formatoFecha);
            } catch (DateTimeParseException e) {
                System.out.println("Formato de fecha inválido. Use 'dd-MM-yyyy'. Intente de nuevo.");
            }
        }

        System.out.print("Ingrese el número de días que dura la excursión: ");
        int numDias = scanner.nextInt();

        System.out.print("Ingrese el precio de la inscripción: ");
        double precioInscripcion = scanner.nextDouble();

        // Crear la instancia de ExcursionDTO
        ExcursionDTO excursionDTO = new ExcursionDTO(codigo, descripcion, fecha, numDias, precioInscripcion);

        // Intentar agregar la excursión a través del controlador
        try {
            controladorExcursion.addExcursion(excursionDTO);
            System.out.println("Excursión añadida con éxito.");
        } catch (ControladorExcepcion e) {
            System.out.println("Error al añadir la excursión: " + e.getMessage());
        }
    }

    //CASO 2:
    private void filtrarExcursiones() {
        System.out.println("Ingrese las fechas para filtrar excursiones o presione Enter para ver todas:");

        System.out.print("Fecha de inicio (dd-MM-yyyy) o Enter para mostrar todas las excursiones: ");
        String inicioString = scanner.nextLine().trim();
        LocalDate inicio = null;

        // Asegurarse de que el usuario tiene oportunidad de ingresar la fecha de fin
        System.out.print("Fecha de fin (dd-MM-yyyy) o Enter para mostrar todas: ");
        String finString = scanner.nextLine().trim();
        LocalDate fin = null;

        try {
            DateTimeFormatter formatoFecha = DateTimeFormatter.ofPattern("dd-MM-yyyy");

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
            System.out.println("Formato de fecha inválido. Use 'dd-MM-yyyy'.");
        } catch (ControladorExcepcion e) {
            System.out.println("Error al filtrar las excursiones: " + e.getMessage());
        }
    }


    //CASO 3:
    private void addSocio(Scanner scanner) {
        // Solicitar al usuario los datos para crear un socio
        System.out.println("Ingrese el tipo de socio (1 = Estándar, 2 = Federado, 3 = Infantil): ");
        int tipoSocio = scanner.nextInt();
        scanner.nextLine(); // Limpiar el buffer

        System.out.print("Ingrese el número de socio: ");
        int numSocio = scanner.nextInt();
        scanner.nextLine(); // Limpiar el buffer

        System.out.print("Ingrese el nombre del socio: ");
        String nombreSocio = scanner.nextLine();

        Socio socio = null;

        // Según el tipo de socio, crear la instancia correspondiente
        switch (tipoSocio) {
            case 1:
                System.out.print("Ingrese el NIF del socio: ");
                String nif = scanner.nextLine();
                System.out.print("Ingrese el tipo de seguro: ");
                String tipo = scanner.nextLine();
                System.out.print("Ingrese el precio del seguro: ");
                double precio = scanner.nextDouble();

                socio = new Estandar(numSocio, nombreSocio, nif, new Seguro(tipo, precio));
                break;
            case 2:
                System.out.print("Ingrese el NIF del socio Federado: ");
                String nifFederado = scanner.nextLine();
                System.out.print("Ingrese el codigo de la Federacion: ");
                String codigo = scanner.nextLine();
                System.out.print("Ingrese el nombre de la Federacion: ");
                String nombre = scanner.nextLine();
                socio = new Federado(numSocio, nombreSocio, nifFederado, new Federacion(codigo,nombre));
                break;
            case 3:
                System.out.print("Ingrese el número de socio del tutor: ");
                String numSocioTutor = scanner.nextLine();
                socio = new Infantil(numSocio, nombreSocio, numSocioTutor);
                break;
            default:
                System.out.println("Tipo de socio no válido.");
                return; //Salir si el tipo de socio es inválido
        }

        //Agregamos el socio a través del controlador
        try {
            controladorSocio.addSocio(socio);
            System.out.println("Socio añadido con éxito.");
        } catch (ControladorExcepcion e) {
            System.out.println("Error al añadir el socio: " + e.getMessage());
        }
    }

    //CASO 4:
    private void modificarSeguro(Scanner scanner) {
        System.out.print("Ingrese el número de socio estándar: ");
        int numSocio = scanner.nextInt();
        scanner.nextLine(); // Limpiar el buffer

        System.out.print("Ingrese el nuevo tipo de seguro: ");
        String nuevoTipoSeguro = scanner.nextLine();

        System.out.print("Ingrese el nuevo precio del seguro: ");
        double nuevoPrecioSeguro = scanner.nextDouble();

        //Modificamos el seguro a través del controlador
        try {
            controladorSocio.modificarSeguro(numSocio, nuevoTipoSeguro, nuevoPrecioSeguro);
            System.out.println("Tipo de seguro modificado con éxito.");
        } catch (ControladorExcepcion e) {
            System.out.println("Error al modificar el seguro: " + e.getMessage());
        }
    }

    //CASO 5:
    private void deleteSocio(Scanner scanner) {
        System.out.print("Ingrese el número de socio a eliminar: ");
        int numSocio = scanner.nextInt();

        //Eliminamos el socio a través del controlador
        try {
            controladorSocio.deleteSocio(numSocio);
            System.out.println("Socio eliminado con éxito.");
        } catch (ControladorExcepcion e) {
            System.out.println("Error al eliminar el socio: " + e.getMessage());
        }
    }

    //CASO 6:
    private void mostrarSocios() {
        System.out.println("Seleccione el filtro (todos, estandar, federado, infantil): ");
        String filtro = scanner.nextLine();

        try {
            List<Socio> socios = controladorSocio.mostrarSociosFiltrados(filtro);
            if (socios.isEmpty()) {
                System.out.println("No se encontraron socios.");
            } else {
                for (Socio socio : socios) {
                    System.out.println(socio); // Esto llamará al método toString() del socio
                }
            }
        } catch (ControladorExcepcion e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    //CASO 7:
    private void mostrarFacturaMensual() {
        System.out.print("Ingrese el número de socio para mostrar la factura mensual: ");
        int numSocio = scanner.nextInt();

        // Buscar el socio correspondiente utilizando el ControladorSocio
        Socio socio = controladorSocio.buscarSocio(numSocio);
        if (socio == null) {
            System.out.println("No se encontró un socio con el número proporcionado.");
            return;
        }

        // Generar la factura mensual
        try {
            double totalFactura = controladorInscripcion.generarFacturaMensual(socio);
            System.out.println("La factura mensual para el socio " + numSocio + " es: " + totalFactura + "€");
        } catch (ControladorExcepcion e) {
            System.out.println("Error al generar la factura: " + e.getMessage());
        }
    }

    //CASO 8:
    private void addInscripcion(Scanner scanner) {
        System.out.print("Ingrese el número de socio para la inscripción: ");
        int numSocio = scanner.nextInt();
        scanner.nextLine(); // Limpiar el buffer

        System.out.print("Ingrese el código de la excursión a inscribir: ");
        String codigoExcursion = scanner.nextLine();

        System.out.print("Ingrese el tipo de seguro (opcional): ");
        String tipoSeguro = scanner.nextLine();
        double precioSeguro = 0.0;

        // Si se ingresó un tipo de seguro, pedir el precio
        if (!tipoSeguro.isEmpty()) {
            System.out.print("Ingrese el precio del seguro: ");
            precioSeguro = scanner.nextDouble();
        }

        // Llamamos a addInscripcion en el controlador, con seguro opcional
        try {
            controladorInscripcion.addInscripcion(codigoExcursion, numSocio, tipoSeguro, precioSeguro);
            System.out.println("Inscripción añadida con éxito.");
        } catch (ControladorExcepcion e) {
            System.out.println("Error al añadir la inscripción: " + e.getMessage());
        }
    }


    //CASO 9:
    private void cancelarInscripcion(Scanner scanner) {
        System.out.print("Ingrese el número de socio para cancelar la inscripción: ");
        int numSocio = scanner.nextInt();
        scanner.nextLine(); // Limpiar el buffer

        System.out.print("Ingrese el código de la excursión para cancelar la inscripción: ");
        String codigoExcursion = scanner.nextLine();

        try {
            controladorInscripcion.cancelarInscripcion(numSocio, codigoExcursion);
            System.out.println("Inscripción cancelada con éxito.");
        } catch (ControladorExcepcion e) {
            System.out.println("Error al cancelar la inscripción: " + e.getMessage());
        }
    }

    // CASO 10:
    private void mostrarInscripciones() {
        try {
            List<Inscripcion> inscripciones = controladorInscripcion.mostrarInscripciones();

            if (inscripciones.isEmpty()) {
                System.out.println("No hay inscripciones registradas.");
            } else {
                System.out.println("Lista de Inscripciones:");
                for (Inscripcion inscripcion : inscripciones) {
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


    // Metodo para calcular el importe con cargos o descuentos aplicados
    private double calcularImporte(Inscripcion inscripcion) {
        double precioExcursion = inscripcion.getExcursion().getPrecioInscripcion();
        double cuotaMensual = inscripcion.getSocio().calculoCuotaMensual(); // Asegúrate de que este metodo exista en la clase Socio

        // Calcular el importe basado en el tipo de socio y los cargos o descuentos
        double importe = precioExcursion + cuotaMensual;

        // Aquí puedes aplicar lógica adicional para descuentos o cargos según el tipo de socio
        if (inscripcion.getSocio() instanceof Estandar) {
            // Suponiendo que los Estandar no tienen descuentos adicionales
        } else if (inscripcion.getSocio() instanceof Federado) {
            // Aplica un descuento, por ejemplo:
            importe *= 0.9; // 10% de descuento para federados
        } else if (inscripcion.getSocio() instanceof Infantil) {
            // Lógica para socios infantiles
            importe -= inscripcion.getExcursion().getPrecioInscripcion(); // Sin cargos
        }

        return importe;
    }


}