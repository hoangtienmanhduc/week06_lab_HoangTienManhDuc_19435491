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
@Table(name = "post")
@AllArgsConstructor
@NoArgsConstructor
public class Post {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY,optional = false)
    @JoinColumn(name = "authorId",nullable = false)
    private User author;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "parentId")
    private Post parent;

    @Column(name = "title",nullable = false,length = 75)
    private String title;

    @Column(name = "metaTitle",length = 100)
    private String metaTitle;

    @Lob
    @Column(name = "sumary")
    private String sumary;

    @Column(name = "published",nullable = false)
    private Boolean published = false;

    @Column(name = "createAt",nullable = false)
    private Instant createAt;

    @Column(name = "updateAt",nullable = false)
    private Instant updateAt;

    @Column(name = "publishedAt")
    private Instant publishedAt;

    @Lob
    @Column(name = "content")
    private String content;

    @OneToMany(mappedBy = "parent")
    private Set<Post> posts = new LinkedHashSet<>();

    @OneToMany(mappedBy = "post")
    private Set<PostComment> postComments = new LinkedHashSet<>();
}
