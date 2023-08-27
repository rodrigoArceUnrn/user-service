package ar.edu.unrn.userservice.service;

import ar.edu.unrn.userservice.model.User;

public interface UserService {

    User findByUsername(String username);

}
