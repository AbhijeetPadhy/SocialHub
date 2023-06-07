package com.abhijeetpadhy.SocialHub.business.service;

import com.abhijeetpadhy.SocialHub.business.domain.MessageDTO;
import com.abhijeetpadhy.SocialHub.model.entity.Messages;
import com.abhijeetpadhy.SocialHub.model.repository.MessagesRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MessageService {
    private final MessagesRepository messagesRepository;

    public MessageService(MessagesRepository messagesRepository) {
        this.messagesRepository = messagesRepository;
    }

    private MessageDTO getDTOFromEntity(Messages message) {
        MessageDTO messageDTO = new MessageDTO();
        messageDTO.setMessage(message.getMessage());
        messageDTO.setReceiver(message.getReceiver());
        messageDTO.setCreated(message.getCreated());
        messageDTO.setSeen(message.isSeen());
        messageDTO.setSender(message.getSender());
        return messageDTO;
    }

    public List<MessageDTO> getUnseenMessages(String receiver){
        List<Messages> listOfMessageEntities = messagesRepository.findMessagesByReceiver(receiver);
        List<MessageDTO> listOfMessageDTOs = new ArrayList<>();
        for(Messages message : listOfMessageEntities)
            listOfMessageDTOs.add(getDTOFromEntity(message));
        return listOfMessageDTOs;
    }
}
