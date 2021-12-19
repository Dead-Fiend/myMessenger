package ageevcode.myMessenger.service;

import ageevcode.myMessenger.domain.Message;
import ageevcode.myMessenger.domain.UserDetails;
import ageevcode.myMessenger.repo.MessageRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class MessageService {
    private final MessageRepo messageRepo;

    @Autowired
    public MessageService(MessageRepo messageRepo) {
        this.messageRepo = messageRepo;
    }

    public Message create(Message message, UserDetails userDetails) {
        message.setCreatedAt(LocalDateTime.now());
        message.setUpdatedAt(LocalDateTime.now());
        message.setAuthor(userDetails);
        messageRepo.save(message);
        return message;
    }
}
