package ar.edu.unrn.userservice.security;

public interface AuthenticationService {

    boolean login(String username, String password);

    String logout();

}
