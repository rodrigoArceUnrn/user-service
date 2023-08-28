package ar.edu.unrn.userservice.repository;

import ar.edu.unrn.userservice.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RolRepository extends JpaRepository<Role, Integer> {
    Optional<Role> findByName(String name);
}
