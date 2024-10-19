package backsolutions.vista;

import backsolutions.controlador.*;
import backsolutions.modelo.*;


import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;
import java.util.List;


public class VistaPrincipal {

    private ControladorExcursion controladorExcursion;
    private ControladorSocio controladorSocio;
    private ControladorInscripcion controladorInscripcion;

    public VistaPrincipal(ControladorExcursion controladorExcursion, ControladorSocio controladorSocio, ControladorInscripcion controladorInscripcion) { // Modificado
        this.controladorExcursion = controladorExcursion;
        this.controladorSocio = controladorSocio;
        this.controladorInscripcion = controladorInscripcion; // Asignado
    }

    public void mostrarMenu() {
        Scanner scanner = new Scanner(System.in);
        int opcion;

        do {
            System.out.println("Menú de opciones:");
            System.out.println("1. Añadir Excursión");
            System.out.println("2. Mostrar Excursiones con filtro entre fechas");
            System.out.println("3. Añadir Socio");
            System.out.println("4. Modificar tipo de seguro de un socio Estandar");
            System.out.println("5. Eliminar Socio");
            System.out.println("6. Añadir Inscripción");
            System.out.println("7. Cancelar Inscripción");
            System.out.println("8. Mostrar Inscripciones");
            System.out.println("9. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    addExcursion(scanner);
                    break;
                case 2:
                    filtrarExcursiones(scanner);
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
                    addInscripcion(scanner);
                    break;
                case 7:
                    cancelarInscripcion(scanner);
                    break;
                case 8:
                    mostrarInscripciones();
                    break;
                case 9:
                    System.out.println("Saliendo del programa.");
                    break;
                default:
                    System.out.println("Opción no válida.");
            }
        } while (opcion != 9);

        scanner.close();
    }

    //CASO 1:
    private void addExcursion(Scanner scanner) {
        // Solicitar al usuario los datos para crear la excursión
        System.out.print("Ingrese el código de la excursión: ");
        String codigo = scanner.next();

        System.out.print("Ingrese la descripción de la excursión: ");
        String descripcion = scanner.next();

        System.out.print("Ingrese la fecha de la excursión (dd-MM-yyyy): ");
        String fechaString = scanner.next();
        LocalDate fecha = null;
        try {
            DateTimeFormatter formatoFecha = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            fecha = LocalDate.parse(fechaString, formatoFecha);
        } catch (DateTimeParseException e) {
            System.out.println("Formato de fecha inválido. Use 'dd-MM-yyyy'.");
            return; // Salir si la fecha es inválida
        }

        System.out.print("Ingrese el número de días que dura la excursión: ");
        int numDias = scanner.nextInt(); // Cambiado a nextInt()

        System.out.print("Ingrese el precio de la inscripción: ");
        double precioInscripcion = scanner.nextDouble();

        // Crear la instancia de Excursion
        Excursion excursion = new Excursion(codigo, descripcion, fecha, numDias, precioInscripcion);

        // Intentar agregar la excursión a través del controlador
        try {
            controladorExcursion.addExcursion(excursion);
        } catch (ControladorExcepcion e) {
            System.out.println("Error al añadir la excursión: " + e.getMessage());
        }
    }

