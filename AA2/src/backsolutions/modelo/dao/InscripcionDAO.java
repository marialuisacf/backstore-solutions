package backsolutions.modelo.dao;

import backsolutions.modelo.Inscripcion;
import java.sql.SQLException;
import java.util.List;

public interface InscripcionDAO {
    //Metodo para guardar una inscripción en la BD
    void guardarInscripcion(Inscripcion inscripcion) throws SQLException;

    //Metodo para buscar una inscripción por su identificador que es numInscripcion
    Inscripcion buscarInscripcion(String numInscripcion) throws SQLException;

    //metodo para listar todas las inscripciones
    List<Inscripcion> listarInscripciones() throws SQLException;

    //Metodo para listar inscripciones de un socio concreto
    List<Inscripcion> listarInscripcionesPorSocio(int numSocio) throws SQLException;

    //metodo para eliminar una inscripción por su identificador
    void eliminarInscripcion(String numInscripcion) throws SQLException;

    //metodo para calcular el importe de una inscripción
    double calcularImporte(Inscripcion inscripcion) throws SQLException;
}
