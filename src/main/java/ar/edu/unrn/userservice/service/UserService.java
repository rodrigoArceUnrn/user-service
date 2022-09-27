package ar.edu.unrn.userservice.service;
import ar.edu.unrn.userservice.generic.GenericService;
import ar.edu.unrn.userservice.model.User;
import org.apache.catalina.connector.Response;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;


public interface UserService extends GenericService<User, Long> {

    User findByMail(String mail);

    User findByName(String username);

    boolean validateMailUniqueness(String value);

    boolean validateUserUniqueness(User user);

    void changePassword(User user);

    BCryptPasswordEncoder encoder();

    User getLoggedUser();

    void notifyUserByEmail(User user, String newPasswordUser);

    void notifyUserClientByEmail(User user, String newPasswordUser);

    User getUser(String username);

    User registerUser(User user);

    void logout(User user);

    boolean validateUserClientUniqueness(User user);

    void sendMailRecovery(User user, String newPass);

    void recoveryPassword(User user, String newPass);

    boolean validateUsernameUniqueses(String username);

    User getAuthenticatedUser();

    void validatePassword(String pass1, String pass2 );

}