    //CASO 2:
    private void filtrarExcursiones(Scanner scanner) {
        System.out.print("Ingrese la fecha de inicio (dd-MM-yyyy): ");
        String inicioString = scanner.next();
        LocalDate inicio = null;

        System.out.print("Ingrese la fecha de fin (dd-MM-yyyy): ");
        String finString = scanner.next();
        LocalDate fin = null;

        try {
            DateTimeFormatter formatoFecha = DateTimeFormatter.ofPattern("dd-MM-yyyy");
            inicio = LocalDate.parse(inicioString, formatoFecha);
            fin = LocalDate.parse(finString, formatoFecha);
        } catch (DateTimeParseException e) {
            System.out.println("Formato de fecha inválido. Use 'dd-MM-yyyy'.");
            return; // Salir si las fechas son inválidas
        }

        //Filtramos excursiones a través del controlador
        try {
            controladorExcursion.filtrarExcursiones(inicio, fin);
        } catch (ControladorExcepcion e) {
            System.out.println("Error: " + e.getMessage());
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
    private void addInscripcion(Scanner scanner) {
        // Solicitar al usuario los datos necesarios para la inscripción
        System.out.print("Ingrese el número de inscripción: ");
        String numInscripcion = scanner.nextLine();
        scanner.nextLine(); // Limpiar el buffer después de nextInt()

        System.out.print("Ingrese el número de socio para la inscripción: ");
        int numSocio = scanner.nextInt();
        scanner.nextLine(); // Limpiar el buffer después de nextInt()

        System.out.print("Ingrese el código de la excursión a inscribir: ");
        String codigoExcursion = scanner.nextLine();

        // Buscar el socio correspondiente utilizando el ControladorSocio
        Socio socio = controladorSocio.buscarSocio(numSocio);
        if (socio == null) {
            System.out.println("No se encontró un socio con el número proporcionado.");
            return;
        }

        // Buscar la excursión correspondiente utilizando el ControladorExcursion
        Excursion excursion = controladorExcursion.buscarExcursion(codigoExcursion);
        if (excursion == null) {
            System.out.println("No se encontró una excursión con el código proporcionado.");
            return;
        }

        // Crear el seguro solo si el socio es de tipo Estandar
        Seguro seguro = null;
        if (socio instanceof Estandar) {
            seguro = ((Estandar) socio).getSeguro(); // Obtener el seguro del socio Estandar
        }

        // Crear la nueva instancia de Inscripción
        Inscripcion inscripcion = new Inscripcion(numInscripcion, socio, excursion, LocalDate.now(), seguro);

        // Agregamos la inscripción a través del controlador
        try {
            controladorInscripcion.addInscripcion(inscripcion);
            System.out.println("Inscripción añadida con éxito.");
        } catch (InscripcionInvalidaExcepcion e) {
            System.out.println("Error al añadir la inscripción: " + e.getMessage());
        }
    }

    //CASO 7:
    private void cancelarInscripcion(Scanner scanner) {
        System.out.print("Ingrese el número de socio para cancelar la inscripción: ");
        int numSocio = scanner.nextInt();
        scanner.nextLine(); // Limpiar el buffer

        System.out.print("Ingrese el código de la excursión para cancelar la inscripción: ");
        String codigoExcursion = scanner.nextLine();

        // Buscar el socio correspondiente utilizando el ControladorSocio
        Socio socio = controladorSocio.buscarSocio(numSocio);
        if (socio == null) {
            System.out.println("No se encontró un socio con el número proporcionado.");
            return;
        }

        // Buscar la excursión correspondiente utilizando el ControladorExcursion
        Excursion excursion = controladorExcursion.buscarExcursion(codigoExcursion);
        if (excursion == null) {
            System.out.println("No se encontró una excursión con el código proporcionado.");
            return;
        }

        //Cancelamos la inscripción a través del controlador
        try {
            controladorInscripcion.cancelarInscripcion(socio, excursion);
        } catch (InscripcionInvalidaExcepcion e) {
            System.out.println("Error al cancelar la inscripción: " + e.getMessage());
        } catch (SocioNoEncontradoExcepcion e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    //CASO 8:
    private void mostrarInscripciones() {
        // Llamar al metodo del controlador para obtener la lista de inscripciones
        List<Inscripcion> inscripciones = controladorInscripcion.mostrarInscripciones();

        // Verificar si hay inscripciones
        if (inscripciones.isEmpty()) {
            System.out.println("No hay inscripciones registradas.");
            return;
        }

        // Imprimir encabezado
        System.out.println("Lista de Inscripciones:");

        // Recorrer y mostrar cada inscripción
        for (Inscripcion inscripcion : inscripciones) {
            // Suponiendo que tienes métodos en Inscripcion, Socio y Excursion para obtener los detalles
            int numSocio = inscripcion.getSocio().getNumSocio(); // Cambia según el método correcto
            String nombre = ""; // Cambia según el método correcto para obtener el nombre del socio
            String fechaExcursion = inscripcion.getExcursion().getFecha().toString(); // Asegúrate de que el formato sea adecuado
            String descripcion = inscripcion.getExcursion().getDescripcion();
            double importe = calcularImporte(inscripcion); // Método que calculará el importe con cargos o descuentos

            // Imprimir detalles de la inscripción
            System.out.printf("Número de Socio: %s, Nombre: %s, Fecha de Excursión: %s, Descripción: %s, Importe: %.2f\n",
                    numSocio, nombre, fechaExcursion, descripcion, importe);
        }
    }

    // Metodo para calcular el importe con cargos o descuentos aplicados
    private double calcularImporte(Inscripcion inscripcion) {
        double precioExcursion = inscripcion.getExcursion().getPrecioInscripcion();
        double cuotaMensual = inscripcion.getSocio().calculoCuotaMensual(); // Asegúrate de que este método exista en la clase Socio

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