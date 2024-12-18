package backsolutions.modelo.dao;

import backsolutions.modelo.Excursion;
import backsolutions.util.DatabaseConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;

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
    public List<Excursion> filtrarExcursiones(LocalDate inicio, LocalDate fin) throws SQLException {
        String sql = "SELECT * FROM Excursiones";
        List<Excursion> excursionesFiltradas = new ArrayList<>();

        if (inicio != null && fin != null) {
            sql += " WHERE fecha BETWEEN ? AND ?";
        }

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            if (inicio != null && fin != null) {
                stmt.setDate(1, Date.valueOf(inicio));
                stmt.setDate(2, Date.valueOf(fin));
            }

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    String codigo = rs.getString("codigo");
                    String descripcion = rs.getString("descripcion");
                    LocalDate fecha = rs.getDate("fecha").toLocalDate();
                    int numDias = rs.getInt("numDias");
                    double precioInscripcion = rs.getDouble("precioInscripcion");

                    Excursion excursion = new Excursion(codigo, descripcion, fecha, numDias, precioInscripcion);
                    excursionesFiltradas.add(excursion);
                }
            }
        }
        return excursionesFiltradas;
    }

    private Excursion mapearExcursion(ResultSet rs) throws SQLException {
        //el metodo es una forma de reutilizar el codigo para convertir un ResulSet en un objeto Excursion
        String codigo = rs.getString("codigo");
        String descripcion = rs.getString("descripcion");
        LocalDate fecha = rs.getDate("fecha").toLocalDate();
        int numDias = rs.getInt("numDias");
        double precioInscripcion = rs.getDouble("precioInscripcion");

        return new Excursion(codigo, descripcion, fecha, numDias, precioInscripcion);
    }


    @Override
    public List<Excursion> filtrarExcursionesPorFechas(LocalDate inicio, LocalDate fin) throws SQLException {
        String sql = "SELECT * FROM excursiones WHERE fecha >= ? AND fecha <= ?";
        List<Excursion> excursiones = new ArrayList<>();

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setDate(1, Date.valueOf(inicio));
            pstmt.setDate(2, Date.valueOf(fin));
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                Excursion excursion = mapearExcursion(rs);
                excursiones.add(excursion);
            }
        }
        return excursiones;
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

    public List<Excursion> filtrarExcursionesPorFecha(LocalDate fechaInicio, LocalDate fechaFin) throws SQLException {
        String sql = "{ CALL FiltrarExcursiones(?, ?) }";  //los ? ? representan los parametros fechaInicio fechaFin
        List<Excursion> excursionesFiltradas = new ArrayList<>();

        try (Connection conn = DatabaseConnection.getConnection();
             //utilizamos callablestatement para llamar al procedimiento almacenado en MySQL
             CallableStatement stmt = conn.prepareCall(sql)) {

            stmt.setDate(1, Date.valueOf(fechaInicio)); //convertimos los valores de LocalDate a java.sql.Date
            stmt.setDate(2, Date.valueOf(fechaFin));//para que sean compatibles con JDBC y el procedimiento almacenado

            ResultSet rs = stmt.executeQuery(); //recuperacion de resultados

            while (rs.next()) {
                String codigo = rs.getString("codigo");
                String descripcion = rs.getString("descripcion");
                LocalDate fecha = rs.getDate("fecha").toLocalDate();
                int numDias = rs.getInt("numDias");
                double precioInscripcion = rs.getDouble("precioInscripcion");

                Excursion excursion = new Excursion(codigo, descripcion, fecha, numDias, precioInscripcion);
                excursionesFiltradas.add(excursion);
            }
        }

        return excursionesFiltradas;
    }

}
