package ar.edu.unrn.userservice.controller.dto;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

@SpringBootTest
public class AuthRequestDtoTest {

    @Test
    public void testAuthRequestDto() {
        AuthRequestDto authRequestDto = new AuthRequestDto("testuser", "password123");
        AuthRequestDto authRequestDto1 = new AuthRequestDto("testuser", "password123");
        AuthRequestDto authRequestDto2 = new AuthRequestDto("testuser", "password123");

        assertThat(authRequestDto.getUsername()).isEqualTo("testuser");
        assertThat(authRequestDto.getPassword()).isEqualTo("password123");

        //Reflexividad
        assertEquals(authRequestDto, authRequestDto);

        //Simetría
        assertEquals(authRequestDto, authRequestDto1);
        assertEquals(authRequestDto1, authRequestDto);

        //Transitividad
        assertEquals(authRequestDto, authRequestDto1);
        assertEquals(authRequestDto1, authRequestDto2);
        assertEquals(authRequestDto, authRequestDto2);

        //consistencia
        Assertions.assertEquals(authRequestDto.equals(authRequestDto1), authRequestDto.equals(authRequestDto1));

        Assertions.assertNotEquals(null, authRequestDto); // Comparación con null:
    }

    @Test
    public void testHashCodeConsistency() {
        AuthRequestDto AuthRequestDto1 = new AuthRequestDto("username", "1234");
        AuthRequestDto AuthRequestDto2 = new AuthRequestDto("username", "1234");

        assertEquals(AuthRequestDto1.hashCode(), AuthRequestDto2.hashCode());
    }

    @Test
    public void testHashCodeEquality() {
        AuthRequestDto AuthRequestDto1 = new AuthRequestDto("username21", "12345");
        AuthRequestDto AuthRequestDto2 = new AuthRequestDto("username", "1234");

        assertNotEquals(AuthRequestDto1.hashCode(), AuthRequestDto2.hashCode());
    }
}
