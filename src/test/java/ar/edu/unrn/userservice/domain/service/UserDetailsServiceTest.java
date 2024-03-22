package ar.edu.unrn.userservice.domain.service;

import ar.edu.unrn.userservice.data.repository.UserRepository;
import ar.edu.unrn.userservice.domain.security.service.UserDetailsServiceImpl;
import ar.edu.unrn.userservice.model.User;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Optional;

import static org.junit.Assert.assertThrows;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@SpringBootTest
public class UserDetailsServiceTest {

    @Test
    public void testLoadUserByUsername() {
        // Configuración del mock
        UserRepository userRepositoryMock = mock(UserRepository.class);

        User user = new User();
        user.setUsername("username");
        user.setEmail("email@gmail.com");
        user.setPassword("1234");
        user.setId(1L);

        when(userRepositoryMock.findByUsername("username")).thenReturn(Optional.of(user));

        // Crear una instancia de UserDetailsServiceImpl con el mock
        UserDetailsServiceImpl userDetailsServiceImpl = new UserDetailsServiceImpl(userRepositoryMock);

        // Invocar el método a probar
        UserDetails userDetails = userDetailsServiceImpl.loadUserByUsername("username");

        Assertions.assertEquals("username", userDetails.getUsername());
        Assertions.assertEquals("1234", userDetails.getPassword()); // Por ejemplo, verifica el password
    }

    @Test
    public void testLoadUserByUsername_UserNotFound() {
        // Configuración del mock
        UserRepository userRepositoryMock = mock(UserRepository.class);
        when(userRepositoryMock.findByUsername("username")).thenReturn(Optional.empty());

        // Crear una instancia de UserDetailsServiceImpl con el mock
        UserDetailsServiceImpl userDetailsServiceImpl = new UserDetailsServiceImpl(userRepositoryMock);

        // Verificar que se lance una UsernameNotFoundException al cargar un usuario no encontrado
        assertThrows(UsernameNotFoundException.class, () -> {
            userDetailsServiceImpl.loadUserByUsername("username");
        });
    }
}
