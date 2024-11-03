package backsolutions.modelo.dao;

import backsolutions.modelo.Seguro;

import java.sql.SQLException;
import java.util.List;

public class SeguroDAOTest {
    public static void main(String[] args) {
        SeguroDAO seguroDAO = new SeguroDAOImpl();

        try {
            //1.Guardar nuevos seguros. Crea y guarda los dos tipos de seguro del caso practico 'basico' y 'completo' en la BD
            System.out.println("Guardando seguros...");
            Seguro seguroBasico = new Seguro("basico", 10.0);
            Seguro seguroCompleto = new Seguro("completo", 20.0);
            seguroDAO.guardarSeguro(seguroBasico);
            seguroDAO.guardarSeguro(seguroCompleto);
            System.out.println("Seguros guardados correctamente.");

            //2.Buscar un seguro por tipo
            System.out.println("\nBuscando seguro de tipo 'basico'...");
            Seguro encontrado = seguroDAO.buscarSeguro("basico");
            if (encontrado != null) {
                System.out.println("Seguro encontrado: " + encontrado);
            } else {
                System.out.println("Seguro 'basico' no encontrado.");
            }

            //3.Listar todos los seguros
            System.out.println("\nListando todos los seguros...");
            List<Seguro> todosLosSeguros = seguroDAO.listarSeguros();
            todosLosSeguros.forEach(System.out::println);

            //4.Eliminar un seguro
            System.out.println("\nEliminando seguro de tipo 'basico'...");
            seguroDAO.eliminarSeguro("basico");
            System.out.println("Seguro 'basico' eliminado.");

            //para verificar si el seguro fue eliminado
            System.out.println("\nListando todos los seguros después de la eliminación...");
            todosLosSeguros = seguroDAO.listarSeguros();
            todosLosSeguros.forEach(System.out::println);

        } catch (SQLException e) {
            System.err.println("Error en la operación con la base de datos: " + e.getMessage());
        }
    }
}
