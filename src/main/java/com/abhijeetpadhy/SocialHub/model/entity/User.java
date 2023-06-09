package com.abhijeetpadhy.SocialHub.model.entity;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.persistence.*;

@Entity
@Table(name = "USERS")
public class User {
    @Column(name = "NAME", nullable = false)
    private String name;

    @Column(name = "EMAIL", nullable = false)
    private String email;

    @Id
    @Column(name = "USERNAME", nullable = false, unique = true)
    private String username;

    @Column(name = "PASSWORD")
    private String password;

    @Column(name = "PROFILE_PHOTO_NAME")
    private String profilePhotoName;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = (new BCryptPasswordEncoder(11)).encode(password);
    }

    public String getProfilePhotoName() {
        return profilePhotoName;
    }

    public void setProfilePhotoName(String profilePhotoName) {
        this.profilePhotoName = profilePhotoName;
    }
}
