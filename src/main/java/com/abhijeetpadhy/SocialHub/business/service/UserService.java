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

    public List<UserEntryDTO> getAllUsers() {
        List<UserEntryDTO> listOfUsers = new ArrayList<>();
        for(User user : userRepository.findAll()){
            UserEntryDTO userEntryDTO = new UserEntryDTO();
            userEntryDTO.setEmail(user.getEmail());
            userEntryDTO.setName(user.getName());
            userEntryDTO.setUsername(user.getUsername());
            listOfUsers.add(userEntryDTO);
        }
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
        UserEntryDTO userEntryDTO = new UserEntryDTO();
        userEntryDTO.setName(user.getName());
        userEntryDTO.setEmail(user.getEmail());
        userEntryDTO.setUsername(user.getUsername());
        return userEntryDTO;
    }
}
