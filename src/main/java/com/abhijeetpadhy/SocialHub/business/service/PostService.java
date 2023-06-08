package com.abhijeetpadhy.SocialHub.business.service;

import com.abhijeetpadhy.SocialHub.business.domain.PostDTO;
import com.abhijeetpadhy.SocialHub.model.entity.*;
import com.abhijeetpadhy.SocialHub.model.repository.FeedsRepository;
import com.abhijeetpadhy.SocialHub.model.repository.FollowsRepository;
import com.abhijeetpadhy.SocialHub.model.repository.PostsRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;

@Service
public class PostService {
    private final PostsRepository postsRepository;
    private final FeedsService feedsService;
    int photoFileName = 0;

    @Value("${photos.directory}")
    private String photosDirectory;

    public PostService(PostsRepository postsRepository, FeedsService feedsService) {
        this.postsRepository = postsRepository;
        this.feedsService = feedsService;
    }

    public boolean addPost(PostDTO postDTO, MultipartFile image) {
        Posts post = new Posts();
        post.setUsername(postDTO.getUsername());
        post.setContent(postDTO.getContent());
        post.setCreated(postDTO.getCreated());
        if(image == null || image.isEmpty()){
            Posts savedPost = postsRepository.save(post);
            feedsService.addToFeeds(savedPost);
            return true;
        }

        try {
            Path storagePath = Path.of(photosDirectory);
            if (!Files.exists(storagePath)) {
                Files.createDirectories(storagePath);
            }
            String originalFileName = StringUtils.cleanPath(image.getOriginalFilename());
            String fileExtension = StringUtils.getFilenameExtension(originalFileName);
            String newFileName = photoFileName++ + "." + fileExtension;
            post.setPhotoName(newFileName);
            Path targetPath = storagePath.resolve(newFileName);
            Files.copy(image.getInputStream(), targetPath, StandardCopyOption.REPLACE_EXISTING);

            Posts savedPost = postsRepository.save(post);
            feedsService.addToFeeds(savedPost);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
}
