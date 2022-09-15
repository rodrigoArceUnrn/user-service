package ar.edu.unrn.userservice.generic;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.*;
import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

public class GenericDAOJpaImpl<T, PK extends Serializable> implements
        GenericDAO<T, PK> {

    protected Class<T> entityClass;

    @PersistenceContext
    protected EntityManager entityManager;


    public GenericDAOJpaImpl() {
        ParameterizedType genericSuperclass = (ParameterizedType) getClass()
                .getGenericSuperclass();
        this.entityClass = (Class<T>) genericSuperclass
                .getActualTypeArguments()[0];
    }

    @Override
    public T create(T t) {
        this.entityManager.persist(t);
        return t;
    }

    @Override
    public T read(PK id) {
        return this.entityManager.find(entityClass, id);
    }

    @Override
    public T update(T t) {
        return this.entityManager.merge(t);
    }

    @Override
    public void delete(T t) {
        this.entityManager.remove(this.entityManager.merge(t));
    }

    @Override
    public List<T> findAll() {
        return this.entityManager.createQuery(
                "from " + this.entityClass.getName()).getResultList();
    }

    public Root<T> rootCount() {
        CriteriaBuilder builder = this.entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> countCriteria = builder.createQuery(Long.class);
        return countCriteria.from(entityClass);
    }

    public List<T> listwithPag(Predicate[] where, Integer page,
                               Integer pagesize, String sortField, Boolean asc, Boolean distinct) {
        CriteriaBuilder builder = this.entityManager.getCriteriaBuilder();
        CriteriaQuery<T> criteria = builder.createQuery(entityClass);
        criteria.distinct(distinct);
        Root<T> root = criteria.from(entityClass);
        root.alias("entity");
        Order order;
        // TODO root.get(orders[0]).get(orders[1]) Mejor Solucion
        String[] orders = sortField.split("\\.");
        order = getOrder(sortField, asc, builder, root, orders);
        TypedQuery<T> query = this.entityManager.createQuery(criteria
                .select(root).where(where).orderBy(order));
        query.setFirstResult(page).setMaxResults(pagesize);
        return query.getResultList();
    }

    public List<T> listAll(Predicate[] where) {
        CriteriaBuilder builder = this.entityManager.getCriteriaBuilder();
        CriteriaQuery<T> criteria = builder.createQuery(entityClass);
        Root<T> root = criteria.from(entityClass);
        root.alias("entity");
        TypedQuery<T> query = this.entityManager.createQuery(criteria
                .select(root).where(where));
        return query.getResultList();
    }

    private Order getOrder(String sortField, Boolean asc, CriteriaBuilder builder, Root<T> root, String[] orders) {
        Order order;
        if (orders.length == 1) {
            if (asc)
                order = builder.asc(root.get(sortField));
            else
                order = builder.desc(root.get(sortField));
        } else if (orders.length == 2) {
            if (asc)
                order = builder.asc(root.get(orders[0]).get(orders[1]));
            else
                order = builder.desc(root.get(orders[0]).get(orders[1]));
        } else {
            if (asc)
                order = builder.asc(root.get(orders[0]).get(orders[1])
                        .get(orders[2]));
            else
                order = builder.desc(root.get(orders[0]).get(orders[1])
                        .get(orders[2]));
        }
        return order;
    }

    public Long count(Predicate[] where) {
        CriteriaBuilder builder = this.entityManager.getCriteriaBuilder();
        CriteriaQuery<Long> countCriteria = builder.createQuery(Long.class);
        countCriteria.distinct(true);
        Root<T> root = countCriteria.from(entityClass);
        root.alias("entity");
        countCriteria = countCriteria.select(builder.count(root)).where(where);
        return this.entityManager.createQuery(countCriteria).getSingleResult();
    }

    @Override
    public Predicate[] getSearchPredicates(Root<T> root, Map<String, Object> filters) {
        // TODO Auto-generated method stub
        return null;
    }

    public EntityManager getEntityManager() {
        return entityManager;
    }

    public void setEntityManager(EntityManager entityManager) {
        this.entityManager = entityManager;
    }


    private Path builPath(Root root, String key) {
        Path path = root;
        List<String> strings = Arrays.asList(key.split("\\."));
        for (int i = 0; i < strings.size(); i++) {
            path = path.get(strings.get(i));
        }
        return path;
    }

}