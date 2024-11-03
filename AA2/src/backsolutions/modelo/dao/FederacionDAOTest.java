package backsolutions.modelo.dao;

import backsolutions.modelo.Federacion;
import java.sql.SQLException;
import java.util.List;

public class FederacionDAOTest {
    public static void main(String[] args) {
        FederacionDAO federacionDAO = new FederacionDAOImpl();

        try {
            //1. Guardar una nueva federación
            System.out.println("Guardando nuevas federaciones...");
            Federacion federacion1 = new Federacion("FED001", "Federación nacional");
            Federacion federacion2 = new Federacion("FED002", "Federación comarcal");

            federacionDAO.guardarFederacion(federacion1);
            federacionDAO.guardarFederacion(federacion2);
            System.out.println("Federaciones guardadas correctamente.");

            //2. Buscar una federación por código
            System.out.println("\nBuscando federación con código FED001...");
            Federacion encontrada = federacionDAO.buscarFederacion("FED001");
            if (encontrada != null) {
                System.out.println("Federación encontrada: " + encontrada);
            } else {
                System.out.println("Federación no encontrada.");
            }

            //3.Listar todas las federaciones
            System.out.println("\nListando todas las federaciones...");
            List<Federacion> todasLasFederaciones = federacionDAO.listarFederaciones();
            todasLasFederaciones.forEach(System.out::println);

            //4. Eliminar una federación
            System.out.println("\nEliminando federación con código FED001...");
            federacionDAO.eliminarFederacion("FED002");
            System.out.println("Federación eliminada.");

            //Verificar que la federación fue eliminada
            System.out.println("\nListando todas las federaciones después de la eliminación...");
            todasLasFederaciones = federacionDAO.listarFederaciones();
            todasLasFederaciones.forEach(System.out::println);

        } catch (SQLException e) {
            System.err.println("Error en la operación con la base de datos: " + e.getMessage());
        }
    }
}
