package backsolutions.modelo.dao;

import backsolutions.modelo.Socio;
import java.sql.SQLException;
import java.util.List;
import java.sql.Connection;

public interface SocioDAO {
    void guardarSocio(Socio socio) throws SQLException; //inserta nuevo socio en la BD
    Socio buscarSocio(int numSocio) throws SQLException; //busca un socio usando su numSocio
    void actualizarSocio(Socio socio) throws SQLException; //actualiza la info de un socio
    void eliminarSocio(int numSocio) throws SQLException; //elimina un socio utilizando su numSocio
    List<Socio> listarSocios(String filtroTipo) throws SQLException; //devuelve listado con todos los socios según el tipo
    void actualizarSeguro(int numSocio, String tipoSeguro, double precioSeguro, Connection conn) throws SQLException;
}
