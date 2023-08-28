package ar.edu.unrn.userservice.service;

import ar.edu.unrn.userservice.dto.AuthRequestDTO;
import ar.edu.unrn.userservice.dto.AuthResponseDTO;
import ar.edu.unrn.userservice.model.User;

public interface UserService {

    User findByUsername(String username);

    AuthResponseDTO autenticate(AuthRequestDTO authRequest) throws Exception;
}
