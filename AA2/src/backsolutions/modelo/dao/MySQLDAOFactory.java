package backsolutions.modelo.dao;

public class MySQLDAOFactory implements DAOFactory {

    @Override
    public SocioDAO getSocioDAO() {
        return new SocioDAOImpl();
    }

    @Override
    public ExcursionDAO getExcursionDAO() {
        return new ExcursionDAOImpl();
    }

    @Override
    public InscripcionDAO getInscripcionDAO() {
        return new InscripcionDAOImpl();
    }

    @Override
    public FacturaDAO getFacturaDAO() {
        return new FacturaDAOImpl();
    }

    @Override
    public FederacionDAO getFederacionDAO() {
        return new FederacionDAOImpl();
    }

    @Override
    public SeguroDAO getSeguroDAO() {
        return new SeguroDAOImpl();
    }
}
