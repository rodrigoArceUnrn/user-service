package ar.edu.unrn.userservice.generic;

import java.io.IOException;
import java.util.Collection;
import java.util.List;
import java.util.Map;

public interface GenericService<T, PK> {

    void save(T t);

    void delete(T t) throws IOException;

    T getEntityById(PK id);

    List<T> getAll();

    Long getCount(Map<String, Object> filters);

    List<T> getList(Integer page, Integer pageSize, Map<String, Object> filters, String sortField, Boolean asc, boolean distinct);

    Collection<T> listAll();

    void deleteLogic(T entity);

}
