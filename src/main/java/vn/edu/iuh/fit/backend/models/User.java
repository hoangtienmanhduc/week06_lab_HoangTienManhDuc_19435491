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
@Table(name = "user")
@NoArgsConstructor
@AllArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id",nullable = false)
    private Long id;

    @Column(name = "first_name",length = 150)
    private String firstName;

    @Column(name = "middle_name",length = 150)
    private String middleName;

    @Column(name = "last_name",length = 150)
    private String lastName;

    @Column(name = "mobile", length = 15)
    private String mobile;

    @Column(name = "email",length = 100)
    private String email;

    @Column(name = "password_hash",length = 210,nullable = false)
    private String passwordHash;

    @Column(name = "registered_at",nullable = false)
    private Instant registered_at;

    @Column(name = "last_login")
    private Instant last_login;

    @Lob
    @Column(name = "intro")
    private String intro;

    @Lob
    @Column(name = "profile")
    private String profile;

    @OneToMany(mappedBy = "author", fetch = FetchType.EAGER)
    private Set<Post> posts = new LinkedHashSet<>();

    @OneToMany(mappedBy = "user")
    private Set<PostComment> commentSets = new LinkedHashSet<>();
    public String getFirstName() {
        return firstName;
    }
    public User(String username, String mobile, String email, String passwordHash) {
        this.firstName = username;
        this.mobile = mobile;
        this.email = email;
        this.passwordHash = passwordHash;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public Instant getRegisterAt() {
        return registered_at;
    }

    public void setRegisterAt(Instant registerAt) {
        this.registered_at = registerAt;
    }

    public Instant getLastLogin() {
        return last_login;
    }

    public void setLastLogin(Instant lastLogin) {
        this.last_login = lastLogin;
    }

    public String getIntro() {
        return intro;
    }

    public void setIntro(String intro) {
        this.intro = intro;
    }

    public String getProfile() {
        return profile;
    }

    public void setProfile(String profile) {
        this.profile = profile;
    }
}
