package ar.edu.unrn.userservice.controller;

import ar.edu.unrn.userservice.controller.dto.AuthRequestDto;
import ar.edu.unrn.userservice.domain.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * User controller.
 *
 */
@RestController
@CrossOrigin(origins = {"http://localhost:4200/"})
@RequestMapping("/users")
public class UserController {
  private final UserService userService;

  public UserController(UserService userService) {
    this.userService = userService;
  }

  @PostMapping("/login")
  public ResponseEntity<?> login(@RequestBody AuthRequestDto authRequest) throws Exception {
    return ResponseEntity.ok(userService.autenticate(authRequest));
  }

  @PostMapping("/logout")
  public ResponseEntity<String> logout() {
    SecurityContextHolder.clearContext();
    return ResponseEntity.ok("Logout exitoso");
  }
}
