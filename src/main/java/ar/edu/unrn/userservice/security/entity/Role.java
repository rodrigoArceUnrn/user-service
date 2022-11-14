package ar.edu.unrn.userservice.security.entity;


import ar.edu.unrn.userservice.security.enums.RolName;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "role")
public class Role extends BaseEntity implements Serializable {

    @Enumerated(EnumType.STRING)
    private RolName name;

    public Role() {
    }

    public Role(RolName name) {
        this.name = name;
    }

    public RolName getName() {
        return name;
    }

    public void setName(RolName name) {
        this.name = name;
    }
}
