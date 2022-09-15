package ar.edu.unrn.userservice.generic;

import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.Root;
import java.io.Serializable;
import java.lang.reflect.InvocationTargetException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class GenericServiceImpl<T, PK extends Serializable> implements GenericService<T, PK> {

    protected final GenericDAO<T, PK> entityDAO;

    public GenericServiceImpl(GenericDAO<T, PK> entityDAO) {
        this.entityDAO = entityDAO;
    }

    @Override
    public T getEntityById(PK id) {
        return (id == null) ? null : entityDAO.read(id);
    }

    @Override
    public List<T> getAll() {
        return entityDAO.findAll();
    }

    @Override
    @Transactional
    public void delete(T entity) {
        entityDAO.delete(entity);
    }

    @Override
    @Transactional(readOnly = true)
    public Long getCount(Map<String, Object> filters) {
        return entityDAO.count(entityDAO.getSearchPredicates(entityDAO.rootCount(), filters));
    }

    @Override
    @Transactional(readOnly = true)
    public List<T> getList(Integer page, Integer pageSize, Map<String, Object> filters, String sortField, Boolean asc, boolean distinct) {
        return entityDAO.listwithPag(entityDAO.getSearchPredicates(entityDAO.rootCount(), filters), page,
                pageSize, sortField, asc, false);
    }

    @Override
    @Transactional(readOnly = true)
    public List<T> listAll() {
        return entityDAO.listAll(entityDAO.getSearchPredicates(entityDAO.rootCount(), new HashMap<>()));
    }

    public void create(T entity) {
        entityDAO.create(entity);
    }

    public void update(T entity) {
        entityDAO.update(entity);
    }

    @Override
    @Transactional
    public void deleteLogic(T entity) {
        CriteriaBuilder cb = entityDAO.getEntityManager().getCriteriaBuilder();
        CriteriaUpdate<T> update = (CriteriaUpdate<T>) cb.createCriteriaUpdate(entityDAO.getClass());
        Root<T> root = update.from((Class<T>) entity.getClass());
        update.set("deleteDate", LocalDateTime.now());
        try {
            update.where(cb.equal(root.get("id"), entity.getClass().getMethod("getId").invoke(entity)));
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        entityDAO.getEntityManager().createQuery(update).executeUpdate();
    }

}