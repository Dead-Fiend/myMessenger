package ageevcode.myMessenger.controller;

import ageevcode.myMessenger.domain.Message;
import ageevcode.myMessenger.domain.UserDetails;
import ageevcode.myMessenger.domain.Views;
import ageevcode.myMessenger.dto.EventType;
import ageevcode.myMessenger.dto.ObjectType;
import ageevcode.myMessenger.repo.MessageRepo;
import ageevcode.myMessenger.repo.UserRepo;
import ageevcode.myMessenger.util.WsSender;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.function.BiConsumer;

@RestController
@RequestMapping("/message")
public class MessageController {
    private final MessageRepo messageRepo;
    private final UserRepo userRepo;
    private final BiConsumer<EventType, Message> wsSender;

    @Autowired
    public MessageController(MessageRepo messageRepo, UserRepo userRepo, WsSender wsSender) {
        this.messageRepo = messageRepo;
        this.userRepo = userRepo;
        this.wsSender = wsSender.getSender(ObjectType.MESSAGE, Views.IdName.class);
    }

    @GetMapping
    public List<Message> list() {
        return messageRepo.findAll();
    }

    @GetMapping("{id}")
    public Message getOne(@PathVariable("id") Message message) {
        return message;
    }

    @PostMapping
    public Message create(@RequestBody Message message, @AuthenticationPrincipal UserDetails userDetails) {
        message.setCreatedAt(LocalDateTime.now());
        message.setUpdatedAt(LocalDateTime.now());


        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        message.setAuthor(userRepo.findByUsername(auth.getName()));

        Message updatedMessage = messageRepo.save(message);

        wsSender.accept(EventType.CREATE, message);

        return updatedMessage;
    }

    @PutMapping("{id}")
    public Message update(@PathVariable("id") Message messageFromDB, @RequestBody Message message) {
        message.setUpdatedAt(LocalDateTime.now());
        BeanUtils.copyProperties(message, messageFromDB, "id");
        Message updatedMessage = messageRepo.save(messageFromDB);

        wsSender.accept(EventType.UPDATE, updatedMessage);

        return updatedMessage;
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable("id") Message message) {
        messageRepo.delete(message);
        wsSender.accept(EventType.REMOVE, message);
    }
}
