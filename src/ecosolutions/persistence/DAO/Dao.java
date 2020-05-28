package ecosolutions.persistence.DAO;

import java.util.List;

public interface Dao<T> {
    Object get(int id);

    List<T> getAll();

    void save(T t);

     void update(T t, String[] params);

     void delete(T t);


}
