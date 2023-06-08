package com.abhijeetpadhy.SocialHub.business.service;

import com.abhijeetpadhy.SocialHub.business.domain.PostDTO;
import com.abhijeetpadhy.SocialHub.model.entity.Feeds;
import com.abhijeetpadhy.SocialHub.model.entity.Follows;
import com.abhijeetpadhy.SocialHub.model.entity.Posts;
import com.abhijeetpadhy.SocialHub.model.repository.FeedsRepository;
import com.abhijeetpadhy.SocialHub.model.repository.FollowsRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FeedsService {
    private final FeedsRepository feedsRepository;
    private final FollowsRepository followsRepository;

    public FeedsService(FeedsRepository feedsRepository, FollowsRepository followsRepository) {
        this.feedsRepository = feedsRepository;
        this.followsRepository = followsRepository;
    }

    public void addToFeeds(Posts post){
        String username = post.getUsername();
        long postId = post.getPostId();

        for(Follows follows : followsRepository.getFollowers(username)){
            String follower = follows.getFollower();
            Feeds feed = new Feeds();
            feed.setPostId(postId);
            feed.setUsername(follower);
            feedsRepository.save(feed);
        }
    }

    private PostDTO getDTOFromEntity(Posts post) {
        PostDTO postDTO = new PostDTO();
        postDTO.setPostId(post.getPostId());
        postDTO.setContent(post.getContent());
        postDTO.setPhotoName(post.getPhotoName());
        postDTO.setUsername(post.getUsername());
        postDTO.setCreated(post.getCreated());
        return postDTO;
    }

    public List<PostDTO> getAllFeeds(String username) {
        List<PostDTO> listOfPosts = new ArrayList<>();
        for(Posts post : feedsRepository.getFeeds(username))
            listOfPosts.add(getDTOFromEntity(post));

        feedsRepository.deleteFeeds(username);

        return listOfPosts;
    }
}
