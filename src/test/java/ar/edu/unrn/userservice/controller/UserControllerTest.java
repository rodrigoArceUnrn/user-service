package ar.edu.unrn.userservice.controller;

import ar.edu.unrn.userservice.dto.AuthRequestDto;
import ar.edu.unrn.userservice.dto.AuthResponseDto;
import ar.edu.unrn.userservice.service.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

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
        AuthRequestDto authRequestDto = new AuthRequestDto("rodrigoa", "Rodri123");

        when(userService.autenticate(authRequestDto)).thenReturn(new AuthResponseDto());

        ResponseEntity<?> responseEntity = userController.login(authRequestDto);
        Assertions.assertNotNull(responseEntity);
        Assertions.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    public void testLogout() {
        ResponseEntity<String> responseEntity = userController.logout();
        Assertions.assertNotNull(responseEntity);
        Assertions.assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        Assertions.assertEquals("Logout exitoso", responseEntity.getBody());
    }
}
