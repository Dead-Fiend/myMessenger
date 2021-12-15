package ageevcode.myMessenger.controller;

import ageevcode.myMessenger.domain.Message;
import ageevcode.myMessenger.domain.Views;
import ageevcode.myMessenger.dto.EventType;
import ageevcode.myMessenger.dto.ObjectType;
import ageevcode.myMessenger.repo.MessageRepo;
import ageevcode.myMessenger.util.WsSender;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.function.BiConsumer;

@RestController
@RequestMapping("/message")
public class MessageController {
    private final MessageRepo messageRepo;
    private final BiConsumer<EventType, Message> wsSender;

    @Autowired
    public MessageController(MessageRepo messageRepo, WsSender wsSender) {
        this.messageRepo = messageRepo;
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
    public Message create(@RequestBody Message message) {
        message.setCreatedAt(LocalDateTime.now());
        message.setUpdatedAt(LocalDateTime.now());
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

    /*
    @MessageMapping("/changeMessage")
    @SendTo("/topic/activity")
    public Message change(Message message) {
        message.setUpdatedAt(LocalDateTime.now()); return messageRepo.save(message);
    }*/

    /*
    @MessageMapping("/removeMessage")
    @SendTo("/topic/activity")
    public void remove(Message message) {
        messageRepo.delete(message);
    }*/

}
