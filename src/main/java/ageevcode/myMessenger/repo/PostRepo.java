package ageevcode.myMessenger.repo;


import ageevcode.myMessenger.domain.Post;
import ageevcode.myMessenger.domain.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PostRepo extends JpaRepository<Post, Long> {
    @EntityGraph(attributePaths = {"comments"})
    Page<Post> findByAuthorIn(List<User> users, Pageable pageable);
}
