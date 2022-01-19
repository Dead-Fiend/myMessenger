package ageevcode.myMessenger.repo;


import ageevcode.myMessenger.domain.User;
import ageevcode.myMessenger.domain.UserChat;
import ageevcode.myMessenger.domain.UserChatId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserChatRepo extends JpaRepository<UserChat, UserChatId> {
    List<UserChat> findByAuthor(User author);

    List<UserChat> findByInterlocutor(User interlocutor);

    UserChat findByAuthorAndInterlocutor(User author, User interlocutor);
}
