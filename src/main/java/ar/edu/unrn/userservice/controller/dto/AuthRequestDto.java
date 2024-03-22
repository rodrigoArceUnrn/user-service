package ar.edu.unrn.userservice.controller.dto;

import lombok.Data;

/**
 * AuthRequestDto.
 */
@Data
public class AuthRequestDto {
  private String username;
  private String password;

  public AuthRequestDto(String username, String password) {
    this.username = username;
    this.password = password;
  }
}
