package com.abhijeetpadhy.SocialHub.business.service;

import com.abhijeetpadhy.SocialHub.auth.UserPrincipal;
import com.abhijeetpadhy.SocialHub.business.domain.UserEntryDTO;
import com.abhijeetpadhy.SocialHub.business.domain.UserRegisterDTO;
import com.abhijeetpadhy.SocialHub.model.repository.UserRepository;
import com.abhijeetpadhy.SocialHub.model.entity.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = this.userRepository.findByUsername(username);
        if(user == null)
            throw new UsernameNotFoundException("cannot find username: " + username);
        return new UserPrincipal(user);
    }
    private UserEntryDTO getDTOFromEntity(User userEntity) {
        UserEntryDTO userEntryDTO = new UserEntryDTO();
        userEntryDTO.setEmail(userEntity.getEmail());
        userEntryDTO.setName(userEntity.getName());
        userEntryDTO.setUsername(userEntity.getUsername());
        userEntryDTO.setProfilePhotoName(userEntity.getProfilePhotoName());
        return userEntryDTO;
    }

    public List<UserEntryDTO> getAllUsers() {
        List<UserEntryDTO> listOfUsers = new ArrayList<>();
        for(User user : userRepository.findAll())
            listOfUsers.add(getDTOFromEntity(user));
        return listOfUsers;
    }

    public List<UserEntryDTO> getAllStrangers(String username) {
        List<UserEntryDTO> listOfUsers = new ArrayList<>();
        for(User user : userRepository.findAllStrangers(username))
            listOfUsers.add(getDTOFromEntity(user));
        return listOfUsers;
    }

    public boolean addUser(UserRegisterDTO userRegisterDTO){
        User user = new User();

        user.setEmail(userRegisterDTO.getEmail());
        user.setName(userRegisterDTO.getName());
        user.setUsername(userRegisterDTO.getUsername());
        user.setPassword(userRegisterDTO.getPassword());

        userRepository.save(user);

        return true;
    }

    public UserEntryDTO getUserDetails(String username) {
        User user = userRepository.findByUsername(username);
        return getDTOFromEntity(user);
    }
}
