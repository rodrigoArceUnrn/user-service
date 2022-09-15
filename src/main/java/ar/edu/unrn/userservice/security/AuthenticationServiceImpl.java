package ar.edu.unrn.userservice.security;

import ar.edu.unrn.userservice.model.User;
import ar.edu.unrn.userservice.utils.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;


import java.io.Serializable;

@Component("authenticationService")
public class AuthenticationServiceImpl implements AuthenticationService, Serializable {
    private static final long serialVersionUID = 5371998321050759039L;

    private final AuthenticationManager authenticationManager;

    public AuthenticationServiceImpl(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Override
    public boolean login(String username, String password)
            throws AuthenticationException {
        Authentication authenticate = authenticationManager
                .authenticate(new UsernamePasswordAuthenticationToken(username,
                        password));
        User user = (User) authenticate.getPrincipal();
        return isAuthenticateUser(authenticate, user);
    }

    private boolean isAuthenticateUser(Authentication authenticate, User user) {
        if (authenticate.isAuthenticated()) {
            SecurityContextHolder.getContext().setAuthentication(authenticate);
            return true;
        }
        return false;
    }

    public String logout() {
        SecurityContextHolder.getContext().setAuthentication(null);
        SecurityContextHolder.clearContext();
        return Constants.REDIRECT_LOGIN;
    }

}


