package ar.edu.unrn.userservice.model;


import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * User.
 */
@Entity
@Table(name = "user")
public class User extends BaseEntity {

  private String username;
  private String password;
  private String email;
  private Role role;

  public User() {
  }

  /**
   * Crea un nuevo usuario con la información proporcionada.
   *
   * @param id el identificador único del usuario
   * @param username el nombre de usuario del usuario
   * @param password la contraseña del usuario
   * @param mail el correo electrónico del usuario
   * @param role el rol del usuario
   */
  public User(Long id, String username, String password, String mail, Role role) {
    super.id = id;
    this.username = username;
    this.password = password;
    this.email = mail;
    this.role = role;
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