package ageevcode.myMessenger.repo;

import ageevcode.myMessenger.domain.UserDetails;
import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepo extends JpaRepository<UserDetails, Long>{
    UserDetails findByUsername(String username);
    UserDetails findByUsernameAndPassword(String username, String password);

    @EntityGraph(attributePaths = {"subscriptions", "subscribers"})
    Optional<UserDetails> findById(String s);
}
