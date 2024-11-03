package backsolutions.modelo.dao;

import backsolutions.modelo.Factura;
import java.sql.SQLException;
import java.util.List;

public interface FacturaDAO {
    void guardarFactura(Factura factura) throws SQLException;
    Factura buscarFactura(int idFactura) throws SQLException;
    List<Factura> listarFacturas() throws SQLException;
    void eliminarFactura(int idFactura) throws SQLException;
    List<Factura> listarFacturasPorSocio(int numSocio) throws SQLException;
}
