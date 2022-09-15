package ar.edu.unrn.userservice.generic;

import javax.persistence.EntityManager;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.io.Serializable;
import java.util.List;
import java.util.Map;

public interface GenericDAO<T, PK extends Serializable> {
    EntityManager getEntityManager();

    T create(T t);

    T read(PK id);

    T update(T t);

    void delete(T t);

    List<T> findAll();

    Long count(Predicate[] where);

    Root<T> rootCount();

    Predicate[] getSearchPredicates(Root<T> root,
                                    Map<String, Object> filters);

    List<T> listwithPag(Predicate[] where, Integer page,
                        Integer pagesize, String sortField, Boolean asc, Boolean distinct);

    List<T> listAll(Predicate[] where);

}