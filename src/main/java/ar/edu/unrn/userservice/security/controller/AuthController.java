package ar.edu.unrn.userservice.security.controller;

import ar.edu.unrn.userservice.security.dto.JwtDto;
import ar.edu.unrn.userservice.security.dto.LoginDto;
import ar.edu.unrn.userservice.security.dto.NewUserDto;
import ar.edu.unrn.userservice.security.entity.Role;
import ar.edu.unrn.userservice.security.entity.User;
import ar.edu.unrn.userservice.security.enums.RolName;
import ar.edu.unrn.userservice.security.jwt.JwtProvider;
import ar.edu.unrn.userservice.security.service.RolService;
import ar.edu.unrn.userservice.security.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.Set;

@RestController
@RequestMapping("/auth")
@CrossOrigin
public class AuthController {

    @Autowired
    PasswordEncoder passwordEncoder;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserService userService;

    @Autowired
    RolService rolService;

    @Autowired
    JwtProvider jwtProvider;

    @PostMapping("/create")
    public ResponseEntity<?> newUser(@Valid @RequestBody NewUserDto newUserDto, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("DTO mas formado");
        }
        if(userService.existsByUsername(newUserDto.getUsername())){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Ya existe ese usename");
        }
        if(userService.existsByEmail(newUserDto.getEmail())){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Ya existe ese email");
        }
        User user = new User(newUserDto.getUsername(),passwordEncoder.encode(newUserDto.getPassword()),newUserDto.getEmail(),true);
        Set<Role> roles = new HashSet<>();
        roles.add(rolService.getByName(RolName.ROLE_USER).get());
        if(newUserDto.getRoles().contains("admin")){
            roles.add(rolService.getByName(RolName.ROLE_ADMIN).get());
        }
        userService.save(user);
        return ResponseEntity.status(HttpStatus.OK).body("Usuario creado");

    }


    @PostMapping("/login")
    public ResponseEntity<JwtDto> login(@Valid @RequestBody LoginDto loginDto, BindingResult bindingResult){
        if(bindingResult.hasErrors()){
            return  new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        Authentication authentication = authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginDto.getUsername(), loginDto.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtProvider.generateToken(authentication);
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        JwtDto jwtDto = new JwtDto(2,jwt, userDetails.getUsername(), userDetails.getAuthorities());
        return  new ResponseEntity<>(jwtDto, HttpStatus.OK);
    }
}
