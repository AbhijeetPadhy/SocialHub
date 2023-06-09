package com.abhijeetpadhy.SocialHub.controller;

import com.abhijeetpadhy.SocialHub.auth.UserPrincipal;
import com.abhijeetpadhy.SocialHub.business.domain.FriendsDTO;
import com.abhijeetpadhy.SocialHub.business.domain.UserEntryDTO;
import com.abhijeetpadhy.SocialHub.business.service.FriendsService;
import com.abhijeetpadhy.SocialHub.business.service.NavbarService;
import com.abhijeetpadhy.SocialHub.business.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class FriendsController {
    private final FriendsService friendsService;
    private final NavbarService navbarService;
    private final UserService userService;

    public FriendsController(FriendsService friendsService, NavbarService navbarService, UserService userService) {
        this.friendsService = friendsService;
        this.navbarService = navbarService;
        this.userService = userService;
    }

    @GetMapping("/friends")
    public String displayFriends(Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserPrincipal userPrincipal = (UserPrincipal) auth.getPrincipal();
        String username = userPrincipal.getUsername();

        navbarService.addInfoAboutNavbar(model);

        List<FriendsDTO> friends = friendsService.getAllFriends(username);
        model.addAttribute("friends", friends);

        List<UserEntryDTO> strangers = userService.getAllStrangers(username);
        model.addAttribute("strangers", strangers);

        return "friends";
    }
}
