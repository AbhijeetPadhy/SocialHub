package com.abhijeetpadhy.SocialHub.business.service;

import com.abhijeetpadhy.SocialHub.business.domain.UserDataDTO;
import com.abhijeetpadhy.SocialHub.model.entity.User;
import com.abhijeetpadhy.SocialHub.model.entity.UserData;
import com.abhijeetpadhy.SocialHub.model.repository.UserDataRepository;
import com.abhijeetpadhy.SocialHub.model.repository.UserRepository;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.time.LocalDate;

@Service
public class UserDataService {
    private final UserDataRepository userDataRepository;
    private final UserRepository userRepository;

    public UserDataService(UserDataRepository userDataRepository, UserRepository userRepository) {
        this.userDataRepository = userDataRepository;
        this.userRepository = userRepository;
    }

    private UserDataDTO getDTOFromEntity(UserData entity, String name) {
        UserDataDTO dto = new UserDataDTO();

        dto.setName(name);
        if(entity.getDOB() != null)
            dto.setDOB(entity.getDOB());
        else
            dto.setDOB(Date.valueOf(LocalDate.now()));
        dto.setAbout(entity.getAbout());
        dto.setProfession(entity.getProfession());
        dto.setPlace(entity.getPlace());
        dto.setGender(entity.getGender());
        dto.setTwitter("");
        dto.setFacebook("");
        dto.setInstagram("");
        dto.setLinkedin("");
        dto.setLastModified(entity.getLastModified());

        return dto;
    }

    public UserDataDTO getUserData(String username) {
        UserData userData = userDataRepository.findByUsername(username);
        User user = userRepository.findByUsername(username);
        return getDTOFromEntity(userData, user.getName());
    }
}
