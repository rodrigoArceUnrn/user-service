package ar.edu.unrn.userservice.model;


import org.springframework.security.core.GrantedAuthority;

import java.io.Serializable;

public enum Role implements GrantedAuthority, Serializable {

    ROLE_ADMIN("Administrador"), ROLE_USER("Cliente");

    public static Role[] getRoles() {
        Role[] roles = new Role[2];
        roles[0] = ROLE_ADMIN;
        roles[1] = ROLE_USER;
        return roles;
    }

    private String description;

    Role(String description) {
        this.description = description;
    }

    public String getRol() {
        return description;
    }

    public void setRol(String description) {
        this.description = description;
    }

    public String getAuthority() {
        return this.name();
    }

    @Override
    public String toString() {
        return this.name();
    }

}
