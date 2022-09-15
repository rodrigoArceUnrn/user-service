package ar.edu.unrn.userservice.model;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "client")
public class Client extends BaseEntity implements java.io.Serializable {

    private String name;
    private String lastname;
    private String document_type;
    private String document;
    private LocalDate date_of_birth;
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
        return document_type;
    }

    public void setDocumentType(String document_type) {
        this.document_type = document_type;
    }


    @Column(name = "document")
    public String getDocument() {
        return document;
    }

    public void setDocument(String document) {
        this.document = document;
    }

    public LocalDate getDateOfBirth() {
        return date_of_birth;
    }

    public void setDateOfBirth(LocalDate date_of_birth) {
        this.date_of_birth = date_of_birth;
    }
}
