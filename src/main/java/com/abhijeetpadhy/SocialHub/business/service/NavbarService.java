package com.abhijeetpadhy.SocialHub.business.service;

import com.abhijeetpadhy.SocialHub.auth.UserPrincipal;
import com.abhijeetpadhy.SocialHub.business.domain.MessageDTO;
import com.abhijeetpadhy.SocialHub.business.domain.NotificationDTO;
import com.abhijeetpadhy.SocialHub.business.domain.UserEntryDTO;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.HashMap;
import java.util.List;

@Service
public class NavbarService {
    private final UserService userService;
    private final NotificationService notifService;
    private final MessageService messageService;

    public NavbarService(UserService userService, NotificationService notifService, MessageService messageService) {
        this.userService = userService;
        this.notifService = notifService;
        this.messageService = messageService;
    }

    public void addInfoAboutNavbar(Model model){
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
    }
}
