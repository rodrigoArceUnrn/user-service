package ar.edu.unrn.userservice.data.repository;

import ar.edu.unrn.userservice.model.Role;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * RolRepository.
 */
@Repository
public interface RolRepository extends JpaRepository<Role, Integer> {
  Optional<Role> findByName(String name);
}
