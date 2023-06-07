package com.abhijeetpadhy.SocialHub.controller;

import com.abhijeetpadhy.SocialHub.business.domain.UserEntryDTO;
import com.abhijeetpadhy.SocialHub.business.domain.UserRegisterDTO;
import com.abhijeetpadhy.SocialHub.business.service.UserService;
import com.abhijeetpadhy.SocialHub.model.repository.UserRepository;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
public class RegisterController {
    private final UserService userService;

    public RegisterController(UserService userService) {
        this.userService = userService;
    }

    private boolean isAuthenticated() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication == null || AnonymousAuthenticationToken.class.
                isAssignableFrom(authentication.getClass())) {
            return false;
        }
        return authentication.isAuthenticated();
    }

    @GetMapping("/register")
    public String registerForm(Model model) {
        if(isAuthenticated())
            return "redirect:dashboard";
        model.addAttribute("user", new UserRegisterDTO());
        return "register";
    }

    @PostMapping("/register")
    public String loginSubmit(@ModelAttribute UserRegisterDTO userRegisterDTO, Model model) {
        boolean isNewUser = true;
        for(UserEntryDTO user : userService.getAllUsers()){
            if(user.getName().equals(userRegisterDTO.getName())) {
                isNewUser = false;
                break;
            }
        }

        if(isNewUser && userService.addUser(userRegisterDTO)){
            return "login";
        }

        model.addAttribute("user", new UserRegisterDTO());
        return "register";
    }
}
