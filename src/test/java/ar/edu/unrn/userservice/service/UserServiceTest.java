package ar.edu.unrn.userservice.service;

import ar.edu.unrn.userservice.dto.AuthRequestDto;
import ar.edu.unrn.userservice.dto.AuthResponseDto;
import ar.edu.unrn.userservice.dto.ClientDto;
import ar.edu.unrn.userservice.model.Client;
import ar.edu.unrn.userservice.model.Role;
import ar.edu.unrn.userservice.model.User;
import ar.edu.unrn.userservice.repository.ClientRepository;
import ar.edu.unrn.userservice.repository.UserRepository;
import ar.edu.unrn.userservice.security.utils.JwtUtil;
import ar.edu.unrn.userservice.service.impl.ClientServiceImpl;
import ar.edu.unrn.userservice.service.impl.RabbitService;
import ar.edu.unrn.userservice.service.impl.UserServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.junit.jupiter.api.Assertions.*;


public class UserServiceTest {

    private UserService userService;
    private UserRepository userRepository;

    private ClientRepository clientRepository;

    private AuthenticationManager authenticationManager;
    private JwtUtil jwtUtil;
    private ClientService clientService;

    private RabbitService rabbitService;
    private ModelMapper modelMapper;

    @BeforeEach
    public void setUp() {
        userRepository = mock(UserRepository.class);
        authenticationManager = mock(AuthenticationManager.class);
        jwtUtil = mock(JwtUtil.class);
        clientRepository = mock(ClientRepository.class);
        rabbitService = mock(RabbitService.class);
        modelMapper = mock(ModelMapper.class);

        clientService = new ClientServiceImpl(clientRepository,rabbitService,modelMapper);
        userService = new UserServiceImpl(userRepository, authenticationManager, jwtUtil, clientService);
    }

    @Test
    public void testFindByUsername() {
        String username = "testuser";
        User expectedUser = new User(1L, username, "password", "email@example.com", new Role(1L,"CLIENTE", "Rol Cliente"));
        when(userRepository.findByUsername(username)).thenReturn(Optional.of(expectedUser));

        User actualUser = userService.findByUsername(username);

        assertEquals(expectedUser, actualUser);
    }

    @Test
    public void testAutenticate() throws Exception {
        AuthRequestDto authRequest = new AuthRequestDto("rodrigoa", "Rodri123");
        User user = new User(1L,"rodrigoa", "password", "email@example.com", new Role(1L,"CLIENTE", "Rol Cliente"));
        when(userRepository.findByUsername("rodrigoa")).thenReturn(Optional.of(user));

        AuthResponseDto authResponse = userService.autenticate(authRequest);

        assertNull(authResponse.getToken());
    }

    @Test
    public void testAuthenticateWithCorrectCredentials() throws Exception {

        User user = new User(1L,"rodrigoa", "password", "email@example.com", new Role(1L,"CLIENTE", "Rol Cliente"));

        when(authenticationManager.authenticate(any()))
                .thenReturn(null); // Simular autenticación exitosa
        when(clientService.getClientByUserId(1L))
                .thenReturn(new Client()); // Simular obtener cliente
        when(userRepository.findByUsername("rodrigoa")).thenReturn(Optional.of(user));

        when(jwtUtil.generateToken(any()))
                .thenReturn("mockedToken"); // Simular generar token

        // Ejecutar método a probar
        AuthRequestDto authRequest = new AuthRequestDto("rodrigoa", "Rodri123");
        AuthResponseDto response = userService.autenticate(authRequest);

        // Verificar resultados
        assertNotNull(response);
        assertEquals("mockedToken", response.getToken());
    }

    @Test
    public void testAuthenticateWithIncorrectCredentials() throws Exception {
        // Configurar mock para lanzar excepción
        when(authenticationManager.authenticate(any()))
                .thenThrow(new BadCredentialsException("Credenciales incorrectas"));

        // Ejecutar método a probar y verificar que se lance la excepción
        AuthRequestDto authRequest = new AuthRequestDto("username", "password");
        Exception exception = assertThrows(Exception.class, () -> {
            userService.autenticate(authRequest);
        });
        assertEquals("Credenciales incorrectas", exception.getMessage());
    }
}