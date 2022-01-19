package ageevcode.myMessenger.repo;

import ageevcode.myMessenger.domain.Message;
import ageevcode.myMessenger.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MessageRepo extends JpaRepository<Message, Long> {
    List<Message> findByAuthorAndInterlocutor(User author, User interlocutor);
/*    Page<Message> findByAuthorAndInterlocutor(User author, User interlocutor, Pageable pageable);*/

    Page<Message> findByAuthorIn(List<User> users, Pageable pageable);

    List<Message> findByAuthor(User author);

    List<Message> findByInterlocutor(User interlocutor);
}
