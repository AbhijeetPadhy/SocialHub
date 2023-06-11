package com.abhijeetpadhy.SocialHub.model.repository;

import com.abhijeetpadhy.SocialHub.model.entity.UserData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface UserDataRepository extends JpaRepository<UserData, String> {
    UserData findByUsername(String username);
}
