package com.abhijeetpadhy.SocialHub.business.domain;

import org.springframework.web.multipart.MultipartFile;

public class PostInputDTO {
    private String content;
    private MultipartFile image;

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public MultipartFile getImage() {
        return image;
    }

    public void setImage(MultipartFile image) {
        this.image = image;
    }
}
