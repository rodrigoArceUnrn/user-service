package ar.edu.unrn.userservice.model;

import javax.persistence.*;
import java.time.LocalDate;

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

    public void setDocumentType(String document_type) {
        this.documentType = document_type;
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

    public void setDateOfBirth(LocalDate date_of_birth) {
        this.dateOfBirth = date_of_birth;
    }
}
