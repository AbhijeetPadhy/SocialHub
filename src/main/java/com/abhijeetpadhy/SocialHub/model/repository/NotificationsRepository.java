package com.abhijeetpadhy.SocialHub.model.repository;

import com.abhijeetpadhy.SocialHub.model.entity.Notifications;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface NotificationsRepository extends JpaRepository<Notifications, Long> {
    @Query("SELECT u FROM Notifications u WHERE u.username = :username ORDER BY CREATED DESC")
    public List<Notifications> findByUsername(String username);

    @Query("SELECT u FROM Notifications u WHERE u.username = :username AND u.seen = FALSE ORDER BY CREATED DESC")
    public List<Notifications> findUnseenNotifsByUsername(String username);

    @Modifying
    @Transactional
    @Query("UPDATE Notifications u SET u.seen = TRUE WHERE u.username = :username AND u.seen = FALSE")
    public int readAllNotifs(@Param("username") String username);
}
