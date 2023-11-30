package vn.edu.iuh.fit.backend.servicesImpl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.edu.iuh.fit.backend.models.User;
import vn.edu.iuh.fit.backend.repositories.UserRepository;
import vn.edu.iuh.fit.backend.services.UserService;

import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Override
    public Optional<User> findUserByEmail(String email){
        return userRepository.findUserByEmail(email);
    }
    @Override
    public Optional<User> findUserByMobile(String mobile){
        return userRepository.findUserByMobile(mobile);
    }

    @Override
    public Optional<User> findByUsername(String authUsername) {
        return userRepository.findUserByFirstName(authUsername);
    }
}
