package vn.edu.iuh.fit.backend.services;



import vn.edu.iuh.fit.backend.models.User;

import java.util.Optional;

public interface UserService {
    Optional<User> findUserByEmail(String email);
    Optional<User> findUserByMobile(String mobile);

    Optional<User> findByUsername(String authUsername);
}
