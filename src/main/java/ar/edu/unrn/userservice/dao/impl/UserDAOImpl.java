package ar.edu.unrn.userservice.dao.impl;

import ar.edu.unrn.userservice.dao.UserDAO;
import ar.edu.unrn.userservice.generic.GenericDAOJpaImpl;
import ar.edu.unrn.userservice.security.entity.Role;
import ar.edu.unrn.userservice.security.entity.User;
import org.springframework.stereotype.Component;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.persistence.NoResultException;
import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

@Component("userDao")
public class UserDAOImpl extends GenericDAOJpaImpl<User, Long> implements UserDAO, Serializable {

    public Predicate[] getSearchPredicates(Root<User> root, Map<String, Object> filters) {

        CriteriaBuilder builder = this.entityManager.getCriteriaBuilder();
        List<Predicate> predicatesList = new ArrayList<>();
        root.alias("entity");

        String description = (String) filters.get("username");
        if (description != null && !"".equals(description))
            predicatesList.add(builder.like(root.get("username"), '%' + description + '%'));

        String name = (String) filters.get("name");
        if (name != null && !"".equals(name))
            predicatesList.add(builder.like(root.get("name"), '%' + name + '%'));

        String lastname = (String) filters.get("lastname");
        if (lastname != null && !"".equals(lastname))
            predicatesList.add(builder.like(root.get("lastname"), '%' + lastname + '%'));

        //String role = (String) filters.get("role");
        //if (role != null && !"".equals(role))
        //  predicatesList.add(builder.equal(root.get("role"), Role.valueOf(role)));

        //predicatesList.add(builder.isNull(root.get("deleteDate")));

        return predicatesList.toArray(new Predicate[predicatesList.size()]);
    }

    @Override
    public User getEntityByName(String username) throws NoResultException {
        Query query = this.entityManager.createQuery("from User u where u.username = :username");
        query.setParameter("username", username);
        return (User) query.getSingleResult();
    }

    public User getEntityByMail(String mail) throws NoResultException {
        Query query = this.entityManager.createQuery("from User u where u.mail = :mail");
        query.setParameter("mail", mail);
        return (User) query.getSingleResult();
    }

}
