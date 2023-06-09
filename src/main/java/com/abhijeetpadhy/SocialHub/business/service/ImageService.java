package com.abhijeetpadhy.SocialHub.business.service;

import com.abhijeetpadhy.SocialHub.model.entity.Friends;
import com.abhijeetpadhy.SocialHub.model.entity.Posts;
import com.abhijeetpadhy.SocialHub.model.repository.FriendsRepository;
import com.abhijeetpadhy.SocialHub.model.repository.PostsRepository;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.util.List;

@Service
public class ImageService {
    private final FriendsRepository friendsRepository;
    private final PostsRepository postsRepository;

    public ImageService(FriendsRepository friendsRepository, PostsRepository postsRepository) {
        this.friendsRepository = friendsRepository;
        this.postsRepository = postsRepository;
    }

    public byte[] loadImage(String username, long postId) throws IOException {
        List<Friends> friends = friendsRepository.getAllFriends(username);
        Posts post = postsRepository.findPostsByPostId(postId);
        String postOwner = post.getUsername();
        boolean allowed = false;
        if(postOwner.equals(username))
            allowed = true;
        else{
            for(Friends friend : friends){
                if(friend.getFriend().equals(postOwner)){
                    allowed = true;
                    break;
                }
            }
        }
        if(!allowed)
            return null;
        String fileName = post.getPhotoName();
        Path uploadDir = Paths.get("uploads");
        String uploadPath = uploadDir.toFile().getAbsolutePath();
        String fullPath = uploadPath + "/" + fileName;
        byte[] image = Files.readAllBytes(new File(fullPath).toPath());
        return image;
    }
}