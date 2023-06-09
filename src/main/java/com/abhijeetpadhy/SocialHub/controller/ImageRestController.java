package com.abhijeetpadhy.SocialHub.controller;

import com.abhijeetpadhy.SocialHub.auth.UserPrincipal;
import com.abhijeetpadhy.SocialHub.business.service.ImageService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping(value = "/fetch")
public class ImageRestController {
    private final ImageService imageService;

    public ImageRestController(ImageService imageService) {
        this.imageService = imageService;
    }

    /*
    @GetMapping(value = "/{fileName}")
    public ResponseEntity<byte[]> getImage(@PathVariable(value = "fileName") String fileName) throws IOException {
        Path uploadDir = Paths.get("uploads");
        String uploadPath = uploadDir.toFile().getAbsolutePath();
        String fullPath = uploadPath + "/" + fileName;
        byte[] image = Files.readAllBytes(new File(fullPath).toPath());
        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.valueOf("image/png")).body(image);
    }
     */
    @GetMapping
    public ResponseEntity<byte[]> getImage(@RequestParam("postid") String postId) throws IOException {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserPrincipal userPrincipal = (UserPrincipal) auth.getPrincipal();
        String username = userPrincipal.getUsername();
        byte[] image = imageService.loadImage(username, Long.parseLong(postId));
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).contentType(MediaType.valueOf("image/png")).body(image);
    }
}
