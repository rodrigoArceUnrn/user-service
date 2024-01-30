package ar.edu.unrn.userservice.controller;

import ar.edu.unrn.userservice.dto.AuthRequestDto;
import ar.edu.unrn.userservice.dto.AuthResponseDto;
import ar.edu.unrn.userservice.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerTest {

    @Mock
    private UserService userService;

    @InjectMocks
    private UserController userController;

    @Test
    public void testLogin() throws Exception {
        // Configurar datos de prueba
        AuthRequestDto authRequestDto = new AuthRequestDto("rodrigoa","Rodri123");
        // Simular el servicio userService.autenticate()
        when(userService.autenticate(authRequestDto)).thenReturn(new AuthResponseDto());

        // Llamar al método de controlador y verificar la respuesta
        ResponseEntity<?> responseEntity = userController.login(authRequestDto);
        assertNotNull(responseEntity);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        // Agregar más verificaciones si es necesario
    }

    @Test
    public void testLogout() {
        // Llamar al método de controlador y verificar la respuesta
        ResponseEntity<String> responseEntity = userController.logout();
        assertNotNull(responseEntity);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("Logout exitoso", responseEntity.getBody());
    }
}
