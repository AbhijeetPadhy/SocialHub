package com.abhijeetpadhy.SocialHub.model.repository;

import com.abhijeetpadhy.SocialHub.model.entity.Messages;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface MessagesRepository extends JpaRepository<Messages, Long> {
    @Query("SELECT m FROM Messages m WHERE m.receiver = :receiver AND m.seen = FALSE")
    public List<Messages> findMessagesByReceiver(@Param("receiver") String receiver);
}
