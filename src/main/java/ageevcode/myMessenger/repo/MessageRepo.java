package ageevcode.myMessenger.repo;


import ageevcode.myMessenger.domain.Message;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface MessageRepo extends JpaRepository<Message, Long> {
    @EntityGraph(attributePaths = {"comments"})
    List<Message> findAll();
}
