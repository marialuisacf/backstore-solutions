package backsolutions.modelo.dao;

import backsolutions.modelo.Seguro;
import java.sql.SQLException;
import java.util.List;

public interface SeguroDAO {
    void guardarSeguro(Seguro seguro) throws SQLException;
    Seguro buscarSeguro(String tipo) throws SQLException;
    List<Seguro> listarSeguros() throws SQLException;
    void eliminarSeguro(String tipo) throws SQLException;
}
