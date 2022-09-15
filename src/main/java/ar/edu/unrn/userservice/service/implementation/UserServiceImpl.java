package ar.edu.unrn.userservice.service.implementation;

import ar.edu.unrn.userservice.auth.JwtTokenUtil;
import ar.edu.unrn.userservice.config.ParamValue;
import ar.edu.unrn.userservice.dao.UserDAO;
import ar.edu.unrn.userservice.generic.GenericDAO;
import ar.edu.unrn.userservice.generic.GenericServiceImpl;
import ar.edu.unrn.userservice.model.User;
import ar.edu.unrn.userservice.security.AuthenticationService;
import ar.edu.unrn.userservice.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import javax.persistence.NoResultException;
import java.util.List;
import java.util.Map;

@Component("userService")
public class UserServiceImpl extends GenericServiceImpl<User, Long> implements UserDetailsService, UserService {

    @Autowired
    AuthenticationService authenticationService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    UserDAO entityDAO;

    @Autowired
    JwtTokenUtil jwtTokenUtil;

    public static final String TOKEN_INVALID = "invalidToken";
    public static final String TOKEN_EXPIRED = "expired";
    public static final String TOKEN_VALID = "valid";
    //TODO: PARA EL FUTURO ENVÍO DE MENSAJES private SendEmail sendEmail = new SendEmail();

    @Autowired
    public UserServiceImpl(GenericDAO<User, Long> entityDAO) {
        super(entityDAO);
    }

    public UserDAO getEntityDAO() {
        return entityDAO;
    }

    @Override
    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException {
        return findByName(username);
    }

    public User findByName(String username) {
        User user = null;
        try {
            user = entityDAO.getEntityByName(username);
        } catch (NoResultException e) {
            throw new UsernameNotFoundException("Credenciales erróneas.");
        }
        return user;
    }

    public User findByMail(String mail) {
        User user = null;
        try {
            user = entityDAO.getEntityByMail(mail);
        } catch (NoResultException e) {
            throw new UsernameNotFoundException("Usuario no existe.");
        }
        return user;
    }

    public User findByDni(String mail) {
        User user = null;
        try {
            user = entityDAO.getEntityByMail(mail);
        } catch (NoResultException e) {
            throw new UsernameNotFoundException("Usuario no existe.");
        }
        return user;
    }

    @Transactional(readOnly = true)
    public Long getCount(Map<String, Object> filters) {
        return getEntityDAO().count(getEntityDAO().getSearchPredicates(getEntityDAO().rootCount(), filters));
    }

    @Override
    public List<User> getList(Integer page, Integer pageSize, Map<String, Object> filters, String sortField, Boolean asc, boolean distinct) {
        return getEntityDAO().listwithPag(getEntityDAO().getSearchPredicates(getEntityDAO().rootCount(),
                filters), page, pageSize, sortField, asc, distinct);
    }

    @Transactional
    public void save(User entity) {
        if (entity.getId() == null) {
            encryptKey(entity);
            getEntityDAO().create(entity);
        } else {
            getEntityDAO().update(entity);
        }
    }

    @Transactional
    public void delete(User entity) {
        getEntityDAO().delete(entity);
    }

    public User getEntityById(Long id) {
        return getEntityDAO().read(id);
    }

    public List<User> getAll() {
        return getEntityDAO().findAll();
    }

    public boolean validateUserUniqueness(User user) {
        try {
            return (findByName(user.getUsername()) == null) || (findByName(user.getUsername()).getId() == user.getId());
        } catch (UsernameNotFoundException e) {
            return true;
        }
    }


    public boolean validateMailUniqueness(String mail) {
        try {
            return findByMail(mail) == null;
        } catch (UsernameNotFoundException e) {
            return true;
        }
    }


    @Transactional
    public void changePassword(User user) {
        encryptKey(user);
        getEntityDAO().update(user);
    }

    void encryptKey(User t) {
        t.setPassword(encoder().encode(t.getPassword()));
    }

    public BCryptPasswordEncoder encoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    public User getLoggedUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication.getPrincipal().getClass() == User.class)
            return (User) authentication.getPrincipal();
        return null;
    }

    @Override
    public void notifyUserByEmail(User user, String newPasswordUser) {

    }

    @Override
    public void notifyUserClientByEmail(User user, String newPasswordUser) {

    }

    public User getAuthenticatedUser() {
        String username = SecurityContextHolder.getContext().getAuthentication().getPrincipal().toString();
        return getEntityDAO().getEntityByMail(username);
    }


    @Override
    public User getUser(String username) {
        return getEntityDAO().getEntityByName(username);
    }

    @Transactional
    @Override
    public String authenticate(String email, String password) {

        try {

            if (authenticationService.login(email, password)) {
                Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
                User user = (User) authentication.getPrincipal();
                user.setAccessToken(jwtTokenUtil.generateToken(user));
                this.save(user);
                return user.getAccessToken();
            } else
                return null;

        } catch (BadCredentialsException e) {
            return e.getMessage();
        }
    }

    @Override
    public User registerUser(User user) {
        return null;
    }

    @Transactional
    @Override
    public void logout(User user) {
        SecurityContextHolder.getContext().setAuthentication(null);
        this.save(user);
    }

    @Override
    public boolean validateUserClientUniqueness(User user) {
        if (this.validateMailUniqueness(user.getMail()) &&
                this.validateUserUniqueness(user)) {
            return true;
        } else
            return false;
    }

    @Override
    public void sendMailRecovery(User user, String newPass) {

    }


    @Override
    @Transactional
    public void recoveryPassword(User user, String newPass) {
        user.setPassword(newPass);
        this.changePassword(user);
        this.sendMailRecovery(user, newPass);
    }

    public boolean validateUsernameUniqueses(String username) {
        try {
            return (findByName(username) == null);
        } catch (UsernameNotFoundException e) {
            return true;
        }
    }




}
