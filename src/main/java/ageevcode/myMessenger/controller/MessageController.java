package ageevcode.myMessenger.controller;

import ageevcode.myMessenger.domain.Message;
import ageevcode.myMessenger.domain.User;
import ageevcode.myMessenger.domain.Views;
import ageevcode.myMessenger.dto.MessagePageDto;
import ageevcode.myMessenger.service.MessageService;
import com.fasterxml.jackson.annotation.JsonView;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/message")
public class MessageController {
    public static final int MESSAGES_PER_PAGE = 3;

    private final MessageService messageService;

    @Autowired
    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @GetMapping
    @JsonView(Views.FullMessage.class)
    public MessagePageDto list(@PageableDefault(size = MESSAGES_PER_PAGE, sort = { "id" }, direction = Sort.Direction.DESC) Pageable pageable, @AuthenticationPrincipal User user) {
        return messageService.findForUser(pageable, user);
    }

    @GetMapping("{id}")
    @JsonView(Views.FullMessage.class)
    public Message getOne(@PathVariable("id") Message message) {
        return message;
    }

    @PostMapping
    @JsonView(Views.FullMessage.class)
    public Message create(@RequestBody Message message, @AuthenticationPrincipal User user) {
        return messageService.create(message, user);
    }

    @PutMapping("{id}")
    @JsonView(Views.FullMessage.class)
    public Message update(@PathVariable("id") Message messageFromDB, @RequestBody Message message) {
        return messageService.update(messageFromDB, message);
    }

    @DeleteMapping("{id}")
    @JsonView(Views.FullMessage.class)
    public void delete(@PathVariable("id") Message message) {
        messageService.delete(message);
    }
}
