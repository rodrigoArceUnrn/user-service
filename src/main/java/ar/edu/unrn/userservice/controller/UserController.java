package ar.edu.unrn.userservice.controller;

import ar.edu.unrn.userservice.dto.AuthRequestDTO;
import ar.edu.unrn.userservice.dto.AuthResponseDTO;
import ar.edu.unrn.userservice.model.Client;
import ar.edu.unrn.userservice.model.User;
import ar.edu.unrn.userservice.security.utils.JwtUtil;
import ar.edu.unrn.userservice.service.ClientService;
import ar.edu.unrn.userservice.service.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;

@RestController
@CrossOrigin(origins = {"http://localhost:4200/"})
@RequestMapping("/users")
public class UserController {
    private final AuthenticationManager authenticationManager;


    private final JwtUtil jwtUtil;

    private final UserService userService;

    private final ClientService clientService;

    public UserController(AuthenticationManager authenticationManager, JwtUtil jwtUtil, UserService userService, ClientService clientService) {
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
        this.userService = userService;
        this.clientService = clientService;
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody AuthRequestDTO authRequest) throws Exception {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword())
            );
        } catch (BadCredentialsException e) {
            throw new Exception("Credenciales incorrectas", e);
        }

        final User user = userService.findByUsername(authRequest.getUsername());

        Client client = clientService.getClientByUserId(user.getId());
        final String token = jwtUtil.generateToken(client);
        AuthResponseDTO response = new AuthResponseDTO();
        response.setToken(token);
        return ResponseEntity.ok(response);
    }

    @PostMapping("/logout")
    public ResponseEntity<String> logout() {
        SecurityContextHolder.clearContext();
        return ResponseEntity.ok("Logout exitoso");
    }
}
