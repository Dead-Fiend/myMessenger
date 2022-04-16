package ageevcode.myMessenger.repo;

import ageevcode.myMessenger.domain.User;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepo extends JpaRepository<User, Long>{
    User findByUsername(String username);
    User findByUsernameAndPassword(String username, String password);
    User findByIdAndActive(Long  id, Boolean active);

    @EntityGraph(attributePaths = {"subscriptions", "subscribers"})
    Optional<User> findById(String s);
}
