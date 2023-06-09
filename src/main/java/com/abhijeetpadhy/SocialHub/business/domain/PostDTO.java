package com.abhijeetpadhy.SocialHub.business.domain;

import org.springframework.beans.factory.annotation.Value;

import java.sql.Timestamp;

public class PostDTO {
    private long postId;
    private String username;
    private Timestamp created;
    private String content;
    private String photoName;
    private String photoPath;

    private String photosDirectory = "/uploads";

    public long getPostId() {
        return postId;
    }

    public void setPostId(long postId) {
        this.postId = postId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public Timestamp getCreated() {
        return created;
    }

    public void setCreated(Timestamp created) {
        this.created = created;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getPhotoName() {
        return photoName;
    }

    public void setPhotoName(String photoName) {
        this.photoName = photoName;
        photoPath = photosDirectory + "/" + photoName;
    }

    public String getPhotoPath() {
        return photoPath;
    }

    public boolean hasPhoto() {
        if(getPhotoName() == null)
            return false;
        return true;
    }
}
