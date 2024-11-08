package backsolutions.modelo.dao;

import backsolutions.modelo.Factura;
import backsolutions.modelo.Socio;
import backsolutions.modelo.Inscripcion;
import backsolutions.util.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;

public class FacturaDAOImpl implements FacturaDAO {
    private static final String INSERT_FACTURA = "INSERT INTO Facturas (numSocio, totalExcursiones, totalCuota, totalPagar) VALUES (?, ?, ?, ?)";
    private static final String SELECT_FACTURA_BY_ID = "SELECT * FROM Facturas WHERE idFactura = ?";
    private static final String SELECT_ALL_FACTURAS = "SELECT * FROM Facturas";
    private static final String DELETE_FACTURA = "DELETE FROM Facturas WHERE idFactura = ?";
    private static final String SELECT_FACTURAS_BY_SOCIO = "SELECT * FROM Facturas WHERE numSocio = ?";

    //metodo para guardar una nueva factura
    @Override
    public void guardarFactura(Factura factura) throws SQLException {
        String sql = "INSERT INTO facturas (numSocio, fechaFactura, totalExcursiones, totalCuota, totalPagar) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            statement.setInt(1, factura.getSocio().getNumSocio());

            //Establecemos la fecha de la factura como la fecha actual
            statement.setDate(2, java.sql.Date.valueOf(LocalDate.now()));

            statement.setDouble(3, factura.calculoTotalExcursionesMensual());
            statement.setDouble(4, factura.getSocio().calculoCuotaMensual());
            statement.setDouble(5, factura.calculoTotalFacturaMensual());

            statement.executeUpdate();

            // Obtener el ID generado y establecerlo en la factura
            try (ResultSet generatedKeys = statement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    factura.setIdFactura(generatedKeys.getInt(1));
                }
            }
        }
    }

    //metodo para buscar una factura por ID
    @Override
    public Factura buscarFactura(int idFactura) throws SQLException {
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement statement = conn.prepareStatement(SELECT_FACTURA_BY_ID)) {
            statement.setInt(1, idFactura);
            ResultSet rs = statement.executeQuery();
            if (rs.next()) {
                return mapearFactura(rs);
            }
            return null;
        }
    }

    //metodo para listar todas las facturas
    @Override
    public List<Factura> listarFacturas() throws SQLException {
        List<Factura> facturas = new ArrayList<>();
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(SELECT_ALL_FACTURAS)) {
            while (rs.next()) {
                facturas.add(mapearFactura(rs));
            }
        }
        return facturas;
    }

    //metodo para eliminar una factura por ID
    @Override
    public void eliminarFactura(int idFactura) throws SQLException {
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement statement = conn.prepareStatement(DELETE_FACTURA)) {
            statement.setInt(1, idFactura);
            statement.executeUpdate();
        }
    }

    //Metodo para listar facturas por socio
    @Override
    public List<Factura> listarFacturasPorSocio(int numSocio) throws SQLException {
        List<Factura> facturas = new ArrayList<>();
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement statement = conn.prepareStatement(SELECT_FACTURAS_BY_SOCIO)) {
            statement.setInt(1, numSocio);
            ResultSet rs = statement.executeQuery();
            while (rs.next()) {
                facturas.add(mapearFactura(rs));
            }
        }
        return facturas;
    }

    //Metodo de utilidad para mapear los datos de ResultSet a un objeto Factura
    private Factura mapearFactura(ResultSet rs) throws SQLException {
        int idFactura = rs.getInt("idFactura");
        int numSocio = rs.getInt("numSocio");

        // Obtener datos de la factura
        Socio socio = new SocioDAOImpl().buscarSocio(numSocio);
        List<Inscripcion> inscripciones = new InscripcionDAOImpl().listarInscripcionesPorSocio(numSocio);

        Factura factura = new Factura(socio, inscripciones);
        factura.setIdFactura(idFactura);

        return factura;
    }
}
