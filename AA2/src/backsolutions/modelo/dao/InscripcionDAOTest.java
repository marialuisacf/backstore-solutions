package backsolutions.modelo.dao;

import backsolutions.modelo.*;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class InscripcionDAOTest {
    public static void main(String[] args) {
        InscripcionDAO inscripcionDAO = new InscripcionDAOImpl();
        SocioDAO socioDAO = new SocioDAOImpl();
        ExcursionDAO excursionDAO = new ExcursionDAOImpl();

        try {
            // 1. Crear una nueva inscripción
            System.out.println("Guardando una nueva inscripción...");
            Socio socio = socioDAO.buscarSocio(2); // Supongamos que existe un socio con numSocio 2
            Excursion excursion = excursionDAO.buscarExcursion("EXC001"); // Supongamos que existe una excursión con código EXC001

            if (socio != null && excursion != null) {
                // Determinar si el seguro es necesario según el tipo de socio
                String tipoSeguro = null;
                double seguroPrecio = 0.0;

                if (socio instanceof Estandar) {
                    tipoSeguro = "básico"; // Puedes establecer el tipo de seguro por defecto
                    seguroPrecio = 15.0; // Precio de ejemplo para el seguro
                }

                // Crear la inscripción con el seguro opcional
                Inscripcion inscripcion = new Inscripcion("INS001", socio, excursion, LocalDate.now(), tipoSeguro, seguroPrecio);
                inscripcionDAO.guardarInscripcion(inscripcion);
                System.out.println("Inscripción guardada correctamente.");
            } else {
                System.out.println("Socio o Excursión no encontrados para la inscripción.");
            }

            //2.Buscar una inscripción por número
            System.out.println("\nBuscando inscripción con numInscripcion 'INS001'...");
            Inscripcion encontrada = inscripcionDAO.buscarInscripcion("INS001");
            if (encontrada != null) {
                System.out.println("Inscripción encontrada: " + encontrada);
            } else {
                System.out.println("Inscripción no encontrada.");
            }

            //3.Listar todas las inscripciones
            System.out.println("\nListando todas las inscripciones...");
            List<Inscripcion> todasLasInscripciones = inscripcionDAO.listarInscripciones();
            todasLasInscripciones.forEach(System.out::println);

            //4.Eliminar una inscripción
            System.out.println("\nEliminando inscripción con numInscripcion 'INS001'...");
            inscripcionDAO.eliminarInscripcion("INS001");
            System.out.println("Inscripción eliminada.");

            //5.Verificar si la inscripción fue eliminada
            System.out.println("\nListando todas las inscripciones después de la eliminación...");
            todasLasInscripciones = inscripcionDAO.listarInscripciones();
            todasLasInscripciones.forEach(System.out::println);

        } catch (SQLException e) {
            System.err.println("Error en la operación con la base de datos: " + e.getMessage());
        }
    }
}
