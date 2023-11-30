package vn.edu.iuh.fit.backend.services;



import vn.edu.iuh.fit.backend.models.Post;

import java.util.Collection;
import java.util.Optional;

public interface PostService {
    boolean insertPost(Post post);
    Optional<Post> getPostById(long id);

    void deletePost(Post post);

    Collection<Post> getAllPosts();
}
