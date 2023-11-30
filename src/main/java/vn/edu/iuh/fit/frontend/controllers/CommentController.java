package vn.edu.iuh.fit.frontend.controllers;


import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.support.SessionStatus;
import vn.edu.iuh.fit.backend.models.Post;
import vn.edu.iuh.fit.backend.models.PostComment;
import vn.edu.iuh.fit.backend.models.User;
import vn.edu.iuh.fit.backend.services.CommentService;
import vn.edu.iuh.fit.backend.services.PostService;
import vn.edu.iuh.fit.backend.services.UserService;

import java.time.Instant;
import java.util.Optional;

@Controller
public class CommentController {
    @Autowired private CommentService commentService;
    @Autowired private PostService postService;
    @Autowired private UserService userService;


    @GetMapping("/comment/{id}")
    public String showComment(@PathVariable Long id, Model model, HttpSession session) {

        String authUsername = "anonymousUser";
        User principal = (User) session.getAttribute("user");
        if (principal != null) {
            authUsername = principal.getFirstName();
        }



        Optional<Post> postOptional = this.postService.getPostById(id);
        if (postOptional.isPresent() && principal!= null) {
            PostComment comment = new PostComment();
            comment.setPost(postOptional.get());
            comment.setUser(principal);
            model.addAttribute("comment", comment);
            session.setAttribute("comment", comment);
            System.err.println("GET comment/{id}: " + comment + "/" + id);
            return "commentForm";
        } else {
            System.err.println("Could not find a post by id: " + id + " or user by logged in username: " + authUsername);
            return "error";
        }
    }


    @PostMapping("/comment")
    public String validateComment(@ModelAttribute PostComment comment, BindingResult bindingResult, SessionStatus sessionStatus, HttpSession session) {
        System.err.println("POST comment: " + comment);
        if (bindingResult.hasErrors()) {
            System.err.println("Comment did not validate");
            return "commentForm";
        } else {
            PostComment savedComment = session.getAttribute("comment") != null ? (PostComment) session.getAttribute("comment") : null;
            session.removeAttribute("comment");
            comment.setContent(savedComment.getContent());
            savedComment.setTitle("FA");
            savedComment.setCreatedAt(Instant.now());
            savedComment.setPublished(true);
            savedComment.setPublishedAt(Instant.now());
            this.commentService.save(savedComment);
            System.err.println("SAVE comment: " + savedComment);
            sessionStatus.setComplete();
            return "redirect:/post/" + savedComment.getPost().getId();
        }
    }
}
