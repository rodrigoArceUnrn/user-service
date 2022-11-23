package ar.edu.unrn.userservice.security.service;

import ar.edu.unrn.userservice.security.entity.Role;
import ar.edu.unrn.userservice.security.enums.RolName;
import ar.edu.unrn.userservice.security.repository.RolRepository;
import ar.edu.unrn.userservice.security.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class RolService {

    @Autowired
    private RolRepository rolRepository;

    public Optional<Role> getByName(RolName rolName){
        return rolRepository.findByName(rolName);
    }

    public void save(Role role){
        rolRepository.save(role);
    }
}
