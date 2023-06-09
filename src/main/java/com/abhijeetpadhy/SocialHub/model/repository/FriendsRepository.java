package com.abhijeetpadhy.SocialHub.model.repository;

import com.abhijeetpadhy.SocialHub.model.entity.Friends;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FriendsRepository extends JpaRepository<Friends, Long> {
    @Query("SELECT f FROM Friends f WHERE f.username = :username")
    List<Friends> getAllFriends(@Param("username") String username);
}
