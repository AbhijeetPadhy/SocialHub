package com.abhijeetpadhy.SocialHub.business.service;

import com.abhijeetpadhy.SocialHub.business.domain.NotificationDTO;
import com.abhijeetpadhy.SocialHub.model.entity.Notifications;
import com.abhijeetpadhy.SocialHub.model.repository.NotificationsRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class NotificationService {
    private final NotificationsRepository notificationsRepository;

    public NotificationService(NotificationsRepository notificationsRepository) {
        this.notificationsRepository = notificationsRepository;
    }

    private NotificationDTO getDTOFromEntity(Notifications notif){
        NotificationDTO notificationDTO = new NotificationDTO();
        notificationDTO.setUsername(notif.getUsername());
        notificationDTO.setContent(notif.getContent());
        notificationDTO.setCreated(notif.getCreated());
        notificationDTO.setSeen(notif.isSeen());
        notificationDTO.setType(notif.getType());
        return notificationDTO;
    }

    public List<NotificationDTO> getAllNotifs(String username){
        List<Notifications> notifEntities = notificationsRepository.findByUsername(username);
        List<NotificationDTO> listOfNotifs = new ArrayList<>();
        for(Notifications notif : notifEntities)
            listOfNotifs.add(getDTOFromEntity(notif));
        return listOfNotifs;
    }

    public List<NotificationDTO> getUnseenNotifs(String username){
        List<Notifications> notifEntities = notificationsRepository.findUnseenNotifsByUsername(username);
        List<NotificationDTO> listOfNotifs = new ArrayList<>();
        for(Notifications notif : notifEntities)
            listOfNotifs.add(getDTOFromEntity(notif));
        return listOfNotifs;
    }

    public void readAllNotifs(String username){
        notificationsRepository.readAllNotifs(username);
    }
}
