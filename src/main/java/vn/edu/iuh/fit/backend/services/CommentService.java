package vn.edu.iuh.fit.backend.services;


import vn.edu.iuh.fit.backend.models.PostComment;

public interface CommentService {
    PostComment save(PostComment comment);

    void delete(PostComment comment);
}
