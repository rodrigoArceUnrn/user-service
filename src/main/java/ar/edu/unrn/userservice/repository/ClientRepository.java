package ar.edu.unrn.userservice.repository;

import ar.edu.unrn.userservice.security.entity.Client;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, String> {


    Client findClientById(Long id);
}
