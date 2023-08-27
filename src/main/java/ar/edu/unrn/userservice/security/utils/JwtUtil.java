package ar.edu.unrn.userservice.security.utils;

import ar.edu.unrn.userservice.model.Client;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtUtil {

    // Genera una clave segura
    private static final Key SECRET_KEY = Keys.secretKeyFor(io.jsonwebtoken.SignatureAlgorithm.HS256);

    public static String generateToken(Client client) {
        return Jwts.builder()
                .setClaims(getClaims(client))
                .setSubject(client.getUser().getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 3600000)) // Token válido durante 1 hora
                .signWith(SECRET_KEY)
                .compact();
    }

    private static Map<String, Object> getClaims(Client client) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("name", client.getName());
        claims.put("lastname", client.getLastname());
        claims.put("id", client.getId());
        claims.put("role", "ROLE_" + client.getUser().getRole().getName());
        return claims;
    }

    public static boolean validateToken(String token) {
        try {
            Jws<Claims> claims = Jwts.parserBuilder()
                    .setSigningKey(SECRET_KEY)
                    .build()
                    .parseClaimsJws(token);
            return true; // Token válido
        } catch (Exception e) {
            return false; // Token inválido
        }
    }

    public static Jws<Claims> getClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(SECRET_KEY)
                .build()
                .parseClaimsJws(token);
    }
}
