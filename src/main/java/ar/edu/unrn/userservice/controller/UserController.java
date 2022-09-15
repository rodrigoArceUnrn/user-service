package ar.edu.unrn.userservice.controller;

import ar.edu.unrn.userservice.auth.JwtAuthenticationRequest;
import ar.edu.unrn.userservice.auth.JwtAuthenticationResponse;
import ar.edu.unrn.userservice.model.User;
import ar.edu.unrn.userservice.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = {"http://localhost:4200"}, allowedHeaders = "*")
public class UserController {

    @Autowired
    UserService userService;


    @RequestMapping(value = "oauth/token", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<JwtAuthenticationResponse> login(@RequestBody JwtAuthenticationRequest request) throws AuthenticationException {
        System.out.println("INTENTANDO INGRESAR");
        final String token = userService.authenticate(request.getEmail(), request.getPassword());
        return ResponseEntity.ok(new JwtAuthenticationResponse(token));
    }

    @RequestMapping(value = "oauth/token/logout", method = RequestMethod.PUT)
    public ResponseEntity logout() {
        User user = userService.getLoggedUser();
        user.setAccessToken(null);
        userService.logout(user);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
