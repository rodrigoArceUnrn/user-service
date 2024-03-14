package ar.edu.unrn.userservice.dto;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

@SpringBootTest
public class AuthResponseDtoTest {

    @Test
    public void testAuthRequestDto() {
        AuthResponseDto authResponseDto = new AuthResponseDto();
        authResponseDto.setToken("tokenDefault");

        AuthResponseDto authResponseDto1 = new AuthResponseDto();
        authResponseDto1.setToken("tokenDefault");

        AuthResponseDto authResponseDto2 = new AuthResponseDto();
        authResponseDto2.setToken("tokenDefault");

        assertThat(authResponseDto.getToken()).isEqualTo("tokenDefault");

        //Reflexividad
        assertEquals(authResponseDto, authResponseDto);

        //Simetría
        assertEquals(authResponseDto, authResponseDto1);
        assertEquals(authResponseDto1, authResponseDto);

        //Transitividad
        assertEquals(authResponseDto, authResponseDto1);
        assertEquals(authResponseDto1, authResponseDto2);
        assertEquals(authResponseDto, authResponseDto2);

        //consistencia
        Assertions.assertEquals(authResponseDto.equals(authResponseDto1), authResponseDto.equals(authResponseDto1));

        Assertions.assertNotEquals(null, authResponseDto);
    }

    @Test
    public void testHashCodeConsistency() {
        AuthResponseDto authResponseDto1 = new AuthResponseDto();
        authResponseDto1.setToken("token");
        AuthResponseDto authResponseDto2 = new AuthResponseDto();
        authResponseDto2.setToken("token");

        assertEquals(authResponseDto1.hashCode(), authResponseDto2.hashCode());
    }

    @Test
    public void testHashCodeEquality() {
        AuthResponseDto authResponseDto1 = new AuthResponseDto();
        authResponseDto1.setToken("token");
        AuthResponseDto authResponseDto2 = new AuthResponseDto();
        authResponseDto2.setToken("token21");

        assertNotEquals(authResponseDto1.hashCode(), authResponseDto2.hashCode());
    }
}
