package ageevcode.myMessenger.service;

import ageevcode.myMessenger.domain.Message;
import ageevcode.myMessenger.domain.User;
import ageevcode.myMessenger.domain.UserChat;
import ageevcode.myMessenger.dto.MessagePageDto;
import ageevcode.myMessenger.repo.MessageRepo;
import ageevcode.myMessenger.repo.UserChatRepo;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class MessageService {
    private final MessageRepo messageRepo;
    private final UserChatRepo userChatRepo;

    @Autowired
    public MessageService(MessageRepo messageRepo, UserChatRepo userChatRepo) {
        this.messageRepo = messageRepo;
        this.userChatRepo = userChatRepo;
    }

    public List<Message> findAll(Pageable pageable, User user) {
        List<Message> fromUser = messageRepo.findByAuthor(user);
        List<Message> toUser = messageRepo.findByInterlocutor(user);

        List<Message> messages = new ArrayList<>();
        messages.addAll(fromUser);
        messages.addAll(toUser);
/*        List<Message> messages = new ArrayList<>();
        List<List<Message>> list = new ArrayList<>();
        list.add(messages);*/

        return messages;
    }

    public List<Message> findForUser(User author, User interlocutor) {

/*        Page<Message> page = messageRepo.findByAuthorAndInterlocutor(author, interlocutor, pageable);

        return new MessagePageDto(
                page.getContent(),
                pageable.getPageNumber(),
                page.getTotalPages()
        );
        */
        List<Message> messages = new ArrayList<>();
        messages.addAll(messageRepo.findByAuthorAndInterlocutor(author, interlocutor));
        messages.addAll(messageRepo.findByAuthorAndInterlocutor(interlocutor, author));
        return messages;
    }

    public Message create(Message message, User user, User interlocutor) {
        message.setCreatedAt(LocalDateTime.now());
        message.setUpdatedAt(LocalDateTime.now());

        message.setAuthor(user);
        message.setInterlocutor(interlocutor);

        return messageRepo.save(message);
    }

}
