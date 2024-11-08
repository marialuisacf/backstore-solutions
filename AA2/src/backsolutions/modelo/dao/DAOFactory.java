package backsolutions.modelo.dao;

public interface DAOFactory {
    SocioDAO getSocioDAO();
    ExcursionDAO getExcursionDAO();
    InscripcionDAO getInscripcionDAO();
    FacturaDAO getFacturaDAO();
    FederacionDAO getFederacionDAO();
    SeguroDAO getSeguroDAO();
}
