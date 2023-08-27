package ar.edu.unrn.userservice.service.impl;

import ar.edu.unrn.userservice.model.User;
import ar.edu.unrn.userservice.repository.UserRepository;
import ar.edu.unrn.userservice.service.UserService;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElse(null);
    }
}
