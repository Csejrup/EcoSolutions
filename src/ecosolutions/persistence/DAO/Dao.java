package ecosolutions.persistence.DAO;

import java.util.List;
import java.util.Optional;

/**
 * One DAO class per table or view
 * @param <T>
 */
public interface Dao<T> {
    Optional<T> getbyID(int id);
    List<T> getAll();
    void save(T t);
    void update(T t);
    void delete(T t);
}
