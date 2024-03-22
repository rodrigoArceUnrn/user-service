package ar.edu.unrn.userservice.domain.security.utils;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class JwtConfigTest {

    @Test
    public void testGetSecretKey() {
        // Crear una instancia de la clase
        JwtConfig jwtConfig = new JwtConfig();

        // Establecer el valor de la clave secreta
        String secretKey = "mySecretKey";
        jwtConfig.setSecretKey(secretKey);

        // Verificar que getSecretKey() devuelve el valor correcto
        Assertions.assertEquals(secretKey, jwtConfig.getSecretKey());
    }

    @Test
    public void testTokenPrefixMethods() {
        // Crear una instancia de la clase
        JwtConfig jwtConfig = new JwtConfig();

        // Establecer y obtener el prefijo del token
        String tokenPrefix = "Bearer";
        jwtConfig.setTokenPrefix(tokenPrefix);
        Assertions.assertEquals(tokenPrefix, jwtConfig.getTokenPrefix());
    }

    @Test
    public void testHeaderMethods() {
        // Crear una instancia de la clase
        JwtConfig jwtConfig = new JwtConfig();

        // Establecer y obtener el encabezado
        String header = "Authorization";
        jwtConfig.setHeader(header);
        Assertions.assertEquals(header, jwtConfig.getHeader());
    }
}
