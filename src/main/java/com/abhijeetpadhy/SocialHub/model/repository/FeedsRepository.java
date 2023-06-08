package com.abhijeetpadhy.SocialHub.model.repository;

import com.abhijeetpadhy.SocialHub.model.entity.Feeds;
import com.abhijeetpadhy.SocialHub.model.entity.Posts;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface FeedsRepository extends JpaRepository<Feeds, Long> {
    @Query("SELECT p FROM Posts p, Feeds f WHERE f.username = :username AND f.postId = p.postId ORDER BY p.created DESC")
    public List<Posts> getFeeds(@Param("username") String username);

    @Modifying
    @Transactional
    @Query("DELETE FROM Feeds f WHERE f.username = :username")
    public void deleteFeeds(@Param("username") String username);
}
