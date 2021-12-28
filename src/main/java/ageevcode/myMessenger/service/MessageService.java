package ageevcode.myMessenger.service;

import ageevcode.myMessenger.domain.Comment;
import ageevcode.myMessenger.domain.Message;
import ageevcode.myMessenger.domain.Views;
import ageevcode.myMessenger.dto.EventType;
import ageevcode.myMessenger.dto.MessagePageDto;
import ageevcode.myMessenger.dto.ObjectType;
import ageevcode.myMessenger.repo.MessageRepo;
import ageevcode.myMessenger.repo.UserRepo;
import ageevcode.myMessenger.util.WsSender;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;

@Service
public class MessageService {
    private final MessageRepo messageRepo;
    private final UserRepo userRepo;
    private final BiConsumer<EventType, Message> wsSender;

    @Autowired
    public MessageService(MessageRepo messageRepo, UserRepo userRepo, WsSender wsSender) {
        this.messageRepo = messageRepo;
        this.userRepo = userRepo;
        //this.wsSender = wsSender.getSender(ObjectType.MESSAGE, Views.IdName.class);
        this.wsSender = wsSender.getSender(ObjectType.MESSAGE, Views.FullMessage.class);
    }


    public void delete(Message message) {
        messageRepo.delete(message);
        wsSender.accept(EventType.REMOVE, message);
    }

    public Message update(Message messageFromDB, Message message) {
        message.setUpdatedAt(LocalDateTime.now());
        BeanUtils.copyProperties(message, messageFromDB, "id", "author", "comments");
        Message updatedMessage = messageRepo.save(messageFromDB);

        wsSender.accept(EventType.UPDATE, updatedMessage);

        return updatedMessage;
    }

    public Message create(Message message) {
        message.setCreatedAt(LocalDateTime.now());
        message.setUpdatedAt(LocalDateTime.now());

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        message.setAuthor(userRepo.findByUsername(auth.getName()));
        List<Comment> list = new ArrayList<>();
        message.setComments(list);
        Message updatedMessage = messageRepo.save(message);

        wsSender.accept(EventType.CREATE, message);

        return updatedMessage;
    }

    public MessagePageDto findAll(Pageable pageable) {
        Page<Message> page = messageRepo.findAll(pageable);
        return new MessagePageDto(
                page.getContent(),
                pageable.getPageNumber(),
                page.getTotalPages()
        );
    }
}
