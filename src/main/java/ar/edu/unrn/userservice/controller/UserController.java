package ar.edu.unrn.userservice.controller;

import ar.edu.unrn.userservice.exception.InvalidCredentialsException;
import ar.edu.unrn.userservice.model.User;
import ar.edu.unrn.userservice.service.UserService;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.hibernate.service.spi.InjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("user")
    public User login(@RequestParam("email") String username, @RequestParam("password") String password) {
        User u = null;
        try{
            u = this.userService.findByName(username);
            this.userService.validatePassword(u.getPassword(),password);
            String token = getJWTToken(username);
            u.setAccessToken(token);
            this.userService.save(u);
        }catch (UsernameNotFoundException e){
            u = null;
        }catch(InvalidCredentialsException e2){
            u = null;
        }finally {
            return u;
        }

    }

    private String getJWTToken(String username) {
        String secretKey = "secret";
        List<GrantedAuthority> grantedAuthorities = AuthorityUtils
                .commaSeparatedStringToAuthorityList("ROLE_ADMIN");

        String token = Jwts
                .builder()
                .setId("secret")
                .setSubject(username)
                .claim("authorities",
                        grantedAuthorities.stream()
                                .map(GrantedAuthority::getAuthority)
                                .collect(Collectors.toList()))
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + 600000))
                .signWith(SignatureAlgorithm.HS512,
                        secretKey.getBytes()).compact();

        return token;
    }
}