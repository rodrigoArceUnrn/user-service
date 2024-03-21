package ar.edu.unrn.userservice.security.utils;

import ar.edu.unrn.userservice.domain.security.utils.JwtUtil;
import ar.edu.unrn.userservice.model.Client;
import ar.edu.unrn.userservice.model.Role;
import ar.edu.unrn.userservice.model.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class JwtUtilTest {

    @Test
    public void testGenerateToken() {
        User user = new User(1L, "rodrigoa", "password", "email@example.com", new Role(1L, "CLIENTE", "Rol Cliente"));
        Client client = new Client(1L, "Pepo-ito", "arce", "DNI", "38083954", null, user);

        JwtUtil jwtUtil = new JwtUtil();
        jwtUtil.setSecret("mySecretKey");

        String token = jwtUtil.generateToken(client);

        Assertions.assertNotNull(token);

        Jws<Claims> claims = jwtUtil.getClaims(token);

        Assertions.assertEquals("Pepo-ito", claims.getBody().get("name"));
        Assertions.assertEquals("arce", claims.getBody().get("lastname"));
    }
}
