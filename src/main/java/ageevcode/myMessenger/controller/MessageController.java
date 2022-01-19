package ageevcode.myMessenger.controller;

import ageevcode.myMessenger.domain.Message;
import ageevcode.myMessenger.domain.Post;
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

import java.util.List;

@RestController
@RequestMapping("/message")
public class MessageController {
    public static final int MESSAGES_PER_PAGE = 15;

    private final MessageService messageService;

    @Autowired
    public MessageController(MessageService messageService) {
        this.messageService = messageService;
    }

    @GetMapping("{id}")
    @JsonView(Views.FullMessage.class)
    public List<Message> list(@PathVariable("id") User interlocutor, @AuthenticationPrincipal User user) {
        return messageService.findForUser(user, interlocutor);
    }


    @PostMapping("{id}")
    @JsonView(Views.FullMessage.class)
    public Message create(@PathVariable("id") User interlocutor, @RequestBody Message message, @AuthenticationPrincipal User user) {
        return messageService.create(message, user, interlocutor);
    }

/*    @PutMapping("{id}")
    public Message update(@PathVariable("id") Message messageFromDB, @RequestBody Message message) {
        return messageService.update(messageFromDB, message);
    }

    @DeleteMapping("{id}")
    public void delete(@PathVariable("id") Message message) {
        messageService.delete(message);
    }*/
}
