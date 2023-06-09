package com.abhijeetpadhy.SocialHub.business.service;

import com.abhijeetpadhy.SocialHub.business.domain.FriendsDTO;
import com.abhijeetpadhy.SocialHub.model.entity.Friends;
import com.abhijeetpadhy.SocialHub.model.repository.FriendsRepository;
import com.abhijeetpadhy.SocialHub.model.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FriendsService {
    private final FriendsRepository friendsRepository;
    private final UserRepository userRepository;

    public FriendsService(FriendsRepository friendsRepository, UserRepository userRepository) {
        this.friendsRepository = friendsRepository;
        this.userRepository = userRepository;
    }

    private FriendsDTO getDTOFromEntity(Friends friendsEntity) {
        FriendsDTO friendsDTO = new FriendsDTO();
        String friendUsername = friendsEntity.getFriend();
        friendsDTO.setFriend(friendUsername);
        friendsDTO.setName(userRepository.findByUsername(friendUsername).getName());
        friendsDTO.setSince(friendsEntity.getSince());
        return friendsDTO;
    }

    public List<FriendsDTO> getAllFriends(String username) {
        List<FriendsDTO> friends = new ArrayList<>();
        for(Friends friend : friendsRepository.getAllFriends(username)) {
            friends.add(getDTOFromEntity(friend));
        }
        return friends;
    }
}
