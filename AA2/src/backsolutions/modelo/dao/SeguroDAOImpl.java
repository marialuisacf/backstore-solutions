package backsolutions.modelo.dao;

import backsolutions.modelo.Seguro;
import backsolutions.util.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SeguroDAOImpl implements SeguroDAO {
    private static final String INSERT_SEGURO = "INSERT INTO Seguros (tipo, precio) VALUES (?, ?)";
    private static final String SELECT_SEGURO_BY_TIPO = "SELECT * FROM Seguros WHERE tipo = ?";
    private static final String SELECT_ALL_SEGUROS = "SELECT * FROM Seguros";
    private static final String DELETE_SEGURO = "DELETE FROM Seguros WHERE tipo = ?";

    //metodo que inserta un nuevo seguro en la BD
    @Override
    public void guardarSeguro(Seguro seguro) throws SQLException {
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(INSERT_SEGURO)) {
            stmt.setString(1, seguro.getTipo());
            stmt.setDouble(2, seguro.getPrecio());
            stmt.executeUpdate();
        }
    }

    //busca un seguro específico: basico o ocmpleto
    @Override
    public Seguro buscarSeguro(String tipo) throws SQLException {
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(SELECT_SEGURO_BY_TIPO)) {
            stmt.setString(1, tipo);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return new Seguro(rs.getString("Tipo"), rs.getDouble("Precio"));
            }
            return null;
        }
    }

    //recupera todos los seguros almacenados
    @Override
    public List<Seguro> listarSeguros() throws SQLException {
        List<Seguro> seguros = new ArrayList<>();
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(SELECT_ALL_SEGUROS)) {
            while (rs.next()) {
                seguros.add(new Seguro(rs.getString("Tipo"), rs.getDouble("Precio")));
            }
        }
        return seguros;
    }

    //aunque quizas no es necesario, creamos este metodo para liminar un seguro
    @Override
    public void eliminarSeguro(String tipo) throws SQLException {
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(DELETE_SEGURO)) {
            stmt.setString(1, tipo);
            stmt.executeUpdate();
        }
    }
}
