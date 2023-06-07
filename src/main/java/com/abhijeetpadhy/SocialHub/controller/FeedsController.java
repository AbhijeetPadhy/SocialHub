package com.abhijeetpadhy.SocialHub.controller;

import com.abhijeetpadhy.SocialHub.auth.UserPrincipal;
import com.abhijeetpadhy.SocialHub.business.domain.MessageDTO;
import com.abhijeetpadhy.SocialHub.business.domain.NotificationDTO;
import com.abhijeetpadhy.SocialHub.business.domain.UserEntryDTO;
import com.abhijeetpadhy.SocialHub.business.service.MessageService;
import com.abhijeetpadhy.SocialHub.business.service.NotificationService;
import com.abhijeetpadhy.SocialHub.business.service.UserService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.HashMap;
import java.util.List;

@Controller
public class FeedsController {
    private final UserService userService;
    private final NotificationService notifService;
    private final MessageService messageService;

    public FeedsController(UserService userService, NotificationService notifService, MessageService messageService) {
        this.userService = userService;
        this.notifService = notifService;
        this.messageService = messageService;
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
}
