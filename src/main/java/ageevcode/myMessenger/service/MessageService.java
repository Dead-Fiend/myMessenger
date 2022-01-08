package ageevcode.myMessenger.service;

import ageevcode.myMessenger.domain.*;
import ageevcode.myMessenger.dto.EventType;
import ageevcode.myMessenger.dto.MessagePageDto;
import ageevcode.myMessenger.dto.ObjectType;
import ageevcode.myMessenger.repo.MessageRepo;
import ageevcode.myMessenger.repo.UserSubscriptionRepo;
import ageevcode.myMessenger.util.WsSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.function.BiConsumer;
import java.util.stream.Collectors;

@Service
public class MessageService {
    private final MessageRepo messageRepo;
    private final UserSubscriptionRepo userSubscriptionRepo;
    private final BiConsumer<EventType, Message> wsSender;

    @Autowired
    public MessageService(MessageRepo messageRepo, UserSubscriptionRepo userSubscriptionRepo, WsSender wsSender) {
        this.messageRepo = messageRepo;
        this.userSubscriptionRepo = userSubscriptionRepo;
        this.wsSender = wsSender.getSender(ObjectType.MESSAGE, Views.FullMessage.class);
    }


    public void delete(Message message) {
        messageRepo.delete(message);
        wsSender.accept(EventType.REMOVE, message);
    }

    public Message update(Message messageFromDB, Message message) {
        messageFromDB.setUpdatedAt(LocalDateTime.now());
        messageFromDB.setText(message.getText());
        Message updatedMessage = messageRepo.save(messageFromDB);

        wsSender.accept(EventType.UPDATE, updatedMessage);

        return updatedMessage;
    }

    public Message create(Message message, User user) {
        message.setCreatedAt(LocalDateTime.now());
        message.setUpdatedAt(LocalDateTime.now());

        message.setAuthor(user);

        List<Comment> list = new ArrayList<>();
        message.setComments(list);

        Message updatedMessage = messageRepo.save(message);

        wsSender.accept(EventType.CREATE, updatedMessage);

        return updatedMessage;
    }

    public MessagePageDto findForUser(Pageable pageable, User user) {
        List<User> channels = userSubscriptionRepo.findBySubscriber(user)
                .stream()
                .filter(UserSubscription::isActive)
                .map(UserSubscription::getChannel)
                .collect(Collectors.toList());

        channels.add(user);

        Page<Message> page = messageRepo.findByAuthorIn(channels, pageable);

        return new MessagePageDto(
                page.getContent(),
                pageable.getPageNumber(),
                page.getTotalPages()
        );
    }
}
