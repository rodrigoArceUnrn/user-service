package ar.edu.unrn.userservice.model;


import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Role.
 */
@Entity
@Table(name = "role")
public class Role extends BaseEntity {

  private String name;
  private String description;

  public Role() {
  }

  /**
   * Crea un nuevo objeto con la información proporcionada.
   *
   * @param id el identificador único del objeto
   * @param name el nombre del objeto
   * @param description la descripción del objeto
   */
  public Role(Long id, String name, String description) {
    super.id = id;
    this.name = name;
    this.description = description;
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