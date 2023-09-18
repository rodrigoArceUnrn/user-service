package ar.edu.unrn.userservice.dto;

import lombok.Data;

/**
 * AuthRequestDto.
 */
@Data
public class AuthRequestDto {
  private String username;
  private String password;
}
