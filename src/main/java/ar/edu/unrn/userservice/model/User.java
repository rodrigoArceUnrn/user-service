package ar.edu.unrn.userservice.model;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Entity
@Table(name = "user", uniqueConstraints = @UniqueConstraint(columnNames = {"username","mail"}))
public class User extends BaseEntity implements Serializable, UserDetails {

    private static final long serialVersionUID = 327336392782096608L;

    private String username;
    private String password;
    private String mail;
    private Role role;
    private String access_token;
    private boolean active = false;

    public User() {
    }

    public User(String password) {
        super();
        this.password = password;
    }

    public User(Role role) {
        super();
        this.role = role;
    }

    @Column(name = "password", nullable = false)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Column(name = "username", unique = true, nullable = false)
    public String getUsername() {
        return username;
    }

    @Transient
    public boolean isAccountNonExpired() {
        return false;
    }

    @Transient
    public boolean isAccountNonLocked() {
        return false;
    }

    @Transient
    public boolean isCredentialsNonExpired() {
        return false;
    }

    @Override
    public boolean isEnabled() {
        return this.active;
    }

    public void setEnabled(boolean active) {
        this.active = active;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Transient
    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<GrantedAuthority> result = new ArrayList<GrantedAuthority>();
        result.add(this.getRole());
        return result;
    }


    @Enumerated(EnumType.STRING)
    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Column(name = "mail")
    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }


    @Column(name = "access_token")
    public String getAccessToken() {
        return access_token;
    }

    public void setAccessToken(String accessToken) {
        this.access_token = accessToken;
    }

    @Column(name = "active", nullable = false)
    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
}
