package backsolutions.modelo.dao;

import backsolutions.modelo.Federacion;
import backsolutions.util.DatabaseConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FederacionDAOImpl implements FederacionDAO {
    private static final String INSERT_FEDERACION = "INSERT INTO Federaciones (codigo, nombre) VALUES (?, ?)";
    private static final String SELECT_FEDERACION_BY_CODIGO = "SELECT * FROM Federaciones WHERE codigo = ?";
    private static final String SELECT_ALL_FEDERACIONES = "SELECT * FROM Federaciones";
    private static final String DELETE_FEDERACION = "DELETE FROM Federaciones WHERE codigo = ?";

    @Override
    public void guardarFederacion(Federacion federacion) throws SQLException {
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(INSERT_FEDERACION)) {
            pstmt.setString(1, federacion.getCodigo());
            pstmt.setString(2, federacion.getNombre());
            pstmt.executeUpdate();
        }
    }

    @Override
    public Federacion buscarFederacion(String codigo) throws SQLException {
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(SELECT_FEDERACION_BY_CODIGO)) {
            pstmt.setString(1, codigo);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return new Federacion(rs.getString("Codigo"), rs.getString("Nombre federacion"));
            }
            return null;
        }
    }

    @Override
    public List<Federacion> listarFederaciones() throws SQLException {
        List<Federacion> federaciones = new ArrayList<>();
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(SELECT_ALL_FEDERACIONES)) {
            while (rs.next()) {
                Federacion federacion = new Federacion(rs.getString("Codigo"), rs.getString("Nombre federacion"));
                federaciones.add(federacion);
            }
        }
        return federaciones;
    }

    @Override
    public void eliminarFederacion(String codigo) throws SQLException {
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(DELETE_FEDERACION)) {
            pstmt.setString(1, codigo);
            pstmt.executeUpdate();
        }
    }
}
