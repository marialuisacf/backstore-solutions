package backsolutions.util;

import java.sql.Connection;
import java.sql.SQLException;

public class TestDatabaseConnection {
    public static void main(String[] args) {
        try (Connection connection = DatabaseConnection.getConnection()) {
            if (connection != null) {
                System.out.println("Conexión exitosa a la base de datos.");
            } else {
                System.out.println("Conexión fallida.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
