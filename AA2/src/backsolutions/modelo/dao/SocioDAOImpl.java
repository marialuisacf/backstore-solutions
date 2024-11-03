package backsolutions.modelo.dao;

import backsolutions.modelo.Socio;
import backsolutions.modelo.Estandar;
import backsolutions.modelo.Federado;
import backsolutions.modelo.Infantil;
import backsolutions.util.DatabaseConnection;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SocioDAOImpl implements SocioDAO {
    //Inserta un nuevo socio en la tabla Socios usando un PreparedStatement para evitar SQL injection.
    @Override
    public void guardarSocio(Socio socio) throws SQLException {
        String sql = "INSERT INTO Socios (numSocio, nombre, tipo) VALUES (?, ?, ?)";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, socio.getNumSocio());
            stmt.setString(2, socio.getNombre());
            stmt.setString(3, socio.getTipo());
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
                        return new Estandar(numSocio, nombre, rs.getString("nif"), null); // Completar con seguro
                    case "Federado":
                        return new Federado(numSocio, nombre, rs.getString("nif"), null); // Completar con federación
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

        //ajustamos la consulta según el filtro
        if (!filtroTipo.equalsIgnoreCase("todos")) {
            sql += " WHERE tipo = ?"; //clausula solo si el filtro no es 'todos'
        }

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            // Configurar el filtro en el PreparedStatement si no es "todos"
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
                        socio = new Estandar(numSocio, nombre, rs.getString("nif"), null); //se completa con seguro
                        break;
                    case "Federado":
                        socio = new Federado(numSocio, nombre, rs.getString("nif"), null); //completaremos con federación
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
}
