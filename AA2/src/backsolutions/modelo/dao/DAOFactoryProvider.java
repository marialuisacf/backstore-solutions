package backsolutions.modelo.dao;

public class DAOFactoryProvider {

    //Metodo getDAOFactory que devuelve una instancia de MySQLDAOFactory.
    public static DAOFactory getDAOFactory() {
        return new MySQLDAOFactory();
    }
    
}
