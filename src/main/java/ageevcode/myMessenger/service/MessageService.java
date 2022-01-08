package ageevcode.myMessenger.service;

import ageevcode.myMessenger.domain.*;
import ageevcode.myMessenger.dto.EventType;
import ageevcode.myMessenger.dto.MessagePageDto;
import ageevcode.myMessenger.dto.ObjectType;
import ageevcode.myMessenger.repo.MessageRepo;
import ageevcode.myMessenger.repo.UserRepo;
import ageevcode.myMessenger.repo.UserSubscriptionRepo;
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
import java.util.stream.Collectors;

@Service
public class MessageService {
    private final MessageRepo messageRepo;
    private final UserSubscriptionRepo userSubscriptionRepo;
    private final UserRepo userRepo;
    private final BiConsumer<EventType, Message> wsSender;

    @Autowired
    public MessageService(MessageRepo messageRepo,
                          UserSubscriptionRepo userSubscriptionRepo,
                          UserRepo userRepo,
                          WsSender wsSender
    ) {
        this.messageRepo = messageRepo;
        this.userSubscriptionRepo = userSubscriptionRepo;
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

    public MessagePageDto findForUser(Pageable pageable, User user) {
        List<User> channels = userSubscriptionRepo.findBySubscriber(user)
                .stream()
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
