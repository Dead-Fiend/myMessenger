package ageevcode.myMessenger.controller;

import ageevcode.myMessenger.domain.Message;
import ageevcode.myMessenger.domain.User;
import ageevcode.myMessenger.domain.Views;
import ageevcode.myMessenger.repo.MessageRepo;
import ageevcode.myMessenger.service.MessageService;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/message")
public class MessageController {
    public static final int MESSAGES_PER_PAGE = 15;

    private final MessageService messageService;
    private final MessageRepo messageRepo;

    @Autowired
    public MessageController(MessageService messageService, MessageRepo messageRepo) {
        this.messageService = messageService;
        this.messageRepo = messageRepo;
    }

    @GetMapping
    @JsonView(Views.FullMessage.class)
    public List<Message> allMessages() {
        return messageRepo.findAll();
    }

    @GetMapping("{id}")
    @JsonView(Views.FullMessage.class)
    public List<Message> list(@PathVariable("id") User interlocutor, @AuthenticationPrincipal User user) {
        return messageService.findForUser(user, interlocutor);
    }

    @PostMapping
    @JsonView(Views.FullMessage.class)
    public Message create(@RequestBody Message message, @AuthenticationPrincipal User user) {
        return messageService.create(message, user, message.getInterlocutor());
    }

    @PutMapping("{id}")
    public Message update(@PathVariable("id") Message messageFromDB, @RequestBody Message message) {
        return messageService.update(messageFromDB, message);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable("id") Message message) {
        messageService.delete(message);
    }
}
