package ar.edu.unrn.userservice.utils;

import ar.edu.unrn.userservice.security.entity.Role;
import ar.edu.unrn.userservice.security.enums.RolName;
import ar.edu.unrn.userservice.security.service.RolService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class CreateRoles implements CommandLineRunner {

    @Autowired
    RolService rolService;

    @Override
    public void run(String... args) throws Exception {
        /*Role rolAdmin = new Role(RolName.ROLE_ADMIN);
        Role rolUser = new Role(RolName.ROLE_USER);
        rolService.save(rolAdmin);
        rolService.save(rolUser);*/
    }
}
