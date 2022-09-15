package ar.edu.unrn.userservice.auth;

import ar.edu.unrn.userservice.config.ParamValue;
import ar.edu.unrn.userservice.model.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtTokenUtil {bgjmmmmmmmmmmmm         

    static final String CLAIM_KEY_USERNAME = "username";
    static final String CLAIM_KEY_MAIL = "mail";
    static final String CLAIM_KEY_NAME = "name";
    static final String CLAIM_KEY_LASTNAME = "lastname";
    static final String CLAIM_KEY_CREATED = "created";
    static final String CLAIM_KEY_PROFILE = "userId";

    @ParamValue(key = "jwt.secret")
    private String secret;

    public String getUsernameFromToken(String token) {
        String email;
        try {
            final Claims claims = getClaimsFromToken(token);
            email = (String) claims.get("username");
        } catch (Exception e) {
            email = null;
        }
        return email;
    }

    private Claims getClaimsFromToken(String token) {
        return Jwts.parser().setSigningKey(secret).parseClaimsJws(token).getBody();
    }

    public String generateToken(User user) {
        Map<String, Object> claims = new HashMap<>();
        claims.put(CLAIM_KEY_CREATED, new Date());
        claims.put(CLAIM_KEY_USERNAME, user.getUsername());
        claims.put(CLAIM_KEY_MAIL, user.getMail());
        claims.put(CLAIM_KEY_PROFILE, user.getId());
        return generateToken(claims);
    }

    String generateToken(Map<String, Object> claims) {
        return Jwts.builder()
                .setClaims(claims)
                .signWith(SignatureAlgorithm.HS512, secret)
                .compact();
    }

}
