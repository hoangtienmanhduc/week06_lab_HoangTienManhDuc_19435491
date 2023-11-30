package vn.edu.iuh.fit.backend.servicesImpl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.edu.iuh.fit.backend.models.PostComment;
import vn.edu.iuh.fit.backend.repositories.PostCommentRepository;
import vn.edu.iuh.fit.backend.services.CommentService;

@Service
public class CommentServiceImpl implements CommentService {
    @Autowired private PostCommentRepository postCommentRepository;
    @Override
    public PostComment save(PostComment comment) {
        return postCommentRepository.save(comment);
    }

    @Override
    public void delete(PostComment comment) {
        postCommentRepository.delete(comment);
    }
}
