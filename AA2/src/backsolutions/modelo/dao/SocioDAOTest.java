package backsolutions.modelo.dao;

import backsolutions.modelo.*;
import java.sql.SQLException;
import java.util.List;

public class SocioDAOTest {
    public static void main(String[] args) {
        SocioDAO socioDAO = new SocioDAOImpl();

        try {
            //1. Guardar un nuevo socio
            System.out.println("Guardando nuevos socios...");
            Socio estandar = new Estandar(1, "Maria Cruz", "12345678A", new Seguro("basico", 10.0));
            Socio federado = new Federado(2, "Anna Davins", "87654321B", new Federacion("FED001", "Federacion Montaña"));
            Socio infantil = new Infantil(3, "Carolina Espinoza", "34567890C");

            socioDAO.guardarSocio(estandar);
            socioDAO.guardarSocio(federado);
            socioDAO.guardarSocio(infantil);
            System.out.println("Socios guardados correctamente.");

            //2. Buscar un socio por número
            System.out.println("\nBuscando socio con numSocio 1...");
            Socio encontrado = socioDAO.buscarSocio(1);
            if (encontrado != null) {
                System.out.println("Socio encontrado: " + encontrado);
            } else {
                System.out.println("Socio no encontrado.");
            }

            //3. Actualizar un socio
            System.out.println("\nActualizando el nombre del socio con numSocio 1...");
            estandar.setNombre("Maria Cruz Modificado");
            socioDAO.actualizarSocio(estandar);
            System.out.println("Socio actualizado.");

            //4. Listar todos los socios
            System.out.println("\nListando todos los socios...");
            List<Socio> todosLosSocios = socioDAO.listarSocios("todos");
            todosLosSocios.forEach(System.out::println);

            //5. Listar socios por tipo
            System.out.println("\nListando socios de tipo 'estandar'...");
            List<Socio> sociosEstandar = socioDAO.listarSocios("Estandar");
            sociosEstandar.forEach(System.out::println);

            System.out.println("\nListando socios de tipo 'federado'...");
            List<Socio> sociosFederado = socioDAO.listarSocios("Federado");
            sociosFederado.forEach(System.out::println);

            System.out.println("\nListando socios de tipo 'Infantil'...");
            List<Socio> sociosInfantil = socioDAO.listarSocios("Infantil");
            sociosInfantil.forEach(System.out::println);

            //6. Eliminar un socio
            System.out.println("\nEliminando socio con numSocio 1...");
            socioDAO.eliminarSocio(1);
            System.out.println("Socio eliminado.");

            // Verificar si el socio fue eliminado
            System.out.println("\nListando todos los socios después de la eliminación...");
            todosLosSocios = socioDAO.listarSocios("todos");
            todosLosSocios.forEach(System.out::println);

        } catch (SQLException e) {
            System.err.println("Error en la operación con la base de datos: " + e.getMessage());
        }
    }
}
