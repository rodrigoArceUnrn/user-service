package ar.edu.unrn.userservice.model;


import java.io.Serial;
import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

/**
 * BaseEntity.
 */
@MappedSuperclass
public abstract class BaseEntity implements Serializable {

  public BaseEntity() {
  }

  @Serial
  private static final long serialVersionUID = 1L;
  protected Long id;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id", unique = true, nullable = false)
  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }
}
