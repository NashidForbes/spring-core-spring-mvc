package guru.springframework.services.interfaces;

import java.util.List;

public interface CRUDService<T> {

    List<?> listAll();

    T getById(Integer id);

    T saveOrUpdate(T domainObject);

    void deleteById(Integer id);
}
