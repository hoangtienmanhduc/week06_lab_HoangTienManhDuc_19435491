package vn.edu.iuh.fit.backend.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.util.LinkedHashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "post_comment")
@AllArgsConstructor
@NoArgsConstructor
public class PostComment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "postId", nullable = false)
    private Post post;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "parentId")
    private PostComment parent;

    @Column(name = "title", nullable = false, length = 100)
    private String title;

    @ManyToOne(fetch = FetchType.EAGER, optional = false)
    @JoinColumn(name = "userId", nullable = false)
    private User user;

    @Column(name = "published", nullable = false)
    private Boolean published = false;

    @Column(name = "createAt", nullable = false)
    private Instant createAt;

    //    @Column(name = "updateAt",nullable = false)
//    private Instant updateAt;
    @Column(name = "publishedAt")
    private Instant publishedAt;

    @Lob
    @Column(name = "content")
    private String content;

    @OneToMany(mappedBy = "parent")
    private Set<PostComment> postComments = new LinkedHashSet<>();

    public Long getId() {
        return id;
    }

    public void setId(long id) {
        id = id;
    }

    public Post getPost() {
        return post;
    }

    public void setPost(Post post) {
        this.post = post;
    }

    public Set<PostComment> getPostComments() {
        return postComments;
    }

    public void setPostComments(Set<PostComment> postComments) {
        this.postComments = postComments;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Boolean getPublished() {
        return published;
    }

    public void setPublished(Boolean published) {
        this.published = published;
    }

    public Instant getPublishedAt() {
        return publishedAt;
    }

    public void setPublishedAt(Instant publishedAt) {
        this.publishedAt = publishedAt;
    }

    public Instant getCreatedAt() {
        return createAt;
    }

    public void setCreatedAt(Instant createdAt) {
        this.createAt = createdAt;
    }

    public PostComment getParentComment() {
        return parent;
    }

    public void setParentComment(PostComment parentComment) {
        this.parent = parentComment;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
