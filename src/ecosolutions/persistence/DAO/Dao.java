package ecosolutions.persistence.DAO;

import ecosolutions.Domain.Order.Order;

import java.sql.SQLException;
import java.util.List;
import java.util.Optional;

public interface Dao<T> {
    Object get(int id);

    List<T> getAll();

    void save(T t);

     void update(T t, String[] params);

     void delete(T t);


}
