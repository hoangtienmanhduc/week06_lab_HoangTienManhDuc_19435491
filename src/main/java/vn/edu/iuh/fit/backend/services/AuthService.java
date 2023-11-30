package vn.edu.iuh.fit.backend.services;


import vn.edu.iuh.fit.backend.models.User;

public interface AuthService {
    boolean userLogin(String email, String password);
    boolean userRegister(User user) throws Exception;
}
