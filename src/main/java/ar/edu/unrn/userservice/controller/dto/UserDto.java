package ar.edu.unrn.userservice.controller.dto;

import lombok.Data;

/**
 * UserDto.
 */
@Data
public class UserDto {
  private String username;
  private String password;
  private String email;
  private RoleDto role;
}
