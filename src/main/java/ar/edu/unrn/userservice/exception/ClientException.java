package ar.edu.unrn.userservice.exception;

import org.springframework.security.core.AuthenticationException;

public class ClientException extends AuthenticationException {

    public ClientException(String msg, Throwable cause) {
        super(msg, cause);
    }

    public ClientException(String msg) {
        super(msg);
    }
}
