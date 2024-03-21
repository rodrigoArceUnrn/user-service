package ar.edu.unrn.userservice.domain.service;

import ar.edu.unrn.userservice.controller.dto.AuthRequestDto;
import ar.edu.unrn.userservice.controller.dto.AuthResponseDto;
import ar.edu.unrn.userservice.model.User;

/**
 * UserService.
 */
public interface UserService {

  User findByUsername(String username);

  AuthResponseDto autenticate(AuthRequestDto authRequest) throws Exception;
}