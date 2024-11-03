package backsolutions.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/senderos_montanas"; //indicamos el nombre de nuestra BBDD
    private static final String USER = "developer";
    private static final String PASSWORD = "Poobbdd.1";

    // Metodo para OBTENER la conexión, que abrirá la conexión cuando sea necesario
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    // Metodo para CERRAR la conexión
    public static void closeConnection(Connection connection) {
        try {
            if (connection != null && !connection.isClosed()) {
                connection.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
