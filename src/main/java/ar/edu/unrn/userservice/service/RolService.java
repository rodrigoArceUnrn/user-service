package ar.edu.unrn.userservice.service;

import ar.edu.unrn.userservice.model.Role;
import ar.edu.unrn.userservice.repository.RolRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class RolService {

    @Autowired
    private RolRepository rolRepository;

    public Optional<Role> getByName(String name) {
        return rolRepository.findByName(name);
    }

    public void save(Role role) {
        rolRepository.save(role);
    }
}
