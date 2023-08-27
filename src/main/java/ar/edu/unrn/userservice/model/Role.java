package ar.edu.unrn.userservice.model;


import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "role")
public class Role extends BaseEntity {

    private String name;
    private String description;

    public Role() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
