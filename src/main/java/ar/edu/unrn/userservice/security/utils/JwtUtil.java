package ar.edu.unrn.userservice.security.utils;

import ar.edu.unrn.userservice.model.Client;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtUtil {

    // Genera una clave segura
    @Value("${jwt.secret_key}")
    private String secret;

    public String generateToken(Client client) {
        return Jwts.builder()
                .setClaims(getClaims(client))
                .setSubject(client.getUser().getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 3600000)) // Token válido durante 1 hora
                .signWith(SignatureAlgorithm.HS512, secret)
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

    public Jws<Claims> getClaims(String token) {
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token);
    }
}
