package ar.edu.unrn.userservice.dao;


import ar.edu.unrn.userservice.generic.GenericDAO;
import ar.edu.unrn.userservice.model.User;

import javax.persistence.NoResultException;

public interface UserDAO extends GenericDAO<User, Long> {

    User getEntityByName(String username) throws NoResultException;

    User getEntityByMail(String mail);
}
