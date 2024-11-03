package backsolutions.modelo.dao;

import backsolutions.modelo.Excursion;
import backsolutions.util.DatabaseConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ExcursionDAOImpl implements ExcursionDAO {

    @Override
    public void guardarExcursion(Excursion excursion) throws SQLException {
        String query = "INSERT INTO Excursiones (codigo, descripcion, fecha, numDias, precioInscripcion) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, excursion.getCodigo());
            stmt.setString(2, excursion.getDescripcion());
            stmt.setDate(3, Date.valueOf(excursion.getFecha()));
            stmt.setInt(4, excursion.getNumDias());
            stmt.setDouble(5, excursion.getPrecioInscripcion());
            stmt.executeUpdate();
        }
    }

    @Override
    public Excursion buscarExcursion(String codigo) throws SQLException {
        String query = "SELECT * FROM Excursiones WHERE codigo = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, codigo);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Excursion(
                        rs.getString("codigo"),
                        rs.getString("descripcion"),
                        rs.getDate("fecha").toLocalDate(),
                        rs.getInt("numDias"),
                        rs.getDouble("precioInscripcion")
                );
            }
        }
        return null;
    }

    @Override
    public List<Excursion> listarExcursiones() throws SQLException {
        String query = "SELECT * FROM Excursiones";
        List<Excursion> excursiones = new ArrayList<>();
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                Excursion excursion = new Excursion(
                        rs.getString("codigo"),
                        rs.getString("descripcion"),
                        rs.getDate("fecha").toLocalDate(),
                        rs.getInt("numDias"),
                        rs.getDouble("precioInscripcion")
                );
                excursiones.add(excursion);
            }
        }
        return excursiones;
    }

    @Override
    public void actualizarExcursion(Excursion excursion) throws SQLException {
        String query = "UPDATE Excursiones SET descripcion = ?, fecha = ?, numDias = ?, precioInscripcion = ? WHERE codigo = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, excursion.getDescripcion());
            stmt.setDate(2, Date.valueOf(excursion.getFecha()));
            stmt.setInt(3, excursion.getNumDias());
            stmt.setDouble(4, excursion.getPrecioInscripcion());
            stmt.setString(5, excursion.getCodigo());
            stmt.executeUpdate();
        }
    }

    @Override
    public void eliminarExcursion(String codigo) throws SQLException {
        String query = "DELETE FROM Excursiones WHERE codigo = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, codigo);
            stmt.executeUpdate();
        }
    }
}
