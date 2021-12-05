package ageevcode.myMessenger.repo;


import ageevcode.myMessenger.domain.Message;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MessageRepo extends JpaRepository<Message, Long> {
}
