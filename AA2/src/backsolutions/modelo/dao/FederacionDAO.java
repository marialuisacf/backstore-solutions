package backsolutions.modelo.dao;

import backsolutions.modelo.Federacion;
import java.sql.SQLException;
import java.util.List;

public interface FederacionDAO {
    void guardarFederacion(Federacion federacion) throws SQLException;
    Federacion buscarFederacion(String codigo) throws SQLException;
    List<Federacion> listarFederaciones() throws SQLException;
    void eliminarFederacion(String codigo) throws SQLException;
}
