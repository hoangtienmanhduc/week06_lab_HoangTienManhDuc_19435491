package vn.edu.iuh.fit.backend.servicesImpl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.edu.iuh.fit.backend.help.AuthHelper;
import vn.edu.iuh.fit.backend.models.User;
import vn.edu.iuh.fit.backend.repositories.UserRepository;
import vn.edu.iuh.fit.backend.services.AuthService;

import java.time.Instant;
import java.util.Optional;

@Service
public class AuthServiceImpl implements AuthService {
    @Autowired
    private UserRepository userRepository;
    @Override
    public boolean userLogin(String email, String password) {
        Optional<User> user = userRepository.findUserByMobile(email);
        Optional<User> mobile = userRepository.findUserByEmail(email);
        if (user.isPresent() && AuthHelper.verifyPassword(password, user.get().getPasswordHash()) ||
                mobile.isPresent() && AuthHelper.verifyPassword(password, mobile.get().getPasswordHash())) {
            return true;
        }
        return false;
    }

    public boolean userRegister(User user) throws Exception {
        if (userRepository.existsUserByMobile(user.getMobile()) || userRepository.existsUserByEmail(user.getEmail())) {
            throw new Exception("Phone or email already exists");
        }
        String passWordHash = AuthHelper.hashPassword(user.getPasswordHash());
        user.setPasswordHash(passWordHash);
        user.setRegisterAt(Instant.now());
        userRepository.save(user);
        return true;
    }
}
