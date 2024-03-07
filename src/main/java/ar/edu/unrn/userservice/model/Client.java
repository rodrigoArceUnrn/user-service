package ar.edu.unrn.userservice.model;

import java.time.LocalDate;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * Client.
 */
@Entity
@Table(name = "client")
public class Client extends BaseEntity implements java.io.Serializable {

  private String name;
  private String lastname;
  private String documentType;
  private String documentNumber;
  private LocalDate dateOfBirth;
  private User user;

  public Client() {
  }

  /**
   * Crea un nuevo cliente con la información proporcionada.
   *
   * @param id el identificador único del cliente
   * @param name el nombre del cliente
   * @param lastname el apellido del cliente
   * @param documentType el tipo de documento del cliente
   * @param documentNumber el número de documento del cliente
   * @param dateOfBirth la fecha de nacimiento del cliente
   * @param user el usuario asociado al cliente
   */
  public Client(Long id, String name, String lastname, String documentType,
                String documentNumber, LocalDate dateOfBirth, User user) {
    super.id = id;
    this.name = name;
    this.lastname = lastname;
    this.documentType = documentType;
    this.documentNumber = documentNumber;
    this.dateOfBirth = dateOfBirth;
    this.user = user;
  }

  @OneToOne(cascade = CascadeType.ALL)
  @JoinColumn(name = "user_id", referencedColumnName = "id")
  public User getUser() {
    return user;
  }

  public void setUser(User user) {
    this.user = user;
  }

  @Column(name = "name")
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @Column(name = "lastname")
  public String getLastname() {
    return lastname;
  }

  public void setLastname(String lastname) {
    this.lastname = lastname;
  }

  @Column(name = "document_type")
  public String getDocumentType() {
    return documentType;
  }

  public void setDocumentType(String documentType) {
    this.documentType = documentType;
  }

  @Column(name = "document_number")
  public String getDocumentNumber() {
    return documentNumber;
  }

  public void setDocumentNumber(String documentNumber) {
    this.documentNumber = documentNumber;
  }

  @Column(name = "date_of_birth")
  public LocalDate getDateOfBirth() {
    return dateOfBirth;
  }

  public void setDateOfBirth(LocalDate dateOfBirth) {
    this.dateOfBirth = dateOfBirth;
  }
}
