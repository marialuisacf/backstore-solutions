package backsolutions.modelo.dao;

import backsolutions.modelo.Inscripcion;
import backsolutions.modelo.Seguro;
import backsolutions.modelo.Socio;
import backsolutions.modelo.Excursion;
import backsolutions.util.DatabaseConnection;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class InscripcionDAOImpl implements InscripcionDAO {
    private static final String INSERT_INSCRIPCION = "INSERT INTO Inscripciones (numInscripcion, numSocio, codigoExcursion, fechaInscripcion, tipoSeguro) VALUES (?, ?, ?, ?, ?)";
    private static final String SELECT_INSCRIPCION_BY_NUM = "SELECT * FROM Inscripciones WHERE numInscripcion = ?";
    private static final String SELECT_ALL_INSCRIPCIONES = "SELECT * FROM Inscripciones";
    private static final String SELECT_INSCRIPCIONES_BY_SOCIO = "SELECT * FROM Inscripciones WHERE numSocio = ?";
    private static final String DELETE_INSCRIPCION = "DELETE FROM Inscripciones WHERE numInscripcion = ?";

    //metodo inserta una inscripcion en la tabla
    @Override
    public void guardarInscripcion(Inscripcion inscripcion) throws SQLException {
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement statement = conn.prepareStatement(INSERT_INSCRIPCION)) {

            statement.setString(1, inscripcion.getNumInscripcion());
            statement.setInt(2, inscripcion.getSocio().getNumSocio());
            statement.setString(3, inscripcion.getExcursion().getCodigo());
            statement.setDate(4, java.sql.Date.valueOf(inscripcion.getFechaInscripcion()));

            // Verificar si el seguro está presente
            statement.setString(5, inscripcion.getTipoSeguro());
            statement.setDouble(6, inscripcion.getSeguroPrecio());

            statement.executeUpdate();
        }
    }

    //metodo que busca una inscripcion especifica
    @Override
    public Inscripcion buscarInscripcion(String numInscripcion) throws SQLException {
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(SELECT_INSCRIPCION_BY_NUM)) {
            pstmt.setString(1, numInscripcion);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return mapearInscripcion(rs);
            }
            return null;
        }
    }

    //metodo devuelve todas las inscripciones
    @Override
    public List<Inscripcion> listarInscripciones() throws SQLException {
        List<Inscripcion> inscripciones = new ArrayList<>();
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(SELECT_ALL_INSCRIPCIONES)) {
            while (rs.next()) {
                inscripciones.add(mapearInscripcion(rs));
            }
        }
        return inscripciones;
    }

    //metodo que filtra inscripciones según el socio indicado
    @Override
    public List<Inscripcion> listarInscripcionesPorSocio(int numSocio) throws SQLException {
        List<Inscripcion> inscripciones = new ArrayList<>();
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(SELECT_INSCRIPCIONES_BY_SOCIO)) {
            pstmt.setInt(1, numSocio);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                inscripciones.add(mapearInscripcion(rs));
            }
        }
        return inscripciones;
    }

    //metodo elimina 1 inscripcion utilizando el numInscripcion
    @Override
    public void eliminarInscripcion(String numInscripcion) throws SQLException {
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(DELETE_INSCRIPCION)) {
            pstmt.setString(1, numInscripcion);
            pstmt.executeUpdate();
        }
    }

    //metodo calcula el importe de la inscripcion considerando los dto. segun el tipo de socio
    @Override
    public double calcularImporte(Inscripcion inscripcion) throws SQLException {
        double precioBase = inscripcion.getExcursion().getPrecioInscripcion();
        double cuotaMensual = inscripcion.getSocio().calculoCuotaMensual();

        double importe = precioBase + cuotaMensual;
        if (inscripcion.getSocio() instanceof backsolutions.modelo.Federado) {
            importe *= 0.9;
        } else if (inscripcion.getSocio() instanceof backsolutions.modelo.Infantil) {
            importe = precioBase;
        }
        return importe;
    }

    // Metodo para mapear una inscripción desde un ResultSet
    private Inscripcion mapearInscripcion(ResultSet rs) throws SQLException {
        String numInscripcion = rs.getString("numInscripcion");
        int numSocio = rs.getInt("numSocio");
        String codigoExcursion = rs.getString("codigoExcursion");
        Date fechaInscripcion = rs.getDate("fechaInscripcion");
        String tipoSeguro = rs.getString("tipoSeguro");
        double precioSeguro = rs.getDouble("seguroPrecio");

        Socio socio = new SocioDAOImpl().buscarSocio(numSocio);
        Excursion excursion = new ExcursionDAOImpl().buscarExcursion(codigoExcursion);

        return new Inscripcion(numInscripcion, socio, excursion, fechaInscripcion.toLocalDate(), tipoSeguro, precioSeguro);
    }
}
