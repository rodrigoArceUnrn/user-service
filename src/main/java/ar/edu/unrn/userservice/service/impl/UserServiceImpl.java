package ar.edu.unrn.userservice.service.impl;

import ar.edu.unrn.userservice.dto.AuthRequestDTO;
import ar.edu.unrn.userservice.dto.AuthResponseDTO;
import ar.edu.unrn.userservice.model.Client;
import ar.edu.unrn.userservice.model.User;
import ar.edu.unrn.userservice.repository.UserRepository;
import ar.edu.unrn.userservice.security.utils.JwtUtil;
import ar.edu.unrn.userservice.service.ClientService;
import ar.edu.unrn.userservice.service.UserService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtUtil jwtUtil;
    private final ClientService clientService;


    public UserServiceImpl(UserRepository userRepository, AuthenticationManager authenticationManager, JwtUtil jwtUtil, ClientService clientService) {
        this.userRepository = userRepository;
        this.authenticationManager = authenticationManager;
        this.jwtUtil = jwtUtil;
        this.clientService = clientService;
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElse(null);
    }

    @Override
    public AuthResponseDTO autenticate(AuthRequestDTO authRequest) throws Exception {
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword())
            );
        } catch (BadCredentialsException e) {
            throw new Exception("Credenciales incorrectas", e);
        }

        final User user = this.findByUsername(authRequest.getUsername());

        Client client = clientService.getClientByUserId(user.getId());
        final String token = jwtUtil.generateToken(client);
        AuthResponseDTO response = new AuthResponseDTO();
        response.setToken(token);
        return response;
    }
}
