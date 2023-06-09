package com.abhijeetpadhy.SocialHub.model.repository;

import com.abhijeetpadhy.SocialHub.model.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<User, String> {
    User findByUsername(String username);

    @Query("SELECT u FROM User u WHERE u.username NOT IN (SELECT f.friend FROM Friends f WHERE f.username = :username) AND u.username != :username")
    List<User> findAllStrangers(String username);
}
