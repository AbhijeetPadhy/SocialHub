package com.abhijeetpadhy.SocialHub.model.entity;

import javax.persistence.*;
import java.sql.Timestamp;

@Entity
@Table(name = "COMMENTS")
public class Comments {
    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "POST_ID")
    private long post_id;

    @Column(name = "USERNAME")
    private String username;

    @Column(name = "CONTENT")
    private String content;

    @Column(name = "CREATED")
    private Timestamp created;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getPost_id() {
        return post_id;
    }

    public void setPost_id(long post_id) {
        this.post_id = post_id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Timestamp getCreated() {
        return created;
    }

    public void setCreated(Timestamp created) {
        this.created = created;
    }
}
