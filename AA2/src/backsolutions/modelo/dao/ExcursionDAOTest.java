package backsolutions.modelo.dao;

import backsolutions.modelo.Excursion;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public class ExcursionDAOTest {
    public static void main(String[] args) {
        ExcursionDAO excursionDAO = new ExcursionDAOImpl();

        try {
            //1.Guardar una nueva excursión en la BD para probar el metodo guardarExcursion
            System.out.println("Guardando nuevas excursiones...");
            Excursion excursion1 = new Excursion("EXC001", "Excursion Montserrat", LocalDate.of(2024, 11, 22), 1, 10.0);
            Excursion excursion2 = new Excursion("EXC002", "Excursion StCugat", LocalDate.of(2024, 11, 24), 1, 10.0);

            excursionDAO.guardarExcursion(excursion1);
            excursionDAO.guardarExcursion(excursion2);
            System.out.println("Excursiones guardadas correctamente.");

            //2.Buscar una excursión por código, para probar el metodo buscarExcursion
            System.out.println("\nBuscando excursión con código 'EXC001'...");
            Excursion encontrada = excursionDAO.buscarExcursion("EXC001");
            if (encontrada != null) {
                System.out.println("Excursión encontrada: " + encontrada);
            } else {
                System.out.println("Excursión no encontrada.");
            }

            //3.Listar todas las excursiones. Recupera y muestra todas las excursiones almacenadas en la BD
            System.out.println("\nListando todas las excursiones...");
            List<Excursion> todasLasExcursiones = excursionDAO.listarExcursiones();
            todasLasExcursiones.forEach(System.out::println);

            //4.Actualizar una excursión. cambia la descripcion y actualiza la excursion en la BD
            System.out.println("\nActualizando la descripción de la excursión 'EXC001'...");
            excursion1.setDescripcion("Excursion Tibidabo, antes era Montserrat");
            excursionDAO.actualizarExcursion(excursion1);
            System.out.println("Excursión actualizada.");

            //5.Eliminar una excursión con el codigo indicado para verificar eliminarExcursion
            System.out.println("\nEliminando la excursión con código 'EXC002'...");
            excursionDAO.eliminarExcursion("EXC002");
            System.out.println("Excursión eliminada.");

            // Verificar que la excursión fue eliminada
            System.out.println("\nListando todas las excursiones después de la eliminación...");
            todasLasExcursiones = excursionDAO.listarExcursiones();
            todasLasExcursiones.forEach(System.out::println);

        } catch (SQLException e) {
            System.err.println("Error en la operación con la base de datos: " + e.getMessage());
        }
    }
}
