package com.abhijeetpadhy.SocialHub.controller;

import com.abhijeetpadhy.SocialHub.auth.UserPrincipal;
import com.abhijeetpadhy.SocialHub.business.service.ImageService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping(value = "/fetch")
public class ImageRestController {
    private final ImageService imageService;

    public ImageRestController(ImageService imageService) {
        this.imageService = imageService;
    }

    @GetMapping("/post/{postid}")
    public ResponseEntity<byte[]> getPostImage(@PathVariable("postid") String postId, Model model) throws IOException {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserPrincipal userPrincipal = (UserPrincipal) auth.getPrincipal();
        String username = userPrincipal.getUsername();
        ResponseEntity<byte[]> image = imageService.loadImage(username, Long.parseLong(postId));
        return image;
    }

    @GetMapping("/profile/{username}")
    public ResponseEntity<byte[]> getProfileImage(@PathVariable("username") String username, Model model) throws IOException {
        ResponseEntity<byte[]> image = imageService.loadProfileImage(username);
        return image;
    }
}
