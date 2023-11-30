package vn.edu.iuh.fit.backend.servicesImpl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.edu.iuh.fit.backend.models.Post;
import vn.edu.iuh.fit.backend.repositories.PostRepository;
import vn.edu.iuh.fit.backend.services.PostService;

import java.util.Collection;
import java.util.Optional;

@Service
public class PostServicesImpl implements PostService {
    @Autowired private PostRepository postRepository;
    @Override
    public boolean insertPost(Post post) {
        postRepository.save(post);
        return true;
    }

    @Override
    public Optional<Post> getPostById(long id) {
        return postRepository.findById(id);
    }

    @Override
    public void deletePost(Post post) {
        postRepository.deleteById(post.getId());
    }

    @Override
    public Collection<Post> getAllPosts() {
        return postRepository.findAll();
    }
}
