package ar.edu.unrn.userservice.model;


import javax.persistence.*;

@Entity
@Table(name = "user")
public class User extends BaseEntity {

    private String username;
    private String password;
    private String email;
    private Role role;

    public User() {
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "role_id", referencedColumnName = "id")
    public Role getRole() {
        return this.role;
    }

}

