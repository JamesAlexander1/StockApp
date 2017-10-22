package factory.dao;

public interface IDAOFactory<E> {

    E instantiateDAO(String type);
}
