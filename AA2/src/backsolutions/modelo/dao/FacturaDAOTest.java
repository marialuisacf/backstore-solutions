package backsolutions.modelo.dao;

import backsolutions.modelo.Factura;
import backsolutions.modelo.Socio;
import backsolutions.modelo.Inscripcion;
import backsolutions.modelo.Seguro;
import backsolutions.modelo.Estandar;
import backsolutions.modelo.Excursion;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FacturaDAOTest {
    public static void main(String[] args) {
        FacturaDAO facturaDAO = new FacturaDAOImpl();
        SocioDAO socioDAO = new SocioDAOImpl();
        ExcursionDAO excursionDAO = new ExcursionDAOImpl();
        InscripcionDAO inscripcionDAO = new InscripcionDAOImpl();

        try {
            //Preparación de datos para la prueba
            System.out.println("Guardando datos de prueba para socio, excursión e inscripción...");

            //1.Crear un socio
            Socio socio = new Estandar(4, "Fernando", "12345678A", new Seguro("basico", 10.0));
            socioDAO.guardarSocio(socio);

            //2.Crear una excursión
            Excursion excursion = new Excursion("EXC002", "Excursion Montserrat", java.sql.Date.valueOf("2024-12-15").toLocalDate(), 2, 50.0);
            excursionDAO.guardarExcursion(excursion);

            //3.Crear una inscripción
            Inscripcion inscripcion = new Inscripcion("INS001", socio, excursion, java.sql.Date.valueOf("2024-12-01").toLocalDate(), socio instanceof Estandar ? ((Estandar) socio).getSeguro() : null);
            inscripcionDAO.guardarInscripcion(inscripcion);

            List<Inscripcion> inscripciones = new ArrayList<>();
            inscripciones.add(inscripcion);

            //4.guardar una nueva factura
            System.out.println("\nGuardando una nueva factura...");
            Factura factura = new Factura(socio, inscripciones);
            facturaDAO.guardarFactura(factura);
            System.out.println("Factura guardada con éxito.");

            //5.Buscar una factura por ID
            System.out.println("\nBuscando la factura con idFactura " + factura.getIdFactura() + "...");
            Factura facturaEncontrada = facturaDAO.buscarFactura(factura.getIdFactura());
            if (facturaEncontrada != null) {
                System.out.println("Factura encontrada: " + facturaEncontrada);
            } else {
                System.out.println("Factura no encontrada.");
            }

            //6.Listar todas las facturas
            System.out.println("\nListando todas las facturas...");
            List<Factura> todasLasFacturas = facturaDAO.listarFacturas();
            todasLasFacturas.forEach(System.out::println);

            //7.Eliminar la factura
            System.out.println("\nEliminando la factura con idFactura " + factura.getIdFactura() + "...");
            facturaDAO.eliminarFactura(factura.getIdFactura());
            System.out.println("Factura eliminada con éxito.");

            //Verificar si la factura fue eliminada
            System.out.println("\nListando todas las facturas después de la eliminación...");
            todasLasFacturas = facturaDAO.listarFacturas();
            todasLasFacturas.forEach(System.out::println);

        } catch (SQLException e) {
            System.err.println("Error en la operación con la base de datos: " + e.getMessage());
        }
    }
}
