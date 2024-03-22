package ar.edu.unrn.userservice.service;

import ar.edu.unrn.userservice.dto.AuthRequestDto;
import ar.edu.unrn.userservice.dto.AuthResponseDto;
import ar.edu.unrn.userservice.model.User;

/**
 * UserService.
 */
public interface UserService {

  User findByUsername(String username);

  AuthResponseDto autenticate(AuthRequestDto authRequest) throws Exception;
}