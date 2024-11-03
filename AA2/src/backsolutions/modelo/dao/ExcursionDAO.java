package backsolutions.modelo.dao;

import backsolutions.modelo.Excursion;
import java.sql.SQLException;
import java.util.List;

public interface ExcursionDAO {
    void guardarExcursion(Excursion excursion) throws SQLException; //guarda una excursion en la BD
    Excursion buscarExcursion(String codigo) throws SQLException; //busca una excursion por su identificador que es 'codigo'
    List<Excursion> listarExcursiones() throws SQLException; //lista todas las excursiones
    void actualizarExcursion(Excursion excursion) throws SQLException; //actualiza una excursion en la BD
    void eliminarExcursion(String codigo) throws SQLException; //elimina una excursion mediante el 'codigo' de la excursion
}
