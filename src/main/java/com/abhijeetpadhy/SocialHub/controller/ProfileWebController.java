package com.abhijeetpadhy.SocialHub.controller;

import com.abhijeetpadhy.SocialHub.auth.UserPrincipal;
import com.abhijeetpadhy.SocialHub.business.domain.UserDataDTO;
import com.abhijeetpadhy.SocialHub.business.service.NavbarService;
import com.abhijeetpadhy.SocialHub.business.service.ProfileService;
import com.abhijeetpadhy.SocialHub.business.service.UserDataService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import java.sql.Timestamp;

@Controller
public class ProfileWebController {
    private final NavbarService navbarService;
    private final UserDataService userDataService;
    private final ProfileService profileService;

    public ProfileWebController(NavbarService navbarService, UserDataService userDataService, ProfileService profileService) {
        this.navbarService = navbarService;
        this.userDataService = userDataService;
        this.profileService = profileService;
    }

    @GetMapping("/profile")
    public String displayProfile(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserPrincipal userPrincipal = (UserPrincipal) auth.getPrincipal();
        String username = userPrincipal.getUsername();

        navbarService.addInfoAboutNavbar(model);

        UserDataDTO userDataDTO = userDataService.getUserData(username);
        model.addAttribute("userData", userDataDTO);

        return "profile";
    }

    @GetMapping("/profile/{username}")
    public String displayOtherProfile(@PathVariable("username") String username, Model model) {

        return "profile";
    }

    @PostMapping("/edituserdata")
    public String processForm(@ModelAttribute UserDataDTO userDataDTO, Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserPrincipal userPrincipal = (UserPrincipal) auth.getPrincipal();
        String username = userPrincipal.getUsername();

        Timestamp currentTimestamp = new Timestamp(System.currentTimeMillis());
        userDataDTO.setLastModified(currentTimestamp);

        boolean postSuccess = profileService.updateUserData(userDataDTO, username);
        if (postSuccess)
            model.addAttribute("updateSuccess", true);
        //use react in future to make updates instead of page visits/redirects
        return "redirect:profile";
    }
}
