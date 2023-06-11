package com.abhijeetpadhy.SocialHub.business.service;

import com.abhijeetpadhy.SocialHub.business.domain.UserDataInputDTO;
import com.abhijeetpadhy.SocialHub.model.entity.Posts;
import com.abhijeetpadhy.SocialHub.model.entity.User;
import com.abhijeetpadhy.SocialHub.model.entity.UserData;
import com.abhijeetpadhy.SocialHub.model.repository.UserDataRepository;
import com.abhijeetpadhy.SocialHub.model.repository.UserRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

@Service
public class ProfileService {
    private final UserRepository userRepository;
    private final UserDataRepository userDataRepository;

    @Value("${profiles.photos.directory}")
    private String profilesPhotosDirectory;

    public ProfileService(UserRepository userRepository, UserDataRepository userDataRepository) {
        this.userRepository = userRepository;
        this.userDataRepository = userDataRepository;
    }

    public boolean updateUserData(UserDataInputDTO userDataInputDTO, String username) {
        UserData userData = userDataRepository.findByUsername(username);
        User user = userRepository.findByUsername(username);
        userData.setAbout(userDataInputDTO.getAbout());
        userData.setDOB(userDataInputDTO.getDOB());
        userData.setGender(userDataInputDTO.getGender());
        userData.setUsername(username);
        userData.setPlace(userDataInputDTO.getPlace());
        userData.setProfession(userDataInputDTO.getProfession());
        userData.setLastModified(userDataInputDTO.getLastUpdated());
        userDataRepository.save(userData);

        user.setName(userDataInputDTO.getName());

        MultipartFile image = userDataInputDTO.getImage();
        if(image == null || image.isEmpty()) {
            userRepository.save(user);
            return true;
        }
        try {
            Path storagePath = Path.of(profilesPhotosDirectory);
            if (!Files.exists(storagePath)) {
                Files.createDirectories(storagePath);
            }
            String originalFileName = StringUtils.cleanPath(image.getOriginalFilename());
            String fileExtension = StringUtils.getFilenameExtension(originalFileName);
            String newFileName = username + "." + fileExtension;
            user.setProfilePhotoName(newFileName);
            Path targetPath = storagePath.resolve(newFileName);
            Files.copy(image.getInputStream(), targetPath, StandardCopyOption.REPLACE_EXISTING);

            userRepository.save(user);
            return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }
}
