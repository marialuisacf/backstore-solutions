package backsolutions.modelo.dao;

import backsolutions.modelo.Socio;
import backsolutions.modelo.Estandar;
import backsolutions.modelo.Federado;
import backsolutions.modelo.Infantil;
import backsolutions.modelo.Seguro;
import backsolutions.modelo.Federacion;
import backsolutions.util.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SocioDAOImpl implements SocioDAO {
    //Inserta un nuevo socio en la tabla Socios usando un PreparedStatement para evitar SQL injection.
    @Override
    public void guardarSocio(Socio socio) throws SQLException {
        String sql = "INSERT INTO Socios (numSocio, nombre, tipo, nif, seguroTipo, precioSeguro, codigoFederacion, nombreFederacion, numSocioTutor) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, socio.getNumSocio());
            stmt.setString(2, socio.getNombre());
            stmt.setString(3, socio.getTipo());

            // Asignar valores adicionales según el tipo de socio
            if (socio instanceof Estandar) {
                Estandar estandar = (Estandar) socio;
                stmt.setString(4, estandar.getNif()); // NIF
                stmt.setString(5, estandar.getSeguro().getTipo()); // Tipo de seguro
                stmt.setDouble(6, estandar.getSeguro().getPrecio()); // Precio del seguro
                stmt.setNull(7, java.sql.Types.VARCHAR); // Sin federación
                stmt.setNull(8, java.sql.Types.VARCHAR); // Sin federación
                stmt.setNull(9, java.sql.Types.VARCHAR); // Sin tutor
            } else if (socio instanceof Federado) {
                Federado federado = (Federado) socio;
                stmt.setString(4, federado.getNif()); // NIF
                stmt.setNull(5, java.sql.Types.VARCHAR); // Sin seguro
                stmt.setNull(6, java.sql.Types.DOUBLE); // Sin seguro
                stmt.setString(7, federado.getFederacion().getCodigo()); // Código federación
                stmt.setString(8, federado.getFederacion().getNombre()); // Nombre federación
                stmt.setNull(9, java.sql.Types.VARCHAR); // Sin tutor
            } else if (socio instanceof Infantil) {
                Infantil infantil = (Infantil) socio;
                stmt.setNull(4, java.sql.Types.VARCHAR); // Sin NIF
                stmt.setNull(5, java.sql.Types.VARCHAR); // Sin seguro
                stmt.setNull(6, java.sql.Types.DOUBLE); // Sin seguro
                stmt.setNull(7, java.sql.Types.VARCHAR); // Sin federación
                stmt.setNull(8, java.sql.Types.VARCHAR); // Sin federación
                stmt.setString(9, infantil.getNumSocioTutor()); // Número de socio del tutor
            }

            stmt.executeUpdate();
        }
    }

    //recupera un socio de la BD mediante su identificador numSocio
    @Override
    public Socio buscarSocio(int numSocio) throws SQLException {
        String sql = "SELECT * FROM Socios WHERE numSocio = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, numSocio);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                String tipo = rs.getString("tipo");
                String nombre = rs.getString("nombre");

                switch (tipo) {
                    case "Estandar":
                        // Recuperar seguro
                        String seguroTipo = rs.getString("seguroTipo");
                        double precioSeguro = rs.getDouble("precioSeguro");
                        Seguro seguro = new Seguro(seguroTipo, precioSeguro);
                        return new Estandar(numSocio, nombre, rs.getString("nif"), seguro);
                    case "Federado":
                        String codigoFederacion = rs.getString("codigoFederacion");
                        Federacion federacion = new Federacion(codigoFederacion, rs.getString("nombreFederacion"));
                        return new Federado(numSocio, nombre, rs.getString("nif"), federacion);
                    case "Infantil":
                        return new Infantil(numSocio, nombre, rs.getString("numSocioTutor"));
                }
            }
        }
        return null;
    }

    //Actualiza los datos del socio en la BD
    @Override
    public void actualizarSocio(Socio socio) throws SQLException {
        String sql = "UPDATE Socios SET nombre = ?, tipo = ? WHERE numSocio = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, socio.getNombre());
            stmt.setString(2, socio.getTipo());
            stmt.setInt(3, socio.getNumSocio());
            stmt.executeUpdate();
        }
    }
    //elimina un socio por su numSocio
    @Override
    public void eliminarSocio(int numSocio) throws SQLException {
        String sql = "DELETE FROM Socios WHERE numSocio = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, numSocio);
            stmt.executeUpdate();
        }
    }
    //recupera todos los socios de la base de datos instanciando la subclase de socio en función del tipo
    @Override
    public List<Socio> listarSocios(String filtroTipo) throws SQLException {
        List<Socio> socios = new ArrayList<>();
        String sql = "SELECT * FROM Socios";
        if (!filtroTipo.equalsIgnoreCase("todos")) {
            sql += " WHERE tipo = ?";
        }

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            if (!filtroTipo.equalsIgnoreCase("todos")) {
                stmt.setString(1, filtroTipo);
            }

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                int numSocio = rs.getInt("numSocio");
                String nombre = rs.getString("nombre");
                String tipo = rs.getString("tipo");

                Socio socio = null;
                switch (tipo) {
                    case "Estandar":
                        String seguroTipo = rs.getString("seguroTipo");
                        double precioSeguro = rs.getDouble("precioSeguro");
                        Seguro seguro = new Seguro(seguroTipo, precioSeguro);
                        socio = new Estandar(numSocio, nombre, rs.getString("nif"), seguro);
                        break;
                    case "Federado":
                        String codigoFederacion = rs.getString("codigoFederacion");
                        Federacion federacion = new Federacion(codigoFederacion, rs.getString("nombreFederacion"));
                        socio = new Federado(numSocio, nombre, rs.getString("nif"), federacion);
                        break;
                    case "Infantil":
                        socio = new Infantil(numSocio, nombre, rs.getString("numSocioTutor"));
                        break;
                }
                socios.add(socio);
            }
        }
        return socios;
    }

    public double generarFacturaMensual(int socioId) throws SQLException {
        String sql = "{ CALL GenerarFacturaMensual(?, ?) }";
        try (Connection conn = DatabaseConnection.getConnection();
             CallableStatement stmt = conn.prepareCall(sql)) {

            stmt.setInt(1, socioId);
            stmt.registerOutParameter(2, Types.DECIMAL);

            stmt.execute();
            return stmt.getDouble(2);
        }
    }

    public void actualizarSeguro(int numSocio, String tipoSeguro, double precioSeguro, Connection conn) throws SQLException {
        String sql = "UPDATE Socios SET seguroTipo = ?, precioSeguro = ? WHERE numSocio = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, tipoSeguro);
            stmt.setDouble(2, precioSeguro);
            stmt.setInt(3, numSocio);
            stmt.executeUpdate();
        }
    }


}
