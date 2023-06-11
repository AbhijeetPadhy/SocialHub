package com.abhijeetpadhy.SocialHub.business.service;

import com.abhijeetpadhy.SocialHub.business.domain.UserDataDTO;
import com.abhijeetpadhy.SocialHub.model.entity.UserData;
import com.abhijeetpadhy.SocialHub.model.repository.UserDataRepository;
import org.springframework.stereotype.Service;

@Service
public class UserDataService {
    private final UserDataRepository userDataRepository;

    public UserDataService(UserDataRepository userDataRepository) {
        this.userDataRepository = userDataRepository;
    }

    private UserDataDTO getDTOFromEntity(UserData entity) {
        UserDataDTO dto = new UserDataDTO();
        dto.setDOB(entity.getDOB());
        dto.setGender(entity.getGender());
        dto.setAbout(entity.getAbout());
        dto.setLastModified(entity.getLastModified());
        dto.setUsername(entity.getUsername());
        return dto;
    }

    public UserDataDTO getUserData(String username) {
        UserData userData = userDataRepository.findByUsername(username);
        System.out.println("Userdata: " + userData);
        return getDTOFromEntity(userData);
    }
}
