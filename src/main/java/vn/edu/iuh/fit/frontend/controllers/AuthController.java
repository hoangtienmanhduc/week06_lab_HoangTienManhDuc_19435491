package vn.edu.iuh.fit.frontend.controllers;


import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import vn.edu.iuh.fit.backend.models.User;
import vn.edu.iuh.fit.backend.repositories.UserRepository;
import vn.edu.iuh.fit.backend.services.AuthService;

import java.security.Principal;
@Controller
@ComponentScan(basePackages = "vn.edu.iuh.fit")
public class AuthController {
    @Autowired private AuthService authService;
    @Autowired private UserRepository userRepository;
    @GetMapping("/login")
    public String login(Principal principal) {
        if (principal != null) {
            return "redirect:/";
        } else {
            return "login";
        }
    }

    @GetMapping("/signup")
    public String register() {
        return "registerForm";
    }

    @PostMapping("/signup")
    public String register(@RequestParam(name = "username") String username,
                           @RequestParam(name = "password") String password,
                           @RequestParam(name = "email") String email,
                           @RequestParam(name = "mobile") String mobile,
                           HttpServletRequest request,
                           Model model) {

        User user = new User(username, mobile,email, password);
        try {
            authService.userRegister(user);
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "registerForm";
        }
        return "redirect:/login";
    }

    @PostMapping("/login")
    public String login(@RequestParam(name = "username") String username,
                        @RequestParam(name = "password") String password,
                        HttpSession session,
                        HttpServletResponse response) {

        if (authService.userLogin(username, password)) {

            User user = null;
            if (userRepository.findUserByEmail(username).isPresent()) {
                user = userRepository.findUserByEmail(username).get();
            } else {
                user = userRepository.findUserByMobile(username).get();
            }

            // set session
            response.addCookie(new Cookie("username", user.getFirstName()));
            response.addCookie(new Cookie("isLogin", "true"));
            session.setAttribute("isLogin", true);
            session.setAttribute("user", user);
            return "redirect:/";
        } else {
            return "redirect:/login";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session, HttpServletResponse response) {
        session.invalidate();
        response.addCookie(new Cookie("isLogin", "false"));
        response.addCookie(new Cookie("username", ""));
        return "redirect:/login";
    }
}
