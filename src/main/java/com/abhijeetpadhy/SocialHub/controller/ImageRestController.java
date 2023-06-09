package com.abhijeetpadhy.SocialHub.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@RestController
@RequestMapping(value = "/uploads")
public class ImageRestController {
    @GetMapping(value = "/{fileName}")
    public ResponseEntity<byte[]> getImage(@PathVariable(value = "fileName") String fileName) throws IOException {
        Path uploadDir = Paths.get("uploads");
        String uploadPath = uploadDir.toFile().getAbsolutePath();
        String fullPath = uploadPath + "/" + fileName;
        byte[] image = Files.readAllBytes(new File(fullPath).toPath());
        return ResponseEntity.status(HttpStatus.OK).contentType(MediaType.valueOf("image/png")).body(image);
    }
}
