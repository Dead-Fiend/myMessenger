package ageevcode.myMessenger.repo;

import ageevcode.myMessenger.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepo extends JpaRepository<Comment, Long> {
}
