package com.abhijeetpadhy.SocialHub.model.repository;

import com.abhijeetpadhy.SocialHub.model.entity.Follows;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FollowsRepository extends JpaRepository<Follows, Long> {
    @Query("SELECT f FROM Follows f WHERE f.followedUser = :followedUser")
    public List<Follows> getFollowers(@Param("followedUser") String followedUser);
}
