package com.abhijeetpadhy.SocialHub.controller;

import com.abhijeetpadhy.SocialHub.auth.UserPrincipal;
import com.abhijeetpadhy.SocialHub.business.domain.*;
import com.abhijeetpadhy.SocialHub.business.service.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;

@Controller
public class FeedsController {
    private final UserService userService;
    private final NotificationService notifService;
    private final MessageService messageService;
    private final PostService postService;
    private final FeedsService feedsService;

    public FeedsController(UserService userService, NotificationService notifService, MessageService messageService, PostService postService, FeedsService feedsService) {
        this.userService = userService;
        this.notifService = notifService;
        this.messageService = messageService;
        this.postService = postService;
        this.feedsService = feedsService;
    }

    @GetMapping("/feeds")
    public String displayFeeds(Model model){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserPrincipal userPrincipal = (UserPrincipal) auth.getPrincipal();
        String username = userPrincipal.getUsername();
        UserEntryDTO userEntryDTO = userService.getUserDetails(username);
        model.addAttribute("user", userEntryDTO);

        List<NotificationDTO> listOfUnseenNotifs = notifService.getUnseenNotifs(username);
        int unseenNotifCount = listOfUnseenNotifs.size();
        model.addAttribute("unseenNotifs", listOfUnseenNotifs.subList(0, Math.min(3, unseenNotifCount)));
        model.addAttribute("unseenNotifCount", unseenNotifCount);

        List<MessageDTO> listOfUnseenMessages = messageService.getUnseenMessages(username);
        HashMap<String, Integer> map = new HashMap<>();
        for(MessageDTO message : listOfUnseenMessages){
            String sender = message.getSender();
            map.put(sender, 1 + map.getOrDefault(sender, 0));
        }
        model.addAttribute("messagesFrom", map);
        model.addAttribute("unseenMessagesCount", listOfUnseenMessages.size());

        model.addAttribute("postInputDTO", new PostInputDTO());

        List<PostDTO> newsFeeds = feedsService.getAllFeeds(username);
        model.addAttribute("feeds", newsFeeds);

        return "feeds";
    }

    @GetMapping("/read-notifications")
    public String readNotifs(Model model){
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserPrincipal userPrincipal = (UserPrincipal) auth.getPrincipal();
        String username = userPrincipal.getUsername();
        notifService.readAllNotifs(username);
        return "redirect:feeds";
    }

    // There is an error when max file size exceeds 1048576 bytes. We need to correct it!
    @PostMapping("/feeds")
    public String processForm(@ModelAttribute PostInputDTO postInputDTO, Model model) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        UserPrincipal userPrincipal = (UserPrincipal) auth.getPrincipal();
        String username = userPrincipal.getUsername();

        PostDTO postDTO = new PostDTO();
        postDTO.setContent(postInputDTO.getContent());
        postDTO.setUsername(username);
        Timestamp currentTimestamp = new Timestamp(System.currentTimeMillis());
        postDTO.setCreated(currentTimestamp);

        boolean postSuccess = postService.addPost(postDTO, postInputDTO.getImage());
        if(postSuccess)
            model.addAttribute("postSuccess", true);
        return displayFeeds(model);
    }
}
