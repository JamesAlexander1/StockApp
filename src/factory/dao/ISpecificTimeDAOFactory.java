package factory.dao;

public interface ISpecificTimeDAOFactory<E> {

    E instantiateDAO(String type, String time_interval);
}
